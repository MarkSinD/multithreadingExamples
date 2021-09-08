package com.company;

public class Deadlock {

    public static void main(String[] args) {
        // Ресурсы
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        // Обмен ссылками
        resourceA.resourceB = resourceB;
        resourceB.resourceA = resourceA;

        // Помещение ссылок в потоки
        MyThread2 myThread2 = new MyThread2();
        myThread2.resourceB = resourceB;

        MyThread1 myThread1 = new MyThread1();
        myThread1.resourceA = resourceA;

        myThread2.start();
        myThread1.start();
    }

    static class MyThread1 extends Thread {

        ResourceA resourceA;

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++) {
                resourceA.get();
            }
        }
    }

    static class MyThread2 extends Thread {

        ResourceB resourceB;

        @Override
        public void run() {
            for(int i = 0; i < 1000; i++) {
                resourceB.get();
            }
        }
    }


    static class ResourceA {
        ResourceB resourceB;

        public synchronized void get(){
            resourceB.returnI();
        }

        public synchronized void returnI(){
            System.out.println(1);
        }
    }

    static class ResourceB {
        ResourceA resourceA;

        public synchronized void get(){
            resourceA.returnI();
        }

        public synchronized void returnI(){
            System.out.println(2);
        }
    }
}
