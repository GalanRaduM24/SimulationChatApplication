import java.util.ArrayList;
import java.util.List;

public class Chat_History {
    private final User sender;
    private Chat chat;
    private List<Message> messages;

    public Chat_History(User sender, Chat chat, List<Message> messages) {
        this.sender = sender;
        this.chat = chat;
        this.messages = new ArrayList<>(messages);
    }

    // Method to add a message to the history
    public void addMessage(Message message) {
        messages.add(message);
    }

    // Method to get the list of messages
    public List<Message> getMessageList() {
        System.out.println(messages +  " " +  sender + " " + chat);
        return messages;
    }
}
