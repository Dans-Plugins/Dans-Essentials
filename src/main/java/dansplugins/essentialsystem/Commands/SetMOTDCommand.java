// Permissions:
// 'me.setmotd'

package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.MedievalEssentials;
import dansplugins.essentialsystem.PersistentData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetMOTDCommand {

    MedievalEssentials medievalEssentials = null;

    public SetMOTDCommand(MedievalEssentials plugin) {
        medievalEssentials = plugin;
    }

    public void setMOTD(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Usage: /setmotd (message)");
            return;
        }
        String message = MedievalEssentials.createStringFromArgs(0, args.length, args);
        if (!(sender instanceof Player)) {
            // console can just set MOTD anytime
            PersistentData.getInstance().getMotd().setMessage(message);
            sender.sendMessage("MOTD set!");
        }
        else {
            // player needs permission
            Player player = (Player) sender;
            if (player.hasPermission("me.setmotd") || player.hasPermission("me.admin")) {
                PersistentData.getInstance().getMotd().setMessage(message);
                player.sendMessage(ChatColor.GREEN + "MOTD set!");
            }
            else {
                sender.sendMessage("Sorry! You need the 'me.setmotd' permission to use this command.");
            }
        }
    }

}
