package essentialsystem.Commands;

import essentialsystem.Main;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand {

    Main main = null;

    public GamemodeCommand(Main plugin) {
        main = plugin;
    }

    public void setGamemode(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("medievalessentials.gm") || player.hasPermission("medievalessentials.admin")) {

                if (args.length > 0) {

                    if (args[0].equalsIgnoreCase("0")) {

                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.GREEN + "You are now in survival mdoe!");

                    }

                    if (args[0].equalsIgnoreCase("1")) {

                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.GREEN + "You are now in creative mdoe!");

                    }

                }
                else {
                    player.sendMessage(ChatColor.RED + "Usage: /gm (number)");
                }

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'medievalessentials.gm");
            }

        }

    }

}
