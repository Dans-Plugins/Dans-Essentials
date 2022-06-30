package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class BackCommand extends AbstractPluginCommand {
    private final EphemeralData ephemeralData;

    public BackCommand(EphemeralData ephemeralData) {
        super(new ArrayList<>(Arrays.asList("back")), new ArrayList<>(Arrays.asList("de.back")));
        this.ephemeralData = ephemeralData;
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("At this time, only players can use this command.");
            return false;
        }

        Player player = (Player) commandSender;

        player.teleport(ephemeralData.getLastLogins().get(player));
        player.sendMessage(ChatColor.AQUA + "Teleported to your last location!");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}