package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Conditions {
    static int account = 0;
    public static void main(String[] args) {
        ReentrantLock locking = new ReentrantLock();
        Condition condition = locking.newCondition();
        AccountPlus accountPlus = new AccountPlus(condition, locking);
        AccountMinus accountMinus = new AccountMinus(condition, locking);
        accountMinus.start();
        accountPlus.start();
    }

    public static class AccountPlus extends Thread {
        private Condition condition;
        ReentrantLock lock;

        public AccountPlus(){
        }

        public AccountPlus(Condition condition, ReentrantLock lock){
            this.condition = condition;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                account += 10;
                System.out.println("Add");
                condition.signal();
                lock.unlock();
            }
        }
    }

    public static class AccountMinus extends Thread {
        private Condition condition;
        private ReentrantLock lock;

        public AccountMinus(){
        }

        public AccountMinus(Condition condition, ReentrantLock lock){
            this.condition = condition;
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true) {
                if (account < 10) {
                    try {
                        lock.lock();
                        System.out.println("Account = " + account + ". Before await");
                        condition.await();
                        System.out.println("Account = " + account + ". After await");
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                account -= 10;
            }
        }
    }
}
