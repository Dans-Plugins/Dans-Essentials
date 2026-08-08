package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * @author Daniel McCoy Stephenson
 */
public class BackCommandTest {
    private EphemeralData ephemeralData;
    private BackCommand backCommand;

    @BeforeEach
    public void setUp() {
        ephemeralData = new EphemeralData();
        backCommand = new BackCommand(ephemeralData);
    }

    @Test
    public void testExecute_nonPlayerSender_returnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = backCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage("At this time, only players can use this command.");
    }

    @Test
    public void testExecute_noStoredLocation_sendsErrorAndDoesNotTeleport() {
        Player player = mock(Player.class);

        boolean result = backCommand.execute(player);

        assertFalse(result);
        verify(player, never()).teleport(any(Location.class));
    }

    @Test
    public void testExecute_storedLocation_teleportsPlayer() {
        Player player = mock(Player.class);
        Location previousLocation = mock(Location.class);
        ephemeralData.getLastLogins().put(player, previousLocation);

        boolean result = backCommand.execute(player);

        assertTrue(result);
        verify(player).teleport(previousLocation);
    }
}
