package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Daniel McCoy Stephenson
 */
public class BroadcastCommandTest {
    private List<Player> onlinePlayers;
    private BroadcastCommand broadcastCommand;

    @BeforeEach
    public void setUp() {
        onlinePlayers = new ArrayList<>();
        broadcastCommand = new BroadcastCommand() {
            @Override
            Collection<? extends Player> getOnlinePlayers() {
                return onlinePlayers;
            }
        };
    }

    @Test
    public void testExecute_noArgs_sendsUsageAndReturnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = broadcastCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "Usage: /de broadcast \"message\"");
    }

    @Test
    public void testExecute_unquotedMessage_sendsErrorAndBroadcastsNothing() {
        CommandSender commandSender = mock(CommandSender.class);
        Player player = mock(Player.class);
        onlinePlayers.add(player);

        boolean result = broadcastCommand.execute(commandSender, new String[]{"hello", "world"});

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "Message must be specified in between quotation marks.");
        verify(player, never()).sendMessage(anyString());
    }

    @Test
    public void testExecute_quotedMessage_sendsItToEveryOnlinePlayerInGreen() {
        CommandSender commandSender = mock(CommandSender.class);
        Player first = mock(Player.class);
        Player second = mock(Player.class);
        onlinePlayers.add(first);
        onlinePlayers.add(second);

        boolean result = broadcastCommand.execute(commandSender, new String[]{"\"Server", "restarting", "soon\""});

        assertTrue(result);
        verify(first).sendMessage(ChatColor.GREEN + "Server restarting soon");
        verify(second).sendMessage(ChatColor.GREEN + "Server restarting soon");
    }

    @Test
    public void testExecute_noOnlinePlayers_stillSucceeds() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = broadcastCommand.execute(commandSender, new String[]{"\"hello\""});

        assertTrue(result);
    }
}
