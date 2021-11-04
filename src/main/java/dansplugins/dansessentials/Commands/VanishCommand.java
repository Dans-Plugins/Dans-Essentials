package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.DansEssentials;
import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.misc.AbstractCommand;

import java.util.ArrayList;
import java.util.Collections;

import static org.bukkit.Bukkit.getServer;

/**
 * @author Daniel Stephenson
 */
public class VanishCommand extends AbstractCommand {

    private ArrayList<String> names = new ArrayList<>(Collections.singletonList("vanish"));
    private ArrayList<String> permissions = new ArrayList<>(Collections.singletonList("de.vanish"));

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
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }
        Player player = (Player) commandSender;
        if (!EphemeralData.getInstance().getVanishedPlayers().contains(player.getName())) {
            hidePlayer(player);
            player.sendMessage(ChatColor.GREEN + "You are now hidden!");
        }
        else {
            showPlayer(player);
            player.sendMessage(ChatColor.GREEN + "You are now visible!");
        }
        return true;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        return execute(commandSender);
    }

    private void hidePlayer(Player player) {
        for (Player target : getServer().getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(target.getName())) {
                target.hidePlayer(DansEssentials.getInstance(), player);
                EphemeralData.getInstance().getVanishedPlayers().add(player.getName());
            }
        }

    }

    private void showPlayer(Player player) {
        for (Player target : getServer().getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(target.getName())) {
                target.showPlayer(DansEssentials.getInstance(), player);
                EphemeralData.getInstance().getVanishedPlayers().remove(player.getName());
            }
        }
    }
}
