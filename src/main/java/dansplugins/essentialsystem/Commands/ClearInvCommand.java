package dansplugins.essentialsystem.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearInvCommand {

    public void clearInv(CommandSender sender, String[] args){
        if (sender instanceof Player) {
            Player clearer = (Player) sender;

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
