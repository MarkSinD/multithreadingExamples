package com.company;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLocking {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Acting acting = new Acting(lock);
        Waiting waiting = new Waiting((lock));
        acting.start();
        waiting.start();

    }

    static class Acting extends Thread {
        ReentrantLock locking;

        public Acting(ReentrantLock lock) {
            this.locking = lock;
        }

        @Override
        public void run() {
            while (true){
                locking.lock();
                System.out.println(getName() + ". Action...");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                locking.unlock();
            }
        }
    }

    static  class Waiting extends Thread{
        ReentrantLock lock;

        public Waiting(ReentrantLock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while(true){
                if(lock.tryLock()){
                    System.out.println("WORK!");
                    break;
                }
                else{
                    System.out.println(getName() + ". Waiting...");
                }
            }
        }
    }
}
