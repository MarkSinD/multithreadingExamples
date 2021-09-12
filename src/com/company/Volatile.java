package com.company;

public class Volatile {

    static volatile int i;

    public static void main(String[] args) throws InterruptedException {
        new MyThreadWrite();
        new MyThreadRead();
    }

    static class MyThreadWrite extends Thread{

        @Override
        public void run() {
            while(i < 5){
                System.out.println("increment i to " + (++i));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public MyThreadWrite(){
            this.start();
        }
    }

    static class MyThreadRead extends Thread {

        @Override
        public void run() {
            int localVar = i;
            while(localVar < 5){
                if(localVar != i){
                    System.out.println("new value of i is " + i);
                    localVar = i;
                }
            }
        }

        public MyThreadRead(){
            this.start();
        }
    }
}



