package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Daniel McCoy Stephenson
 */
public class QuitListenerTest {
    private EphemeralData ephemeralData;
    private QuitListener quitListener;

    @BeforeEach
    public void setUp() {
        ephemeralData = new EphemeralData();
        quitListener = new QuitListener(ephemeralData);
    }

    @Test
    public void testHandle_removesOnlyQuittingPlayersLastLogin() {
        Player quittingPlayer = mock(Player.class);
        Player otherPlayer = mock(Player.class);
        ephemeralData.getLastLogins().put(quittingPlayer, mock(Location.class));
        ephemeralData.getLastLogins().put(otherPlayer, mock(Location.class));
        // getPlayer() is final on PlayerEvent, so a real event wraps the mocked player
        // instead of mocking the event itself.
        PlayerQuitEvent event = new PlayerQuitEvent(quittingPlayer, "");

        quitListener.handle(event);

        assertFalse(ephemeralData.getLastLogins().containsKey(quittingPlayer));
        assertTrue(ephemeralData.getLastLogins().containsKey(otherPlayer));
    }
}
