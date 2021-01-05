package dansplugins.essentialsystem;

import dansplugins.essentialsystem.Objects.NicknameRecord;
import dansplugins.essentialsystem.Objects.PlayerActivityRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StorageManager {

    private static StorageManager instance;

    private StorageManager() {

    }

    public static StorageManager getInstance() {
        if (instance == null) {
            instance = new StorageManager();
        }
        return instance;
    }

    public void save() {
        PersistentData.getInstance().getMotd().save();
        saveActivityRecords();
        saveActivityRecordFilenames();
        saveNicknames();
        saveNicknameFilenames();
    }

    public void load() {
        PersistentData.getInstance().getMotd().load();
        loadActivityRecords();
        loadNicknames();
    }

    public void saveActivityRecords() {
        for (PlayerActivityRecord record : PersistentData.getInstance().getActivityRecords()) {
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
//                System.out.println("Save file for activity record filenames created.");
            } else {
//                System.out.println("Save file for activity record filenames already exists. Overwriting.");
            }

            FileWriter saveWriter = new FileWriter(saveFile);

            // actual saving takes place here
            for (PlayerActivityRecord record : PersistentData.getInstance().getActivityRecords()) {
                saveWriter.write(record.getPlayerName() + ".txt" + "\n");
            }

            saveWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred while saving activity record filenames.");
        }
    }

    public void saveNicknames() {
        for (NicknameRecord record : PersistentData.getInstance().getNicknames()) {
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
//                System.out.println("Save file for nickname record filenames created.");
            } else {
//                System.out.println("Save file for nickname record filenames already exists. Overwriting.");
            }

            FileWriter saveWriter = new FileWriter(saveFile);

            // actual saving takes place here
            for (NicknameRecord record : PersistentData.getInstance().getNicknames()) {
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
                for (int i = 0; i < PersistentData.getInstance().getActivityRecords().size(); i++) {
                    if (PersistentData.getInstance().getActivityRecords().get(i).getPlayerName().equalsIgnoreCase(temp.getPlayerName())) {
                        PersistentData.getInstance().getActivityRecords().remove(i);
                    }
                }

                PersistentData.getInstance().getActivityRecords().add(temp);

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
                for (int i = 0; i < PersistentData.getInstance().getNicknames().size(); i++) {
                    if (PersistentData.getInstance().getNicknames().get(i).getPlayerName().equalsIgnoreCase(temp.getPlayerName())) {
                        PersistentData.getInstance().getNicknames().remove(i);
                    }
                }

                PersistentData.getInstance().getNicknames().add(temp);

            }

            loadReader.close();
            System.out.println("Nickname records successfully loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Error loading the nickname records!");
        }
    }
}
