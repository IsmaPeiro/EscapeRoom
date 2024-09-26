package factory;

import model.clues.Clue;
import model.clues.FantasticClue;
import model.clues.MedievalClue;
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
    public Room createRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new TerrorRoom(id, name, difficulty, clues, decorations);
    }
    
    @Override
    public Clue createClue(float score, String difficulty, float value) {
        return new TerrorClue(score, difficulty, value);
    }
    
    @Override
    public Decoration createDecoration(int id, String material, float value) {
        return new TerrorDecoration(id, material, value);
    }
}
