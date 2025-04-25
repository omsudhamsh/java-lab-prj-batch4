package model;

public abstract class User {
    protected String username;
    protected String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public abstract void showMenu();

    public String getUsername() {
        return username;
    }
}
