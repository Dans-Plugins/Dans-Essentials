package dansplugins.dansessentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class GetPosCommand extends AbstractPluginCommand {

    public GetPosCommand() {
        super(new ArrayList<>(Arrays.asList("getpos")), new ArrayList<>(Arrays.asList("de.getpos")));
    }

    private int[] getPlayersPosition(Player player) {
        int[] coords = new int[3];
        coords[0] = player.getLocation().getBlockX();
        coords[1] = player.getLocation().getBlockY();
        coords[2] = player.getLocation().getBlockZ();
        return coords;
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }
        Player player = (Player) commandSender;

        int[] coordinates = getPlayersPosition(player);

        player.sendMessage(ChatColor.AQUA + "Your current coordinates are " + coordinates[0] + " " + coordinates[1] + " " + coordinates[2]);
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}