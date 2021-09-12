package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocking {
    public static void main(String[] args) {

        Resource resource = new Resource();
        MyThread myThread1 = new MyThread(resource);
        myThread1.start();
    }

    static class MyThread extends Thread{

        Resource resource;

        public MyThread(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            while(true) {
                resource.changeI();
            }
        }
    }
    static class Resource {
        int i = 0;
        int j = 0;
        Lock lock = new ReentrantLock();

        void changeI(){
            lock.lock();
            System.out.println("1 change I");
            int i = this.i;
            if(Thread.currentThread().getName().equals("one")){
                Thread.yield();
            }
            i++;
            this.i = i;
            changeJ();
        }

        void changeJ(){
            System.out.println("2 change J");

            int j = this.j;
            if(Thread.currentThread().getName().equals("one")){
                Thread.yield();
            }
            j++;
            this.j = j;
            lock.unlock();
        }
    }
}
