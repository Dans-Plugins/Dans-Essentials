package dansplugins.dansessentials.data;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Daniel Stephenson
 */
public class EphemeralData {

    private static EphemeralData instance;

    private ArrayList<String> vanishedPlayers = new ArrayList<>();
    private ArrayList<String> mutedPlayers = new ArrayList<>();
    private HashMap<Player, Location> lastLogins = new HashMap<>();

    private EphemeralData() {

    }

    public static EphemeralData getInstance() {
        if (instance == null) {
            instance = new EphemeralData();
        }
        return instance;
    }

    public ArrayList<String> getVanishedPlayers() {
        return vanishedPlayers;
    }

    public void setVanishedPlayers(ArrayList<String> vanishedPlayers) {
        this.vanishedPlayers = vanishedPlayers;
    }

    public ArrayList<String> getMutedPlayers() {
        return mutedPlayers;
    }

    public void setMutedPlayers(ArrayList<String> mutedPlayers) {
        this.mutedPlayers = mutedPlayers;
    }

    public HashMap<Player, Location> getLastLogins() {
        return lastLogins;
    }

    public void setLastLogins(HashMap<Player, Location> lastLogins) {
        this.lastLogins = lastLogins;
    }

}
