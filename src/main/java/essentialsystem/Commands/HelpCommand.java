package essentialsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class HelpCommand {

    public void sendHelpInfo(CommandSender sender) {
        sender.sendMessage(ChatColor.AQUA + "/help - See a list of helpful commands.");
        sender.sendMessage(ChatColor.AQUA + "/fly - Toggle flight for you or another player.");
        sender.sendMessage(ChatColor.AQUA + "/broadcast - Broadcast a message to everyone online.");
    }

}
