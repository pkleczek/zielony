package pl.kleczek.zielony.atmservice;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class VisitPlanner {
    private static final Comparator<Task> COMPARATOR = Comparator.comparing(Task::region).thenComparing(t -> t.requestType().priority);

    List<Visit> plan(List<Task> tasks) {
        return tasks
            .stream()
            .sorted(COMPARATOR)
            .map(t -> new Visit(t.region(), t.atmId()))
            .collect(Collectors.toCollection(LinkedHashSet::new))
            .stream().toList();
    }
}
