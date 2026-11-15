package dansplugins.dansessentials.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class PlayerDirectionTest {

    @Test
    public void testGetDirection_cardinalYaws() {
        assertEquals("South", PlayerDirection.getDirection(0f));
        assertEquals("West", PlayerDirection.getDirection(90f));
        assertEquals("North", PlayerDirection.getDirection(180f));
        assertEquals("East", PlayerDirection.getDirection(270f));
    }

    @Test
    public void testGetDirection_intercardinalYaws() {
        assertEquals("Southwest", PlayerDirection.getDirection(45f));
        assertEquals("Northwest", PlayerDirection.getDirection(135f));
        assertEquals("Northeast", PlayerDirection.getDirection(225f));
        assertEquals("Southeast", PlayerDirection.getDirection(315f));
    }

    @Test
    public void testGetDirection_negativeYawsMatchTheirPositiveEquivalents() {
        assertEquals("East", PlayerDirection.getDirection(-90f));
        assertEquals("North", PlayerDirection.getDirection(-180f));
        assertEquals("Southeast", PlayerDirection.getDirection(-45f));
    }

    @Test
    public void testGetDirection_yawsBeyondOneTurnWrapAround() {
        assertEquals("West", PlayerDirection.getDirection(450f));
        assertEquals("South", PlayerDirection.getDirection(720f));
        assertEquals("East", PlayerDirection.getDirection(-450f));
    }

    @Test
    public void testGetDirection_roundsToNearestDirection() {
        assertEquals("South", PlayerDirection.getDirection(22f));
        assertEquals("Southwest", PlayerDirection.getDirection(23f));
        assertEquals("South", PlayerDirection.getDirection(359f));
        assertEquals("South", PlayerDirection.getDirection(337.6f));
        assertEquals("Southeast", PlayerDirection.getDirection(337.4f));
    }

    @Test
    public void testGetDirection_halfwayRoundsClockwise() {
        assertEquals("Southwest", PlayerDirection.getDirection(22.5f));
        assertEquals("South", PlayerDirection.getDirection(337.5f));
    }

    @Test
    public void testGetDirection_player_usesLocationYaw() {
        Location location = mock(Location.class);
        when(location.getYaw()).thenReturn(180f);
        Player player = mock(Player.class);
        when(player.getLocation()).thenReturn(location);

        assertEquals("North", PlayerDirection.getDirection(player));
    }
}
