package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

import static org.bukkit.ChatColor.GREEN;

/**
 * @author Daniel McCoy Stephenson
 */
public class GamemodeCommand extends AbstractPluginCommand {

    public GamemodeCommand() {
        super(new ArrayList<>(Arrays.asList("gm")), new ArrayList<>(Arrays.asList("de.gm")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /gm [ 0 | 1 | 2]");
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }
        Player player = (Player) commandSender;

        if (args[0].equalsIgnoreCase("0")) {
            if (!player.hasPermission("de.gm.0")) {
                player.sendMessage("You don't have permission to use this command to enter survival mode.");
                return false;
            }
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(GREEN + "You are now in survival mode.");
        }

        if (args[0].equalsIgnoreCase("1")) {
            if (!player.hasPermission("de.gm.1")) {
                player.sendMessage("You don't have permission to use this command to enter creative mode.");
                return false;
            }
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(GREEN + "You are now in creative mode.");
        }

        if (args[0].equalsIgnoreCase("2")) {
            if (!player.hasPermission("de.gm.2")) {
                player.sendMessage("You don't have permission to use this command to enter spectator mode.");
                return false;
            }
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(GREEN + "You are now in spectator mode.");
        }
        return true;
    }
}