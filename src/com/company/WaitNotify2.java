package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


// Wait и Notify отлично подходят как методы остановки и запуска потоками
public class WaitNotify2 {
    static List<String> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        new Operator().start();
        new Machine().start();
        new MachineSmall().start();
    }

    static class Operator extends Thread {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            //while(true){
                synchronized (list){
                    list.add(scanner.nextLine());

                    System.out.println("Будет вызов notify");
                    list.notify();
                    System.out.println("Вызов notify был");

                }
                //System.out.println("operation end run");
                // Если не поставить что то за циклом, тогда второй поток
            //}
        }
    }

    static class Machine extends Thread {
        @Override
        public void run() {
            while(list.isEmpty()){
                synchronized (list){
                    try {
                        System.out.println("******");
                        System.out.println("Machine start");
                        list.wait();
                        System.out.println("Machine finish");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(3);

                    System.out.println(list.remove(0));
                }
            }
        }
    }

    static class MachineSmall extends Thread {
        @Override
        public void run() {
            while(list.isEmpty()){
                synchronized (list){
                    try {
                        System.out.println("******");
                        System.out.println("MachineSmall start");
                        list.wait();
                        System.out.println("MachineSmall finish");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(3);

                    System.out.println(list.remove(0));
                }
            }
        }
    }
}
