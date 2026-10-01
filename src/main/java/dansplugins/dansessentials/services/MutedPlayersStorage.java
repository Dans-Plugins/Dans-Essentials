package dansplugins.dansessentials.services;

import dansplugins.dansessentials.data.EphemeralData;
import dansplugins.dansessentials.utils.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Keeps the muted-player list across restarts by writing it to a plain-text file, one player name per line.
 * @author Daniel McCoy Stephenson
 */
public class MutedPlayersStorage {
    public static final String FILE_NAME = "muted-players.txt";

    private final File file;
    private final EphemeralData ephemeralData;
    private final Logger logger;
    private boolean loadFailed = false;

    public MutedPlayersStorage(File dataFolder, EphemeralData ephemeralData, Logger logger) {
        this.file = new File(dataFolder, FILE_NAME);
        this.ephemeralData = ephemeralData;
        this.logger = logger;
    }

    /**
     * Replaces the in-memory muted-player list with the names in the file. A missing file leaves the list empty.
     */
    public void load() {
        if (!file.exists()) {
            return;
        }
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            loadFailed = true;
            logger.warn("Could not read " + file.getPath() + "; no players are muted. Cause: " + e.getMessage());
            return;
        }
        ArrayList<String> mutedPlayers = new ArrayList<>();
        for (String line : lines) {
            String name = line.trim();
            if (!name.isEmpty() && !mutedPlayers.contains(name)) {
                mutedPlayers.add(name);
            }
        }
        ephemeralData.setMutedPlayers(mutedPlayers);
    }

    /**
     * Writes the in-memory muted-player list to the file, replacing its previous contents. If the file could not be
     * read on load, it is left untouched so the mutes it holds are not overwritten.
     */
    public void save() {
        if (loadFailed) {
            logger.warn("Not saving the muted-player list because " + file.getPath()
                    + " could not be read at startup; fix or remove the file to keep mutes across restarts.");
            return;
        }
        try {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new IOException("could not create " + parent.getPath());
            }
            Files.write(file.toPath(), ephemeralData.getMutedPlayers(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.warn("Could not save the muted-player list to " + file.getPath()
                    + "; mutes will be lost on restart. Cause: " + e.getMessage());
        }
    }
}
