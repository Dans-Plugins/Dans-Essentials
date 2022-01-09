package dansplugins.dansessentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.abs.AbstractPluginCommand;

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
        Player targetPlayer = Bukkit.getPlayer(args[0]);

        if (targetPlayer == null){
            commandSender.sendMessage("That player isn't online.");
            return false;
        }

        targetPlayer.getInventory().clear();
        return true;
    }
}