package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class LabelCommandTest {
    private LabelCommand labelCommand;
    private Player player;
    private ItemStack item;

    @BeforeEach
    public void setUp() {
        labelCommand = new LabelCommand();
        player = mock(Player.class);
        PlayerInventory inventory = mock(PlayerInventory.class);
        item = mock(ItemStack.class);
        when(player.getInventory()).thenReturn(inventory);
        when(inventory.getItemInMainHand()).thenReturn(item);
    }

    @Test
    public void testExecute_emptyMainHand_sendsMessageAndReturnsFalse() {
        when(item.getType()).thenReturn(Material.AIR);

        boolean result = labelCommand.execute(player, new String[]{"\"Magic", "Sword\""});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "You must be holding an item in your main hand!");
        verify(item, never()).setItemMeta(any());
    }

    @Test
    public void testExecute_heldItem_renamesItem() {
        ItemMeta meta = mock(ItemMeta.class);
        when(item.getType()).thenReturn(Material.DIAMOND_SWORD);
        when(item.getItemMeta()).thenReturn(meta);

        boolean result = labelCommand.execute(player, new String[]{"\"Magic", "Sword\""});

        assertTrue(result);
        verify(meta).setDisplayName("Magic Sword");
        verify(item).setItemMeta(meta);
        verify(player).sendMessage(ChatColor.GREEN + "Item has been renamed!");
    }

    @Test
    public void testExecute_itemWithoutMeta_sendsMessageAndReturnsFalse() {
        when(item.getType()).thenReturn(Material.DIAMOND_SWORD);
        when(item.getItemMeta()).thenReturn(null);

        boolean result = labelCommand.execute(player, new String[]{"\"Magic", "Sword\""});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "That item can't be renamed.");
    }

    @Test
    public void testExecute_labelWithoutQuotes_leavesItemUnchanged() {
        when(item.getType()).thenReturn(Material.DIAMOND_SWORD);

        boolean result = labelCommand.execute(player, new String[]{"Magic"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "New label must be specified between double quotes.");
        verify(item, never()).setItemMeta(any());
    }
}
