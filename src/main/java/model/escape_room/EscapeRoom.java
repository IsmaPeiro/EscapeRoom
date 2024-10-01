
package model.escape_room;

import dao.*;
import dao.mysql.*;
import model.clients.Client;
import model.clients.Ticket;
import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Room;
import observer.ClientObservable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EscapeRoom {
    public void newClue(Clue clue) {
        Connection conn = null;

        try {
            conn = MySQLUtils.getConn();
            ClueDAO dao = new MySQLClueDAO(conn);
            dao.create(clue);
//            ClientObservable clientObservable = new ClientObservable();
//            clientObservable.notifyClients(clue);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }


    public void newDecoration(Decoration decoration) {
        Connection conn = null;

        try {
            conn = MySQLUtils.getConn();
            DecorationDAO dao = new MySQLDecorationDAO(conn);
            dao.create(decoration);
//            ClientObservable clientObservable = new ClientObservable();
//            clientObservable.notifyClients(clue);
        } catch (DAOException | SQLException e) {
            System.out.println(e);
        } finally {
            MySQLUtils.closeConn(conn);
        }
    }
}
