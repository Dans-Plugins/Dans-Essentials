package essentialsystem.Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class PlayerActivityRecord {
    private String playerName = null;
    private int logins = 0;
    private ZonedDateTime lastLogout = null;

    public void setPlayerName(String name) {
        playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setLastLogout(ZonedDateTime date) {
        lastLogout = date;
    }

    public ZonedDateTime getLastLogout() {
        return lastLogout;
    }

    public void incrementLogins() {
        logins++;
    }

    public int getLogins() {
        return logins;
    }

    public String getTimeSinceLastLogout() {
        if (lastLogout != null) {
            ZonedDateTime now = ZonedDateTime.now();
            Duration duration = Duration.between(lastLogout, now);
            double totalSeconds = duration.getSeconds();
            int minutes = (int) totalSeconds/60;
            int hours = minutes / 60;
            int days = hours / 24;
            int hoursSince = hours - (days * 24);
            int minutesSince = minutes - (hours * 60) - (days * 24 * 60);

            return days + " days, " + hoursSince + " hours, and " + minutesSince + " minutes";
        }
        else {
            return null;
        }
    }

    public void save() {
        try {
            File saveFolder = new File("./plugins/Medieval-Essentials/");
            if (!saveFolder.exists()) {
                saveFolder.mkdir();
            }
            File saveFolder2 = new File("./plugins/Medieval-Essentials/Activity-Records/");
            if (!saveFolder2.exists()) {
                saveFolder2.mkdir();
            }
            File saveFile = new File("./plugins/Medieval-Essentials/Activity-Records/" + playerName + ".txt");
            if (saveFile.createNewFile()) {
                System.out.println("Activity Record for " +  playerName + " created.");
            } else {
                System.out.println("Activity Record for " +  playerName + " already exists. Altering.");
            }

            FileWriter saveWriter = new FileWriter("./plugins/Medieval-Essentials/Activity-Records/" + playerName + ".txt");

            // actual saving takes place here
            saveWriter.write(playerName + "\n");
            saveWriter.write(logins + "\n");

            // saving date of last logout
            if (lastLogout != null) {
                saveWriter.write(lastLogout.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) + "\n");
            }
            else {
                System.out.println("Error! Last logout was null!");
                return;
            }

            saveWriter.close();

            System.out.println("Successfully saved activity record.");

        } catch (IOException e) {
            System.out.println("An error occurred saving an activity record.");
            e.printStackTrace();
        }
    }

    public void load(String filename) {
        try {
            File loadFile = new File("./plugins/Medieval-Essentials/Activity-Records/" + filename);
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                playerName = loadReader.nextLine();
            }
            if (loadReader.hasNextLine()) {
                logins = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                lastLogout = ZonedDateTime.parse(loadReader.nextLine(), DateTimeFormatter.ISO_ZONED_DATE_TIME);
            }
            else {
                System.out.println("Error! Last logout not found!");
            }

            loadReader.close();
            System.out.println(filename + " successfully loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred loading " + filename + ".");
            e.printStackTrace();
        }
    }
}
