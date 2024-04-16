import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatServerTest {

    ChatServer chatServer;

    @BeforeEach
    void setUp() {
        chatServer = ChatServer.getChatServer();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void registerUser() {
        chatServer.registerUser(new User("darren"));
        assertEquals(true, chatServer.getUserRegistry().containsKey("darren"));
    }

    @Test
    void blockUser() {
        User darren = new User("darren");
        User mark = new User("mark");
        chatServer.registerUser(darren);
        chatServer.registerUser(mark);
        chatServer.blockUser(mark, darren);
        assertEquals(true, chatServer.getBlockedUsersMap().get(mark).contains(darren));

    }

    @Test
    void deregisterUser() {
        User darren = new User("darren");

        chatServer.registerUser(darren);
        chatServer.deregisterUser(darren);
        assertEquals(false, chatServer.getUserRegistry().containsKey("darren"));
    }

}