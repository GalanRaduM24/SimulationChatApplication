public class Settings {
    private User user;

    private boolean notifications;

    public Settings(User user) {
        this.user = user;
        this.notifications = false;
    }

    // Method to view profile
    public void viewMonProfile(){
        System.out.println("View profile of:" + user.username);
        user.getUsername();
        user.getStatus();
        user.getPassword();
    }

    // Method to set notifications
    public boolean setNotifications(){
        this.notifications = true;
        System.out.println("Notification has been set to true");
        return notifications;
    }

    // Method to display help
    public void help(){
        System.out.println("Displaying help for " + user.username);
    }

    // Method to change password
    public void changePassword(String password){
        System.out.println("Changing password from: " + user.getPassword() + "to : " + password);
        user.setPassword(password);
    }

    // Method to change phone number
    public void changeNumber(String telephone){
        System.out.println("Changing number from: " + user.telephone + "to : " + telephone);
        user.setTelephone(telephone);
    }

    // Method to update status
    public void updateStatus(Status status){
        System.out.println("Updating status for " + user.username + " to " + status);
        user.setStatus(status);
    }

}
