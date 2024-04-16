import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ChatHistoryTest {

    ChatHistory chatHistory;
    User user1;
    User user2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        chatHistory = new ChatHistory();
        user1 = new User("user 1");
        user2 = new User("user 2");
    }

    @org.junit.jupiter.api.Test
    void addMessage() {
        Message message = new Message(user1);
        message.setContent("hello");
        chatHistory.addMessage(message);
        assertEquals(message.getMessageMemento(), chatHistory.getMessageHistory().getLast());
    }

    @org.junit.jupiter.api.Test
    void removeLastMessage() {
        Message message = new Message(user1);
        message.setContent("hello");
        chatHistory.addMessage(message);
        message.setContent("goodbye");
        chatHistory.addMessage(message);
        chatHistory.removeLastMessage(user1);

        assertEquals("hello", chatHistory.getMessageHistory().getFirst().getSavedContent());
    }

    @org.junit.jupiter.api.Test
    void getLastMessage() {
        Message message = new Message(user1);
        message.setContent("hello");
        chatHistory.addMessage(message);
        message.setContent("goodbye");
        chatHistory.addMessage(message);
        assertEquals("goodbye", chatHistory.getMessageHistory().getLast().getSavedContent());

    }


    @org.junit.jupiter.api.Test
    void getFilteredChatHistoryFromUser() {
        Message message = new Message(user1);
        message.setContent("hello");
        chatHistory.addMessage(message);
        message.setContent("goodbye");
        chatHistory.addMessage(message);
        assertEquals("goodbye", chatHistory.getMessageHistory().getLast().getSavedContent());

        Message message1 = new Message(user2);
        message1.setContent("HIIIII");

        chatHistory.getFilteredChatHistoryFromUser(user1);
        assertEquals("hello", chatHistory.getMessageHistory().getFirst().getSavedContent());
        assertEquals("goodbye", chatHistory.getMessageHistory().getLast().getSavedContent());
    }

    @org.junit.jupiter.api.Test
    void iterator() {

        Message message = new Message(user1);
        message.setContent("hello");
        chatHistory.addMessage(message);
        message.setContent("goodbye");
        chatHistory.addMessage(message);
        assertEquals("goodbye", chatHistory.getMessageHistory().getLast().getSavedContent());


        Iterator chatHistoryIterator = chatHistory.iterator(user1);
        while (chatHistoryIterator.hasNext()){
            MessageMemento memento = (MessageMemento) chatHistoryIterator.next();
            assertEquals(user1, memento.getSender());
        }
    }
}