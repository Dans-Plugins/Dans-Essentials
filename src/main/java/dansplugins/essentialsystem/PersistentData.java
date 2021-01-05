package dansplugins.essentialsystem;

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
}
