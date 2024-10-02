import dao.mysql.SelectDB;
import model.escape_room.EscapeIU;

public class Main {
    public static void main(String[] args) {
        /*
        EscapeIU escapeIU = new EscapeIU();
        escapeIU.init();
        */
        SelectDB sdb=new SelectDB();
        sdb.init();
    }
}
