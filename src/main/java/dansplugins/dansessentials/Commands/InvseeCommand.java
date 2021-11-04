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
public class InvseeCommand extends AbstractCommand {

    private ArrayList<String> names = new ArrayList<>(Collections.singletonList("invsee"));
    private ArrayList<String> permissions = new ArrayList<>(Collections.singletonList("de.invsee"));

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

        Player targetPlayer = Bukkit.getPlayer(args[0]);

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
}
