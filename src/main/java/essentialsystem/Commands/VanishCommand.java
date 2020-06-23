package essentialsystem.Commands;

import essentialsystem.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand {

    Main main = null;

    public VanishCommand(Main plugin) {
        main = plugin;
    }

    public void toggleVisibility(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("me.vanish") || player.hasPermission("me.admin")) {
                if (!main.vanishedPlayers.contains(player.getName())) {
                    hidePlayer(player);
                }
                else {
                    showPlayer(player);
                }
            }
            else {
                sender.sendMessage("Sorry! You need the 'me.vanish' permission to use this command.");
            }
        }

    }

    public void hidePlayer(Player player) {
        player.hidePlayer(main, player);
        main.vanishedPlayers.add(player.getName());
    }

    public void showPlayer(Player player) {
        player.showPlayer(main, player);
        main.vanishedPlayers.remove(player.getName());
    }
}
