package dansplugins.dansessentials.eventhandlers;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportHandler implements Listener {
    @EventHandler()
    public void onTeleport(PlayerTeleportEvent event) {
        EphemeralData.getInstance().getLastLogins().put(event.getPlayer(), event.getFrom());
    }
}