package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class GetPosCommandTest {
    private GetPosCommand getPosCommand;

    @BeforeEach
    public void setUp() {
        getPosCommand = new GetPosCommand();
    }

    @Test
    public void testExecute_nonPlayerSender_returnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = getPosCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage("Only players can use this command.");
    }

    @Test
    public void testExecute_player_sendsBlockCoordinates() {
        Player player = playerAt(12, 64, -305);

        boolean result = getPosCommand.execute(player);

        assertTrue(result);
        verify(player).sendMessage(ChatColor.AQUA + "Your current coordinates are 12 64 -305");
    }

    @Test
    public void testExecute_withArgs_ignoresArgsAndSendsCoordinates() {
        Player player = playerAt(-7, 3, 41);

        boolean result = getPosCommand.execute(player, new String[]{"ignored"});

        assertTrue(result);
        verify(player).sendMessage(ChatColor.AQUA + "Your current coordinates are -7 3 41");
    }

    private Player playerAt(int x, int y, int z) {
        Location location = mock(Location.class);
        when(location.getBlockX()).thenReturn(x);
        when(location.getBlockY()).thenReturn(y);
        when(location.getBlockZ()).thenReturn(z);
        Player player = mock(Player.class);
        when(player.getLocation()).thenReturn(location);
        return player;
    }
}
