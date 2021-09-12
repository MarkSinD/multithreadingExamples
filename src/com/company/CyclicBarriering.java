package com.company;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarriering {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Run());
        new Sportsman(cyclicBarrier);
        new Sportsman(cyclicBarrier);
        new Sportsman(cyclicBarrier);


    }

    static class Run extends Thread {
        @Override
        public void run() {
            System.out.println("Run is begun");
        }
    }

    static class Sportsman extends Thread {
        CyclicBarrier cyclicBarrier;

        Sportsman(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
            start();
        }


        @Override
        public void run() {
            try {
                int random = (int) (Math.random() * 3000);
                sleep(random);
                System.out.println(getName() + " get ready");
                cyclicBarrier.await();
                System.out.println(getName() + " run!");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
