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
        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

        if (targetPlayer == null) {
            operator.sendMessage(ChatColor.RED + "That player isn't online!");
            return false;
        }

        if (ephemeralData.getMutedPlayers().contains(args[0])) {
            operator.sendMessage(ChatColor.RED + "That player is already muted!");
            return false;
        }

        if (operator.getName().equalsIgnoreCase(args[0])) {
            operator.sendMessage(ChatColor.RED + "You can't mute yourself!");
            return false;
        }

        ephemeralData.getMutedPlayers().add(args[0]);
        targetPlayer.sendMessage(ChatColor.RED + "You have been muted.");
        operator.sendMessage(ChatColor.GREEN + "Player has been muted.");
        return true;
    }
}