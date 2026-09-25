package dansplugins.dansessentials.listeners;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.SignChangeEvent;
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
public class SignListenerTest {
    private Player player;
    private SignListener signListener;

    @BeforeEach
    public void setUp() {
        player = mock(Player.class);
        signListener = new SignListener();
    }

    private SignChangeEvent createWarpSignEvent() {
        // getPlayer() is final on SignChangeEvent, so a real event wraps the mocked player
        // instead of mocking the event itself.
        return new SignChangeEvent(mock(Block.class), player, new String[]{"[Warp]", "100", "64", "-200"});
    }

    @Test
    public void testHandle_withoutPermission_namesRealNodeAndCancels() {
        when(player.hasPermission("de.placeWarpSign")).thenReturn(false);
        SignChangeEvent event = createWarpSignEvent();

        signListener.handle(event);

        verify(player).sendMessage(ChatColor.RED + "Sorry! You need the 'de.placeWarpSign' permission to place a warp sign.");
        assertTrue(event.isCancelled());
    }

    @Test
    public void testHandle_withPermission_createsSign() {
        when(player.hasPermission("de.placeWarpSign")).thenReturn(true);
        SignChangeEvent event = createWarpSignEvent();

        signListener.handle(event);

        verify(player).sendMessage(ChatColor.GREEN + "Warp sign created!");
        assertFalse(event.isCancelled());
    }
}
