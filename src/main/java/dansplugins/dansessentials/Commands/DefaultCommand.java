package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.DansEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import preponderous.ponder.minecraft.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class DefaultCommand extends AbstractPluginCommand {

    public DefaultCommand() {
        super(new ArrayList<>(Arrays.asList("default")), new ArrayList<>(Arrays.asList("de.default")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.AQUA + "Dan's Essentials " + DansEssentials.getInstance().getVersion());
        commandSender.sendMessage(ChatColor.AQUA + "Developed by: DanTheTechMan");
        commandSender.sendMessage(ChatColor.AQUA + "Wiki: https://github.com/dmccoystephenson/Dans-Essentials/wiki");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}