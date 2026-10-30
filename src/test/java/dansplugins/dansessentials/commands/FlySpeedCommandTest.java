package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class FlySpeedCommandTest {

    @Test
    public void testExecute_noArguments_sendsUsageWithDePrefix() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = new FlySpeedCommand().execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "Usage: /de flyspeed (number)");
    }

    @Test
    public void testExecute_notANumber_describesTheAcceptedRange() {
        Player player = mock(Player.class);

        boolean result = new FlySpeedCommand().execute(player, new String[]{"fast"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "Fly speed must be a number greater than 0 and at most 10");
        verify(player, never()).setFlySpeed(anyFloat());
    }

    @Test
    public void testExecute_zero_describesTheAcceptedRange() {
        Player player = mock(Player.class);
        when(player.getAllowFlight()).thenReturn(true);

        boolean result = new FlySpeedCommand().execute(player, new String[]{"0"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "Fly speed must be greater than 0 and at most 10");
        verify(player, never()).setFlySpeed(anyFloat());
    }

    @Test
    public void testExecute_aboveTen_describesTheAcceptedRange() {
        Player player = mock(Player.class);
        when(player.getAllowFlight()).thenReturn(true);

        boolean result = new FlySpeedCommand().execute(player, new String[]{"11"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "Fly speed must be greater than 0 and at most 10");
        verify(player, never()).setFlySpeed(anyFloat());
    }

    @Test
    public void testExecute_decimalBelowOne_isAccepted() {
        Player player = mock(Player.class);
        when(player.getAllowFlight()).thenReturn(true);

        boolean result = new FlySpeedCommand().execute(player, new String[]{"0.5"});

        assertTrue(result);
        verify(player).setFlySpeed((float) (0.5 * 0.10));
        verify(player).sendMessage(ChatColor.GREEN + "Fly speed set to 0.5");
    }

    @Test
    public void testExecute_flightNotEnabled_doesNotChangeSpeed() {
        Player player = mock(Player.class);
        when(player.getAllowFlight()).thenReturn(false);

        boolean result = new FlySpeedCommand().execute(player, new String[]{"5"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "You don't have flight enabled!");
        verify(player, never()).setFlySpeed(anyFloat());
    }
}
