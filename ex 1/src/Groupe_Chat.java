import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Groupe_Chat extends Chat{
    private static int lastGroupeId = 0;
    private int groupe_id;
    private String groupeNom;
    private  List<User> members;

    public Groupe_Chat(ChatType chat_type) {
        super(chat_type);
        this.groupe_id = ++lastGroupeId;
        connectSession();
    }

    // Setters and getters for group name and members
    public void setGroupeNom(String groupeNom) {
        this.groupeNom = groupeNom;
    }

    public String getGroupeNom() {
        return groupeNom;
    }

    public void setMembers(List<User> members) {
        this.members = new ArrayList<>(members);
    }

    // Method to add a member to the group
    public void addMembers(String username){
        members.add(User.getUserByUsername(username));
    }

    // Method to remove a friend from the group
    public void removeFriend(String username){
        members.remove(User.getUserByUsername(username));
    }

    @Override
    public void receiveMessage(Message message) {
        messageList.add(message);
    }

    @Override
    public void sentERequest(String email) {
        System.out.println("Mail Request sent");
    }

    @Override
    public void sentMRequest(String telephone) {
        System.out.println("Telephone Request sent");
    }
    public void connectSession() {
        chat_status = true;
        System.out.println("Chat session connected.");
    }

    // Method for a user to leave the group
    public static void leaveGroupe(int groupe_id, User user) {
        Groupe_Chat groupToLeave = findGroupById(groupe_id);
        if (groupToLeave != null) {
            groupToLeave.members.remove(user);
            System.out.println("User '" + user.getUsername() + "' left the group.");
        } else {
            System.out.println("Group not found with ID: " + groupe_id);
        }
    }

    // Method to delete a group
    public static void deleteGroupe(int groupe_id) {
        Groupe_Chat groupToDelete = findGroupById(groupe_id);
        if (groupToDelete != null) {
            User.activeChats.remove(groupToDelete);
            System.out.println("Group deleted with ID: " + groupe_id);
        } else {
            System.out.println("Group not found with ID: " + groupe_id);
        }
    }

    // Find group by ID
    private static Groupe_Chat findGroupById(int groupe_id) {
        for (Chat chat : User.activeChats) {
            if (chat instanceof Groupe_Chat) {
                Groupe_Chat groupChat = (Groupe_Chat) chat;
                if (groupChat.groupe_id == groupe_id) {
                    return groupChat;
                }
            }
        }
        return null;
    }


}
