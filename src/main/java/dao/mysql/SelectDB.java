package dao.mysql;

import model.escape_room.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SelectDB {
    public void init() {
        
        SettingDB sdb=new SettingDB();
        
        boolean exit = false;
        
        do {
            switch (menu()) {
                case 1 -> createDB(sdb);
                case 2 -> selectDB(sdb);
                case 0 -> {
                    System.out.println("thanks.");
                    exit = true;
                }
            }
        } while (!exit);
    }
    
    public byte menu() {
        Scanner sc = new Scanner(System.in);
        byte option = -1;
        final byte MINIMUM = 0;
        final byte MAXIMUM = 17;
        
        do {
            System.out.println("\nDB MENU");
            System.out.println("1. Create new DB.");
            System.out.println("2. Select DB.");
            System.out.println("0. Exit.\n");
            if (sc.hasNextByte()) option = sc.nextByte();
            sc.nextLine();
            if (option < MINIMUM || option > MAXIMUM) {
                System.out.println("Chose a valid option.");
            }
        } while (option < MINIMUM || option > MAXIMUM);
        return option;
    }
    
    private void selectDB(SettingDB sdb) {
        List<String> databases=null;
        boolean exit=false;
        
        try {
            databases=sdb.showDB();
            databases.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        do {
            String database = Input.readString("Input the database of DB to use or 'exit':");
            if (databases.contains(database)) {
                exit=true;
                EscapeIU iu = new EscapeIU();
                iu.init(database);
            } else if (database.equalsIgnoreCase("exit")) {
                exit = true;
            } else {
                System.out.println("Incorrect database.");
            }
        } while (!exit);
    }
    
    private void createDB(SettingDB sdb) {
        
        try {
            sdb.createSchema(Input.readString("Input the name of the new Database:"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
