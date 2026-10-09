package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class ClearInvCommandTest {
    private Map<String, Player> onlinePlayers;
    private ClearInvCommand clearInvCommand;

    @BeforeEach
    public void setUp() {
        onlinePlayers = new HashMap<>();
        clearInvCommand = new ClearInvCommand() {
            @Override
            Player getTargetPlayer(String name) {
                return onlinePlayers.get(name);
            }
        };
    }

    @Test
    public void testExecute_noArgs_sendsUsageAndReturnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = clearInvCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "Usage: /de clearinv (playerName)");
    }

    @Test
    public void testExecute_offlineTarget_sendsErrorAndReturnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = clearInvCommand.execute(commandSender, new String[]{"Nobody"});

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "That player isn't online.");
    }

    @Test
    public void testExecute_onlineTarget_clearsThatPlayersInventoryOnly() {
        Player operator = mock(Player.class);
        PlayerInventory operatorInventory = mock(PlayerInventory.class);
        when(operator.getInventory()).thenReturn(operatorInventory);
        Player target = mock(Player.class);
        PlayerInventory targetInventory = mock(PlayerInventory.class);
        when(target.getInventory()).thenReturn(targetInventory);
        onlinePlayers.put("Steve", target);

        boolean result = clearInvCommand.execute(operator, new String[]{"Steve"});

        assertTrue(result);
        verify(targetInventory).clear();
        verify(operatorInventory, never()).clear();
    }

    @Test
    public void testExecute_consoleSender_canClearAnInventory() {
        // Unlike /de invsee, /de clearinv does not require the sender to be a player.
        CommandSender console = mock(CommandSender.class);
        Player target = mock(Player.class);
        PlayerInventory targetInventory = mock(PlayerInventory.class);
        when(target.getInventory()).thenReturn(targetInventory);
        onlinePlayers.put("Steve", target);

        boolean result = clearInvCommand.execute(console, new String[]{"Steve"});

        assertTrue(result);
        verify(targetInventory).clear();
    }

    @Test
    public void testExecute_onlineTarget_confirmsToSenderAndNotifiesTarget() {
        CommandSender commandSender = mock(CommandSender.class);
        Player target = mock(Player.class);
        when(target.getName()).thenReturn("Steve");
        when(target.getInventory()).thenReturn(mock(PlayerInventory.class));
        onlinePlayers.put("Steve", target);

        clearInvCommand.execute(commandSender, new String[]{"Steve"});

        verify(commandSender).sendMessage(ChatColor.GREEN + "Cleared Steve's inventory.");
        verify(target).sendMessage(ChatColor.RED + "Your inventory has been cleared.");
    }
}
