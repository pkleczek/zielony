package pl.kleczek.zielony.onlinegame;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class OnlineGameController implements Handler {
    private final OnlineGameCalculator onlineGameCalculator;

    public OnlineGameController(OnlineGameCalculator onlineGameCalculator) {
        this.onlineGameCalculator = onlineGameCalculator;
    }

    @Override
    public void handle(Context context) {
        GameRequest game = context.bodyStreamAsClass(GameRequest.class);
        List<List<Clan>> order = onlineGameCalculator.calculateOrder(game);
        context.json(order);
    }
}
