package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * @author Daniel McCoy Stephenson
 */
public class DeathListener implements Listener {
    private final EphemeralData ephemeralData;

    public DeathListener(EphemeralData ephemeralData) {
        this.ephemeralData = ephemeralData;
    }

    @EventHandler()
    public void handle(PlayerDeathEvent event) {
        Player player = event.getEntity();
        ephemeralData.getLastLogins().put(player, player.getLocation());
    }
}
