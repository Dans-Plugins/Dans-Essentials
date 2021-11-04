package dansplugins.dansessentials;

import dansplugins.dansessentials.data.EphemeralData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EventHandlers implements Listener {

    @EventHandler()
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.GREEN + "Welcome " + player.getName() + " to the server!");
            }
        }

        // hide vanished players from this player
        for (String vanishedPlayer : EphemeralData.getInstance().getVanishedPlayers()) {
            event.getPlayer().hidePlayer(DansEssentials.getInstance(), Bukkit.getServer().getPlayer(vanishedPlayer));
        }

    }

    @EventHandler()
    public void onChat(AsyncPlayerChatEvent event) {
        if (EphemeralData.getInstance().getMutedPlayers().contains(event.getPlayer().getName())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You are currently muted.");
            event.setCancelled(true);
        }
    }

    @EventHandler()
    public void onTeleport(PlayerTeleportEvent event) {
        EphemeralData.getInstance().getLastLogins().put(event.getPlayer(), event.getFrom());
    }

    @EventHandler()
    public void onSignChangeEvent(SignChangeEvent event) {
        // check if it contains says [Spawn]
        if (event.getLine(0).contains("[Warp]")) {
            // if it does, check if the player has permission
            if (event.getPlayer().hasPermission("medievalessentials.placeWarpSign") || event.getPlayer().hasPermission("medievalessentials.admin")) {
                event.getPlayer().sendMessage(ChatColor.GREEN + "Warp sign created!");
            }
            else {
                // if they don't, cancel the event with a message
                event.getPlayer().sendMessage(ChatColor.RED + "Sorry! In order to place a spawn selection sign, you must have the following permission: 'medievalessentials.placeWarpSign");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler()
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        // if player is right clicking a block
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null) {
            // if that block is a sign
            if (isSign(clickedBlock)) {
                // if that sign has [Warp]
                Sign sign = (Sign) clickedBlock.getState();
                if (sign.getLine(0).contains("[Warp]")) {

                    if (!event.getPlayer().hasPermission("medievalessentials.usewarpsign")) {
                        event.getPlayer().sendMessage(ChatColor.RED + "You don't have permission to use this.");
                        return;
                    }

                    // acquire coordinates
                    try {
                        int x = Integer.parseInt(sign.getLine(1));
                        int y = Integer.parseInt(sign.getLine(2));;
                        int z = Integer.parseInt(sign.getLine(3));;
                        World world = event.getPlayer().getWorld();

                        Location warpLocation = new Location(world, x, y, z);

                        // teleport player
                        event.getPlayer().teleport(warpLocation);

                    } catch(Exception e) {
                        System.out.println("A problem occurred with a warp sign located at [" + clickedBlock.getX() + ", " + clickedBlock.getY()  + ", " + clickedBlock.getZ() + "] in " + event.getPlayer().getWorld().getName());
                    }
                }
            }
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
