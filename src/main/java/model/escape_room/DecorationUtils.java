package model.escape_room;

import model.rooms.Room;

import java.util.Scanner;

public class DecorationUtils {
    public static void addDecoration (Room room) {
    room.createDecoration();
    }


     public static String getNameDecoration(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Write the name of the decoration you want to add:");
        String name = sc.nextLine();
        return name;
    }

    public static String getMaterialDecoration(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Write the material of the decoration:");
        String material = sc.nextLine();
        return material;
    }
     public static float getValueDecoration(){
         Scanner sc = new Scanner(System.in);

         System.out.println("Write the value of the decoration:");
         float value = sc.nextFloat();
         return value;
     }
}
