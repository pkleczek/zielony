package pl.kleczek.zielony.transactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AccountStatistics {
    public List<AccountSummary> calculateSummary(List<Transaction> transactions) {
        Map<String, AccountSummary> accounts = new TreeMap<>();

        for (Transaction transaction : transactions) {
            accounts.putIfAbsent(transaction.debitAccount(), new AccountSummary(transaction.debitAccount()));
            accounts.putIfAbsent(transaction.creditAccount(), new AccountSummary(transaction.creditAccount()));

            AccountSummary debitAccount = accounts.get(transaction.debitAccount());
            AccountSummary creditAccount = accounts.get(transaction.creditAccount());

            if (debitAccount == creditAccount) {
                handleSingleAccountTransaction(transaction, debitAccount);
            } else {
                handleTwoAccountsTransaction(transaction, debitAccount, creditAccount);
            }
        }

        return new ArrayList<>(accounts.values());
    }

    private static void handleSingleAccountTransaction(Transaction transaction, AccountSummary debitAccount) {
        debitAccount.handle(transaction);
    }

    private static void handleTwoAccountsTransaction(Transaction transaction, AccountSummary debitAccount, AccountSummary creditAccount) {
        debitAccount.handle(transaction);
        creditAccount.handle(transaction);
    }
}
