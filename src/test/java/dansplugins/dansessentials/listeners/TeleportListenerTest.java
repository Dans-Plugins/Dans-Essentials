package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author Daniel McCoy Stephenson
 */
public class TeleportListenerTest {
    private EphemeralData ephemeralData;
    private Player player;
    private TeleportListener teleportListener;

    @BeforeEach
    public void setUp() {
        ephemeralData = new EphemeralData();
        player = mock(Player.class);
        teleportListener = new TeleportListener(ephemeralData);
    }

    @Test
    public void testHandle_storesOriginLocationAsLastLogin() {
        Location from = mock(Location.class);
        Location to = mock(Location.class);
        // getPlayer() is final on PlayerEvent, so a real event wraps the mocked player
        // instead of mocking the event itself.
        PlayerTeleportEvent event = new PlayerTeleportEvent(player, from, to);

        teleportListener.handle(event);

        assertEquals(from, ephemeralData.getLastLogins().get(player));
    }

    @Test
    public void testHandle_overwritesPreviousLastLogin() {
        Location joinLocation = mock(Location.class);
        ephemeralData.getLastLogins().put(player, joinLocation);
        Location from = mock(Location.class);

        teleportListener.handle(new PlayerTeleportEvent(player, from, mock(Location.class)));

        assertEquals(from, ephemeralData.getLastLogins().get(player));
    }
}
