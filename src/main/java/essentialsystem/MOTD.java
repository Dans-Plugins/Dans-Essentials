package essentialsystem;

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

}
