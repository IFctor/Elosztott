package elso.model;

public class User {
    String userName;

    public User(String userName) {
        this.userName = userName != null ? userName : "";
    }

    public String getUserName() {
        return userName;
    }
}
