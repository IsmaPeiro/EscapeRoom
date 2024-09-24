package dao;

import model.clues.Clue;

import java.util.List;

public interface ClueDAO extends DAO<Clue, Integer>{
    List<Clue> readAllIdRoom (Integer idRoom) throws DAOException;
}
