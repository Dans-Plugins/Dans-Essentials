package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author Daniel McCoy Stephenson
 */
public class TeleportListener implements Listener {
    private final EphemeralData ephemeralData;

    public TeleportListener(EphemeralData ephemeralData) {
        this.ephemeralData = ephemeralData;
    }

    @EventHandler()
    public void handle(PlayerTeleportEvent event) {
        ephemeralData.getLastLogins().put(event.getPlayer(), event.getFrom());
    }
}