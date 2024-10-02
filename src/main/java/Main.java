import dao.mysql.SelectDB;
import model.escape_room.EscapeIU;

public class Main {
    public static void main(String[] args) {
        SelectDB sdb=new SelectDB();
        sdb.init();
    }
}
