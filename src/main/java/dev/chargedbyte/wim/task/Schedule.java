package dev.chargedbyte.wim.task;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Component
public class Schedule {
    private final TaskScheduler taskScheduler;

    private final ProductUpdateTask productUpdateTask;

    public Schedule(TaskScheduler taskScheduler, ProductUpdateTask productUpdateTask) {
        this.taskScheduler = taskScheduler;
        this.productUpdateTask = productUpdateTask;
    }

    @PostConstruct
    public void run() {
        taskScheduler.schedule(productUpdateTask, new PeriodicTrigger(5, TimeUnit.MINUTES));
    }
}
