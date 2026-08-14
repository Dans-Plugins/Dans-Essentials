package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Daniel McCoy Stephenson
 */
public class QuitListener implements Listener {
    private final EphemeralData ephemeralData;

    public QuitListener(EphemeralData ephemeralData) {
        this.ephemeralData = ephemeralData;
    }

    @EventHandler()
    public void handle(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        ephemeralData.getLastLogins().remove(player);
    }
}
