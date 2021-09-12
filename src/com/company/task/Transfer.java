package com.company.task;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Transfer<T extends Account> implements Runnable {
    private static final Logger log = Logger.getLogger(String.valueOf(BankAccount.class));

    public static volatile AtomicInteger countOfOperation = new AtomicInteger(0);
    private T sourceBankAccount;
    private T destBankAccount;
    private int transferAmount;
    private String threadName;


    Transfer(){

    }

    Transfer(String name){
        this.threadName = name;
    }

    Transfer(T source, T destination, int amount, String name) {
        this.sourceBankAccount = source;
        this.destBankAccount = destination;
        this.transferAmount = amount;
        this.threadName = name;
    }

    public void setSourceBankAccount(T t){
        this.sourceBankAccount = t;
    }

    public void setDestBankAccount(T t){
        this.destBankAccount = t;
    }

    public void setTransferAmount(int amount){
        this.transferAmount = amount;
    }


    public void run() {

        log.info(threadName + " начало транзакции. Адресант ID: " + sourceBankAccount.getId() + ".  Адресат ID: " + destBankAccount.getId());
        //need to check for types somehow

        sourceBankAccount.transfer(destBankAccount, transferAmount);


    }
}
