package pl.kleczek.zielony.onlinegame;

public record Clan(int numberOfPlayers, int points) {
    public boolean fitsInto(int size) {
        return size >= numberOfPlayers;
    }
}
