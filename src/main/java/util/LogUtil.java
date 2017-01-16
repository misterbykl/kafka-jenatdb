package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * misterbaykal
 * <p>
 * 16/01/17 21:48
 */
public class LogUtil {
    private static final String EXCEPTION_LOGGER_NAME = "exceptionLogger";
    private static final String ROOT_LOGGER_NAME = "rootLogger";
    public static final String SLF4J_CONFIGURATION_FILE = "slf4j.configurationFile";

    /**
     * Instantiates a new Log util.
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:48
     */
    public LogUtil() {
    }

    /**
     * Gets logger.
     *
     * @return the logger
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:48
     */
    private static Logger getLogger(String argLoggerName) {
        return LoggerFactory.getLogger(argLoggerName);
    }

    /**
     * Gets root logger.
     *
     * @return the root logger
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:48
     */
    public static Logger getRootLogger() {
        return LogUtil.getLogger(LogUtil.ROOT_LOGGER_NAME);
    }

    /**
     * Gets exception logger.
     *
     * @return the exception logger
     * <p>
     * misterbaykal
     * <p>
     * 16/01/17 21:48
     */
    public static Logger getExceptionLogger() {
        return LogUtil.getLogger(LogUtil.EXCEPTION_LOGGER_NAME);
    }
}
