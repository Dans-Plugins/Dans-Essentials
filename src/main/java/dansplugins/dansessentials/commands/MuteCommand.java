package dansplugins.dansessentials.commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.bukkit.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class MuteCommand extends AbstractPluginCommand {
    private final EphemeralData ephemeralData;

    public MuteCommand(EphemeralData ephemeralData) {
        super(new ArrayList<>(Arrays.asList("mute")), new ArrayList<>(Arrays.asList("de.mute")));
        this.ephemeralData = ephemeralData;
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /de mute (player-name)");
        return false;
    }

    @Override
    public boolean execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return false;
        }

        Player operator = (Player) commandSender;
        Player targetPlayer = getTargetPlayer(args[0]);

        if (targetPlayer == null) {
            operator.sendMessage(ChatColor.RED + "That player isn't online!");
            return false;
        }

        String targetName = targetPlayer.getName();

        if (ephemeralData.getMutedPlayers().contains(targetName)) {
            operator.sendMessage(ChatColor.RED + "That player is already muted!");
            return false;
        }

        if (operator.getName().equalsIgnoreCase(targetName)) {
            operator.sendMessage(ChatColor.RED + "You can't mute yourself!");
            return false;
        }

        ephemeralData.getMutedPlayers().add(targetName);
        targetPlayer.sendMessage(ChatColor.RED + "You have been muted.");
        operator.sendMessage(ChatColor.GREEN + "Player has been muted.");
        return true;
    }

    /**
     * Resolves an online player by name. Extracted so that the surrounding logic can be exercised
     * without a live server.
     * @param name The name to look up.
     * @return The matching online player, or null if none is online.
     */
    Player getTargetPlayer(String name) {
        return Bukkit.getServer().getPlayer(name);
    }
}