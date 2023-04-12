package mc.bedrock.tested5000;


import lombok.Getter;
import mc.bedrock.tested5000.logger.Logger;
import mc.bedrock.tested5000.logger.LoggerWithThisOwnerExistException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * Hello world!
 *
 */
public class App 
{

    private static @Getter Logger logger;

    public static void main( String[] args )
    {

        try {
            logger = Logger.builder(App.class)
                    .prefix("ServerLoader")
                    .build();
        } catch (LoggerWithThisOwnerExistException e) {
            Logger.getInLogger().log("ERROR", e);
        }

        logger.log(Logger.INFO, "Made by NazarbekAld AkA Tested5000.");

        try {
            new Server();
        } catch (Exception e) {
            logger.log(Logger.ERROR, e);
        }

    }
}
