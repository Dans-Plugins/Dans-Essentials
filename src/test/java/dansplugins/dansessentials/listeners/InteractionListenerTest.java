package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class InteractionListenerTest {
    private Logger logger;
    private Player player;
    private InteractionListener interactionListener;

    @BeforeEach
    public void setUp() {
        logger = mock(Logger.class);
        player = mock(Player.class);
        World world = mock(World.class);
        when(world.getName()).thenReturn("world");
        when(player.getWorld()).thenReturn(world);
        when(player.hasPermission("de.usewarpsign")).thenReturn(true);
        interactionListener = new InteractionListener(logger);
    }

    private PlayerInteractEvent createWarpSignClick(String x, String y, String z) {
        Sign sign = mock(Sign.class);
        when(sign.getLine(0)).thenReturn("[Warp]");
        when(sign.getLine(1)).thenReturn(x);
        when(sign.getLine(2)).thenReturn(y);
        when(sign.getLine(3)).thenReturn(z);
        Block block = mock(Block.class);
        when(block.getState()).thenReturn(sign);
        // getPlayer() is final on PlayerEvent, so a real event wraps the mocked player
        // instead of mocking the event itself.
        return new PlayerInteractEvent(player, Action.RIGHT_CLICK_BLOCK, null, block, BlockFace.NORTH);
    }

    @Test
    public void testHandle_validCoordinates_teleportsPlayer() {
        interactionListener.handle(createWarpSignClick("100", "64", "-200"));

        verify(player).teleport(any(Location.class));
        verify(player).sendMessage(ChatColor.GREEN + "You have warped to 100 64 -200.");
    }

    @Test
    public void testHandle_malformedCoordinates_tellsPlayerAndWarns() {
        interactionListener.handle(createWarpSignClick("100", "~64", "-200"));

        verify(player, never()).teleport(any(Location.class));
        verify(player).sendMessage(ChatColor.RED + "This warp sign's coordinates are not valid.");
        verify(logger).warn(contains("~64"));
    }
}
