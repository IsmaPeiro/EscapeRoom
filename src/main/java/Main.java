import dao.DAOException;
import model.escape_room.EscapeRoomManagement;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws DAOException, SQLException {
        EscapeRoomManagement escapeRoomManagement = new EscapeRoomManagement();
        escapeRoomManagement.init();
    }
}
