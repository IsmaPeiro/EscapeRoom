package dao;

import model.decorations.Decoration;

import java.util.List;

public interface DecorationDAO extends DAO <Decoration, Integer> {
    List<Decoration> readAllIdRoom (Integer idRoom) throws DAOException;
}
