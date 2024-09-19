package model.escape_room;

import model.rooms.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EscapeRoom {
    private List<Room> rooms=new ArrayList<>();
    
    public void addRoom (Room room) {
        rooms.add(room);
    }
    
    public void showAllRooms () {
        rooms.forEach(System.out::println);
    }
    
    public void addClueToRoom () {
        Room room=searchRoom();
        ClueUtils.addClue (room);
    }
    
    public void addDecorationToRoom () {
        Room room=searchRoom();
        DecorationUtils.addDecoration (room);
    }
    
    private Room searchRoom () {
        int id; Room room=null;
        boolean found=false;
        int i=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Id:");
        id=sc.nextInt();
        room = rooms.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
        return room;
    }
    
}
