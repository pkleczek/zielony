package pl.kleczek.zielony.atmservice;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VisitPlannerPerformanceTest {
    VisitPlanner visitPlanner = new VisitPlanner();

    @Test
    void benchmarkLargeSetOfTasks() {
        List<Task> tasks = Fixtures.randomTasks(1000, 1000, 750);

        long start = System.nanoTime();
        List<Visit> plan = visitPlanner.plan(tasks);
        long duration = System.nanoTime() - start;

        assertEquals(1, plan.get(0).region());
        System.out.printf("It took %d millis%n", Duration.ofNanos(duration).toMillis());
    }
}
