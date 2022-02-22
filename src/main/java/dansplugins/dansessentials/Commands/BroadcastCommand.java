// Permissions:
// 'me.broadcast'

package dansplugins.dansessentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;
import preponderous.ponder.misc.ArgumentParser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class BroadcastCommand extends AbstractPluginCommand {

    public BroadcastCommand() {
        super(new ArrayList<>(Arrays.asList("broadcast")), new ArrayList<>(Arrays.asList("de.broadcast")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /de broadcast \"message\"");
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        ArgumentParser argumentParser = new ArgumentParser();
        ArrayList<String> doubleQuoteArgs = argumentParser.getArgumentsInsideDoubleQuotes(args);
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