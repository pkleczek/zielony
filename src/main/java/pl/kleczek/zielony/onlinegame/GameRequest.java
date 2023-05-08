package pl.kleczek.zielony.onlinegame;

import java.util.List;

public record GameRequest(int groupCount, List<Clan> clans) {
}
