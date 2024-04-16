import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Message {
    private String content;
    private String timestamp;
    private User sender;
    private ArrayList<User> recipients;
    private MessageMemento memento;
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Message(User sender) {
        this.content = "";
        this.sender = sender;
        recipients = new ArrayList<User>();
        memento = null;
    }


    public void setContent(String content) {
        this.content = content;
    }



    public void addRecipient(User recipient) {
        this.recipients.add(recipient);
    }

    public void removeRecipient(User recipient) {
        this.recipients.remove(recipient);
    }

    public void saveMessage() {
        this.timestamp = dateFormatter.format(LocalDateTime.now());
        this.memento = new MessageMemento(this.content, this.timestamp, this.sender, this.recipients);
    }


    public MessageMemento getMessageMemento() {
        return this.memento;
    }


}
