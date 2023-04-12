package mc.bedrock.tested5000.thread.asyncblock;

import mc.bedrock.tested5000.Server;
import mc.bedrock.tested5000.logger.Logger;

public class AsyncBlockedException extends RuntimeException {

    @Override
    public void printStackTrace() {

        Server.getInstance().getLogger().log(Logger.ERROR, this, "Blocked async call of method.");

    }
}
