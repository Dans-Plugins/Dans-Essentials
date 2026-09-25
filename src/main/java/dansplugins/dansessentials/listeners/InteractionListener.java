package dansplugins.dansessentials.listeners;

import dansplugins.dansessentials.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Daniel McCoy Stephenson
 */
public class InteractionListener implements Listener {
    private final Logger logger;

    public InteractionListener(Logger logger) {
        this.logger = logger;
    }

    @EventHandler()
    public void handle(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // if player isn't right clicking a block
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            logger.log(player.getName() + " didn't click a block.");
            return;
        }

        // if that block isn't a sign
        if (!isSign(clickedBlock)) {
            logger.log(player.getName() + " clicked a block that wasn't a sign.");
            return;
        }

        // if that sign doesn't have [Warp]
        Sign sign = (Sign) clickedBlock.getState();
        if (!sign.getLine(0).contains("[Warp]")) {
            logger.log(player.getName() + " clicked a sign that wasn't a warp sign.");
            return;
        }

        if (!player.hasPermission("de.usewarpsign")) {
            logger.log(player.getName() + " clicked on a warp sign but didn't have permission to use it.");
            event.getPlayer().sendMessage(ChatColor.RED + "You don't have permission to use this.");
            return;
        }

        logger.log(player.getName() + " clicked on a warp sign. Teleporting.");

        // acquire coordinates
        int x;
        int y;
        int z;
        try {
            x = Integer.parseInt(sign.getLine(1));
            y = Integer.parseInt(sign.getLine(2));
            z = Integer.parseInt(sign.getLine(3));
        } catch (NumberFormatException e) {
            logger.warn("The warp sign located at [" + clickedBlock.getX() + ", " + clickedBlock.getY() + ", " + clickedBlock.getZ() + "] in " + player.getWorld().getName() + " has invalid coordinates: " + e.getMessage());
            player.sendMessage(ChatColor.RED + "This warp sign's coordinates are not valid.");
            return;
        }
        World world = player.getWorld();

        Location warpLocation = new Location(world, x, y, z);

        // teleport player
        player.teleport(warpLocation);
        player.sendMessage(ChatColor.GREEN + "You have warped to " + x + " " + y + " " + z  + ".");
    }

    private boolean isSign(Block block) {
        return block.getState() instanceof Sign;
    }
}