package dansplugins.essentialsystem.Commands;

import dansplugins.essentialsystem.MedievalEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LabelCommand {

    MedievalEssentials medievalEssentials = null;

    public LabelCommand(MedievalEssentials plugin) {
        medievalEssentials = plugin;
    }

    public void renameItemInMainHand(CommandSender sender, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("me.label") || player.hasPermission("me.admin")) {

                ItemStack item = player.getInventory().getItemInMainHand();

                if (item != null) {

                    if (args.length > 0) {

                        String newName = args[0];

                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(newName);
                        item.setItemMeta(meta);
                        player.sendMessage(ChatColor.GREEN + "Item has been renamed!");
                    }
                    else {
                        player.sendMessage(ChatColor.RED + "Usage: /label (name)");
                    }

                }
                else {
                    player.sendMessage(ChatColor.RED + "You must be holding an item in your main hand!");
                }

            }
            else {
                player.sendMessage(ChatColor.RED + "Sorry! In order to use this command, you need the following permission: 'me.label'");
            }

        }

    }

}
