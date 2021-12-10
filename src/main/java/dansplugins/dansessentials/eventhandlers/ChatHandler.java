package dansplugins.dansessentials.eventhandlers;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatHandler implements Listener {
    @EventHandler()
    public void handle(AsyncPlayerChatEvent event) {
        if (EphemeralData.getInstance().getMutedPlayers().contains(event.getPlayer().getName())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You are currently muted.");
            event.setCancelled(true);
        }
    }
}