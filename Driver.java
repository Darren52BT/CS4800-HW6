import java.util.Iterator;

public class Driver {
    public static void main(String[] args) {


            User darren = new User("darren");
            User jason = new User("jason");
            User mark = new User("THE_ONE_AND_ONLY");

            ChatServer chatServer = ChatServer.getChatServer();

            chatServer.registerUser(darren);
            chatServer.registerUser(jason);
            chatServer.registerUser(mark);



            Message darMsg = new Message(darren);
            darMsg.setContent("sup");
            darMsg.addRecipient(jason);
            darMsg.addRecipient(mark);
            darren.sendMessage(darMsg);

            Message markMsg = new Message(mark);
            markMsg.setContent("I hate you specifically");
            markMsg.addRecipient(darren);
            mark.sendMessage(markMsg);

            darMsg.setContent("yall wanna hang out");
            darren.sendMessage(darMsg);

            darren.blockUser(mark);

            mark.undoLastMessage();
            markMsg.setContent("hey man sorry my dog jumped on my keyboard and specifically hit those keys am i still invited");
            mark.sendMessage(markMsg);

            System.out.println();
            darren.viewChatHistoryFromUser(mark);
            System.out.println();
            //darren should not be able to see mark's first message since he undid it, and since mark was blocked darren doesn't see his second
            mark.viewChatHistoryFromUser(darren);

            //using iterator to view chat history

            System.out.println();
            Iterator chatHistoryIterator = mark.iterator(darren);
            while (chatHistoryIterator.hasNext()){
                    MessageMemento memento = (MessageMemento) chatHistoryIterator.next();
                    System.out.println("From " + memento.getSender().getUsername() + " to " + mark.getUsername() + " at " + memento.getTimestamp() + ": " + memento.getSavedContent());
            }







    }

}
