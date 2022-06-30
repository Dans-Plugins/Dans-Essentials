package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.DansEssentials;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class DefaultCommand extends AbstractPluginCommand {
    private final DansEssentials dansEssentials;

    public DefaultCommand(DansEssentials dansEssentials) {
        super(new ArrayList<>(Arrays.asList("default")), new ArrayList<>(Arrays.asList("de.default")));
        this.dansEssentials = dansEssentials;
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.AQUA + "Dan's Essentials " + dansEssentials.getVersion());
        commandSender.sendMessage(ChatColor.AQUA + "Developed by: DanTheTechMan");
        commandSender.sendMessage(ChatColor.AQUA + "Wiki: https://github.com/dmccoystephenson/Dans-Essentials/wiki");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}