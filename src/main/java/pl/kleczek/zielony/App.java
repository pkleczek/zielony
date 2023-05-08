package pl.kleczek.zielony;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(8080);
        app.post("/transactions/report", Controllers.accountStatistics());
        app.post("/atms/calculateOrder", Controllers.visitPlanner());
        app.post("/onlinegame/calculate", Controllers.gameController());
    }
}
