// Permissions:
// 'me.help'

package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class HelpCommand extends AbstractPluginCommand {

    public HelpCommand() {
        super(new ArrayList<>(Arrays.asList("help")), new ArrayList<>(Arrays.asList("de.help")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.AQUA + "/de help - See a list of helpful commands.");
        commandSender.sendMessage(ChatColor.AQUA + "/de getpos - Get your coordinates.");
        commandSender.sendMessage(ChatColor.AQUA + "/de fly - Toggle flight for you or another player.");
        commandSender.sendMessage(ChatColor.AQUA + "/de flyspeed <number> - Set your fly speed.");
        commandSender.sendMessage(ChatColor.AQUA + "/de broadcast \"message\"- Broadcast a message to everyone online.");
        commandSender.sendMessage(ChatColor.AQUA + "/de mute <ign> - Mute a player until the next restart.");
        commandSender.sendMessage(ChatColor.AQUA + "/de unmute <ign> - Unmute a player.");
        commandSender.sendMessage(ChatColor.AQUA + "/de gm [ 0 | 1 | 2] - Set your gamemode.");
        commandSender.sendMessage(ChatColor.AQUA + "/de label <name> - Rename an item.");
        commandSender.sendMessage(ChatColor.AQUA + "/de invsee <player> - Interact with a player's inventory.");
        commandSender.sendMessage(ChatColor.AQUA + "/de clearinv <player> - Clear a player's inventory.");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}