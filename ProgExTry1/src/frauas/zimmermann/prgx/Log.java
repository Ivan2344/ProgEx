package frauas.zimmermann.prgx;

	import java.util.logging.FileHandler;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	import java.util.logging.SimpleFormatter;

	/**
	 * This class provides a simple logging mechanism using Java's built-in Logger
	 * and FileHandlee classes.
	 * It allows logging informational, warning, and severe messages to a specified log file.
	 *
	 * <p>
	 * Usage:
	 * {@code Log.getLog("Informational message");}
	 * {@code Log.getLog("Warning message", exceptionObject);}
	 * {@code Log.getLog("Severe message", errorObject);}
	 * </p>
	 *
	 * The logger is initialized with FileHandler for logging to a file named "LogFile.log" in the current user directory.
	 * Log messages are formatted using SimpleFormatter.
	 *
	 * @author Pham Cao Thanh The
	 */
	public class Log {
		private static Logger logger = Logger.getLogger("MyLog");
		static {
			FileHandler fileHandler;
			try {
				fileHandler = new FileHandler(System.getProperty("user.dir")+System.getProperty("file.separator")+"LogFile.log",
						50000,1,true);
				logger.addHandler(fileHandler);
				fileHandler.setFormatter(new SimpleFormatter());
				logger.info("Logger Initialized");	
			} catch (Exception e) {
				logger.log(Level.WARNING,"Exception during logger initialization::",e);
			} 
		}
		/**
	     * Logs an informational message.
	     *
	     * @param msg 
	     * 		The informational message to be logged.
	     */
		public static void getLog(String msg) {
			logger.info(msg);
		}
		/**
	     * Logs a warning message with an associated exception.
	     *
	     * @param msg 
	     * 		The warning message to be logged.
	     * @param e   
	     * 		The exception associated with the warning.
	     */
		public static void getLog(String msg,Exception e) { 
			logger.log(Level.WARNING,msg,e);
		}
		/**
	     * Logs a severe message with an associated error.
	     *
	     * @param msg   
	     * 		The severe message to be logged.
	     * @param error 
	     * 		The error associated with the severe message.
	     */
		public static void getLog(String msg,Error error) { 
			logger.log(Level.SEVERE,msg,error);
		}
	}

