package com.nelliosis.vendingmachine;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
 * My entire logging class came from this StackOverFlow thread.
 * All credits go to them.
 * Visit: https://stackoverflow.com/questions/68471943/disable-showing-logs-on-console-programmatically
 */

public class logger {
    static Logger log;
    public FileHandler fh;
    Formatter plainText;

    public logger() throws IOException {
        // instance the logger and reset the manager
        log = Logger.getLogger(logger.class.getName());
        log.setUseParentHandlers(false);
        // instance the filehandler
        fh = new FileHandler("./log.txt", true);
        // instance formatter, set formatting, and handler
        plainText = new SimpleFormatter();
        fh.setFormatter(plainText);
        log.addHandler(fh);
    }

    private static Logger getLogger() {
        if (log == null) {
            try {
                new logger();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return log;
    }

    public static void log(Level level, String msg) {
        getLogger().log(level, msg);
    }
}
