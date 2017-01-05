package com.cloud.log;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogShow {
	static final Log log = LogFactory.getLog(LogShow.class);
	
	public static void getLogDebug(String name) {
		log.debug(name);
    }
	
	public static void getLogError(String name) {
		log.error(name);
    }
}