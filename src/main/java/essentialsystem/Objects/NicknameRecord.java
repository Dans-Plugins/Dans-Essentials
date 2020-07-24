package essentialsystem.Objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class NicknameRecord {

    private String playerName = "";
    private String nickname = "";

    public void setPlayerName(String s) {
        playerName = s;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setNickname(String s) {
        nickname = s;
    }

    public String getNickname() {
        return nickname;
    }

    public void save() {
        try {
            File saveFolder = new File("./plugins/Medieval-Essentials/");
            if (!saveFolder.exists()) {
                saveFolder.mkdir();
            }
            File saveFolder2 = new File("./plugins/Medieval-Essentials/Nicknames/");
            if (!saveFolder2.exists()) {
                saveFolder2.mkdir();
            }
            File saveFile = new File("./plugins/Medieval-Essentials/Nicknames/" + playerName + ".txt");
            if (saveFile.createNewFile()) {
                System.out.println("Nickname Record for " +  playerName + " created.");
            } else {
                System.out.println("Nickname Record for " +  playerName + " already exists. Altering.");
            }

            FileWriter saveWriter = new FileWriter("./plugins/Medieval-Essentials/Nicknames/" + playerName + ".txt");

            // actual saving takes place here
            saveWriter.write(playerName + "\n");
            saveWriter.write(nickname + "\n");

            saveWriter.close();

            System.out.println("Successfully saved Nickname Records.");

        } catch (IOException e) {
            System.out.println("An error occurred saving a Nickname Record.");
        }
    }

    public void load(String filename) {
        try {
            File loadFile = new File("./plugins/Medieval-Essentials/Nicknames/" + filename);
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                playerName = loadReader.nextLine();
            }

            if (loadReader.hasNextLine()) {
                nickname = loadReader.nextLine();
            }

            loadReader.close();
            System.out.println(filename + " successfully loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred loading " + filename + ".");
            e.printStackTrace();
        }
    }

}
