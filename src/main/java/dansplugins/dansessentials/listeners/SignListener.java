package dansplugins.dansessentials.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

/**
 * @author Daniel McCoy Stephenson
 */
public class SignListener implements Listener {

    @EventHandler()
    public void handle(SignChangeEvent event) {
        // check if it contains says [Spawn]
        if (event.getLine(0).contains("[Warp]")) {
            // if it does, check if the player has permission
            if (event.getPlayer().hasPermission("de.placeWarpSign")) {
                event.getPlayer().sendMessage(ChatColor.GREEN + "Warp sign created!");
            }
            else {
                // if they don't, cancel the event with a message
                event.getPlayer().sendMessage(ChatColor.RED + "Sorry! In order to place a spawn selection sign, you must have the following permission: 'medievalessentials.placeWarpSign");
                event.setCancelled(true);
            }
        }
    }
}