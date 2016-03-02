package webmon.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingUtils {

	private static final Logger log = Logger.getLogger(LoggingUtils.class.getName());
	
	public static void logMsg(String msg, Level level) {
		if(level == Level.SEVERE)
			log.severe(msg);
		else if(level == Level.WARNING)
			log.warning(msg);
		else
			log.info(msg);
	}
	
	public static void logMsg(String msg) {
		logMsg(msg, Level.INFO);
	}
}
