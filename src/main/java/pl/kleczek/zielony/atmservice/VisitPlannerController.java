package pl.kleczek.zielony.atmservice;

import com.fasterxml.jackson.core.type.TypeReference;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.lang.reflect.Type;
import java.util.List;

public class VisitPlannerController implements Handler {
    private static final Type BODY_TYPE = new TypeReference<List<Task>>() {
    }.getType();

    private final VisitPlanner planner;

    public VisitPlannerController(VisitPlanner planner) {
        this.planner = planner;
    }

    @Override
    public void handle(Context context) {
        List<Task> tasks = context.bodyStreamAsClass(BODY_TYPE);
        List<Visit> plan = planner.plan(tasks);
        context.json(plan);
    }
}
