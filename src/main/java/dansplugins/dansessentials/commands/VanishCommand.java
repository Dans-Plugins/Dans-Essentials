package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.DansEssentials;
import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

import static org.bukkit.Bukkit.getServer;

/**
 * @author Daniel McCoy Stephenson
 */
public class VanishCommand extends AbstractPluginCommand {
    private final EphemeralData ephemeralData;
    private final DansEssentials dansEssentials;

    public VanishCommand(EphemeralData ephemeralData, DansEssentials dansEssentials) {
        super(new ArrayList<>(Arrays.asList("vanish")), new ArrayList<>(Arrays.asList("de.vanish")));
        this.ephemeralData = ephemeralData;
        this.dansEssentials = dansEssentials;
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }
        Player player = (Player) commandSender;
        if (!ephemeralData.getVanishedPlayers().contains(player.getName())) {
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
                target.hidePlayer(dansEssentials, player);
                ephemeralData.getVanishedPlayers().add(player.getName());
            }
        }

    }

    private void showPlayer(Player player) {
        for (Player target : getServer().getOnlinePlayers()) {
            if (!player.getName().equalsIgnoreCase(target.getName())) {
                target.showPlayer(dansEssentials, player);
                ephemeralData.getVanishedPlayers().remove(player.getName());
            }
        }
    }
}