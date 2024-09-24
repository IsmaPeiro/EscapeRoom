package model.escape_room;

import model.clients.Client;
import model.clients.Ticket;
import model.rooms.Room;

public class TicketUtils {
    public static Ticket createTicket() {
        int clientId, roomId;
        float value;
        Client client = null;
        Room room = null;
        
        while (client == null) {
            clientId = Input.readInt("Input the id of the client:");
            client = ClientUtils.searchClient(clientId);
        }
        
        while (room == null) {
            roomId = Input.readInt("Input the id of the room:");
            room = RoomUtils.searchRoom(roomId);
        }
        
        value=RoomUtils.calculateValue(room);
        
        return new Ticket (client, value, room);
    }
}
