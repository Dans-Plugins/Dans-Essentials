// Permissions:
// 'me.broadcast'

package dansplugins.dansessentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

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

    public void broadcast(CommandSender sender, String message) {

    }

    private void sendAllPlayersMessage(String message) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.GREEN + "" + message);
        }
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (player.hasPermission("de.broadcast") || player.hasPermission("de.admin")) {
                sendAllPlayersMessage(message);
            }
            else {
                commandSender.sendMessage("Sorry! You need the 'de.broadcast' permission to use this command.");
            }
        }
        else {
            sendAllPlayersMessage(message);
        }
    }
}
