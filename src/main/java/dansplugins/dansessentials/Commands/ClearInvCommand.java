package dansplugins.dansessentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Pasarus
 * @author Daniel Stephenson
 */
public class ClearInvCommand extends AbstractCommand {

    private ArrayList<String> names = new ArrayList<>(Collections.singletonList("clearinv"));
    private ArrayList<String> permissions = new ArrayList<>(Collections.singletonList("de.clearinv"));

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
