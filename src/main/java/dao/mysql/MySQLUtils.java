package dao.mysql;

import dao.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUtils {
    public static void close (PreparedStatement stat) throws DAOException {
        if (stat!=null) {
            try {
                stat.close();
            } catch (SQLException e) {
                throw new DAOException("Error en SQL", e);
            }
        }
    }
    
    public static void close (ResultSet set) throws DAOException {
        if (set!=null) {
            try {
                set.close();
            } catch (SQLException e) {
                throw new DAOException("Error en SQL", e);
            }
        }
    }
}
