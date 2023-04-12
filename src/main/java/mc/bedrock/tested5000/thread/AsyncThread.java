package mc.bedrock.tested5000.thread;

import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;
import jdk.jfr.StackTrace;
import lombok.Getter;
import mc.bedrock.tested5000.logger.Logger;
import mc.bedrock.tested5000.thread.task.AsyncTask;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AsyncThread extends Thread {

    private @Getter List<AsyncTask> tasks;
    private @Getter Logger logger;

    private boolean alive = true;

    public AsyncThread(Logger logger) {
        tasks = new ArrayList<>();
        this.logger = logger;
    }

    public void disable() {
        logger.log(Logger.INFO, "Ticking disabled!");
        alive = false;
    }

    @Override
    public void run() {
        while (alive) {

            tasks.forEach((asyncTask -> {
                try {
                    asyncTask.run();
                }catch (Exception e) {
                    logger.log(Logger.ERROR, e, "Thrown exception while running the task.");
                }
            }));

            tasks.removeIf(AsyncTask::isCanceled);
            tasks.removeIf(asyncTask -> asyncTask.getType() == AsyncTask.Type.ONE_TIME);

        }
    }

}
