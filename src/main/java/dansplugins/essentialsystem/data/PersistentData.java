package dansplugins.essentialsystem.data;

import dansplugins.essentialsystem.Objects.MOTD;
import dansplugins.essentialsystem.Objects.NicknameRecord;
import dansplugins.essentialsystem.Objects.PlayerActivityRecord;

import java.util.ArrayList;

public class PersistentData {

    private static PersistentData instance;

    private MOTD motd = new MOTD();
    private ArrayList<PlayerActivityRecord> activityRecords = new ArrayList<>();
    private ArrayList<NicknameRecord> nicknames = new ArrayList<>();

    private PersistentData() {

    }

    public static PersistentData getInstance() {
        if (instance == null) {
            instance = new PersistentData();
        }
        return instance;
    }

    public MOTD getMotd() {
        return motd;
    }

    public void setMotd(MOTD motd) {
        this.motd = motd;
    }

    public ArrayList<PlayerActivityRecord> getActivityRecords() {
        return activityRecords;
    }

    public void setActivityRecords(ArrayList<PlayerActivityRecord> activityRecords) {
        this.activityRecords = activityRecords;
    }

    public ArrayList<NicknameRecord> getNicknames() {
        return nicknames;
    }

    public void setNicknames(ArrayList<NicknameRecord> nicknames) {
        this.nicknames = nicknames;
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
}
