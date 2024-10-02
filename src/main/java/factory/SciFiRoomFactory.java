package factory;

import model.clues.Clue;
import model.clues.SciFiClue;
import model.decorations.Decoration;
import model.decorations.SciFiDecoration;
import model.rooms.Difficulty;
import model.rooms.Room;
import model.rooms.SciFiRoom;

import java.util.List;

public class SciFiRoomFactory implements RoomAbstractFactory {
    @Override
    public Room createRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new SciFiRoom(name, difficulty, clues, decorations);
    }
    
    @Override
    public Clue createClue(float value) {
        return new SciFiClue(value);
    }
    
    @Override
    public Decoration createDecoration(String name, String material, float value) {
        return new SciFiDecoration(name, material, value);
    }
}
