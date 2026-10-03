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
public class InvseeCommand extends AbstractPluginCommand {

    public InvseeCommand() {
        super(new ArrayList<>(Arrays.asList("invsee")), new ArrayList<>(Arrays.asList("de.invsee")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /de invsee (playerName)");
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }
        Player spy = (Player) commandSender;

        Player targetPlayer = getTargetPlayer(args[0]);

        if (targetPlayer == null){
            spy.sendMessage(ChatColor.RED + "That player isn't online.");
            return false;
        }

        if (spy == targetPlayer){
            spy.sendMessage(ChatColor.RED + "Using this command on yourself is disabled.");
            return false;
        }

        spy.closeInventory();
        spy.openInventory(targetPlayer.getInventory());
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
