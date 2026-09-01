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
public class UnmuteCommand extends AbstractPluginCommand {
    private final EphemeralData ephemeralData;

    public UnmuteCommand(EphemeralData ephemeralData) {
        super(new ArrayList<>(Arrays.asList("unmute")), new ArrayList<>(Arrays.asList("de.unmute")));
        this.ephemeralData = ephemeralData;
    }

    @Override
    public boolean execute(CommandSender commandSender) {
        commandSender.sendMessage(ChatColor.RED + "Usage: /de unmute (player-name)");
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

        if (!ephemeralData.getMutedPlayers().contains(targetName)) {
            operator.sendMessage(ChatColor.RED + "That player is already not muted!");
            return false;
        }

        ephemeralData.getMutedPlayers().remove(targetName);
        targetPlayer.sendMessage(ChatColor.GREEN + "You have been unmuted.");
        operator.sendMessage(ChatColor.GREEN + "Player has been unmuted.");
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