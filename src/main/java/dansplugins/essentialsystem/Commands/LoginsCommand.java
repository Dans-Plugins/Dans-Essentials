package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.MedievalEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginsCommand {

    MedievalEssentials medievalEssentials = null;

    public LoginsCommand(MedievalEssentials plugin) {
        medievalEssentials = plugin;
    }

    public void showLogins(CommandSender sender) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("me.logins") || player.hasPermission("me.default")) {

                int logins = medievalEssentials.getActivityRecord(player.getName()).getLogins();

                player.sendMessage(ChatColor.AQUA + "You have logged in " + logins + " times.");

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'me.logins'");
            }

        }

    }

}
