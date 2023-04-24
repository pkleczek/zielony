package pl.kleczek.zielony.atmservice;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.kleczek.zielony.atmservice.Fixtures.*;

class VisitPlannerTest {
    VisitPlanner visitPlanner = new VisitPlanner();

    @Test
    void itVisitsByRegion() {
        List<Task> tasks = List.of(standard(3, 1), standard(2, 1), standard(1, 1));

        List<Visit> plan = visitPlanner.plan(tasks);

        assertEquals(List.of(aVisit(1, 1), aVisit(2, 1), aVisit(3, 1)), plan);
    }

    @Test
    void itVisitsByPriority() {
        List<Task> tasks = List.of(standard(1, 1), signalLow(1, 2), priority(1, 3), failureRestart(1, 4));

        List<Visit> plan = visitPlanner.plan(tasks);

        assertEquals(List.of(aVisit(1, 4), aVisit(1, 3), aVisit(1, 2), aVisit(1, 1)), plan);
    }

    @Test
    void itAggregatesSameOccurrences() {
        List<Task> tasks = List.of(standard(1, 1), signalLow(1, 1), priority(1, 1), failureRestart(1, 1), priority(1, 2));

        List<Visit> plan = visitPlanner.plan(tasks);

        assertEquals(List.of(aVisit(1, 1), aVisit(1, 2)), plan);
    }

    @Test
    void itVisitsByRegionAndPriority_1() {
        List<Task> tasks = List.of(
            standard(4, 1),
            standard(1, 1),
            standard(2, 1),
            priority(3, 2),
            standard(3, 1),
            signalLow(2, 1),
            standard(5, 2),
            failureRestart(5, 1)
        );

        List<Visit> plan = visitPlanner.plan(tasks);

        List<Visit> expected = List.of(
            aVisit(1, 1),
            aVisit(2, 1),
            aVisit(3, 2),
            aVisit(3, 1),
            aVisit(4, 1),
            aVisit(5, 1),
            aVisit(5, 2)
        );
        assertEquals(expected, plan);
    }

    @Test
    void itVisitsByRegionAndPriority_2() {
        List<Task> tasks = List.of(
            standard(1, 2),
            standard(1, 1),
            priority(2, 3),
            standard(3, 4),
            standard(4, 5),
            priority(5, 2),
            standard(5, 1),
            signalLow(3, 2),
            signalLow(2, 1),
            failureRestart(3, 1)
        );

        List<Visit> plan = visitPlanner.plan(tasks);

        List<Visit> expected = List.of(
            aVisit(1, 2),
            aVisit(1, 1),
            aVisit(2, 3),
            aVisit(2, 1),
            aVisit(3, 1),
            aVisit(3, 2),
            aVisit(3, 4),
            aVisit(4, 5),
            aVisit(5, 2),
            aVisit(5, 1)
        );
        assertEquals(expected, plan);
    }
}
