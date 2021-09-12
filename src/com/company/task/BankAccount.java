package com.company.task;

import java.util.logging.Logger;

public class BankAccount implements Account<BankAccount> {
    private static final Logger log = Logger.getLogger(String.valueOf(BankAccount.class));

    private Long id;

    private int money;

    public BankAccount(long accountId, int accountAmount) {
        setId(accountId);
        setMoney(accountAmount);
    }

    public long getId() {
        return this.id;
    }

    public void setId(long accountId){
        this.id = accountId;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int amount){
        this.money = amount;
    }

    private void withdraw(int amount) {
        try {
            Thread.sleep(100L); //simulating DB access
        } catch(InterruptedException e) {}
        money -= amount;
    }

    // Положить на счет
    private void deposit(int amount) {
        try {
            Thread.sleep(100L); //simulating DB access
        } catch(InterruptedException e) {}
        money += amount;
    }

    private int compareAccountIds(BankAccount destination) {
        return this.id.compareTo(destination.id);
    }

    public int printAmountMoney() {
        log.info("Аккаунт ID: " + this.id + " имеет на счету " + this.money);
        return this.money;
    }

    public void transfer(final BankAccount destination, int amount) {
        BankAccount sourceAccount, destAccount;

        int result = compareAccountIds(destination);
        if (result > 0) {
            sourceAccount = destination;
            destAccount = this;
        } else {
            sourceAccount = this;
            destAccount = destination;
        }
        log.info("Будет выполнена блокировка аккаунта с ID: " + sourceAccount.getId());
        log.info("Будет выполнена блокировка аккаунта с ID: " + destAccount.getId());
        synchronized (sourceAccount) {
            log.info("Блокировка учетной записи аккаутна адресанта (отправитель)");
            if (this.money <= amount) {
                log.info("Недостаточно средств на исходном счете ID: " + this.id + ". Транзакция отклюняется");
                // throw new IllegalArgumentException("Недостаточно средств на исходном счете ID: " + this.id + ". Транзакция отклюняется");
            }
            else {
                this.withdraw(amount);
                synchronized (destAccount) {
                    log.info("Блокировка учетной записи аккаутна адресата (получатель)");
                    destination.deposit(amount);
                }
            }

        }

    }
}
