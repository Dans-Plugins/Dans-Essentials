package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.DansEssentials;
import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import static org.bukkit.Bukkit.getServer;

public class VanishCommand extends AbstractCommand {

    public void toggleVisibility(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("de.vanish") || player.hasPermission("de.admin")) {
                if (!EphemeralData.getInstance().getVanishedPlayers().contains(player.getName())) {
                    hidePlayer(player);
                    player.sendMessage(ChatColor.GREEN + "You are now hidden!");
                }
                else {
                    showPlayer(player);
                    player.sendMessage(ChatColor.GREEN + "You are now visible!");
                }
            }
            else {
                sender.sendMessage("Sorry! You need the 'me.vanish' permission to use this command.");
            }
        }

    }

    public void hidePlayer(Player player) {
        for (Player target : getServer().getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(target.getName())) {
                target.hidePlayer(DansEssentials.getInstance(), player);
                EphemeralData.getInstance().getVanishedPlayers().add(player.getName());
            }
        }

    }

    public void showPlayer(Player player) {
        for (Player target : getServer().getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(target.getName())) {
                target.showPlayer(DansEssentials.getInstance(), player);
                EphemeralData.getInstance().getVanishedPlayers().remove(player.getName());
            }
        }
    }
}
