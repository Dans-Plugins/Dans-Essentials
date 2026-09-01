package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class UnmuteCommandTest {
    private Map<String, Player> onlinePlayers;
    private EphemeralData ephemeralData;
    private UnmuteCommand unmuteCommand;

    @BeforeEach
    public void setUp() {
        onlinePlayers = new HashMap<>();
        ephemeralData = new EphemeralData();
        unmuteCommand = new UnmuteCommand(ephemeralData) {
            @Override
            Player getTargetPlayer(String name) {
                return onlinePlayers.get(name);
            }
        };
    }

    private Player onlinePlayerNamed(String canonicalName, String... alsoResolvesFrom) {
        Player player = mock(Player.class);
        when(player.getName()).thenReturn(canonicalName);
        onlinePlayers.put(canonicalName, player);
        for (String alias : alsoResolvesFrom) {
            onlinePlayers.put(alias, player);
        }
        return player;
    }

    @Test
    public void testExecute_nonPlayerSender_returnsFalse() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = unmuteCommand.execute(commandSender, new String[]{"Steve"});

        assertFalse(result);
        verify(commandSender).sendMessage("Only players can use this command.");
    }

    @Test
    public void testExecute_offlineTarget_sendsError() {
        Player operator = mock(Player.class);

        boolean result = unmuteCommand.execute(operator, new String[]{"Nobody"});

        assertFalse(result);
        verify(operator).sendMessage(ChatColor.RED + "That player isn't online!");
    }

    @Test
    public void testExecute_mutedTarget_removesTheResolvedName() {
        Player operator = mock(Player.class);
        Player target = onlinePlayerNamed("Steve");
        ephemeralData.getMutedPlayers().add("Steve");

        boolean result = unmuteCommand.execute(operator, new String[]{"Steve"});

        assertTrue(result);
        assertTrue(ephemeralData.getMutedPlayers().isEmpty());
        verify(target).sendMessage(ChatColor.GREEN + "You have been unmuted.");
        verify(operator).sendMessage(ChatColor.GREEN + "Player has been unmuted.");
    }

    @Test
    public void testExecute_partialName_unmutesThePlayerMutedUnderTheirCanonicalName() {
        Player operator = mock(Player.class);
        onlinePlayerNamed("Steve", "ste");
        ephemeralData.getMutedPlayers().add("Steve");

        boolean result = unmuteCommand.execute(operator, new String[]{"ste"});

        assertTrue(result);
        assertTrue(ephemeralData.getMutedPlayers().isEmpty());
    }

    @Test
    public void testExecute_targetThatIsNotMuted_sendsError() {
        Player operator = mock(Player.class);
        onlinePlayerNamed("Steve");

        boolean result = unmuteCommand.execute(operator, new String[]{"Steve"});

        assertFalse(result);
        verify(operator).sendMessage(ChatColor.RED + "That player is already not muted!");
    }

    @Test
    public void testExecute_withoutArguments_sendsUsage() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = unmuteCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "Usage: /de unmute (player-name)");
    }
}
