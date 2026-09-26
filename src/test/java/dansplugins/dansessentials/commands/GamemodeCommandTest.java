package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.bukkit.ChatColor.GREEN;
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
public class GamemodeCommandTest {
    private GamemodeCommand gamemodeCommand;
    private Player player;

    @BeforeEach
    public void setUp() {
        gamemodeCommand = new GamemodeCommand();
        player = mock(Player.class);
    }

    @Test
    public void testExecute_noArguments_sendsUsageWithDePrefix() {
        boolean result = gamemodeCommand.execute(player);

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "Usage: /de gm [ 0 | 1 | 2]");
    }

    @ParameterizedTest
    @CsvSource({"0, SURVIVAL, survival", "1, CREATIVE, creative", "2, SPECTATOR, spectator"})
    public void testExecute_recognisedModeWithPermission_setsGamemode(String mode, GameMode gameMode, String name) {
        when(player.hasPermission("de.gm." + mode)).thenReturn(true);

        boolean result = gamemodeCommand.execute(player, new String[]{mode});

        assertTrue(result);
        verify(player).setGameMode(gameMode);
        verify(player).sendMessage(GREEN + "You are now in " + name + " mode.");
    }

    @ParameterizedTest
    @CsvSource({"0, survival", "1, creative", "2, spectator"})
    public void testExecute_recognisedModeWithoutPermission_leavesGamemodeUnchanged(String mode, String name) {
        when(player.hasPermission("de.gm." + mode)).thenReturn(false);

        boolean result = gamemodeCommand.execute(player, new String[]{mode});

        assertFalse(result);
        verify(player, never()).setGameMode(any());
        verify(player).sendMessage("You don't have permission to use this command to enter " + name + " mode.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"3", "creative", "x"})
    public void testExecute_unrecognisedMode_sendsUsageAndReturnsFalse(String mode) {
        boolean result = gamemodeCommand.execute(player, new String[]{mode});

        assertFalse(result);
        verify(player, never()).setGameMode(any());
        verify(player).sendMessage(ChatColor.RED + "Usage: /de gm [ 0 | 1 | 2]");
    }
}
