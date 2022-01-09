package dansplugins.dansessentials.Commands;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import preponderous.ponder.minecraft.abs.AbstractPluginCommand;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Daniel McCoy Stephenson
 */
public class UnmuteCommand extends AbstractPluginCommand {

    public UnmuteCommand() {
        super(new ArrayList<>(Arrays.asList("unmute")), new ArrayList<>(Arrays.asList("de.unmute")));
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
        Player targetPlayer = Bukkit.getServer().getPlayer(args[0]);

        if (targetPlayer == null) {
            operator.sendMessage(ChatColor.RED + "That player isn't online!");
            return false;
        }

        if (!EphemeralData.getInstance().getMutedPlayers().contains(args[0])) {
            operator.sendMessage(ChatColor.RED + "That player is already not muted!");
            return false;
        }

        EphemeralData.getInstance().getMutedPlayers().remove(args[0]);
        targetPlayer.sendMessage(ChatColor.GREEN + "You have been unmuted.");
        operator.sendMessage(ChatColor.GREEN + "Player has been unmuted.");
        return true;
    }
}