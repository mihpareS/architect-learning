package com.kihcyaz.aynorb.patterns.hungry;

/**
 * 如何保证在jvm中，一个类只有一个实例？
 * 1.构造方法私有
 * 2.使用私有静态成员变量初始化类本身对象
 * 3.对外提供静态公共方法获取本身对象
 */
public class Student {


    // 饿汉模式：在类加载时创建类本身对象
    private static Student student = new Student();

    private Student(){}

    public static Student getInstance() {
        return student;
    }

}
