import java.util.ArrayList;


public class MessageMemento {

    private String content;
    private String timestamp;
    private User sender;
    private ArrayList<User> recipients;

    public MessageMemento(String content, String timestamp, User sender, ArrayList<User> recipients) {
        this.content = content;
        this.timestamp = timestamp;
        this.sender = sender;
        this.recipients = recipients;
    }

    public String getSavedContent() {
        return this.content;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public User getSender(){
        return this.sender;
    }

    public ArrayList<User> getRecipients(){
        return this.recipients;
    }
}
