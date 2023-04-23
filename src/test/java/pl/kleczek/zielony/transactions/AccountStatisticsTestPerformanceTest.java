package pl.kleczek.zielony.transactions;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.kleczek.zielony.transactions.Fixtures.randomTransactions;

class AccountStatisticsTestPerformanceTest {
    static final int NUMBER_OF_ACCOUNTS = 100_000;
    static final int TRANSACTION_COUNT = 2_000_000;


    AccountStatistics accountStatistics = new AccountStatistics();

    @Test
    void benchmarkLargeSetOfTransactions() {
        List<Transaction> transactions = randomTransactions(NUMBER_OF_ACCOUNTS, TRANSACTION_COUNT);

        long start = System.nanoTime();
        List<AccountSummary> accountSummaries = accountStatistics.calculateSummary(transactions);
        long duration = System.nanoTime() - start;

        assertEquals(NUMBER_OF_ACCOUNTS, accountSummaries.size());
        System.out.printf("It took %d millis%n", Duration.ofNanos(duration).toMillis());
    }


}
