package dansplugins.dansessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Pasarus
 * @author Daniel McCoy Stephenson
 */
public class ClearInvCommand extends AbstractPluginCommand {

    public ClearInvCommand() {
        super(new ArrayList<>(Arrays.asList("clearinv")), new ArrayList<>(Arrays.asList("de.clearinv")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /de clearinv (playerName)");
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        Player targetPlayer = getTargetPlayer(args[0]);

        if (targetPlayer == null){
            commandSender.sendMessage(ChatColor.RED + "That player isn't online.");
            return false;
        }

        targetPlayer.getInventory().clear();
        targetPlayer.sendMessage(ChatColor.RED + "Your inventory has been cleared.");
        commandSender.sendMessage(ChatColor.GREEN + "Cleared " + targetPlayer.getName() + "'s inventory.");
        return true;
    }

    /**
     * Resolves an online player by name. Extracted so that the surrounding logic can be exercised
     * without a live server.
     * @param name The name to look up.
     * @return The matching online player, or null if none is online.
     */
    Player getTargetPlayer(String name) {
        return Bukkit.getPlayer(name);
    }
}
