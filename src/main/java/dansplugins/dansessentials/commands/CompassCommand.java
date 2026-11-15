package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.utils.PlayerDirection;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class CompassCommand extends AbstractPluginCommand {

    public CompassCommand() {
        super(new ArrayList<>(Arrays.asList("compass")), new ArrayList<>(Arrays.asList("de.compass")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }
        Player player = (Player) commandSender;

        player.sendMessage(ChatColor.AQUA + "You are facing " + PlayerDirection.getDirection(player) + ".");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}
