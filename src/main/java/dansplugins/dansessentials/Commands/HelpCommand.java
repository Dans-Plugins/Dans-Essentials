// Permissions:
// 'me.help'

package dansplugins.dansessentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Stephenson
 */
public class HelpCommand extends AbstractCommand {

    private ArrayList<String> names = new ArrayList<>(Collections.singletonList("help"));
    private ArrayList<String> permissions = new ArrayList<>(Collections.singletonList("de.help"));

    @Override
    public ArrayList<String> getNames() {
        return names;
    }

    @Override
    public ArrayList<String> getPermissions() {
        return permissions;
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.AQUA + "/de help - See a list of helpful commands.");
        commandSender.sendMessage(ChatColor.AQUA + "/de fly - Toggle flight for you or another player.");
        commandSender.sendMessage(ChatColor.AQUA + "/de broadcast - Broadcast a message to everyone online.");
        commandSender.sendMessage(ChatColor.AQUA + "/de vanish - Hide yourself from view.");
        commandSender.sendMessage(ChatColor.AQUA + "/de mute - Hide yourself from view.");
        commandSender.sendMessage(ChatColor.AQUA + "/de unmute - Hide yourself from view.");
        commandSender.sendMessage(ChatColor.AQUA + "/de getpos - Get your coordinates.");
        commandSender.sendMessage(ChatColor.AQUA + "/de (number) - Set your gamemode.");
        commandSender.sendMessage(ChatColor.AQUA + "/de label (name) - Rename an item.");
        commandSender.sendMessage(ChatColor.AQUA + "/de invsee (player) - Interact with a player's inventory.");
        commandSender.sendMessage(ChatColor.AQUA + "/de clearinv (player) - Clear a player's inventory.");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}
