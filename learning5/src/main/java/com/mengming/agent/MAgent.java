package com.mengming.agent;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

public class MAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("This is agent...");
        ClassFileTransformer transformer = new PerformMonitorTransformer();
        inst.addTransformer(transformer);
    }
}
