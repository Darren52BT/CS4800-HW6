import java.util.ArrayList;
import java.util.Iterator;

public class searchMessagesByUser implements Iterator {

    private int indexOfChatHistory;
    private int chatHistorySize;
    private ArrayList<MessageMemento> chatHistoryList;


    public searchMessagesByUser(ChatHistory chatHistory, User userToSearchWith) {
        this.chatHistoryList = chatHistory.getFilteredChatHistoryFromUser(userToSearchWith);
        this.indexOfChatHistory = 0;
        this.chatHistorySize = this.chatHistoryList.size();
    }

    @Override
    public boolean hasNext() {
        return this.indexOfChatHistory < this.chatHistorySize && this.chatHistoryList.get(indexOfChatHistory) != null;
    }

    @Override
    public MessageMemento next() {
        if (this.hasNext()) {
            return this.chatHistoryList.get(indexOfChatHistory++);
        }
        return null;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
