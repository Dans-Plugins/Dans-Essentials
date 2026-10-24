package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class TpPosCommandTest {
    private static final String USAGE = ChatColor.RED + "Usage: /de tppos <x> <y> <z> [world]";

    private TpPosCommand tpPosCommand;
    private Player player;
    private World currentWorld;
    private Server server;

    @BeforeEach
    public void setUp() {
        tpPosCommand = new TpPosCommand();
        player = mock(Player.class);
        currentWorld = mock(World.class);
        server = mock(Server.class);
        when(currentWorld.getName()).thenReturn("world");
        when(player.getWorld()).thenReturn(currentWorld);
        when(player.getServer()).thenReturn(server);
        when(player.getLocation()).thenReturn(new Location(currentWorld, 1, 2, 3, 90f, 45f));
        when(player.teleport(any(Location.class))).thenReturn(true);
    }

    @Test
    public void testExecute_noArgs_sendsUsage() {
        boolean result = tpPosCommand.execute(player);

        assertFalse(result);
        verify(player).sendMessage(USAGE);
    }

    @Test
    public void testExecute_nonPlayerSender_returnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = tpPosCommand.execute(commandSender, new String[]{"1", "2", "3"});

        assertFalse(result);
        verify(commandSender).sendMessage("Only players can use this command.");
    }

    @Test
    public void testExecute_tooFewArgs_sendsUsageAndDoesNotTeleport() {
        boolean result = tpPosCommand.execute(player, new String[]{"1", "2"});

        assertFalse(result);
        verify(player).sendMessage(USAGE);
        verify(player, never()).teleport(any(Location.class));
    }

    @Test
    public void testExecute_tooManyArgs_sendsUsageAndDoesNotTeleport() {
        boolean result = tpPosCommand.execute(player, new String[]{"1", "2", "3", "world", "extra"});

        assertFalse(result);
        verify(player).sendMessage(USAGE);
        verify(player, never()).teleport(any(Location.class));
    }

    @Test
    public void testExecute_nonNumericCoordinate_sendsErrorAndDoesNotTeleport() {
        boolean result = tpPosCommand.execute(player, new String[]{"1", "up", "3"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "Coordinates must be numbers.");
        verify(player, never()).teleport(any(Location.class));
    }

    @Test
    public void testExecute_nonFiniteCoordinate_sendsErrorAndDoesNotTeleport() {
        boolean result = tpPosCommand.execute(player, new String[]{"NaN", "64", "Infinity"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "Coordinates must be numbers.");
        verify(player, never()).teleport(any(Location.class));
    }

    @Test
    public void testExecute_threeCoordinates_teleportsInCurrentWorldKeepingFacing() {
        boolean result = tpPosCommand.execute(player, new String[]{"100.5", "64", "-20"});

        assertTrue(result);
        Location destination = captureTeleport();
        assertSame(currentWorld, destination.getWorld());
        assertEquals(100.5, destination.getX());
        assertEquals(64, destination.getY());
        assertEquals(-20, destination.getZ());
        assertEquals(90f, destination.getYaw());
        assertEquals(45f, destination.getPitch());
        verify(player).sendMessage(ChatColor.AQUA + "Teleported to 100.5, 64, -20 in world.");
    }

    @Test
    public void testExecute_namedWorld_teleportsInThatWorld() {
        World nether = mock(World.class);
        when(nether.getName()).thenReturn("world_nether");
        when(server.getWorld("world_nether")).thenReturn(nether);

        boolean result = tpPosCommand.execute(player, new String[]{"10", "70", "10", "world_nether"});

        assertTrue(result);
        assertSame(nether, captureTeleport().getWorld());
        verify(player).sendMessage(ChatColor.AQUA + "Teleported to 10, 70, 10 in world_nether.");
    }

    @Test
    public void testExecute_unknownWorld_sendsErrorAndDoesNotTeleport() {
        boolean result = tpPosCommand.execute(player, new String[]{"10", "70", "10", "nowhere"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "There is no world named nowhere.");
        verify(player, never()).teleport(any(Location.class));
    }

    @Test
    public void testExecute_teleportCancelled_sendsErrorInsteadOfSuccess() {
        when(player.teleport(any(Location.class))).thenReturn(false);

        boolean result = tpPosCommand.execute(player, new String[]{"10", "70", "10"});

        assertFalse(result);
        verify(player).sendMessage(ChatColor.RED + "The teleport was cancelled.");
        verify(player, never()).sendMessage(ChatColor.AQUA + "Teleported to 10, 70, 10 in world.");
    }

    private Location captureTeleport() {
        ArgumentCaptor<Location> captor = ArgumentCaptor.forClass(Location.class);
        verify(player).teleport(captor.capture());
        return captor.getValue();
    }
}
