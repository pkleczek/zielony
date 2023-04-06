package pl.kleczek.zielony.transactions;

import java.math.BigDecimal;

public class AccountSummary {
    private final String account;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance = BigDecimal.ZERO;

    public AccountSummary(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public int getDebitCount() {
        return debitCount;
    }

    public int getCreditCount() {
        return creditCount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void handle(Transaction transaction) {
        if (account.equals(transaction.creditAccount())) {
            creditCount += 1;
            balance = balance.add(transaction.amount());
        }

        if (account.equals(transaction.debitAccount())) {
            debitCount += 1;
            balance = balance.add(transaction.amount().negate());
        }
    }
}
