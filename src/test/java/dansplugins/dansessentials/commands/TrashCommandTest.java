package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Daniel McCoy Stephenson
 */
public class TrashCommandTest {
    private Inventory trashInventory;
    private int inventoriesCreated;
    private TrashCommand trashCommand;

    @BeforeEach
    public void setUp() {
        trashInventory = mock(Inventory.class);
        inventoriesCreated = 0;
        trashCommand = new TrashCommand() {
            @Override
            Inventory createTrashInventory() {
                inventoriesCreated++;
                return trashInventory;
            }
        };
    }

    @Test
    public void testExecute_nonPlayerSender_returnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = trashCommand.execute(commandSender);

        assertFalse(result);
        assertEquals(0, inventoriesCreated);
        verify(commandSender).sendMessage("Only players can use this command.");
    }

    @Test
    public void testExecute_player_closesCurrentInventoryThenOpensTrash() {
        Player player = mock(Player.class);

        boolean result = trashCommand.execute(player);

        assertTrue(result);
        InOrder order = inOrder(player);
        order.verify(player).closeInventory();
        order.verify(player).openInventory(trashInventory);
        order.verify(player).sendMessage(ChatColor.AQUA + "Items you put in the trash are destroyed when you close it.");
    }

    @Test
    public void testExecute_eachUseCreatesANewInventory() {
        Player player = mock(Player.class);

        trashCommand.execute(player);
        trashCommand.execute(player);

        assertEquals(2, inventoriesCreated);
    }

    @Test
    public void testExecute_withArgs_ignoresArgsAndOpensTrash() {
        Player player = mock(Player.class);

        boolean result = trashCommand.execute(player, new String[]{"ignored"});

        assertTrue(result);
        verify(player).openInventory(trashInventory);
    }
}
