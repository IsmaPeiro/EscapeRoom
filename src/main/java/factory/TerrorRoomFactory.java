package factory;

import model.clues.Clue;
import model.clues.FantasticClue;
import model.clues.TerrorClue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.decorations.TerrorDecoration;
import model.rooms.Difficulty;
import model.rooms.FantasticRoom;
import model.rooms.Room;
import model.rooms.TerrorRoom;

import java.util.List;

public class TerrorRoomFactory implements RoomAbstractFactory {
    @Override
    public Room createRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new TerrorRoom(name, difficulty, clues, decorations);
    }
    
    @Override
    public Clue createClue(int id, float value) {
        return new TerrorClue(id, value);
    }
    
    @Override
    public Decoration createDecoration(int id, String material, float value) {
        return new TerrorDecoration(id, material, value);
    }
}
