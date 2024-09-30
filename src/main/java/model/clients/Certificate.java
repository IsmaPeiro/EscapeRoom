package model.clients;

import model.rooms.Room;

public class Certificate {
    private Client client;
    private Room room;
    private int points;
    
    public Certificate(Client client, Room room, int points) {
        this.client = client;
        this.room = room;
        this.points = points;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public Room getRoom() {
        return room;
    }
    
    public void setRoom(Room room) {
        this.room = room;
    }
    
    public int getPoints() {
        return points;
    }
    
    public void setPoints(int points) {
        this.points = points;
    }
    
    @Override
    public String toString() {
        return "Certificate{" +
                "client=" + client +
                ", room=" + room +
                ", points=" + points +
                '}';
    }
}

