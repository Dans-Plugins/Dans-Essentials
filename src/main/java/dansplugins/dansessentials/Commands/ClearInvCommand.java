package dansplugins.dansessentials.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

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
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (commandSender instanceof Player) {
            Player clearer = (Player) commandSender;

            if (args.length != 1) {
                clearer.sendMessage("You must provide 1 argument, that is a valid online player name.");
                return;
            }

            Player player = Bukkit.getPlayer(args[0]);

            if (player == null){
                clearer.sendMessage("You must provide 1 argument, that is a valid online player name.");
                return;
            }

            if (clearer.hasPermission("de.clearinv") || clearer.hasPermission("de.admin")) {
                player.getInventory().clear();
            }
        }
    }
}
