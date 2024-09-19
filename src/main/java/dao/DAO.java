package dao;

import java.util.List;

public interface DAO<T, K> {
    void create (T a) throws DAOException;
    T readOne (K id) throws DAOException;
    List<T> readAll () throws DAOException;
    void update (T a) throws DAOException;
    void delete (T a) throws DAOException;
}
