package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedList {
    public static void main(String[] args) {
        NameList nameList = new NameList();
        nameList.add("first");
        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println(nameList.removeFirst());
            }
        }

        MyThread myThread = new MyThread();
        myThread.setName("One");
        myThread.start();
        MyThread.yield();
        new MyThread().start();
    }

    static class NameList {
        private List list = Collections.synchronizedList(new ArrayList<>());

        public void add(String name){
            list.add(name);
        }

        public String removeFirst(){
            if(list.size() > 0){
                return (String) list.remove(0);
            }

            return null;
        }
    }
}
