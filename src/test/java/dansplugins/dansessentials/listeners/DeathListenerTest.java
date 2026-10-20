package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class DeathListenerTest {
    private EphemeralData ephemeralData;
    private Player player;
    private DeathListener deathListener;

    @BeforeEach
    public void setUp() {
        ephemeralData = new EphemeralData();
        player = mock(Player.class);
        deathListener = new DeathListener(ephemeralData);
    }

    private PlayerDeathEvent deathEvent(Player deadPlayer) {
        return new PlayerDeathEvent(deadPlayer, new ArrayList<>(), 0, "");
    }

    @Test
    public void testHandle_storesDeathLocationAsLastLogin() {
        Location deathLocation = mock(Location.class);
        when(player.getLocation()).thenReturn(deathLocation);

        deathListener.handle(deathEvent(player));

        assertEquals(deathLocation, ephemeralData.getLastLogins().get(player));
    }

    @Test
    public void testHandle_overwritesPreviousLastLogin() {
        Location beforeTeleport = mock(Location.class);
        ephemeralData.getLastLogins().put(player, beforeTeleport);
        Location deathLocation = mock(Location.class);
        when(player.getLocation()).thenReturn(deathLocation);

        deathListener.handle(deathEvent(player));

        assertEquals(deathLocation, ephemeralData.getLastLogins().get(player));
    }

    @Test
    public void testHandle_leavesOtherPlayersLastLoginUntouched() {
        Player otherPlayer = mock(Player.class);
        Location otherLocation = mock(Location.class);
        ephemeralData.getLastLogins().put(otherPlayer, otherLocation);
        when(player.getLocation()).thenReturn(mock(Location.class));

        deathListener.handle(deathEvent(player));

        assertEquals(otherLocation, ephemeralData.getLastLogins().get(otherPlayer));
        assertTrue(ephemeralData.getLastLogins().containsKey(player));
    }
}
