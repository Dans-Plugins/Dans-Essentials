package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.data.EphemeralData;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The sub-commands registered under /de. DefaultCommand is not one of them,
 * because it runs on a bare /de.
 * @author Daniel McCoy Stephenson
 */
public final class SubCommands {
    private SubCommands() {
    }

    /**
     * Creates a new instance of every sub-command.
     * @param ephemeralData The data shared by the commands that need it.
     * @return The sub-commands, in registration order.
     */
    public static List<AbstractPluginCommand> create(EphemeralData ephemeralData) {
        return new ArrayList<>(Arrays.asList(
                new BackCommand(ephemeralData), new BroadcastCommand(), new ClearInvCommand(),
                new FlyCommand(), new FlySpeedCommand(), new GamemodeCommand(),
                new GetPosCommand(), new HelpCommand(), new InvseeCommand(),
                new LabelCommand(), new MuteCommand(ephemeralData), new TpPosCommand(),
                new UnmuteCommand(ephemeralData)
        ));
    }
}
