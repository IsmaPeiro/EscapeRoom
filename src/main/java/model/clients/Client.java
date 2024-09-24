package model.clients;

public class Client {
    private int id;
    private String name;
    private String surname;
    private boolean subscribed;
    
    public Client(String name, String surname, boolean subscribed) {
        this.name = name;
        this.surname = surname;
        this.subscribed = subscribed;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public boolean isSubscribed() {
        return subscribed;
    }
    
    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
    
    @Override
    public String toString() {
        return "Client id: " + id + "\n" +
                "name: " + name + "\n" +
                "surname: " + surname + "\n" +
                "subscribed: " + subscribed + "\n";
    }
}
