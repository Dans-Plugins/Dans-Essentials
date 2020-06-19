// Permissions:
// 'me.setmotd'

package essentialsystem.Commands;

import essentialsystem.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static essentialsystem.Main.createStringFromArgs;

public class SetMOTDCommand {

    Main main = null;

    public SetMOTDCommand(Main plugin) {
        main = plugin;
    }

    public void setMOTD(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /setmotd (message)");
            return;
        }
        String message = createStringFromArgs(0, args.length, args);
        if (!(sender instanceof Player)) {
            // console can just set MOTD anytime
            main.motd.setMessage(message);
            sender.sendMessage("MOTD set!");
        }
        else {
            // player needs permission
            Player player = (Player) sender;
            if (player.hasPermission("me.setmotd") || player.hasPermission("me.admin")) {
                main.motd.setMessage(message);
                player.sendMessage(ChatColor.GREEN + "MOTD set!");
            }
            else {
                sender.sendMessage("Sorry! You need the 'me.setmotd' permission to use this command.");
            }
        }
    }

}
