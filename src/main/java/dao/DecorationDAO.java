package dao;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;

public interface DecorationDAO extends DAO <Decoration, Integer> {
    List<Decoration> readAllIdRoom (Integer idRoom) throws DAOException;
    List<Decoration> readAvaiable (Thematic thematic) throws DAOException;
    void setRommToNull (Decoration decoration) throws DAOException;
    void buy(Decoration decoration) throws DAOException;
}
