package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.DansEssentials;
import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author Daniel McCoy Stephenson
 */
public class JoinListener implements Listener {
    private final EphemeralData ephemeralData;
    private final DansEssentials dansEssentials;

    public JoinListener(EphemeralData ephemeralData, DansEssentials dansEssentials) {
        this.ephemeralData = ephemeralData;
        this.dansEssentials = dansEssentials;
    }

    @EventHandler()
    public void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.GREEN + "Welcome " + player.getName() + " to the server!");
            }
        }

        // hide vanished players from this player
        for (String vanishedPlayer : ephemeralData.getVanishedPlayers()) {
            event.getPlayer().hidePlayer(dansEssentials, Bukkit.getServer().getPlayer(vanishedPlayer));
        }

    }
}