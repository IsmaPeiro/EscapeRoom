package observer;

import model.clients.Client;
import model.rooms.Room;

public class RoomObserver {

    public RoomObserver() {
    }

    public void update(Room room, Client client){
        System.out.println("Message to client: "+ client.getName() +" "+client.getSurname() +
                " -> A new room with "+ room.getThematic() +" thematic has been added!");

    }
}
