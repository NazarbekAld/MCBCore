package mc.bedrock.tested5000.thread.task;

import lombok.Getter;

public abstract class Task {

    private @Getter boolean isCanceled = false;

    public abstract void run(long currentTick);

    /**
     *
     * <h2> Canceling task will cause removing task from ticking thread. </h2>
     *
     */
    public final void cancel() {
        isCanceled = true;
    }

}
