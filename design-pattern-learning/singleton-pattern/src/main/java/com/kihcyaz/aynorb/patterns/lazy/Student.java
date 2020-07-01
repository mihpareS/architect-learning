package com.kihcyaz.aynorb.patterns.lazy;

/**
 * 懒汉模式，在用到类时才创建对象
 * 懒汉模式步骤：
 * 1.构造私有
 * 2.定义私有成员变量，不初始化
 * 3.定义公共静态方法，获取本身对象，
 *   如果对象不存在，则创建，否则返
 *   回该对象
 *
 *
 * 线程安全问题判断依据：
 *     1.是否存在多线程
 *     2.是否有共享数据
 *     3.是否存在非原子性操作
 */
public class Student {

    private static Student student = null;

//    使用volatile，将对student的修改立即写入主存
//    private static volatile Student student = null;

    private Student() {
    }

    /**
     * 在多线程下会有线程安全问题
     * @return
     */
    public static Student getInstance() {
        if (student == null) {
            student = new Student();
        }
        return student;
    }

    /**
     * 在方法上加 synchronized，会导致整个方法被锁住，所有线程排队获取对象，
     * 效率低
     * @return
     */
    public static synchronized Student getInstanceLockingMethod() {
        if (student == null) {
            student = new Student();
        }
        return student;
    }


    /**
     * 将synchronized加在方法内部，新能有一定的提升，但是还有一些问题
     * 在Java指令中，创建对象和赋值操作是分开进行的，即 student = new Student();
     * 这个语句是分三步进行，但是JVM并不保证这三个操作的先后顺序。也就是说，有可能
     * JVM先为student分配内存空间，然后将student指向分配的内存地址，然后再初始化student，
     * 这样就会出现问题。以A、B两个线程为例：
     *    1.  A、B两个线程同时进入了第一个if判断
     *    2.  A首先进入synchronized块，执行 student = new Student();
     *    3.  JVM给student分配内存空间，将student指向分配的内存空间(此时JVM没有开始初始化这个student实例对象)，然后线程A离开synchronized块
     *    4.  线程B进入synchronized块，由于student并不为null，因此直接返回
     *    5.  线程B获得一个未初始化的student对象
     *
     * 解决办法是将定义的student变量用volatile修饰，valatile可以禁止JVM指令重排，保证
     * 在多线程环境下也能正常运行
     * @return
     */
    public static Student getInstanceLockingClass() {
        if (student == null) {
            synchronized (Student.class) {
                if (student == null) {
                    student = new Student();
                }
            }
        }
        return student;
    }
}
