// Permissions:
// 'me.broadcast'

package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.DansEssentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Stephenson
 */
public class BroadcastCommand extends AbstractCommand {

    private ArrayList<String> names = new ArrayList<>(Collections.singletonList("broadcast"));
    private ArrayList<String> permissions = new ArrayList<>(Collections.singletonList("de.broadcast"));

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
        commandSender.sendMessage(ChatColor.RED + "Usage: /de broadcast \"message\"");
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        ArrayList<String> doubleQuoteArgs = DansEssentials.getInstance().getToolbox().getArgumentParser().getArgumentsInsideDoubleQuotes(args);
        if (doubleQuoteArgs.size() == 0) {
            commandSender.sendMessage(ChatColor.RED + "Message must be specified between double quotes.");
            return false;
        }
        String message = doubleQuoteArgs.get(0);
        sendAllPlayersMessage(message);
        return true;
    }

    private void sendAllPlayersMessage(String message) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + "" + message);
        }
    }
}
