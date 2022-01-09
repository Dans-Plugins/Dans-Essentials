package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.DansEssentials;
import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

/**
 * @author Daniel McCoy Stephenson
 */
public class VanishCommand extends AbstractPluginCommand {

    public VanishCommand() {
        super(new ArrayList<>(Arrays.asList("vanish")), new ArrayList<>(Arrays.asList("de.vanish")));
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