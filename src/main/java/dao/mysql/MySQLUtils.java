package dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUtils {
    public static void close (PreparedStatement stat) {
        if (stat!=null) {
            try {
                stat.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
    
    public static void close (ResultSet set) {
        if (set!=null) {
            try {
                set.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
