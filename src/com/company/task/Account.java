package com.company.task;

public interface Account<T> {
    long getId();
    void transfer(final T destination, int amount);
    int printAmountMoney();
}
