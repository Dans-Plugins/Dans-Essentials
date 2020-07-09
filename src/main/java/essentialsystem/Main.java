package essentialsystem;

import essentialsystem.Commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public final class Main extends JavaPlugin implements Listener {

    public MOTD motd = new MOTD();
    public ArrayList<PlayerActivityRecord> activityRecords = new ArrayList<>();
    public ArrayList<String> vanishedPlayers = new ArrayList<>();
    public ArrayList<String> mutedPlayers = new ArrayList<>();
    public ArrayList<NicknameRecord> nicknames = new ArrayList<>();

    @Override
    public void onEnable() {
        System.out.println("Medieval Essentials in enabling...");

        this.getServer().getPluginManager().registerEvents(this, this);

        motd.load();
        loadActivityRecords();
        loadNicknames();

        System.out.println("Medieval Essentials is enabled!");
    }

    @Override
    public void onDisable() {
        System.out.println("Medieval Essentials in disabling...");

        motd.save();
        saveActivityRecords();
        saveActivityRecordFilenames();
        saveNicknames();
        saveNicknameFilenames();

        System.out.println("Medieval Essentials is disabled!");
    }

    public void saveActivityRecords() {
        for (PlayerActivityRecord record : activityRecords) {
            record.save();
        }
    }

    public void saveActivityRecordFilenames() {
        try {
            File saveFolder = new File("./plugins/Medieval-Essentials/");
            if (!saveFolder.exists()) {
                saveFolder.mkdir();
            }
            File saveFile = new File("./plugins/Medieval-Essentials/" + "activity-record-filenames.txt");
            if (saveFile.createNewFile()) {
                System.out.println("Save file for activity record filenames created.");
            } else {
                System.out.println("Save file for activity record filenames already exists. Overwriting.");
            }

            FileWriter saveWriter = new FileWriter(saveFile);

            // actual saving takes place here
            for (PlayerActivityRecord record : activityRecords) {
                saveWriter.write(record.getPlayerName() + ".txt" + "\n");
            }

            saveWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred while saving activity record filenames.");
        }
    }

    public void saveNicknames() {
        for (NicknameRecord record : nicknames) {
            record.save();
        }
    }

    public void saveNicknameFilenames() {
        try {
            File saveFolder = new File("./plugins/Medieval-Essentials/");
            if (!saveFolder.exists()) {
                saveFolder.mkdir();
            }
            File saveFile = new File("./plugins/Medieval-Essentials/" + "nicknames.txt");
            if (saveFile.createNewFile()) {
                System.out.println("Save file for nickname record filenames created.");
            } else {
                System.out.println("Save file for nickname record filenames already exists. Overwriting.");
            }

            FileWriter saveWriter = new FileWriter(saveFile);

            // actual saving takes place here
            for (NicknameRecord record : nicknames) {
                saveWriter.write(record.getPlayerName() + ".txt" + "\n");
            }

            saveWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred while saving nickname record filenames.");
        }
    }

    public void loadActivityRecords() {
        try {
            System.out.println("Attempting to load activity records...");
            File loadFile = new File("./plugins/Medieval-Essentials/" + "activity-record-filenames.txt");
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            while (loadReader.hasNextLine()) {
                String nextName = loadReader.nextLine();
                PlayerActivityRecord temp = new PlayerActivityRecord();
                temp.setPlayerName(nextName);
                temp.load(nextName); // provides owner field among other things

                // existence check
                boolean exists = false;
                for (int i = 0; i < activityRecords.size(); i++) {
                    if (activityRecords.get(i).getPlayerName().equalsIgnoreCase(temp.getPlayerName())) {
                        activityRecords.remove(i);
                    }
                }

                activityRecords.add(temp);

            }

            loadReader.close();
            System.out.println("Activity records successfully loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading the activity records!");
        }
    }

    public void loadNicknames() {
        try {
            System.out.println("Attempting to load nickname records...");
            File loadFile = new File("./plugins/Medieval-Essentials/" + "nicknames.txt");
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            while (loadReader.hasNextLine()) {
                String nextName = loadReader.nextLine();
                NicknameRecord temp = new NicknameRecord();
                temp.load(nextName); // provides owner field among other things

                // existence check
                boolean exists = false;
                for (int i = 0; i < nicknames.size(); i++) {
                    if (nicknames.get(i).getPlayerName().equalsIgnoreCase(temp.getPlayerName())) {
                        nicknames.remove(i);
                    }
                }

                nicknames.add(temp);

            }

            loadReader.close();
            System.out.println("Nickname records successfully loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading the nickname records!");
        }
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("medievalessentials")) {
            if (args.length > 0 && args[0].equalsIgnoreCase("help")) {
                HelpCommand command = new HelpCommand();
                command.sendHelpInfo(sender);
            }
        }

        if (label.equalsIgnoreCase("fly")) {
            FlyCommand command = new FlyCommand();
            command.toggleFlight(sender, args);
        }

        if (label.equalsIgnoreCase("broadcast")) {
            BroadcastCommand command = new BroadcastCommand();
            if (args.length != 0) {
                command.broadcast(sender, createStringFromArgs(0, args.length, args));
            }
            else {
                sender.sendMessage(ChatColor.RED + "Usage: /broadcast (message)");
                return false;
            }
        }

        if (label.equalsIgnoreCase("motd")) {
            MOTDCommand command = new MOTDCommand(this);
            command.showMOTD(sender);
        }

        if (label.equalsIgnoreCase("setmotd")) {
            SetMOTDCommand command = new SetMOTDCommand(this);
            command.setMOTD(sender, args);
        }

        if (label.equalsIgnoreCase("seen")) {
            SeenCommand command = new SeenCommand(this);
            command.showLastLogout(sender, args);
        }

        if (label.equalsIgnoreCase("vanish")) {
            VanishCommand command = new VanishCommand(this);
            command.toggleVisibility(sender);
        }

        if (label.equalsIgnoreCase("mute")) {
            MuteCommand command = new MuteCommand(this);
            command.mutePlayer(sender, args);
        }

        if (label.equalsIgnoreCase("unmute")) {
            UnmuteCommand command = new UnmuteCommand(this);
            command.unmutePlayer(sender, args);
        }

        if (label.equalsIgnoreCase("nick")) {
            NickCommand command = new NickCommand(this);
            command.changeDisplayName(sender, args);
        }
/*
        if (label.equalsIgnoreCase("whois")) {
            WhoIsCommand command = new WhoIsCommand();
            command.showIGNToPlayer(sender, args);
        }
*/
        return false;
    }

    public static String createStringFromArgs(int start, int end, String[] args) {
        String toReturn = "";
        for (int i = start; i < end; i++) {
            toReturn = toReturn + args[i];
            if (i < end - 1) {
                toReturn = toReturn + " ";
            }
        }
        return toReturn;
    }

    @EventHandler()
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // show motd
        if (motd.isMessageSet()) {
            if (player.hasPermission("me.motd") || player.hasPermission("me.default")) {
                player.sendMessage(ChatColor.AQUA + motd.getMessage());
            }
        }

        // assign activity record if player doesn't have one
        if (!hasActivityRecord(player.getName())) {
            PlayerActivityRecord newRecord = new PlayerActivityRecord();
            newRecord.setPlayerName(player.getName());
            newRecord.incrementLogins();
            activityRecords.add(newRecord);
        }
        else {
            // increment logins for player if player already has record
            getActivityRecord(player.getName()).incrementLogins();
        }

        // hide vanished players from this player
        for (String vanishedPlayer : vanishedPlayers) {
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
        for (PlayerActivityRecord record : activityRecords) {
            if (record.getPlayerName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    public PlayerActivityRecord getActivityRecord(String playerName) {
        for (PlayerActivityRecord record : activityRecords) {
            if (record.getPlayerName().equalsIgnoreCase(playerName)) {
                return record;
            }
        }
        return null;
    }

    public boolean hasNicknameRecord(String playerName) {
        for (NicknameRecord record : nicknames) {
            if (record.getPlayerName().equalsIgnoreCase(playerName)) {
                return true;
            }
        }
        return false;
    }

    public NicknameRecord getNicknameRecord(String playerName) {
        for (NicknameRecord record : nicknames) {
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
        if (mutedPlayers.contains(event.getPlayer().getName())) {
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

}
