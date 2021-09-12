package com.company.task;

import java.util.*;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(String.valueOf(Main.class));

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int countThreads = 0, countTransaction = 0,  id = 0;
        List<BankAccount> bankAccounts = new ArrayList<>();
        Set<Integer> setId = new HashSet<>();
        System.out.print("Введите количество аккаунтов: ");
        countThreads = scanner.nextInt();

        System.out.print("Введите количество операций: ");
        countTransaction = scanner.nextInt();
        for (int i = 0; i < countThreads; i++) {

            // Все генерируемые индексы заносятся в Set для проверки на идентичность
            id = random(countThreads - 1, 0);
            while (setId.contains(id)) {
                id = random(countThreads - 1, 0);
            }
            setId.add(id);

            bankAccounts.add(new BankAccount(id, 1000));

        }
        log.info("Создание аккаунтов прошло успешно. Всего : " + setId.size() + " аккаунтов.");

        while (Transfer.countOfOperation.get() <= countTransaction) {
            int indexSource = random(countThreads - 1, 0);
            BankAccount sourceAccount = bankAccounts.get(indexSource);

            int countDestAccount = random(countThreads, 1);
            for (int i = 0; i < countDestAccount; i++) {
                int indexDest = random(countThreads - 1, 0);
                BankAccount destAccount = bankAccounts.get(indexDest);
                int amount = random(1000, 0);

                if (Transfer.countOfOperation.incrementAndGet() >= countTransaction) {
                    break;
                }
                Thread thread = new Thread(new Transfer<BankAccount>(sourceAccount, destAccount, amount, "Thread " + i));
                thread.start();
                thread.join();
                log.info("Транзакция №" + Transfer.countOfOperation.get());
            }
            int randomTime = random(2000, 1000);
            Thread.sleep(randomTime);
        }


        int sum = 0;
        for (int i = 0; i < bankAccounts.size(); i++) {
            sum += bankAccounts.get(i).printAmountMoney();
        }
        log.info("Сумма на всех счетах = " + sum + " единиц");
    }

    public static int random(int max, int min){
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }

}
