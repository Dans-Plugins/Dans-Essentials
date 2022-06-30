package dansplugins.dansessentials.utils;

import dansplugins.dansessentials.DansEssentials;

import java.util.logging.Level;

/**
 * @author Daniel McCoy Stephenson
 */
public class Logger {
    private final DansEssentials dansEssentials;

    public Logger(DansEssentials dansEssentials) {
        this.dansEssentials = dansEssentials;
    }

    public void log(String message) {
        if (dansEssentials.isDebugEnabled()) {
            dansEssentials.getLogger().log(Level.INFO, "[Dan's Essentials] " + message);
        }
    }

}