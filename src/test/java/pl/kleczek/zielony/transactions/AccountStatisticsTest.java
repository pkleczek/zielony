package pl.kleczek.zielony.transactions;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pl.kleczek.zielony.transactions.Fixtures.aTransaction;

class AccountStatisticsTest {
    AccountStatistics accountStatistics = new AccountStatistics();

    @Test
    void itShouldCalculateTotals() {
        List<Transaction> transactions = List.of(
            aTransaction("32309111922661937852684864", "06105023389842834748547303", "10.90"),
            aTransaction("31074318698137062235845814", "66105036543749403346524547", "200.90"),
            aTransaction("66105036543749403346524547", "32309111922661937852684864", "50.10")
        );

        List<AccountSummary> accountSummaries = accountStatistics.calculateSummary(transactions);

        assertAccountMatches(accountSummaries.get(0), "06105023389842834748547303", 0, 1, "10.90");
        assertAccountMatches(accountSummaries.get(1), "31074318698137062235845814", 1, 0, "-200.90");
        assertAccountMatches(accountSummaries.get(2), "32309111922661937852684864", 1, 1, "39.20");
        assertAccountMatches(accountSummaries.get(3), "66105036543749403346524547", 1, 1, "150.80");
    }

    @Test
    void itShouldHandleSingleTransaction() {
        List<Transaction> transactions = List.of(aTransaction("1", "1", "200.00"));

        List<AccountSummary> accountSummaries = accountStatistics.calculateSummary(transactions);

        assertAccountMatches(accountSummaries.get(0), "1", 1, 1, "0.00");
    }

    @Test
    void itShouldHandleEmptyListOfTransactions() {
        List<Transaction> emptyList = List.of();

        List<AccountSummary> accountSummaries = accountStatistics.calculateSummary(emptyList);

        assertTrue(accountSummaries.isEmpty());
    }

    private static void assertAccountMatches(AccountSummary accountSummary, String expectedAccount, int expectedDebitCount, int expectedCreditCount, String expectedBalance) {
        assertEquals(expectedAccount, accountSummary.getAccount());
        assertEquals(expectedDebitCount, accountSummary.getDebitCount());
        assertEquals(expectedCreditCount, accountSummary.getCreditCount());
        assertEquals(new BigDecimal(expectedBalance), accountSummary.getBalance());
    }

}

