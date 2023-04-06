package pl.kleczek.zielony.transactions;

import com.fasterxml.jackson.core.type.TypeReference;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.lang.reflect.Type;
import java.util.List;

public class AccountStatisticController implements Handler {
    private static final Type BODY_TYPE = new TypeReference<List<Transaction>>() {
    }.getType();
    private final AccountStatistics accountStatistics;

    public AccountStatisticController(AccountStatistics accountStatistics) {
        this.accountStatistics = accountStatistics;
    }

    @Override
    public void handle(Context context) {
        List<Transaction> transactions = context.bodyStreamAsClass(BODY_TYPE);
        List<AccountSummary> accountSummaries = accountStatistics.calculateSummary(transactions);
        context.json(accountSummaries);
    }
}
