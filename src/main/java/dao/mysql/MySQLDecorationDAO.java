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
    
    final String INSERT = "INSERT INTO decorations (thematic, material, value, idroom) " +
            "VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE decorations SET thematic = ?, material = ?, value = ?, idroom = ?"
            + "WHERE iddecorations = ?";
    final String DELETE = "DELETE FROM decorations WHERE iddecorations = ?";
    final String GETALL = "SELECT * FROM decorations";
    final String GETONE = "SELECT iddecorations, thematic, material, value, idroom FROM decorations " +
            "WHERE iddecorations = ?";
    
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
            stat.setString(1, decoration.getThematic().toString());
            stat.setString(2, decoration.getMaterial());
            stat.setFloat(3, decoration.getValue());
            stat.setInt(4, decoration.getIdRoom());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                decoration.setId(rs.getInt(1));
            } else {
                throw new DAOException("No se puede asignar esta ID a este alumno.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
    }
    
    private Decoration convert(ResultSet rs) throws SQLException {
        int id = rs.getInt("iddecorations");
        Thematic thematic = Thematic.valueOf(rs.getString("thematic"));
        String material = rs.getString("material");
        int value = rs.getInt("value");
        Integer idRoom = rs.getInt("idroom");
        if (rs.wasNull()) {
            idRoom = null;
        }
        RoomAbstractFactory raf = selectFactroy(thematic);
        Decoration decoration = raf.createDecoration(id, material, value);
        decoration.setIdRoom(idRoom);
        return decoration;
    }
    
    private RoomAbstractFactory selectFactroy(Thematic thematic) {
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
                throw new DAOException("No se ha encontrado ese registro.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
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
            throw new DAOException("Error en SQL", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return decorations;
    }
    
    @Override
    public void update(Decoration decoration) throws DAOException {
        PreparedStatement stat = null;
        
        try {
            stat = conn.prepareStatement(UPDATE);
            
            stat.setString(1, decoration.getThematic().toString());
            stat.setString(2, decoration.getMaterial());
            stat.setFloat(3, decoration.getValue());
            stat.setInt(4, decoration.getIdRoom());
            stat.setInt(5, decoration.getId());
            if (stat.executeUpdate() == 0) {
                throw new DAOException("Puede que no se haya modificado");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
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
                throw new DAOException("Puede que no se haya modificado");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
        } finally {
            MySQLUtils.close(stat);
        }
    }
}

