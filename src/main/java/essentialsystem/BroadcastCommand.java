package essentialsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BroadcastCommand {
    public void broadcast(String message) {
        sendAllPlayersMessage(message);
    }

    public static void sendAllPlayersMessage(String message) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "" + message);
        }
    }
}
