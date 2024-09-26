package factory;

import model.clues.Clue;
import model.clues.FantasticClue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.rooms.Difficulty;
import model.rooms.FantasticRoom;
import model.rooms.Room;

import java.util.List;

public class FantasticRoomFactory implements RoomAbstractFactory {
    @Override
    public Room createRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new FantasticRoom(name, difficulty, clues, decorations);
    }
    
    @Override
    public Clue createClue(float value) {
        return new FantasticClue(value);
    }
    
    @Override
    public Decoration createDecoration(String name, String material, float value) {
        return new FantasticDecoration(name, material, value);
    }
}
