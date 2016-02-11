package webmon.utils;

import java.util.logging.Logger;

public class LoggingUtils {

	private static final Logger log = Logger.getLogger(LoggingUtils.class.getName());
	
	public static void logMsg(String msg) {
		log.info(msg);
	}
}
