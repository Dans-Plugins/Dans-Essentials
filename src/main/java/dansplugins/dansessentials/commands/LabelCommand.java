package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;
import preponderous.ponder.misc.ArgumentParser;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class LabelCommand extends AbstractPluginCommand {

    public LabelCommand() {
        super(new ArrayList<>(Arrays.asList("label")), new ArrayList<>(Arrays.asList("de.label")));
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

        ArgumentParser argumentParser = new ArgumentParser();
        ArrayList<String> doubleQuoteArgs = argumentParser.getArgumentsInsideDoubleQuotes(args);

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