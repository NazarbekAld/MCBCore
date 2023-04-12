package mc.bedrock.tested5000.thread;

import lombok.Getter;
import mc.bedrock.tested5000.logger.Logger;
import mc.bedrock.tested5000.thread.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Ticking extends Thread {

    private @Getter List<Task> tasks;
    private @Getter Logger logger;
    private long currentTick;

    private boolean alive = true;

    public Ticking(Logger logger) {
        tasks = new ArrayList<>();
        this.logger = logger;
        this.currentTick = 0;
    }

    public void disable() {
        logger.log(Logger.INFO, "Ticking disabled!");
        alive = false;
    }

    @Override
    public void run() {
        while (alive) {
            currentTick++;

            if (!(tasks.isEmpty())){
                tasks.forEach((task -> {
                    task.run(currentTick);
                }));
                tasks.removeIf(Task::isCanceled);
                tasks.removeIf((task -> task.getType() == Task.Type.ONE_TIME));
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                logger.log(Logger.ERROR, e, "Task interrupted while ticking.");
            }

        }
    }
}
