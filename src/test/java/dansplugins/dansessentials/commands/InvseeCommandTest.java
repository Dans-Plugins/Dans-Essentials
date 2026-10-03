package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class InvseeCommandTest {
    private Map<String, Player> onlinePlayers;
    private InvseeCommand invseeCommand;

    @BeforeEach
    public void setUp() {
        onlinePlayers = new HashMap<>();
        invseeCommand = new InvseeCommand() {
            @Override
            Player getTargetPlayer(String name) {
                return onlinePlayers.get(name);
            }
        };
    }

    @Test
    public void testExecute_noArgs_sendsUsageAndReturnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = invseeCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "Usage: /de invsee (playerName)");
    }

    @Test
    public void testExecute_nonPlayerSender_returnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);
        onlinePlayers.put("Steve", mock(Player.class));

        boolean result = invseeCommand.execute(commandSender, new String[]{"Steve"});

        assertFalse(result);
        verify(commandSender).sendMessage("Only players can use this command.");
    }

    @Test
    public void testExecute_offlineTarget_sendsErrorAndOpensNothing() {
        Player spy = mock(Player.class);

        boolean result = invseeCommand.execute(spy, new String[]{"Nobody"});

        assertFalse(result);
        verify(spy).sendMessage(ChatColor.RED + "That player isn't online.");
        verify(spy, never()).openInventory(any(Inventory.class));
    }

    @Test
    public void testExecute_targetIsSender_isRefused() {
        Player spy = mock(Player.class);
        onlinePlayers.put("Steve", spy);

        boolean result = invseeCommand.execute(spy, new String[]{"Steve"});

        assertFalse(result);
        verify(spy).sendMessage(ChatColor.RED + "Using this command on yourself is disabled.");
        verify(spy, never()).openInventory(any(Inventory.class));
    }

    @Test
    public void testExecute_onlineTarget_closesCurrentThenOpensTargetInventory() {
        Player spy = mock(Player.class);
        Player target = mock(Player.class);
        PlayerInventory targetInventory = mock(PlayerInventory.class);
        when(target.getInventory()).thenReturn(targetInventory);
        onlinePlayers.put("Steve", target);

        boolean result = invseeCommand.execute(spy, new String[]{"Steve"});

        assertTrue(result);
        InOrder order = inOrder(spy);
        order.verify(spy).closeInventory();
        order.verify(spy).openInventory(targetInventory);
    }
}
