package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.data.PersistentData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginsCommand {

    public void showLogins(CommandSender sender) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("me.logins") || player.hasPermission("me.default")) {

                int logins = PersistentData.getInstance().getActivityRecord(player.getName()).getLogins();

                player.sendMessage(ChatColor.AQUA + "You have logged in " + logins + " times.");

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'me.logins'");
            }

        }

    }

}
