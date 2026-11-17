package dansplugins.dansessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Opens an empty inventory that is not saved anywhere. Items a player puts in it
 * are destroyed when the player closes it.
 * @author Daniel McCoy Stephenson
 */
public class TrashCommand extends AbstractPluginCommand {
    static final int TRASH_SIZE = 36;
    static final String TRASH_TITLE = "Trash";

    public TrashCommand() {
        super(new ArrayList<>(Arrays.asList("trash")), new ArrayList<>(Arrays.asList("de.trash")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }
        Player player = (Player) commandSender;

        player.closeInventory();
        player.openInventory(createTrashInventory());
        player.sendMessage(ChatColor.AQUA + "Items you put in the trash are destroyed when you close it.");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }

    /**
     * Creates the empty trash inventory. Extracted so that the surrounding logic can be
     * exercised without a live server.
     * @return A new inventory with no holder, which is discarded once it is closed.
     */
    Inventory createTrashInventory() {
        return Bukkit.createInventory(null, TRASH_SIZE, TRASH_TITLE);
    }
}
