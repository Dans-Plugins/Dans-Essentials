package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class BackCommand extends AbstractPluginCommand {

    public BackCommand() {
        super(new ArrayList<>(Arrays.asList("back")), new ArrayList<>(Arrays.asList("de.back")));
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("At this time, only players can use this command.");
            return false;
        }

        Player player = (Player) commandSender;

        player.teleport(EphemeralData.getInstance().getLastLogins().get(player));
        player.sendMessage(ChatColor.AQUA + "Teleported to your last location!");
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }
}