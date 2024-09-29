package observer;

import model.rooms.Room;

public class RoomObserver {

    public RoomObserver() {
    }

    public void update(Room room){
        System.out.println("A new room with "+ room.getThematic()
                +"has been added!");

    }
}
