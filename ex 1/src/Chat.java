import java.util.ArrayList;
import java.util.List;

abstract class Chat {

    protected static boolean chat_status;
    protected ChatType chat_type;
    public List<Message> messageList;

    public Chat(ChatType chat_type) {
        this.chat_type = chat_type;
        this.messageList = new ArrayList<>();
    }

    public ChatType getChat_type() {
        return chat_type;
    }

    public void setChat_type(ChatType chat_type) {
        this.chat_type = chat_type;
    }

    public List<Message> getMessageList() {
        return messageList;
    }


    public abstract void receiveMessage(Message message);
    public abstract void sentERequest(String email);
    public abstract void sentMRequest(String telephone);


}
