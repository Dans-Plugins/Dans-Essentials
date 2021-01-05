package dansplugins.essentialsystem;

import dansplugins.essentialsystem.Commands.*;
import dansplugins.essentialsystem.Objects.NicknameRecord;
import dansplugins.essentialsystem.Objects.PlayerActivityRecord;
import dansplugins.essentialsystem.bStats.Metrics;
import dansplugins.essentialsystem.data.EphemeralData;
import dansplugins.essentialsystem.data.PersistentData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.ZonedDateTime;

public class MedievalEssentials extends JavaPlugin implements Listener {

    private static MedievalEssentials instance;

    @Override
    public void onEnable() {
        System.out.println("Medieval Essentials in enabling...");

        instance = this;

        this.getServer().getPluginManager().registerEvents(this, this);

        StorageManager.getInstance().load();

        int pluginId = 9527;
        Metrics metrics = new Metrics(this, pluginId);

        System.out.println("Medieval Essentials is enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("Medieval Essentials in disabling...");

        StorageManager.getInstance().save();

        System.out.println("Medieval Essentials is disabled!");
    }

    public static MedievalEssentials getInstance() {
        return instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CommandInterpreter.getInstance().interpretCommand(sender, label, args);
        return true;
    }

    @EventHandler()
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.GREEN + "Welcome " + player.getName() + " to the server!");
            }
        }

        // show motd
        if (PersistentData.getInstance().getMotd().isMessageSet()) {
            if (player.hasPermission("me.motd") || player.hasPermission("me.default")) {
                player.sendMessage(ChatColor.AQUA + PersistentData.getInstance().getMotd().getMessage());
            }
        }

        // assign activity record if player doesn't have one
        if (!hasActivityRecord(player.getName())) {
            PlayerActivityRecord newRecord = new PlayerActivityRecord();
            newRecord.setPlayerName(player.getName());
            newRecord.incrementLogins();
            PersistentData.getInstance().getActivityRecords().add(newRecord);
        }
        else {
            // increment logins for player if player already has record
            getActivityRecord(player.getName()).incrementLogins();
        }

        // hide vanished players from this player
        for (String vanishedPlayer : EphemeralData.getInstance().getVanishedPlayers()) {
            event.getPlayer().hidePlayer(this, getServer().getPlayer(vanishedPlayer));
        }

        // assign nickname
        if (hasNicknameRecord(event.getPlayer().getName())) {

            // if nickname not assigned
            if (!event.getPlayer().getName().equalsIgnoreCase(getNicknameRecord(event.getPlayer().getName()).getNickname())) {
                // assign it
                event.getPlayer().setDisplayName(ChatColor.translateAlternateColorCodes('&', getNicknameRecord(event.getPlayer().getName()).getNickname() + "&r"));
            }

        }

    }

    public boolean hasActivityRecord(String playerName) {
        for (PlayerActivityRecord record : PersistentData.getInstance().getActivityRecords()) {
            if (record.getPlayerName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    public PlayerActivityRecord getActivityRecord(String playerName) {
        for (PlayerActivityRecord record : PersistentData.getInstance().getActivityRecords()) {
            if (record.getPlayerName().equalsIgnoreCase(playerName)) {
                return record;
            }
        }
        return null;
    }

    public boolean hasNicknameRecord(String playerName) {
        for (NicknameRecord record : PersistentData.getInstance().getNicknames()) {
            if (record.getPlayerName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    public NicknameRecord getNicknameRecord(String playerName) {
        for (NicknameRecord record : PersistentData.getInstance().getNicknames()) {
            if (record.getPlayerName().equalsIgnoreCase(playerName)) {
                return record;
            }
        }
        return null;
    }

    @EventHandler()
    public void onQuit(PlayerQuitEvent event) {
        ZonedDateTime now = ZonedDateTime.now();
        getActivityRecord(event.getPlayer().getName()).setLastLogout(now);
    }

    @EventHandler()
    public void onChat(AsyncPlayerChatEvent event) {
        if (EphemeralData.getInstance().getMutedPlayers().contains(event.getPlayer().getName())) {
            event.getPlayer().sendMessage(ChatColor.RED + "You are currently muted.");
            event.setCancelled(true);
        }
    }

    public int[] getPlayersPosition(Player player) {
        int[] coords = new int[3];
        coords[0] = player.getLocation().getBlockX();
        coords[1] = player.getLocation().getBlockY();
        coords[2] = player.getLocation().getBlockZ();
        return coords;
    }

    public String getPlayersDirection(Player player) {
        // TODO: implement this
        return null;
    }

    @EventHandler()
    public void onTeleport(PlayerTeleportEvent event) {
        EphemeralData.getInstance().getLastLogins().put(event.getPlayer(), event.getFrom());
    }

}
