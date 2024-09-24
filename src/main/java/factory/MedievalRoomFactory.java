package factory;

import model.clues.Clue;
import model.clues.MedievalClue;
import model.decorations.Decoration;
import model.decorations.MedievalDecoration;
import model.rooms.Difficulty;
import model.rooms.MedievalRoom;
import model.rooms.Room;

import java.util.List;

public class MedievalRoomFactory implements RoomAbstractFactory {
    @Override
    public Room createRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new MedievalRoom(name, difficulty, clues, decorations);
    }
    
    @Override
    public Clue createClue(float value) {
        return new MedievalClue(value);
    }
    
    @Override
    public Decoration createDecoration(String material, float value) {
        return new MedievalDecoration(material, value);
    }
}
