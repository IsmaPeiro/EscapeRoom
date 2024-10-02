package dao.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingDB {
    public void createSchema(String name) throws SQLException {
        Connection conexion = null;
        String jdbc = "jdbc:mysql://localhost:3306";
        conexion = DriverManager.getConnection(jdbc, "root", "Obokaman1976.");
        
        String createDbQuery = "CREATE DATABASE IF NOT EXISTS " + name;
        PreparedStatement createDbStatement = conexion.prepareStatement(createDbQuery);
        createDbStatement.executeUpdate();
        createDbStatement.close();
        
        String useDbQuery = "USE " + name;
        PreparedStatement useDbStatement = conexion.prepareStatement(useDbQuery);
        useDbStatement.execute();
        useDbStatement.close();
        
        String[] dropTables = {
                "DROP TABLE IF EXISTS tickets",
                "DROP TABLE IF EXISTS decorations",
                "DROP TABLE IF EXISTS clues",
                "DROP TABLE IF EXISTS clients",
                "DROP TABLE IF EXISTS rooms"
        };
        
        for (String dropQuery : dropTables) {
            PreparedStatement dropStatement = conexion.prepareStatement(dropQuery);
            dropStatement.execute();
            dropStatement.close();
        }
        
        String createRoomsTable = """
                    CREATE TABLE rooms (
                      idrooms int NOT NULL AUTO_INCREMENT,
                      thematic varchar(45) NOT NULL,
                      name varchar(45) NOT NULL,
                      difficulty varchar(45) NOT NULL,
                      PRIMARY KEY (idrooms)
                    )
                """;
        PreparedStatement createRoomsStatement = conexion.prepareStatement(createRoomsTable);
        createRoomsStatement.execute();
        createRoomsStatement.close();
        
        String createClientsTable = """
                    CREATE TABLE clients (
                      idclients int NOT NULL AUTO_INCREMENT,
                      name varchar(45) NOT NULL,
                      surname varchar(45) NOT NULL,
                      subscribed tinyint NOT NULL,
                      PRIMARY KEY (idclients)
                    )
                """;
        PreparedStatement createClientsStatement = conexion.prepareStatement(createClientsTable);
        createClientsStatement.execute();
        createClientsStatement.close();
        
        String createCluesTable = """
                    CREATE TABLE clues (
                      idclues int NOT NULL AUTO_INCREMENT,
                      thematic varchar(45) NOT NULL,
                      value float NOT NULL,
                      idroom int DEFAULT NULL,
                      PRIMARY KEY (idclues),
                      KEY fk_clues_rooms1_idx (idroom),
                      CONSTRAINT fk_clues_rooms1 FOREIGN KEY (idroom) REFERENCES rooms (idrooms) ON DELETE SET NULL ON UPDATE RESTRICT
                    )
                """;
        PreparedStatement createCluesStatement = conexion.prepareStatement(createCluesTable);
        createCluesStatement.execute();
        createCluesStatement.close();
        
        String createDecorationsTable = """
                    CREATE TABLE decorations (
                      iddecorations int NOT NULL AUTO_INCREMENT,
                      name varchar(45) NOT NULL,
                      thematic varchar(45) NOT NULL,
                      material varchar(45) NOT NULL,
                      value float NOT NULL,
                      idroom int DEFAULT NULL,
                      PRIMARY KEY (iddecorations),
                      KEY fk_decoration_rooms1_idx (idroom),
                      CONSTRAINT fk_decoration_rooms1 FOREIGN KEY (idroom) REFERENCES rooms (idrooms) ON DELETE SET NULL
                    )
                """;
        PreparedStatement createDecorationsStatement = conexion.prepareStatement(createDecorationsTable);
        createDecorationsStatement.execute();
        createDecorationsStatement.close();
        
        String createTicketsTable = """
                    CREATE TABLE tickets (
                      idtickets int NOT NULL AUTO_INCREMENT,
                      idclient int NOT NULL,
                      value float NOT NULL,
                      idroom int DEFAULT NULL,
                      PRIMARY KEY (idtickets),
                      KEY fk_tickets_rooms1_idx (idroom),
                      KEY fk_tickets_clients1_idx (idclient),
                      CONSTRAINT fk_tickets_clients1 FOREIGN KEY (idclient) REFERENCES clients (idclients),
                      CONSTRAINT fk_tickets_rooms1 FOREIGN KEY (idroom) REFERENCES rooms (idrooms) ON DELETE SET NULL ON UPDATE SET NULL
                    )
                """;
        PreparedStatement createTicketsStatement = conexion.prepareStatement(createTicketsTable);
        createTicketsStatement.execute();
        createTicketsStatement.close();
        
        if (conexion != null) {
            conexion.close();
        }
    }
    
    public List<String> showDB() throws SQLException {
        Connection conexion=null;
        List<String> databases=new ArrayList<>();
        String jdbc = "jdbc:mysql://localhost:3306/";
        conexion = DriverManager.getConnection(jdbc, "root", "Obokaman1976.");
        
        String query = "SHOW DATABASES";
        PreparedStatement statement = conexion.prepareStatement(query);
        ResultSet set = statement.executeQuery();
        
        while (set.next()) {
            databases.add(set.getString("Database"));
        }
        set.close();
        statement.close();
        if (conexion != null) {
            conexion.close();
        }
        return databases;
    }
}



