package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Daniel Stephenson
 */
public class BackCommand extends AbstractCommand {

    private ArrayList<String> names = new ArrayList<>(Collections.singletonList("back"));
    private ArrayList<String> permissions = new ArrayList<>(Collections.singletonList("de.back"));

    @Override
    public ArrayList<String> getNames() {
        return names;
    }

    @Override
    public ArrayList<String> getPermissions() {
        return permissions;
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
