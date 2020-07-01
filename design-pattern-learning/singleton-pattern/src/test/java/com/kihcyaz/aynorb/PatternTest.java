package com.kihcyaz.aynorb;

import com.kihcyaz.aynorb.patterns.inamu.Student;

import java.util.concurrent.CountDownLatch;

public class PatternTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    Student student = Student.INSTANCE;
                    System.out.println(student.toString());
                }
            }.start();
        }
        new CountDownLatch(1).countDown();
    }
}
