package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.DansEssentials;
import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class JoinListenerTest {
    private EphemeralData ephemeralData;
    private JoinListener joinListener;

    @BeforeEach
    public void setUp() {
        ephemeralData = new EphemeralData();
        joinListener = new JoinListener(ephemeralData, mock(DansEssentials.class));
    }

    @Test
    public void testHandle_storesJoinLocationAsLastLogin() {
        Player player = mock(Player.class);
        Location joinLocation = mock(Location.class);
        when(player.getLocation()).thenReturn(joinLocation);
        // hasPlayedBefore() must be true so handle() skips the Bukkit.getOnlinePlayers()
        // broadcast branch, which requires a live server instance unavailable in this test.
        when(player.hasPlayedBefore()).thenReturn(true);
        // getPlayer() is final on PlayerEvent, so a real event wraps the mocked player
        // instead of mocking the event itself.
        PlayerJoinEvent event = new PlayerJoinEvent(player, "");

        joinListener.handle(event);

        assertEquals(joinLocation, ephemeralData.getLastLogins().get(player));
    }
}
