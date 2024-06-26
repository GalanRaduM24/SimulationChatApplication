import java.util.Date;

public class Message {
    private MessageType message_type;
    private String content;
    private Date date;

    public Message(MessageType message_type, String content) {
        this.message_type = message_type;
        this.content = content;
        this.date = new Date();
    }

    // Getter and setter for message type
    public MessageType getMessage_type(){
        return message_type;
    }

    public void setMessage_type(MessageType type){
        this.message_type = type;
    }

    // Method to display message details
    public void Display(){
        System.out.println("Message Type: " + message_type);
        System.out.println("Content: " + content);
        System.out.println("Send Date: " + date);
    }

    // Getter for content
    public String getContent() {
        return content;
    }

}
