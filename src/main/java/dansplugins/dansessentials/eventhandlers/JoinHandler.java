package dansplugins.dansessentials.eventhandlers;

import dansplugins.dansessentials.DansEssentials;
import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinHandler implements Listener {
    @EventHandler()
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.GREEN + "Welcome " + player.getName() + " to the server!");
            }
        }

        // hide vanished players from this player
        for (String vanishedPlayer : EphemeralData.getInstance().getVanishedPlayers()) {
            event.getPlayer().hidePlayer(DansEssentials.getInstance(), Bukkit.getServer().getPlayer(vanishedPlayer));
        }

    }
}