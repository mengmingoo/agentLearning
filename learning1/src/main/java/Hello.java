import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class Hello {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        /*
            subclass 指定了新创建的类的父类
            method 指定了 Object 的 toString 方法
            intercept 拦截了 toString 方法并返回固定的 value
            最后 make 方法生产字节码，有类加载器加载到虚拟机中
        */
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World"))
                .make()
                .load(Hello.class.getClassLoader())
                .getLoaded();
        Object instance = dynamicType.newInstance();
        String toString = instance.toString();
        System.out.println(toString);
        System.out.println(instance.getClass().getCanonicalName());
    }
}
