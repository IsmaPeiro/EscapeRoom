package dao;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;

public interface ClueDAO extends DAO<Clue, Integer>{
    List<Clue> readAllIdRoom (Integer idRoom) throws DAOException;
    List<Clue> readAvaiable (Thematic thematic) throws DAOException;
    void setRommToNull (Clue clue) throws DAOException;
}
