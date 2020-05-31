package de.paul.util;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * author: JP
 * date: 31.05.20
 */
public class Log {
    private Logger logger;

    public static Log getLog(Class<? extends Object> clazz) {
        return new Log(clazz);
    }

    public static Log getLog(String clazz) {
        return new Log(clazz);
    }

    Log(Class<? extends Object> category) {
        this.logger = Logger.getLogger(category.getName());
    }

    private Log(String category) {
        this.logger = Logger.getLogger(category);
    }

    public void trace(Object o, Object... params) {
        this.log(Level.FINEST, o, params);
    }

    public boolean isDebugEnabled() {
        return this.logger.isLoggable(Level.FINE);
    }

    public boolean isInfoEnabled() {
        return this.logger.isLoggable(Level.INFO);
    }

    public void trace(Object o, Throwable t, Object... params) {
        this.log(Level.FINEST, o, t, params);
    }

    public void debug(Object o, Object... params) {
        this.log(Level.FINE, o, params);
    }

    public void debug(Object o, Throwable t, Object... params) {
        this.log(Level.FINE, o, t, params);
    }

    public void info(Object o, Object... params) {
        this.log(Level.INFO, o, params);
    }

    public void info(Object o, Throwable t, Object... params) {
        this.log(Level.INFO, o, t, params);
    }

    public void warn(Object o, Object... params) {
        this.log(Level.WARNING, o, params);
    }

    public void warn(Object o, Throwable t, Object... params) {
        this.log(Level.WARNING, o, t, params);
    }

    public void error(Object o, Object... params) {
        this.log(Level.SEVERE, o, params);
    }

    public void error(Object o, Throwable t, Object... params) {
        this.log(Level.SEVERE, o, t, params);
    }

    public void log(Level level, Object o, Object... params) {
        if (this.logger.isLoggable(level)) {
            this.log(level, o, (Throwable)null, params);
        }
    }

    public void log(Level level, Object o, Throwable t, Object... params) {
        if (this.logger.isLoggable(level)) {
            LogRecord record = new LogRecord(level, String.valueOf(o));
            record.setParameters(params);
            record.setThrown(t);
            this.logger.log(record);
        }
    }
}
