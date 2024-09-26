package dao.mysql;

import dao.DAOException;
import dao.DecorationDAO;
import factory.*;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDecorationDAO implements DecorationDAO {
    
    final String INSERT = "INSERT INTO decorations (name, thematic, material, value, idroom) " +
            "VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE decorations SET name = ?, thematic = ?, material = ?, value = ?, idroom = ? WHERE iddecorations = ?";
    final String DELETE = "DELETE FROM decorations WHERE iddecorations = ?";
    final String GETALL = "SELECT * FROM decorations";
    final String GETONE = "SELECT iddecorations, name, thematic, material, value, idroom FROM decorations " +
            "WHERE iddecorations = ?";
    final String GETBYIDROOM = "SELECT * FROM decorations WHERE idroom = ?";
    final String GETAVAILABLE = "SELECT * FROM decorations WHERE thematic = ? AND idroom IS NULL";
    final String SETROOMTONULL = "UPDATE decorations SET idroom = NULL WHERE iddecorations = ?";
    
    private Connection conn;
    
    public MySQLDecorationDAO(Connection conn) {
        this.conn = conn;
    }
    
    @Override
    public void create(Decoration decoration) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        
        try {
            stat = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stat.setString(1, decoration.getName());
            stat.setString(2, decoration.getThematic().toString());
            stat.setString(3, decoration.getMaterial());
            stat.setFloat(4, decoration.getValue());
            stat.setInt(5, decoration.getIdRoom());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("It may not have been saved");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                decoration.setId(rs.getInt(1));
            } else {
                throw new DAOException("This ID cannot be assigned to this decoration.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
    }
    
    private Decoration convert(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        Thematic thematic = Thematic.valueOf(rs.getString("thematic"));
        String material = rs.getString("material");
        int value = rs.getInt("value");
        Integer idRoom = rs.getInt("idroom");
        if (rs.wasNull()) {
            idRoom = null;
        }
        RoomAbstractFactory raf = MySQLUtils.selectFactroy(thematic);
        Decoration decoration = raf.createDecoration(name, material, value);
        decoration.setIdRoom(idRoom);
        decoration.setId(rs.getInt("iddecorations"));
        return decoration;
    }
    
    @Override
    public Decoration readOne(Integer id) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Decoration decoration = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                decoration = convert(rs);
            } else {
                throw new DAOException("Record not found.");
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return decoration;
    }
    
    @Override
    public List<Decoration> readAll() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Decoration> decorations = new ArrayList<>();
        
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()) {
                decorations.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return decorations;
    }
    
    @Override
    public List<Decoration> readAllIdRoom(Integer idRoom) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Decoration> decorations = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETBYIDROOM);
            stat.setInt(1, idRoom);
            rs = stat.executeQuery();
            while (rs.next()) {
                decorations.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return decorations;
    }
    
    @Override
    public List<Decoration> readAvaiable(Thematic thematic) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Decoration> decorations = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETAVAILABLE);
            stat.setString(1, thematic.toString());
            rs = stat.executeQuery();
            while (rs.next()) {
                decorations.add(convert(rs));
            }
        } catch (SQLException e) {
            throw new DAOException("SQL Error", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return decorations;
    }
    
    @Override
    public void setRommToNull(Decoration decoration) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(SETROOMTONULL);
            
            stat.setInt(1, decoration.getId());
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
    public void update(Decoration decoration) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(UPDATE);
            
            stat.setString(1, decoration.getName());
            stat.setString(2, decoration.getThematic().toString());
            stat.setString(3, decoration.getMaterial());
            stat.setFloat(4, decoration.getValue());
            stat.setInt(5, decoration.getIdRoom());
            stat.setInt(6, decoration.getId());
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
    public void delete(Decoration decoration) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, decoration.getId());
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

