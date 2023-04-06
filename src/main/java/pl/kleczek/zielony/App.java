package pl.kleczek.zielony;

import io.javalin.Javalin;
import pl.kleczek.zielony.transactions.AccountStatisticController;
import pl.kleczek.zielony.transactions.AccountStatistics;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.post("/transactions/report", accountStatistics());
    }

    private static AccountStatisticController accountStatistics() {
        return new AccountStatisticController(new AccountStatistics());
    }
}
