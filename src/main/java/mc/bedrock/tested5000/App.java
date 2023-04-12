package mc.bedrock.tested5000;


import mc.bedrock.tested5000.logger.Logger;
import mc.bedrock.tested5000.logger.LoggerWithThisOwnerExistException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        Logger logger = null;
        try {
            logger = Logger.builder(App.class)
                    .prefix("ServerLoader")
                    .build();
        } catch (LoggerWithThisOwnerExistException e) {
            Logger.getInLogger().log("ERROR", e);
        }

        try {
            new Server();
        } catch (Exception e) {
            logger.log(Logger.ERROR, e);
        }

    }
}
