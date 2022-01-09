package dansplugins.dansessentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class FlySpeedCommand extends AbstractPluginCommand {

    public FlySpeedCommand() {
        super(new ArrayList<>(Arrays.asList("flyspeed")), new ArrayList<>(Arrays.asList("de.flyspeed")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /flyspeed (number)");
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }

        Player player = (Player) commandSender;

        float speed = 0;
        try {
            speed = (float) (Double.parseDouble(args[0])* 0.10);
        } catch(Exception e) {
            player.sendMessage(ChatColor.RED + "Must be a number between 1-10");
            return false;
        }

        if (speed > 0 && speed <= 1) {
            if (player.getAllowFlight()) {
                player.setFlySpeed(speed);
                player.sendMessage(ChatColor.GREEN + "Fly speed set to " + args[0]);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "You don't have flight enabled!");
                return false;
            }
        }
        else {
            player.sendMessage(ChatColor.RED + "Fly speed can only be between 1-10");
            return false;
        }
    }
}