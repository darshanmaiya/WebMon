package webmon.utils;

import java.util.logging.Logger;

public class Logging {

	private static final Logger log = Logger.getLogger(Logging.class.getName());
	
	public static void logMsg(String msg) {
		log.info(msg);
	}
}
