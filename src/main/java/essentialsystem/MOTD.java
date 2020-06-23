package essentialsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MOTD {

    private String message = "";

    public MOTD() {

    }

    public void setMessage(String newMessage) {
        message = newMessage;
    }

    public String getMessage() {
        return message;
    }

    public boolean isMessageSet() {
        return (!message.equalsIgnoreCase(""));
    }

    public void save() {
        try {
            File saveFolder = new File("./plugins/Medieval-Essentials/");
            if (!saveFolder.exists()) {
                saveFolder.mkdir();
            }
            File saveFile = new File("./plugins/Medieval-Essentials/MessageOfTheDay.txt");
            if (saveFile.createNewFile()) {
                System.out.println("Save file for the message of the day created.");
            } else {
                System.out.println("Save file for the message of the day already exists. Altering.");
            }

            FileWriter saveWriter = new FileWriter("./plugins/Medieval-Essentials/" + "MessageOfTheDay.txt");

            // actual saving takes place here
            saveWriter.write(message + "\n");

            saveWriter.close();

            System.out.println("Successfully saved the message of the day.");

        } catch (IOException e) {
            System.out.println("An error occurred saving the message of the day");
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            File loadFile = new File("./plugins/Medieval-Essentials/MessageOfTheDay.txt");
            Scanner loadReader = new Scanner(loadFile);

            // actual loading
            if (loadReader.hasNextLine()) {
                setMessage(loadReader.nextLine());
            }

            loadReader.close();
            System.out.println("Message of the day successfully loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred loading the message of the day.");
            e.printStackTrace();
        }
    }

}
