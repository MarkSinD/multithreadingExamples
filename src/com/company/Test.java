package com.company;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Test {

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.start();
        myThread2.start();

        myThread1.join();
        myThread2.join();

        System.out.println(count);

    }

    static class MyThread1 extends Thread {

        @Override
        public void run() {
            for(long i = 0; i < 10000; i++){
                count++;
                //System.out.println("Thread 1");
            }
        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {
            for(long i = 0; i < 10000; i++){
                count++;
                //System.out.println("Thread 2");
            }
        }
    }

}
