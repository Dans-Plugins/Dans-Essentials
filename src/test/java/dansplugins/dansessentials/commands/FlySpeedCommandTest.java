package dansplugins.dansessentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Daniel McCoy Stephenson
 */
public class FlySpeedCommandTest {

    @Test
    public void testExecute_noArguments_sendsUsageWithDePrefix() {
        CommandSender commandSender = mock(CommandSender.class);

        boolean result = new FlySpeedCommand().execute(commandSender);

        assertFalse(result);
        verify(commandSender).sendMessage(ChatColor.RED + "Usage: /de flyspeed (number)");
    }
}
