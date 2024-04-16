import java.util.ArrayList;
import java.util.Iterator;

public class User implements IterableByUser {

    private String username;


    private ChatHistory chatHistory;

    public User(String username) {
        this.username = username;
        this.chatHistory = new ChatHistory();
    }

    public String getUsername() {
        return this.username;
    }

    public ChatHistory getChatHistory() {
        return this.chatHistory;
    }


    public void blockUser(User user) {
        ChatServer.getChatServer().blockUser(this, user);
    }


    public void sendMessage(Message message) {
        message.saveMessage();
        this.chatHistory.addMessage(message);
        ChatServer.getChatServer().sendMessage(this, message);
    }

    public void receiveMessage(Message message) {
        this.chatHistory.addMessage(message);
        MessageMemento memento = message.getMessageMemento();
        System.out.println("From " + memento.getSender().getUsername() + " to " + this.username + " at " + memento.getTimestamp() + ": " + memento.getSavedContent());
    }

    public String getLastMessageSent() {
        MessageMemento memento = this.chatHistory.getLastMessage(this);
        return "From " + memento.getSender().getUsername() + " to " + this.username + " at " + memento.getTimestamp() + ": " + memento.getSavedContent();
    }

    public void undoLastMessage() {

        ChatServer.getChatServer().removeLastMessage(this);
    }

    public void viewChatHistoryFromUser(User user) {
        ArrayList<MessageMemento> filteredChatHistory = this.chatHistory.getFilteredChatHistoryFromUser(user);
        for (MessageMemento memento : filteredChatHistory) {
            System.out.println("From " + memento.getSender().getUsername() + " to " + this.username + " at " + memento.getTimestamp() + ": " + memento.getSavedContent());
        }
    }


    @Override
    public Iterator iterator(User userToSearchWith) {
        return this.chatHistory.iterator(userToSearchWith);
    }
}
