package dansplugins.essentialsystem.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetPosCommand {

    public void sendCoordinates(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("medievalessentials.getpos") || player.hasPermission("medievalessentials.default")) {
                int[] coords = getPlayersPosition(player);

                player.sendMessage(ChatColor.AQUA + "\nYour current coordinates are " + coords[0] + " " + coords[1] + " " + coords[2] + "\n");
            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'medievalessentials.getpos'");
            }

        }

    }

    private int[] getPlayersPosition(Player player) {
        int[] coords = new int[3];
        coords[0] = player.getLocation().getBlockX();
        coords[1] = player.getLocation().getBlockY();
        coords[2] = player.getLocation().getBlockZ();
        return coords;
    }

}
