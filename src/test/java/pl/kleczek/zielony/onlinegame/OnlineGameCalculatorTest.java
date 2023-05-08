package pl.kleczek.zielony.onlinegame;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OnlineGameCalculatorTest {
    OnlineGameCalculator onlineGameCalculator = new OnlineGameCalculator();

    @Test
    void itShouldAcceptClansInDefinedOrder() {
        GameRequest gameRequest = buildRequest();

        List<List<Clan>> response = onlineGameCalculator.calculateOrder(gameRequest);

        assertEquals(expectedResponse(), response);
    }

    private static GameRequest buildRequest() {
        List<Clan> clans = List.of(
            new Clan(4, 50),
            new Clan(2, 70),
            new Clan(6, 60),
            new Clan(1, 15),
            new Clan(5, 40),
            new Clan(3, 45),
            new Clan(1, 12),
            new Clan(4, 40));
        return new GameRequest(6, clans);
    }

    private static List<List<Clan>> expectedResponse() {
        return List.of(
            List.of(new Clan(2, 70), new Clan(4, 50)),
            List.of(new Clan(6, 60)),
            List.of(new Clan(3, 45), new Clan(1, 15), new Clan(1, 12)),
            List.of(new Clan(4, 40)),
            List.of(new Clan(5, 40))
        );
    }
}
