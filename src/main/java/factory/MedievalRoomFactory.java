package factory;

import model.clues.Clue;
import model.clues.FantasticClue;
import model.clues.MedievalClue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.decorations.MedievalDecoration;
import model.rooms.Difficulty;
import model.rooms.FantasticRoom;
import model.rooms.MedievalRoom;
import model.rooms.Room;

import java.util.List;

public class MedievalRoomFactory implements RoomAbstractFactory {
    @Override
    public Room createRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new MedievalRoom(name, difficulty, clues, decorations);
    }
    
    @Override
    public Clue createClue(int id, float value) {
        return new MedievalClue(id, value);
    }
    
    @Override
    public Decoration createDecoration(int id, String material, float value) {
        return new MedievalDecoration(id, material, value);
    }
}
