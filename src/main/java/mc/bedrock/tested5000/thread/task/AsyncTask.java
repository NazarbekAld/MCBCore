package mc.bedrock.tested5000.thread.task;

import lombok.Getter;

public abstract class AsyncTask {

    private @Getter boolean canceled = false;

    public abstract void run();

    public void cancel() {
        canceled = true;
    }

}
