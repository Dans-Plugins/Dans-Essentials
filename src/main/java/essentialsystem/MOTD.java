package essentialsystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MOTD {

    private String message;

    public MOTD() {
        message = null;
    }

    public void setMessage(String newMessage) {
        message = newMessage;
    }

    public String getMessage() {
        return message;
    }

    public boolean isMessageSet() {
        return (message != null);
    }

    public void save() {
        try {
            File saveFolder = new File("./plugins/Medieval-Essentials/");
            if (!saveFolder.exists()) {
                saveFolder.mkdir();
            }
            File saveFile = new File("./plugins/Medieval-Essentials/MessageOfTheDay.txt");
            if (saveFile.createNewFile()) {
                System.out.println("Save file for message of the day created.");
            } else {
                System.out.println("Save file for message of the day already exists. Altering.");
            }

            FileWriter saveWriter = new FileWriter("./plugins/Medieval-Essentials/" + "MessageOfTheDay.txt");

            // actual saving takes place here
            saveWriter.write(message + "\n");


            saveWriter.close();

            System.out.println("Successfully saved message of the day.");

        } catch (IOException e) {
            System.out.println("An error occurred saving message of the day");
            e.printStackTrace();
        }
    }

    public void load() {

    }

}
