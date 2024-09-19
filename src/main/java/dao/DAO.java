package dao;

import java.util.List;

public interface DAO<T, K> {
    void create (T a);
    T readOne (K id);
    List<T> readAll ();
    void update (T a);
    void delete (T a);
}
