package dev.chargedbyte.wim.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Runner responsible for initially scheduling the tasks
 */
@Component
public class ScheduleRunner implements CommandLineRunner {
    private final static Logger log = LoggerFactory.getLogger(ScheduleRunner.class);

    private final TaskScheduler taskScheduler;

    private final ProductUpdateTask productUpdateTask;

    public ScheduleRunner(TaskScheduler taskScheduler, ProductUpdateTask productUpdateTask) {
        this.taskScheduler = taskScheduler;
        this.productUpdateTask = productUpdateTask;
    }

    @Override
    public void run(String... args) {
        taskScheduler.schedule(productUpdateTask, new PeriodicTrigger(5, TimeUnit.MINUTES));
    }
}
