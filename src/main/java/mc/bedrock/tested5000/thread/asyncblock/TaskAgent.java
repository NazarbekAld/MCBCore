package mc.bedrock.tested5000.thread.asyncblock;


import mc.bedrock.tested5000.Server;
import mc.bedrock.tested5000.logger.Logger;

public class TaskAgent {
    public static void blockAsync() {
        if (!(Thread.currentThread().getName().equals("tick"))) {
            throw new AsyncBlockedException();
        }
    }
}
