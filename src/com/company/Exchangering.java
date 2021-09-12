package com.company;

import java.util.concurrent.Exchanger;

public class Exchangering {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        Mike mike = new Mike(exchanger);
        Anket anket = new Anket(exchanger);
        PassportOffice passportOffice = new PassportOffice(exchanger);
    }

    static class Mike extends Thread {
        Exchanger<String> exchanger;

        public Mike(Exchanger<String> exchanger){
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            while(true) {
                try {
                    exchanger.exchange("Hi my name is Mike");
                    sleep(3000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Anket extends Thread {
        Exchanger<String> exchanger;

        public Anket(Exchanger<String> exchanger){
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            while(true) {
                try {
                    System.out.println(exchanger.exchange(null));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class PassportOffice extends Thread {
        Exchanger<String> exchanger;

        public PassportOffice(Exchanger<String> exchanger){
            this.exchanger = exchanger;
            start();
        }

        @Override
        public void run() {
            while(true) {
                /*try {
                    System.out.println(exchanger.exchange(null));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}
