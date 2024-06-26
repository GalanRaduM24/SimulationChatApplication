import java.util.*;

public class Main {
    //List to store users
    public static List<User> userList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Creating users
        User user1 = new User("radu123", "1234", Status.AVAILABLE);
        User user2 = new User("andrei123", "abcd", Status.AVAILABLE);
        User user3 = new User("matei123", "qwerty", Status.OFFLINE);
        User user4 = new User("ana123", "mnbv", Status.AWAY);
        User user5 = new User("maria123", "asdf", Status.BUSY);
        User user6 = new User("ioana123", "zxcv", Status.OFFLINE);
        User user7 = new User("marius123", "0987", Status.AVAILABLE);
        System.out.println();

        //Adding users to the list
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        userList.add(user7);

        // User operations
        user1.createAccount("radu", "ion", new Date(2000-02-02), "radu@mail", "077");
        user1.login("radu123", "1234");
        user1.blockUser("andrei123");
        Settings settingsUser1 = new Settings(user1);

        // Settings operations
        settingsUser1.viewMonProfile();
        settingsUser1.setNotifications();
        settingsUser1.help();
        settingsUser1.changePassword("123456789");
        settingsUser1.changeNumber("+40777");
        settingsUser1.updateStatus(Status.OFFLINE);

        //Accessing user attributes
        user1.getPassword();
        user1.getTelephone();
        user1.getStatus();

        //Chat operations
        Chat_Individual Chat_Individual = new Chat_Individual(ChatType.INDIVIDUAL);
        user1.addChat(Chat_Individual);
        Groupe_Chat groupe_chat = new Groupe_Chat(ChatType.GROUP);
        user1.createGroupChat(groupe_chat);

        user1.getActiveChatsList();

        //Message operations
        Message message1 = new Message(MessageType.IMAGE, "imageimage");
        Message message2 = new Message(MessageType.TEXT, "textetexte");
        Message message3 = new Message(MessageType.LOCATION, "location");
        message1.Display();
        message1.setMessage_type(MessageType.FILE);
        message1.getMessage_type();
        message2.Display();

        Chat_Individual.receiveMessage(message2);

        //Chat_History operations
        Chat_History Chat_HistoryIndividuel = new Chat_History(user1, Chat_Individual,Arrays.asList(message1, message2));
        Chat_HistoryIndividuel.getMessageList();
        Chat_HistoryIndividuel.addMessage(message3);
        Chat_HistoryIndividuel.getMessageList();

        //Groupe_Chat operations
        System.out.println("addMember:");
        groupe_chat.addMembers(scanner.nextLine());
        System.out.println("removeFriend:");
        groupe_chat.removeFriend(scanner.nextLine());

        user1.searchMessages("textetexte");

        groupe_chat.sentERequest("radu@mail");
        groupe_chat.sentMRequest("+40777");

        Groupe_Chat.deleteGroupe(1);
        Groupe_Chat.leaveGroupe(1, user1);

        //User logout
        user1.logout();
    }
}