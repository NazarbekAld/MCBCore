package mc.bedrock.tested5000;

import lombok.Getter;
import mc.bedrock.tested5000.logger.Logger;
import mc.bedrock.tested5000.thread.AsyncThread;
import mc.bedrock.tested5000.thread.Ticking;
import mc.bedrock.tested5000.thread.task.AsyncTask;
import mc.bedrock.tested5000.thread.task.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {


    private ExecutorService service;
    private @Getter static Server instance;

    private @Getter Ticking tickingThread;
    private @Getter AsyncThread asyncThread;

    private Logger logger;


    public Server() throws Exception {
        if (instance != null) throw new Exception("Server object already exists!");
        instance = this;

        logger = Logger.builder(Server.class)
                .prefix("SERVER")
                .build();

        logger.log(Logger.INFO, "Server object created.");

        logger.log(Logger.INFO, "Running ticking thread.");
        tickingThread = new Ticking(Logger.builder(Ticking.class)
                .prefix("TICK")
                .build());
        tickingThread.start();

        logger.log(Logger.INFO, "Running async thread");
        asyncThread = new AsyncThread(Logger.builder(AsyncThread.class)
                .prefix("ASYNC")
                .build());



    }


}
