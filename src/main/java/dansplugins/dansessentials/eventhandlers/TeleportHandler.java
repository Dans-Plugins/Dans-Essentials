package dansplugins.dansessentials.eventhandlers;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * @author Daniel McCoy Stephenson
 */
public class TeleportHandler implements Listener {
    @EventHandler()
    public void handle(PlayerTeleportEvent event) {
        EphemeralData.getInstance().getLastLogins().put(event.getPlayer(), event.getFrom());
    }
}