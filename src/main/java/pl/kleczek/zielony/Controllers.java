package pl.kleczek.zielony;

import pl.kleczek.zielony.atmservice.VisitPlanner;
import pl.kleczek.zielony.atmservice.VisitPlannerController;
import pl.kleczek.zielony.onlinegame.OnlineGameCalculator;
import pl.kleczek.zielony.onlinegame.OnlineGameController;
import pl.kleczek.zielony.transactions.AccountStatisticController;
import pl.kleczek.zielony.transactions.AccountStatistics;

class Controllers {
    private Controllers() {
    }

    static AccountStatisticController accountStatistics() {
        return new AccountStatisticController(new AccountStatistics());
    }

    static VisitPlannerController visitPlanner() {
        return new VisitPlannerController(new VisitPlanner());
    }

    static OnlineGameController gameController() {
        return new OnlineGameController(new OnlineGameCalculator());
    }
}
