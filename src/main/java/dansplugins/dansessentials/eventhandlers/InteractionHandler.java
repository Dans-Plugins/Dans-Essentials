package dansplugins.dansessentials.eventhandlers;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractionHandler implements Listener {
    @EventHandler()
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // if player isn't right clicking a block
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock == null) {
            return;
        }

        // if that block isn't a sign
        if (!isSign(clickedBlock)) {
            return;
        }

        // if that sign doesn't have [Warp]
        Sign sign = (Sign) clickedBlock.getState();
        if (!sign.getLine(0).contains("[Warp]")) {
            return;
        }

        if (!player.hasPermission("de.usewarpsign")) {
            event.getPlayer().sendMessage(ChatColor.RED + "You don't have permission to use this.");
            return;
        }

        try {
            // acquire coordinates
            int x = Integer.parseInt(sign.getLine(1));
            int y = Integer.parseInt(sign.getLine(2));;
            int z = Integer.parseInt(sign.getLine(3));;
            World world = event.getPlayer().getWorld();

            Location warpLocation = new Location(world, x, y, z);

            // teleport player
            player.teleport(warpLocation);
            player.sendMessage(ChatColor.GREEN + "You have warped to " + x + " " + y + " " + z  + ".");

        } catch(Exception e) {
            System.out.println("A problem occurred with a warp sign located at [" + clickedBlock.getX() + ", " + clickedBlock.getY()  + ", " + clickedBlock.getZ() + "] in " + event.getPlayer().getWorld().getName());
        }
    }

    private boolean isSign(Block block) {
        switch(block.getType()) {
            case SIGN:
            case WALL_SIGN:
                return true;
        }
        return false;
    }
}