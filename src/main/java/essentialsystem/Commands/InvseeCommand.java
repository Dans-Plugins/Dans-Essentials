package essentialsystem.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand {

    public void invseePlayer(CommandSender sender, String[] args){
        if (sender instanceof Player) {
            Player spyer = (Player) sender;


            if (args.length != 1) {
                spyer.sendMessage("You must provide 1 argument, that is a valid online player name.");
                return;
            }

            Player player = Bukkit.getPlayer(args[0]);

            if (player == null){
                spyer.sendMessage("You must provide 1 argument, that is a valid online player name.");
                return;
            }

            if (spyer == player){
                spyer.sendMessage("Do not look at your own inventory silly! You can cause accidental dupes!");
            }

            if (spyer.hasPermission("me.invsee") || spyer.hasPermission("me.admin")) {
                spyer.closeInventory();
                spyer.openInventory(player.getInventory());
            }
        }
    }
}
