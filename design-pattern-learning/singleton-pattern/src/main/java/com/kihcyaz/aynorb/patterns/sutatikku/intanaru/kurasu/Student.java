package com.kihcyaz.aynorb.patterns.sutatikku.intanaru.kurasu;

/**
 * 使用静态内部类来维护一个student实例，当Student类被加载时，静态内部类
 * StudentHolder没有被加载进内存，只有当调用getInstance方法时，StudentHolder类
 * 被加载，此时初始化student实例，并且JVM可以保证student只被实例化一次，
 * 这种方式不仅具有延迟加载的好处，而且JVM提供了线程安全的支持
 */
public class Student {

    private Student() {}

    /**
     * 此处用静态内部类来维护单例实例，JVM类加载的时候是互斥的，由此可以保证
     * 线程安全
     */
    private static class StudentHolder {
        private static Student student = new Student();
    }

    public static Student getInstance() {
        return StudentHolder.student;
    }

}
