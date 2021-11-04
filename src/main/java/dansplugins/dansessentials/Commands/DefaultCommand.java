package dansplugins.dansessentials.Commands;

import org.bukkit.command.CommandSender;
import preponderous.ponder.misc.AbstractCommand;

public class DefaultCommand extends AbstractCommand {

    @Override
    public boolean execute(CommandSender commandSender) {
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return false;
    }
}
