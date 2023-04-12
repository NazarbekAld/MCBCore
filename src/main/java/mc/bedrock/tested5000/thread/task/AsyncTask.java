package mc.bedrock.tested5000.thread.task;

import lombok.Getter;

public abstract class AsyncTask {

    private @Getter boolean canceled = false;
    private @Getter Type type = Type.ONE_TIME;

    public AsyncTask(Type type) {
        this.type = type;
    }
    public AsyncTask() {
    }

    public abstract void run();


    public void cancel() {
        canceled = true;
    }

    public enum Type {
        REPEATABLE,
        ONE_TIME
    }

}
