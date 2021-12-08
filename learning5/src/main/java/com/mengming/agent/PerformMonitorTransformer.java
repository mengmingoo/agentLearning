package com.mengming.agent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class PerformMonitorTransformer implements ClassFileTransformer {

    private static final String PACKAGE_PREFIX = "";

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            String currentClassName = className.replaceAll("/", ".");
            if (!currentClassName.startsWith(PACKAGE_PREFIX)) {
                return null;
            }
            System.out.println("now tranform:[" + currentClassName + "]");
            CtClass ctClass = ClassPool.getDefault().get(currentClassName);
            CtBehavior[] methods = ctClass.getDeclaredMethods();
            for (CtBehavior method : methods) {
                enhanceMethod(method);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void enhanceMethod(CtBehavior method) throws CannotCompileException {
        if (method.isEmpty()) {
            return;
        }
        final String methodName = method.getName();
        if (methodName.equalsIgnoreCase("main")) {
            return;
        }
        ExprEditor editor = new ExprEditor() {
            @Override
            public void edit(MethodCall methodCall) throws CannotCompileException {
                methodCall.replace(genSource(methodName));
            }
        };
        method.instrument(editor);
    }

    private String genSource(String methodName) {
        StringBuilder source = new StringBuilder();
        source.append("{")
                .append("long start = System.nanoTime();\n") // 前置增强: 打入时间戳
                .append("$_ = $proceed($$);\n") // 保留原有的代码处理逻辑
                .append("System.out.print(\"method:[" + methodName + "]\");").append("\n")
                .append("System.out.println(\" cost:[\" +(System.nanoTime() -start)+ \"ns]\");") // 后置增强
                .append("}");
        return source.toString();
    }
}
