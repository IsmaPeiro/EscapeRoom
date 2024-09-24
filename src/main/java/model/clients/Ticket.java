package model.clients;

import model.rooms.Room;

public class Ticket {
    private int id;
    private Client client;
    private float value;
    private Room room;
    
    public Ticket(Client client, float value, Room room) {
        this.client = client;
        this.value = value;
        this.room = room;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public float getValue() {
        return value;
    }
    
    public void setValue(float value) {
        this.value = value;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", client=" + client +
                ", value=" + value +
                ", room=" + room +
                '}';
    }
}
