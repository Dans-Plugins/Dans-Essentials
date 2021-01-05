package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.data.EphemeralData;
import dansplugins.essentialsystem.MedievalEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class MuteCommand {

    public void mutePlayer(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("me.mute") || player.hasPermission("me.admin")) {

                if (args.length > 0) {
                    if (getServer().getPlayer(args[0]) != null) {

                        if (!EphemeralData.getInstance().getMutedPlayers().contains(args[0])) {
                            if (!player.getName().equalsIgnoreCase(args[0])) {
                                EphemeralData.getInstance().getMutedPlayers().add(args[0]);
                                getServer().getPlayer(args[0]).sendMessage(ChatColor.RED + "You have been muted.");
                                player.sendMessage(ChatColor.GREEN + "Player has been muted.");
                            }
                            else {
                                player.sendMessage(ChatColor.RED + "You can't mute yourself!");
                            }

                        }
                        else {
                            player.sendMessage(ChatColor.RED + "That player is already muted!");
                        }

                    }
                    else {
                        player.sendMessage(ChatColor.RED + "That player isn't online!");
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Usage: /mute (player-name)");
                }

            }
            else {
                sender.sendMessage(ChatColor.RED + "Sorry! You need the 'me.mute' permission to use this command.");
            }
        }
    }

}
