package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class MuteCommandTest {
    private Map<String, Player> onlinePlayers;
    private EphemeralData ephemeralData;
    private MuteCommand muteCommand;

    @BeforeEach
    public void setUp() {
        onlinePlayers = new HashMap<>();
        ephemeralData = new EphemeralData();
        muteCommand = new MuteCommand(ephemeralData) {
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

        boolean result = muteCommand.execute(commandSender, new String[]{"Steve"});

        assertFalse(result);
        verify(commandSender).sendMessage("Only players can use this command.");
    }

    @Test
    public void testExecute_offlineTarget_sendsErrorAndMutesNobody() {
        Player operator = mock(Player.class);

        boolean result = muteCommand.execute(operator, new String[]{"Nobody"});

        assertFalse(result);
        verify(operator).sendMessage(ChatColor.RED + "That player isn't online!");
        assertTrue(ephemeralData.getMutedPlayers().isEmpty());
    }

    @Test
    public void testExecute_onlineTarget_storesTheResolvedName() {
        Player operator = mock(Player.class);
        when(operator.getName()).thenReturn("Alex");
        Player target = onlinePlayerNamed("Steve");

        boolean result = muteCommand.execute(operator, new String[]{"Steve"});

        assertTrue(result);
        assertTrue(ephemeralData.getMutedPlayers().contains("Steve"));
        verify(target).sendMessage(ChatColor.RED + "You have been muted.");
        verify(operator).sendMessage(ChatColor.GREEN + "Player has been muted.");
    }

    @Test
    public void testExecute_partialName_storesTheCanonicalNameTheChatListenerReads() {
        Player operator = mock(Player.class);
        when(operator.getName()).thenReturn("Alex");
        onlinePlayerNamed("Steve", "ste");

        boolean result = muteCommand.execute(operator, new String[]{"ste"});

        assertTrue(result);
        assertTrue(ephemeralData.getMutedPlayers().contains("Steve"));
        assertFalse(ephemeralData.getMutedPlayers().contains("ste"));
    }

    @Test
    public void testExecute_alreadyMutedUnderAnotherSpelling_isNotMutedTwice() {
        Player operator = mock(Player.class);
        when(operator.getName()).thenReturn("Alex");
        onlinePlayerNamed("Steve", "ste");
        ephemeralData.getMutedPlayers().add("Steve");

        boolean result = muteCommand.execute(operator, new String[]{"ste"});

        assertFalse(result);
        verify(operator).sendMessage(ChatColor.RED + "That player is already muted!");
        assertEquals(1, ephemeralData.getMutedPlayers().size());
    }

    @Test
    public void testExecute_partialNameResolvingToTheOperator_isRefused() {
        Player operator = mock(Player.class);
        when(operator.getName()).thenReturn("Alex");
        onlinePlayers.put("Alex", operator);
        onlinePlayers.put("ale", operator);

        boolean result = muteCommand.execute(operator, new String[]{"ale"});

        assertFalse(result);
        verify(operator).sendMessage(ChatColor.RED + "You can't mute yourself!");
        assertTrue(ephemeralData.getMutedPlayers().isEmpty());
    }

    @Test
    public void testExecute_withoutArguments_sendsUsage() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = muteCommand.execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "Usage: /de mute (player-name)");
    }

    @Test
    public void testExecute_offlineTarget_neverMessagesAnyOnlinePlayer() {
        Player operator = mock(Player.class);
        Player bystander = onlinePlayerNamed("Steve");

        muteCommand.execute(operator, new String[]{"Nobody"});

        verify(bystander, never()).sendMessage(ChatColor.RED + "You have been muted.");
    }
}
