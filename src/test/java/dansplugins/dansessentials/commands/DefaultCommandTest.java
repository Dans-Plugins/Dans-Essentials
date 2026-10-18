package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.DansEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Daniel McCoy Stephenson
 */
public class DefaultCommandTest {
    private DefaultCommand defaultCommand;

    @BeforeEach
    public void setUp() {
        DansEssentials dansEssentials = mock(DansEssentials.class);
        when(dansEssentials.getVersion()).thenReturn("v9.8.7");
        defaultCommand = new DefaultCommand(dansEssentials);
    }

    @Test
    public void testExecute_sendsVersionAuthorAndWiki() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = defaultCommand.execute(commandSender);

        assertTrue(result);
        InOrder inOrder = inOrder(commandSender);
        inOrder.verify(commandSender).sendMessage(ChatColor.AQUA + "Dan's Essentials v9.8.7");
        inOrder.verify(commandSender).sendMessage(ChatColor.AQUA + "Developed by: DanTheTechMan");
        inOrder.verify(commandSender).sendMessage(ChatColor.AQUA + "Wiki: https://github.com/Dans-Plugins/Dans-Essentials/wiki");
    }

    @Test
    public void testExecute_withArgs_sendsSameInfo() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = defaultCommand.execute(commandSender, new String[]{"ignored"});

        assertTrue(result);
        inOrder(commandSender).verify(commandSender).sendMessage(ChatColor.AQUA + "Dan's Essentials v9.8.7");
    }
}
