// Permissions:
// 'me.fly'
// 'me.fly.others'

package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

/**
 * @author Daniel McCoy Stephenson
 */
public class FlyCommand extends AbstractPluginCommand {

    public FlyCommand() {
        super(new ArrayList<>(Arrays.asList("fly")), new ArrayList<>(Arrays.asList("de.fly")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) commandSender;

        player.setAllowFlight(!player.getAllowFlight());

        if (player.getAllowFlight()) {
            player.sendMessage(ChatColor.GREEN + "Flight enabled!");
        }
        else {
            player.sendMessage(ChatColor.GREEN + "Flight disabled!");
        }
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!commandSender.hasPermission("de.fly.others")) {
            commandSender.sendMessage("Sorry! You need the 'me.fly.others' permission to use this command.");
            return false;
        }
        Player target = getServer().getPlayer(args[0]);
        target.setAllowFlight(!target.getAllowFlight());
        if (target.getAllowFlight()) {
            target.sendMessage(ChatColor.GREEN + "Flight enabled!");
            commandSender.sendMessage(ChatColor.GREEN + "Flight enabled for " + target.getName());
        }
        else {
            target.sendMessage(ChatColor.GREEN + "Flight disabled!");
            commandSender.sendMessage(ChatColor.GREEN + "Flight disabled for " + target.getName());
        }
        return true;
    }
}