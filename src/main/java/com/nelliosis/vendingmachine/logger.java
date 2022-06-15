package com.nelliosis.vendingmachine;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
 * Parts of the logger class came from this StackOverFlow thread.
 * Credits go to them.
 * Visit: https://stackoverflow.com/questions/20737880/java-logging-through-multiple-classes
 */

public class logger {
    static Logger log;
    public FileHandler fh;
    Formatter plainText;
    static Writer writer = new StringWriter();

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

    public static String TraceToString(Exception e) {
        e.printStackTrace(new PrintWriter(writer));
        String s = writer.toString();
        return s;
    }
}
