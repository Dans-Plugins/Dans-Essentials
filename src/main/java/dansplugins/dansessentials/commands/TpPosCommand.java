package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class TpPosCommand extends AbstractPluginCommand {
    private static final String USAGE = ChatColor.RED + "Usage: /de tppos <x> <y> <z> [world]";

    public TpPosCommand() {
        super(new ArrayList<>(Arrays.asList("tppos")), new ArrayList<>(Arrays.asList("de.tppos")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(USAGE);
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) commandSender;

        if (args.length < 3 || args.length > 4) {
            player.sendMessage(USAGE);
            return false;
        }

        double x;
        double y;
        double z;
        try {
            x = Double.parseDouble(args[0]);
            y = Double.parseDouble(args[1]);
            z = Double.parseDouble(args[2]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Coordinates must be numbers.");
            return false;
        }
        if (!isFinite(x) || !isFinite(y) || !isFinite(z)) {
            // parseDouble accepts "NaN" and "Infinity", which are not places a player can stand.
            player.sendMessage(ChatColor.RED + "Coordinates must be numbers.");
            return false;
        }

        World world = player.getWorld();
        if (args.length == 4) {
            world = player.getServer().getWorld(args[3]);
            if (world == null) {
                player.sendMessage(ChatColor.RED + "There is no world named " + args[3] + ".");
                return false;
            }
        }

        Location current = player.getLocation();
        if (!player.teleport(new Location(world, x, y, z, current.getYaw(), current.getPitch()))) {
            // Another plugin cancelled the teleport.
            player.sendMessage(ChatColor.RED + "The teleport was cancelled.");
            return false;
        }
        player.sendMessage(ChatColor.AQUA + "Teleported to " + args[0] + ", " + args[1] + ", " + args[2] + " in " + world.getName() + ".");
        return true;
    }

    private boolean isFinite(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }
}
