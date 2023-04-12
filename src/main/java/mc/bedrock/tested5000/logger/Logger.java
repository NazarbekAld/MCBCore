package mc.bedrock.tested5000.logger;


import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Builder(builderMethodName = "rawBuilder")
public class Logger {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String INFO = ANSI_BLUE + "INFO";
    public static final String ERROR = ANSI_RED + "ERROR";
    public static final String DEBUG = ANSI_BLUE + "DEBUG";
    public static final String OK = ANSI_GREEN + "OK";

    private static List<Class<?>> owners = new ArrayList<>();
    private static @Getter Logger inLogger = new Logger(Logger.class, "LoggerBuilder");

    private <T> String formatInfo(T object) {
        return new StringBuffer()
                .append("[")
                .append(object.toString())
                .append(ANSI_RESET)
                .append("]")
                .toString();
    }

    private @Getter Class<?> owner;
    private @Getter String prefix;



    public void log(String severity, String message) {
        System.out.println(
                new StringBuffer()
                        .append(formatInfo(System.currentTimeMillis()))
                        .append(" ")
                        .append(formatInfo(severity))
                        .append(" ")
                        .append(prefix == null ? formatInfo(owner.getName()) : formatInfo(prefix))
                        .append(" \uD83E\uDC22 ")
                        .append(message)
        );
    }

    public void log (String severity, String... message) {
        for(String singe : message) {
            log(severity, singe);
        }
    }

    public void log(String severity, Throwable error) {
        StackTraceElement[] split = error.getStackTrace();

        for (StackTraceElement element : split) {
            log(
                    severity,

                    new StringBuffer()
                            .append("Thrown a exception from ")
                            .append(element.getClassName())
                            .append(" class at ")
                            .append(element.getLineNumber())
                            .append(" line of code.")
                            .toString(),

                    "More information: ",

                    new StringBuffer()
                            .append("\t")
                            .append("toString: ")
                            .append(element.toString())
                            .toString(),
                    new StringBuffer()
                            .append("\t")
                            .append("throwableClass: ")
                            .append(error.getClass().getName())
                            .toString(),
                    new StringBuffer()
                            .append("\t")
                            .append("Message: ")
                            .append(error.getMessage())
                            .toString(),
                    new StringBuffer()
                            .append("\t")
                            .append("File: ")
                            .append(element.getFileName())
                            .toString(),
                    new StringBuffer()
                            .append("\t")
                            .append("Thrown method: ")
                            .append(element.getMethodName())
                            .toString(),
                    new StringBuffer()
                            .append("\t")
                            .append("isNativeMethod: ")
                            .append(element.isNativeMethod())
                            .toString(),
                    new StringBuffer()
                            .append("\t")
                            .append("Module info: ")
                            .append("<VERSION: ")
                            .append(element.getModuleVersion())
                            .append(">")
                            .append("<NAME: ")
                            .append(element.getModuleName())
                            .append(">")
                            .toString()

            );
        }
    }

    public void log(String severity, Throwable error, String message) {
        log(severity, message);
        StackTraceElement[] split = error.getStackTrace();
        log(severity, error);
    }

    public void log(String severity, Throwable error, String... message) {
        log(severity, message);
        StackTraceElement[] split = error.getStackTrace();

        for (StackTraceElement element : split) {
            log(severity, element.toString());
        }
    }

    public static LoggerBuilder builder(Class<?> owner) throws LoggerWithThisOwnerExistException {
        if (owners.contains(owner)) throw new LoggerWithThisOwnerExistException();

        LoggerBuilder builder = rawBuilder().owner(owner);
        owners.add(owner);
        return builder;
    }


}
