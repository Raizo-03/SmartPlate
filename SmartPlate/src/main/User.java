package main;

public class User {
    private String username;
    private String additionalInfo;
    public User(String username, String additionalInfo) {
        this.username = username;
        this.additionalInfo = additionalInfo;
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }
}
