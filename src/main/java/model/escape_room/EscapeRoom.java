package model.escape_room;

import dao.DAOException;
import dao.RoomDAO;
import dao.mysql.MySQLRoomDAO;
import model.rooms.Room;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EscapeRoom {
    private List<Room> rooms=new ArrayList<>();
    
    public void addRoom (Room room) throws SQLException, DAOException {
        //rooms.add(room);
        
        Connection conn = null;
        String jdbc = "jdbc:mysql://localhost:3306/mydb";
        
        
        try {
            conn = DriverManager.getConnection(jdbc, "root", "Obokaman1976.");
            RoomDAO dao = new MySQLRoomDAO(conn);
            dao.create(room);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public void showAllRooms () throws SQLException {
       // rooms.forEach(System.out::println);
        Connection conn = null;
        String jdbc = "jdbc:mysql://localhost:3306/mydb";
        
        
        try {
            conn = DriverManager.getConnection(jdbc, "root", "Obokaman1976.");
            RoomDAO dao = new MySQLRoomDAO(conn);
            List<Room> rooms = dao.readAll();
            rooms.forEach(System.out::println);
        } catch (DAOException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
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
