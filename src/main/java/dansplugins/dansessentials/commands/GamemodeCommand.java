package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class GamemodeCommand extends AbstractPluginCommand {

    public GamemodeCommand() {
        super(new ArrayList<>(Arrays.asList("gm")), new ArrayList<>(Arrays.asList("de.gm")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /gm [ 0 | 1 ]");
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
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatColor.GREEN + "You are now in survival mode.");
        }

        if (args[0].equalsIgnoreCase("1")) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.GREEN + "You are now in creative mode.");
        }
        return true;
    }
}