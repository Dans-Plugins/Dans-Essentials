package dansplugins.dansessentials.utils;

import org.bukkit.entity.Player;

/**
 * Works out which compass direction a player is facing.
 * @author Daniel McCoy Stephenson
 */
public final class PlayerDirection {
    // Bukkit yaw starts at 0 facing south (+Z) and turns clockwise seen from
    // above: 90 is west, 180 is north and 270 is east.
    private static final String[] DIRECTIONS = {
            "South", "Southwest", "West", "Northwest",
            "North", "Northeast", "East", "Southeast"
    };

    private PlayerDirection() {
    }

    /**
     * @param player The player.
     * @return The nearest of the eight compass directions the player is facing.
     */
    public static String getDirection(Player player) {
        return getDirection(player.getLocation().getYaw());
    }

    /**
     * @param yaw A Bukkit yaw in degrees, of any magnitude or sign.
     * @return The nearest of the eight compass directions to that yaw. A yaw
     * exactly halfway between two directions rounds to the clockwise one.
     */
    public static String getDirection(float yaw) {
        double normalized = ((yaw % 360) + 360) % 360;
        int index = (int) Math.floor(normalized / 45 + 0.5) % DIRECTIONS.length;
        return DIRECTIONS[index];
    }
}
