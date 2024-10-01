package dao.mysql;

import dao.ClueDAO;
import dao.DAOException;
import factory.*;
import model.clues.Clue;
import model.escape_room.Thematic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLClueDAO implements ClueDAO {
    
    final String INSERT = "INSERT INTO clues (thematic, value, idroom) " +
            "VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE clues SET thematic = ?, value = ?, idroom = ? WHERE idclues = ?";
    final String DELETE = "DELETE FROM clues WHERE idclues = ?";
    final String GETALL = "SELECT * FROM clues";
    final String GETONE = "SELECT idclues, thematic, value, idroom FROM clues " +
            "WHERE idclues = ?";
    final String GETBYIDROOM = "SELECT * FROM clues WHERE idroom = ?";
    final String GETAVAILABLE = "SELECT * FROM clues WHERE thematic = ? AND idroom IS NULL";
    final String SETROOMTONULL = "UPDATE clues SET idroom = NULL WHERE idclues = ?";
    
    private Connection conn;
    
    public MySQLClueDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void create(Clue clue) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, clue.getThematic().toString());
            stat.setFloat(2, clue.getValue());
            stat.setInt(3, clue.getIdRoom());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been saved");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                clue.setId(rs.getInt(1));
            } else {
                throw new DAOException("This ID cannot be assigned to this clue.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
    }
    
    private Clue convert(ResultSet rs) throws SQLException {
        Thematic thematic = Thematic.valueOf(rs.getString("thematic"));
        int value = rs.getInt("value");
        Integer idRoom = rs.getInt("idroom");
        if (rs.wasNull()) {
            idRoom = null;
        }
        RoomAbstractFactory raf = MySQLUtils.selectFactroy(thematic);
        Clue clue = raf.createClue(value);
        clue.setIdRoom(idRoom);
        clue.setId(rs.getInt("idclues"));
        return clue;
    }
    
    @Override
    public Clue readOne(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Clue clue = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                clue = convert(rs);
            } else {
                throw new DAOException("Record not found.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return clue;
    }
    
    @Override
    public List<Clue> readAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Clue> clues = new ArrayList<>();
        
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                clues.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return clues;
    }
    
    @Override
    public List<Clue> readAllIdRoom(Integer idRoom) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Clue> clues = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETBYIDROOM);
            stat.setInt(1, idRoom);
            rs = stat.executeQuery();
            while (rs.next()) {
                clues.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return clues;
    }
    
    @Override
    public List<Clue> readAvaiable(Thematic thematic) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Clue> clues = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETAVAILABLE);
            stat.setString(1, thematic.toString());
            rs = stat.executeQuery();
            while (rs.next()) {
                clues.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return clues;
    }
    
    @Override
    public void setRommToNull(Clue clue) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(SETROOMTONULL);
            
            stat.setInt(1, clue.getId());
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
    public void update(Clue clue) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(UPDATE);
            
            stat.setString(1, clue.getThematic().toString());
            stat.setFloat(2, clue.getValue());
            stat.setInt(3, clue.getIdRoom());
            stat.setInt(4, clue.getId());
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
    public void delete(Clue clue) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, clue.getId());
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

