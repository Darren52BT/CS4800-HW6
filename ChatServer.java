import java.util.ArrayList;
import java.util.HashMap;

public class ChatServer {
    private static ChatServer chatServer = null;

    private HashMap<String, User> userRegistry;
    private HashMap<User, ArrayList<User>> blockedUsersMap;

    private ChatServer() {
        this.userRegistry = new HashMap<String, User>();
        this.blockedUsersMap = new HashMap<User, ArrayList<User>>();
    }

    public static ChatServer getChatServer() {
        if (chatServer == null) {
            chatServer = new ChatServer();
        }
        return chatServer;
    }

    public HashMap<String, User> getUserRegistry() {
        return userRegistry;
    }

    public HashMap<User, ArrayList<User>> getBlockedUsersMap() {
        return blockedUsersMap;
    }

    public void registerUser(User user) {
        if (!userRegistry.containsKey(user.getUsername())) {
            userRegistry.put(user.getUsername(), user);
            blockedUsersMap.put(user, new ArrayList<User>());
        }
    }

    public void blockUser(User user, User userToBlock) {
        ArrayList<User> blockedUserList = blockedUsersMap.get(user);

        if (blockedUserList != null && !blockedUserList.contains(userToBlock) && user != userToBlock) {
            blockedUserList.add(userToBlock);
        }
    }

    //removes user from registry and purges this user from all chat histories
    public void deregisterUser(User user) {
        if (userRegistry.containsKey(user.getUsername())) {
            userRegistry.remove(user.getUsername());
            for (User currentUser : this.userRegistry.values()) {
                currentUser.getChatHistory().purgeUser(user);
            }
        }
    }

    public void sendMessage(User sender, Message message) {

        //send if sender isn't blocked
        for (User user : message.getMessageMemento().getRecipients()) {
            ArrayList<User> blockedUserList = blockedUsersMap.get(user);
            if (blockedUserList.contains(sender)) {
                continue;
            }
            user.receiveMessage(message);
        }

    }


    public void removeLastMessage(User user){
        MessageMemento removedMemento = user.getChatHistory().removeLastMessage(user);
        if(removedMemento != null){
            for (User recipient : removedMemento.getRecipients()){
                recipient.getChatHistory().removeMessage(removedMemento);
            }
        }
    }


}
