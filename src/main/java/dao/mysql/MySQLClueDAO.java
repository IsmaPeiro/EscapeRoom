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
    final String UPDATE = "UPDATE clues SET thematic = ?, value = ?, idroom = ? "  +
            "WHERE idclues = ?";
    final String DELETE = "DELETE FROM clues WHERE idclues = ?";
    final String GETALL = "SELECT * FROM clues";
    final String GETONE = "SELECT idclues, thematic, value, idroom FROM clues " +
            "WHERE idclues = ?";
    
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
                throw new DAOException("Puede que no se haya guardado");
            }
            rs = stat.getGeneratedKeys();
            if (rs.next()) {
                clue.setId(rs.getInt(1));
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
    
    private Clue convert(ResultSet rs) throws SQLException {
        int id = rs.getInt("idclues");
        Thematic thematic = Thematic.valueOf(rs.getString("thematic"));
        int value = rs.getInt("value");
        Integer idRoom = rs.getInt("idroom");
        if (rs.wasNull()) {
            idRoom = null;
        }
        RoomAbstractFactory raf = selectFactroy(thematic);
        Clue clue = raf.createClue(id, value);
        clue.setIdRoom(idRoom);
        return clue;
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
                throw new DAOException("No se ha encontrado ese registro.");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
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
            throw new DAOException("Error en SQL", e);
        } finally {
            MySQLUtils.close(stat);
            MySQLUtils.close(rs);
        }
        return clues;
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
                throw new DAOException("Puede que no se haya modificado");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
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
                throw new DAOException("Puede que no se haya modificado");
            }
        } catch (SQLException e) {
            throw new DAOException("Error en SQL", e);
        } finally {
            MySQLUtils.close(stat);
        }
    }
}

