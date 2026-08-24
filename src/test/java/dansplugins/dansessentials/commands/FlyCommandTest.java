package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class FlyCommandTest {
    private Map<String, Player> onlinePlayers;
    private FlyCommand flyCommand;

    @BeforeEach
    public void setUp() {
        onlinePlayers = new HashMap<>();
        flyCommand = new FlyCommand() {
            @Override
            Player getTargetPlayer(String name) {
                return onlinePlayers.get(name);
            }
        };
    }

    @Test
    public void testExecute_nonPlayerSender_returnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = flyCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage("Only players can use this command.");
    }

    @Test
    public void testExecute_flightDisabled_enablesFlight() {
        Player player = mock(Player.class);
        when(player.getAllowFlight()).thenReturn(false, true);

        boolean result = flyCommand.execute(player);

        assertTrue(result);
        verify(player).setAllowFlight(true);
        verify(player).sendMessage(ChatColor.GREEN + "Flight enabled!");
    }

    @Test
    public void testExecute_flightEnabled_disablesFlight() {
        Player player = mock(Player.class);
        when(player.getAllowFlight()).thenReturn(true, false);

        boolean result = flyCommand.execute(player);

        assertTrue(result);
        verify(player).setAllowFlight(false);
        verify(player).sendMessage(ChatColor.GREEN + "Flight disabled!");
    }

    @Test
    public void testExecute_withoutOthersPermission_namesTheDeclaredPermissionNode() {
        CommandSender commandSender = mock(CommandSender.class);
        when(commandSender.hasPermission("de.fly.others")).thenReturn(false);

        boolean result = flyCommand.execute(commandSender, new String[]{"Steve"});

        assertFalse(result);
        verify(commandSender).sendMessage("Sorry! You need the 'de.fly.others' permission to use this command.");
    }

    @Test
    public void testExecute_offlineTarget_sendsErrorAndDoesNotToggleFlight() {
        CommandSender commandSender = mock(CommandSender.class);
        when(commandSender.hasPermission("de.fly.others")).thenReturn(true);

        boolean result = flyCommand.execute(commandSender, new String[]{"Nobody"});

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "That player isn't online.");
    }

    @Test
    public void testExecute_onlineTarget_togglesFlightForThatPlayer() {
        CommandSender commandSender = mock(CommandSender.class);
        when(commandSender.hasPermission("de.fly.others")).thenReturn(true);
        Player target = mock(Player.class);
        when(target.getName()).thenReturn("Steve");
        when(target.getAllowFlight()).thenReturn(false, true);
        onlinePlayers.put("Steve", target);

        boolean result = flyCommand.execute(commandSender, new String[]{"Steve"});

        assertTrue(result);
        verify(target).setAllowFlight(true);
        verify(target).sendMessage(ChatColor.GREEN + "Flight enabled!");
        verify(commandSender).sendMessage(ChatColor.GREEN + "Flight enabled for Steve");
    }

    @Test
    public void testExecute_senderWithoutPermission_neverTouchesTheTarget() {
        CommandSender commandSender = mock(CommandSender.class);
        when(commandSender.hasPermission("de.fly.others")).thenReturn(false);
        Player target = mock(Player.class);
        onlinePlayers.put("Steve", target);

        flyCommand.execute(commandSender, new String[]{"Steve"});

        verify(target, never()).setAllowFlight(anyBoolean());
    }
}
