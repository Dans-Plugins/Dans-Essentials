package dansplugins.dansessentials;

public class ArgumentParser {

    private static ArgumentParser instance;

    private ArgumentParser() {

    }

    public static ArgumentParser getInstance() {
        if (instance == null) {
            instance = new ArgumentParser();
        }
        return instance;
    }

    public String createStringFromArgs(int start, int end, String[] args) {
        String toReturn = "";
        for (int i = start; i < end; i++) {
            toReturn = toReturn + args[i];
            if (i < end - 1) {
                toReturn = toReturn + " ";
            }
        }
        return toReturn;
    }
}
