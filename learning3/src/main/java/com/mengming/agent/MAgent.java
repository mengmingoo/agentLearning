package com.mengming.agent;


import java.lang.instrument.Instrumentation;

public class MAgent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("This is agent.");
        System.out.println("args:" + agentArgs + "\n");
    }
}
