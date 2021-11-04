package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.DansEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Stephenson
 */
public class LabelCommand extends AbstractCommand {

    private ArrayList<String> names = new ArrayList<>(Collections.singletonList("label"));
    private ArrayList<String> permissions = new ArrayList<>(Collections.singletonList("de.label"));

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
        commandSender.sendMessage(ChatColor.RED + "Usage: /de label \"newLabel\"");
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return false;
        }

        Player player = (Player) commandSender;

        ItemStack item = player.getInventory().getItemInMainHand();

        if (item == null) {
            player.sendMessage(ChatColor.RED + "You must be holding an item in your main hand!");
        }

        ArrayList<String> doubleQuoteArgs = DansEssentials.getInstance().getToolbox().getArgumentParser().getArgumentsInsideDoubleQuotes(args);

        if (doubleQuoteArgs.size() == 0) {
            player.sendMessage(ChatColor.RED + "New label must be specified between double quotes.");
            return false;
        }

        String newLabel = doubleQuoteArgs.get(0);

        if (item == null) {
            player.sendMessage(ChatColor.RED + "Something went wrong.");
            return false;
        }

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(newLabel);
        item.setItemMeta(meta);
        player.sendMessage(ChatColor.GREEN + "Item has been renamed!");
        return true;
    }
}
