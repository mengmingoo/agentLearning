package com.mengming.test;

public class MAgentTest {
    private void func1() throws InterruptedException {
        System.out.println("This is func1...");
        Thread.sleep(500);
    }

    private void func2() throws InterruptedException {
        System.out.println("This is func2...");
        Thread.sleep(500);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.getProperty("user.dir"));
        MAgentTest mAgentTest = new MAgentTest();
        mAgentTest.func1();
        mAgentTest.func2();
    }
}
