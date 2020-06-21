package essentialsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class PlayerActivityRecord {
    private String playerName = null;
    private int logins = 0;
    private GregorianCalendar lastLogout = null;

    public void setPlayerName(String name) {
        playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setLastLogout(GregorianCalendar date) {
        lastLogout = date;
    }

    public GregorianCalendar getLastLogout() {
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
            GregorianCalendar now = new GregorianCalendar();
            double totalSeconds = (now.getTime().getTime() - lastLogout.getTime().getTime()) / 1000;
            int minutes = (int) totalSeconds/60;
            int hours = minutes / 60;
            int days = hours / 24;
            int hoursSince = hours - (days * 24);
            int minutesSince = minutes - (hours * 60) - (days * 24 * 60);
            int secondsSince = (int) totalSeconds - (minutes * 60) - (hours * 60 * 60) - (days * 24 * 60 * 60);

            return days + " days, " + hoursSince + " hours, " + minutesSince + " minutes and " + secondsSince + " seconds";
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
            saveWriter.write(lastLogout.YEAR + "\n");
            saveWriter.write(lastLogout.MONTH + "\n");
            saveWriter.write(lastLogout.DAY_OF_MONTH + "\n");
            saveWriter.write(lastLogout.HOUR_OF_DAY + "\n");
            saveWriter.write(lastLogout.MINUTE + "\n"); // minutes
            saveWriter.write(lastLogout.SECOND + "\n"); // seconds

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
            int year = 0;
            int month = 0;
            int day = 0;
            int hour = 0;
            int minute = 0;
            int second = 0;

            // actual loading
            if (loadReader.hasNextLine()) {
                playerName = loadReader.nextLine();
            }
            if (loadReader.hasNextLine()) {
                logins = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                year = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                month = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                day = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                hour = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                minute = Integer.parseInt(loadReader.nextLine());
            }
            if (loadReader.hasNextLine()) {
                second = Integer.parseInt(loadReader.nextLine());
            }

            GregorianCalendar myCal = new GregorianCalendar(year, month, day);
            myCal.set(Calendar.HOUR, hour);
            myCal.set(Calendar.MINUTE, minute);
            myCal.set(Calendar.SECOND, second);

            lastLogout = myCal;

            loadReader.close();
            System.out.println(filename + " successfully loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred loading " + filename + ".");
            e.printStackTrace();
        }
    }
}
