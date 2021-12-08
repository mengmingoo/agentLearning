package com.mengming.test;

import java.util.ArrayList;

public class JVMAgentTest {
    public static void main(String[] args) {
        boolean is = true;
        while(is){
            ArrayList<Object> list = new ArrayList<>();
            list.add("jvmAgent test");
        }
    }
}
