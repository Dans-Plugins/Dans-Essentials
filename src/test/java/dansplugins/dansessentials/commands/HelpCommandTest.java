package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.command.CommandSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Daniel McCoy Stephenson
 */
public class HelpCommandTest {
    private HelpCommand helpCommand;

    @BeforeEach
    public void setUp() {
        helpCommand = new HelpCommand();
    }

    @Test
    public void testExecute_sendsOneLinePerSubCommand() {
        List<String> lines = captureHelpLines(new String[]{});

        assertEquals(12, lines.size());
    }

    @Test
    public void testExecute_withArgs_sendsSameList() {
        assertEquals(captureHelpLines(null), captureHelpLines(new String[]{"ignored"}));
    }

    @Test
    public void testExecute_listsEveryRegisteredSubCommand() {
        // The same list DansEssentials#initializeCommandService registers.
        List<AbstractPluginCommand> registered = SubCommands.create(new EphemeralData());
        List<String> lines = captureHelpLines(null);

        for (AbstractPluginCommand command : registered) {
            for (String name : command.getNames()) {
                String prefix = "/de " + name + " ";
                assertTrue(lines.stream().anyMatch(line -> line.contains(prefix)),
                        "help output is missing '/de " + name + "'");
            }
        }
    }

    private List<String> captureHelpLines(String[] args) {
        CommandSender commandSender = mock(CommandSender.class);
        boolean result = args == null ? helpCommand.execute(commandSender) : helpCommand.execute(commandSender, args);
        assertTrue(result);
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(commandSender, atLeastOnce()).sendMessage(captor.capture());
        return captor.getAllValues();
    }
}
