package webmon.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class LoggingUtils.
 */
public class LoggingUtils {

	private static final Logger log = Logger.getLogger(LoggingUtils.class.getName());
	
	/**
	 * Log meesage with given level.
	 *
	 * @param msg the message
	 * @param level the log level
	 */
	public static void logMsg(String msg, Level level) {
		if(level == Level.SEVERE)
			log.severe(msg);
		else if(level == Level.WARNING)
			log.warning(msg);
		else
			log.info(msg);
	}
	
	/**
	 * Log message with default info level.
	 *
	 * @param msg the message
	 */
	public static void logMsg(String msg) {
		logMsg(msg, Level.INFO);
	}
}
