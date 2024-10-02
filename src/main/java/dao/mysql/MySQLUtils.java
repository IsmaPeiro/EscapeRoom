package dao.mysql;

import dao.DAOException;
import factory.*;
import model.escape_room.Thematic;

import java.sql.*;

public class MySQLUtils {
    public static void close (PreparedStatement stat) throws DAOException {
        if (stat!=null) {
            try {
                stat.close();
            } catch (SQLException e) {
                throw new DAOException("Error en SQL", e);
            }
        }
    }
    
    public static void close (ResultSet set) throws DAOException {
        if (set!=null) {
            try {
                set.close();
            } catch (SQLException e) {
                throw new DAOException("Error en SQL", e);
            }
        }
    }
    
    public static RoomAbstractFactory selectFactroy(Thematic thematic) {
        switch (thematic) {
            case FANTASTIC -> {
                return new FantasticRoomFactory();
            }
            case MEDIEVAL -> {
                return new MedievalRoomFactory();
            }
            
            case SCIFI -> {
                return new SciFiRoomFactory();
            }
            
            case TERROR -> {
                return new TerrorRoomFactory();
            }
        }
        return null;
    }
    
    public static Connection getConn (String database) throws SQLException {
        
        Connection conn;
        String jdbc = "jdbc:mysql://localhost:3306/"+database;
        conn = DriverManager.getConnection(jdbc, "root", "Obokaman1976.");
        return conn;
    }
    
    public static void closeConn (Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
