import java.util.ArrayList;
import java.util.Iterator;

public class ChatHistory implements IterableByUser {
    private ArrayList<MessageMemento> messageHistory;


    public ChatHistory() {
        this.messageHistory = new ArrayList<MessageMemento>();
    }

    public ArrayList<MessageMemento> getMessageHistory() {
        return messageHistory;
    }

    public void addMessage(Message message) {
        message.saveMessage();
        MessageMemento memento = message.getMessageMemento();
        this.messageHistory.add(memento);
    }


    public  MessageMemento removeLastMessage(User user) {
        for (int i = this.messageHistory.size() - 1; i >= 0; i--) {
            MessageMemento messageMemento = this.messageHistory.get(i);
            if (messageMemento.getSender() == user) {
                this.messageHistory.remove(i);
                return messageMemento;
            }
        }

        return null;
    }

    public MessageMemento getLastMessage(User user) {
        for (int i = this.messageHistory.size() - 1; i >= 0; i--) {
            MessageMemento messageMemento = this.messageHistory.get(i);
            if (messageMemento.getSender() == user) {
                return messageMemento;
            }
        }
        return null;
    }

    public void removeMessage(MessageMemento memento){
        this.messageHistory.remove(memento);
    }


    public void purgeUser(User user) {
        for (int i = 0; i < this.messageHistory.size(); i++) {
            MessageMemento memento = this.messageHistory.get(i);
            System.out.println(memento.getSender());
            System.out.println(user);
            System.out.println(memento.getSavedContent());
            if (memento.getSender() == user) {
                memento.getRecipients().remove(user);
                this.messageHistory.remove(memento);
            }
        }
    }


    public ArrayList<MessageMemento> getFilteredChatHistoryFromUser(User user) {

        ArrayList<MessageMemento> filteredChatHistory = new ArrayList<MessageMemento>();

        for (MessageMemento memento : this.messageHistory) {
            if (memento.getSender() == user) {
                filteredChatHistory.add(memento);
            }
        }
        return filteredChatHistory;
    }


    @Override
    public Iterator iterator(User userToSearchWith) {
        return new searchMessagesByUser(this, userToSearchWith);
    }
}
