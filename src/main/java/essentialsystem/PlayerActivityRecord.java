package essentialsystem;

import java.time.Period;
import java.util.Date;

public class PlayerActivityRecord {
    private String playerName = null;
    private Date lastLogout = null;
    private int logins = -1;

    public void setPlayerName(String name) {
        playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setLastLogout(Date date) {
        lastLogout = date;
    }

    public Date getLastLogout() {
        return lastLogout;
    }

    public void incrementLogins() {
        logins++;
    }

    public int getLogins() {
        return logins;
    }

    public String getTimeSinceLastLogout() {
        Date now = new Date();
        double seconds = (now.getTime() - lastLogout.getTime()) / 1000;
        int hours = (int) seconds / 3600;
        int days = hours / 24;
        return days + "days and " + hours + " hours";
    }
}
