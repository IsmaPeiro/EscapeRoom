package factory;

import model.clues.Clue;
import model.clues.FantasticClue;
import model.clues.SciFiClue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.decorations.SciFiDecoration;
import model.rooms.Difficulty;
import model.rooms.FantasticRoom;
import model.rooms.Room;
import model.rooms.SciFiRoom;

import java.util.List;

public class SciFiRoomFactory implements RoomAbstractFactory {
    @Override
    public Room createRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new SciFiRoom(name, difficulty, clues, decorations);
    }
    
    @Override
    public Clue createClue(int id, float value) {
        return new SciFiClue(id, value);
    }
    
    @Override
    public Decoration createDecoration(int id, String material, float value) {
        return new SciFiDecoration(id, material, value);
    }
}
