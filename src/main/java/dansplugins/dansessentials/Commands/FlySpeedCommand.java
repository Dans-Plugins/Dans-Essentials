package dansplugins.dansessentials.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Stephenson
 */
public class FlySpeedCommand extends AbstractCommand {

    private ArrayList<String> names = new ArrayList<>(Collections.singletonList("flyspeed"));
    private ArrayList<String> permissions = new ArrayList<>(Collections.singletonList("de.flyspeed"));

    @Override
    public ArrayList<String> getNames() {
        return names;
    }

    @Override
    public ArrayList<String> getPermissions() {
        return permissions;
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
