package pl.kleczek.zielony.onlinegame;

import java.util.*;
import java.util.stream.Collectors;

public class OnlineGameCalculator {
    private static final Comparator<Clan> COMPARATOR = Comparator.comparing(Clan::points).reversed()
        .thenComparing(Clan::numberOfPlayers);

    public List<List<Clan>> calculateOrder(GameRequest request) {
        int groupCount = request.groupCount();

        List<Clan> clansByPower = sortClansByPower(request);

        List<List<Clan>> entries = new ArrayList<>();

        int availableSpace = groupCount;
        List<Clan> slot = new ArrayList<>();

        while (!clansByPower.isEmpty()) {
            Iterator<Clan> iterator = clansByPower.iterator();
            while (iterator.hasNext()) {
                Clan clan = iterator.next();

                if (clan.fitsInto(availableSpace)) {
                    slot.add(clan);
                    iterator.remove();
                    availableSpace -= clan.numberOfPlayers();
                }

                if (availableSpace == 0)
                    break;

            }
            entries.add(slot);
            slot = new ArrayList<>();
            availableSpace = groupCount;
        }


        return entries;
    }

    private static LinkedList<Clan> sortClansByPower(GameRequest request) {
        return request.clans()
            .stream()
            .sorted(COMPARATOR)
            .collect(Collectors.toCollection(LinkedList::new));
    }
}
