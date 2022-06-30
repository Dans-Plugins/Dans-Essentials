package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author Daniel McCoy Stephenson
 */
public class ChatListener implements Listener {
    private final EphemeralData ephemeralData;

    public ChatListener(EphemeralData ephemeralData) {
        this.ephemeralData = ephemeralData;
    }

    @EventHandler()
    public void handle(AsyncPlayerChatEvent event) {
        if (ephemeralData.getMutedPlayers().contains(event.getPlayer().getName())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You are currently muted.");
            event.setCancelled(true);
        }
    }
}