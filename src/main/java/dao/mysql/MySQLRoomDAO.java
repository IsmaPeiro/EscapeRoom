package dao.mysql;

import dao.DAOException;
import dao.RoomDAO;
import factory.*;
import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;
import model.rooms.Difficulty;
import model.rooms.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLRoomDAO implements RoomDAO {
    
    final String INSERT = "INSERT INTO rooms (thematic, name, difficulty) " +
            "VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE rooms SET thematic = ?, name = ?, difficulty = ? " +
            "WHERE idrooms = ?";
    final String DELETE = "DELETE FROM rooms WHERE idrooms = ?";
    final String GETALL = "SELECT * FROM rooms";
    final String GETONE = "SELECT idrooms, thematic, name, difficulty FROM rooms " +
            "WHERE idrooms = ?";
    
    private Connection conn;
    
    public MySQLRoomDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void create(Room room) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, room.getThematic().toString());
            stat.setString(2, room.getName());
            stat.setString(3, room.getDifficulty().toString());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been saved");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                room.setId(rs.getInt(1));
            } else {
                throw new DAOException("This ID cannot be assigned to this room.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
    }
    
    private Room convert(ResultSet rs) throws SQLException, DAOException {
        Thematic thematic = Thematic.valueOf(rs.getString("thematic"));
        String name = rs.getString("name");
        Difficulty difficulty = Difficulty.valueOf(rs.getString("difficulty"));
        
        MySQLClueDAO mySQLClueDAO=new MySQLClueDAO(conn);
        List<Clue> clues=mySQLClueDAO.readAllIdRoom(rs.getInt("idrooms"));
        
        MySQLDecorationDAO mySQLDecorationDAO=new MySQLDecorationDAO(conn);
        List<Decoration> decorations=mySQLDecorationDAO.readAllIdRoom(rs.getInt("idrooms"));
        
        RoomAbstractFactory raf = MySQLUtils.selectFactroy(thematic);
        Room room = raf.createRoom(name, difficulty, clues, decorations);
        room.setId(rs.getInt("idrooms"));
        return room;
    }
    
    @Override
    public Room readOne(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Room room = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                room = convert(rs);
            } else {
                throw new DAOException("Record not found.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return room;
    }
    
    @Override
    public List<Room> readAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Room> rooms = new ArrayList<>();
        
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                rooms.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return rooms;
    }
    
    @Override
    public void update(Room room) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(UPDATE);
            
            stat.setString(1, room.getThematic().toString());
            stat.setString(2, room.getName());
            stat.setString(3, room.getDifficulty().toString());
            stat.setInt(4, room.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been modified");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
        }
    }
    
    @Override
    public void delete(Room a) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, a.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been deleted");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
        }
    }
}

