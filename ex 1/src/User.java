import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class User {
    // Attributes
    public String username;
    private  String password;
    protected String nom;
    protected String prenom;
    protected Date dateDeNaissance;
    public String email;
    public Status status;
    public String telephone;

    // Flags
    public boolean hasAccount;
    public boolean isLoggedin;
    private static List<String> blockedUsers;
    public static List<Chat> activeChats;
    public static User currentUser;

    // Constructor
    public User(String username, String password, Status status) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.hasAccount = false;
        this.isLoggedin = false;
        System.out.println("Welcome:" + username);
    }

    // Create account
    public void createAccount(String nom, String prenom, Date date, String email, String telephone){
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = date;
        this.email = email;
        this.telephone = telephone;
        this.activeChats = new ArrayList<>();
        this.blockedUsers = new ArrayList<>();
        this.hasAccount = true;
        System.out.println("Account completed successfully for:" + username);
    }

    // Getter and setter methods for password, status, username, and telephone
    public String getPassword(){
        return password;
    }
    public void setPassword(String newPassword){
        this.password  = newPassword;
    }
    public Status getStatus(){
        return status;
    }
    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }
    public String getUsername() {
        return username;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // Login and logout methods
    public void login(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password)) {
            System.out.println("Login succesfull:" + username);
            this.isLoggedin = true;
            currentUser = getUserByUsername(username);
            this.status = Status.AVAILABLE;
        }
    }

    public void logout() {
        if (isLoggedin) {
            System.out.println("Logout succefull for:" + username);
            this. currentUser = null;
            this.isLoggedin = false;
        }
        else
            System.out.println("Please login");
    }

    // Chat methods
    public Chat addChat(Chat_Individual individual_chat){
        if(isLoggedin) {
            System.out.println("Chat Partner:");
            Scanner scanner= new Scanner(System.in);
            String individualUsername = scanner.nextLine();

            if (isUserBlocked(getUserByUsername(individualUsername).getUsername())){
                System.out.println("Unblock the user");
                return null;
            }
            individual_chat.setChat_type(ChatType.INDIVIDUAL);
            individual_chat.setPartenerUsername(individualUsername);
            individual_chat.setUser(getUserByUsername(individualUsername));
            activeChats.add(individual_chat);
            System.out.println("Chat was added:" + individual_chat + "Chat created for:"+ this + "and" + individualUsername);
            return individual_chat;
        }
        else {
            System.out.println("Please Login");
            return null;
        }
    }

    // Create group chat
    public Chat createGroupChat(Groupe_Chat group_chat) {
        if(isLoggedin) {
            System.out.println("groupe name:");
            Scanner scanner = new Scanner(System.in);
            String groupName = scanner.nextLine();
            List<User> members = new ArrayList<>();

            System.out.println("Enter usernames for group members. Type 'end' to finish.");
            while (true) {
                System.out.print("Enter username (or 'end' to finish): ");
                String memberUsername = scanner.nextLine();

                if (memberUsername.equalsIgnoreCase("end")) {
                    break;
                }
                members.add(getUserByUsername(memberUsername));
            }

            if (members.isEmpty()) {
                System.out.println("Error: Group chat not created. At least one member is required.");
                return null;
            }
            members.add(this);
            for (User member : members) {
                if (isUserBlocked(member.getUsername())) {
                    System.out.println("Group chat not created. Member '" + member.getUsername() + "' is blocked.");
                    return null;
                }
            }
            group_chat.setChat_type(ChatType.GROUP);
            group_chat.setGroupeNom(groupName);
            group_chat.setMembers(members);
            activeChats.add(group_chat);
            System.out.println("Group chat created with name: " + groupName);
            return group_chat;
        }
        else{
            System.out.println("Please Login");
        return null;
        }
    }

    // Block user
    public void blockUser(String username) {
        if(isLoggedin) {
            blockedUsers.add(username);

            try {
                PrintWriter writer = new PrintWriter(this.username + "_blocked.txt");
                for (String blockedUser : blockedUsers) {
                    writer.println(blockedUser);
                }
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
            System.out.println("Please Login");
    }

    // Check if user is blocked
    static boolean isUserBlocked(String username) {
        return blockedUsers.contains(username);
    }

    // Get user by username
    public static User getUserByUsername(String username) {
        for (User user : Main.userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    // Get active chats list
    public List<Chat> getActiveChatsList() {
        List<Chat> activeChatsWithStatusTrue = new ArrayList<>();

        for (Chat chat : activeChats) {
            if (chat.chat_status == true) {
                activeChatsWithStatusTrue.add(chat);
            }
        }

        System.out.println(activeChatsWithStatusTrue);
        return activeChatsWithStatusTrue;
    }

    // Search messages
    public void searchMessages(String context) {
        boolean found = false; // Flag to check if any messages were found

        for (Chat chat : activeChats) {
            List<Message> chatMessages = chat.getMessageList();
            for (Message message : chatMessages) {
                if (context.equals(message.getContent())) {
                    found = true;
                    System.out.println("Message: " + context + " has been found in chat: " + chat);
                }
            }
        }

        if (!found) {
            System.out.println("No messages found for the context: " + context);
        }

    }
}
