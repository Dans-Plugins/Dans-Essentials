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
public class CompassCommandTest {
    private CompassCommand compassCommand;

    @BeforeEach
    public void setUp() {
        compassCommand = new CompassCommand();
    }

    @Test
    public void testExecute_nonPlayerSender_returnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = compassCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage("Only players can use this command.");
    }

    @Test
    public void testExecute_player_sendsFacingDirection() {
        Player player = playerFacing(270f);

        boolean result = compassCommand.execute(player);

        assertTrue(result);
        verify(player).sendMessage(ChatColor.AQUA + "You are facing East.");
    }

    @Test
    public void testExecute_withArgs_ignoresArgsAndSendsDirection() {
        Player player = playerFacing(135f);

        boolean result = compassCommand.execute(player, new String[]{"ignored"});

        assertTrue(result);
        verify(player).sendMessage(ChatColor.AQUA + "You are facing Northwest.");
    }

    private Player playerFacing(float yaw) {
        Location location = mock(Location.class);
        when(location.getYaw()).thenReturn(yaw);
        Player player = mock(Player.class);
        when(player.getLocation()).thenReturn(location);
        return player;
    }
}
