package dansplugins.dansessentials.utils;

import dansplugins.dansessentials.DansEssentials;

import java.util.logging.Level;

/**
 * @author Daniel McCoy Stephenson
 */
public class Logger {

    private static Logger instance;

    private Logger() {

    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        if (DansEssentials.getInstance().isDebugEnabled()) {
            DansEssentials.getInstance().getLogger().log(Level.INFO, "[Dan's Essentials] " + message);
        }
    }

}