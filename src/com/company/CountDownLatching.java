package com.company;

import java.util.concurrent.CountDownLatch;

public class CountDownLatching {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work(countDownLatch);
        new Work1(countDownLatch);
        countDownLatch.await();
        System.out.println("Done!");
    }

    static class Work extends Thread {
        CountDownLatch countDownLatch;

        public Work(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
            start();
        }

        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("done work");
            countDownLatch.countDown();
        }
    }
    static class Work1 extends Thread {
        CountDownLatch countDownLatch;

        public Work1(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
            start();
        }

        @Override
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("done work");
            countDownLatch.countDown();
        }
    }
}
