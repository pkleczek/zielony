package pl.kleczek.zielony.transactions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Fixtures {
    private final static SecureRandom random = new SecureRandom();

    public static Transaction aTransaction(String debit, String credit, String amount) {
        return new Transaction(debit, credit, new BigDecimal(amount));
    }

    public static List<Transaction> randomTransactions(int numberOfAccounts, int transactionCount) {
        List<Transaction> transactions = new ArrayList<>(transactionCount);

        for (int i = 0; i < transactionCount; ++i) {
            String debit = String.valueOf(random.nextInt(1, numberOfAccounts + 1));
            String credit = String.valueOf(random.nextInt(1, numberOfAccounts));
            double doubleAmount = random.nextDouble(0.01, 10000);
            BigDecimal amount = BigDecimal.valueOf(doubleAmount).setScale(2, RoundingMode.HALF_UP);
            transactions.add(new Transaction(debit, credit, amount));
        }

        return transactions;
    }
}
