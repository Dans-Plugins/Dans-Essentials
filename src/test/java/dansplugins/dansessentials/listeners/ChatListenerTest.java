package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class ChatListenerTest {
    private EphemeralData ephemeralData;
    private Player player;
    private ChatListener chatListener;

    @BeforeEach
    public void setUp() {
        ephemeralData = new EphemeralData();
        player = mock(Player.class);
        when(player.getName()).thenReturn("Steve");
        chatListener = new ChatListener(ephemeralData);
    }

    private AsyncPlayerChatEvent createChatEvent() {
        // getPlayer() is final on PlayerEvent, so a real event wraps the mocked player
        // instead of mocking the event itself.
        return new AsyncPlayerChatEvent(true, player, "hello", new HashSet<>());
    }

    @Test
    public void testHandle_mutedPlayer_cancelsAndNotifies() {
        ephemeralData.getMutedPlayers().add("Steve");
        AsyncPlayerChatEvent event = createChatEvent();

        chatListener.handle(event);

        assertTrue(event.isCancelled());
        verify(player).sendMessage(ChatColor.RED + "You are currently muted.");
    }

    @Test
    public void testHandle_unmutedPlayer_leavesEventUntouched() {
        ephemeralData.getMutedPlayers().add("Alex");
        AsyncPlayerChatEvent event = createChatEvent();

        chatListener.handle(event);

        assertFalse(event.isCancelled());
        verify(player, never()).sendMessage(anyString());
    }

    @Test
    public void testHandle_mutedNameMatchIsCaseSensitive() {
        // MuteCommand stores the name exactly as Bukkit reports it, and the lookup here is
        // a plain List.contains, so a differently-cased entry does not mute the player.
        ephemeralData.getMutedPlayers().add("steve");
        AsyncPlayerChatEvent event = createChatEvent();

        chatListener.handle(event);

        assertFalse(event.isCancelled());
    }
}
