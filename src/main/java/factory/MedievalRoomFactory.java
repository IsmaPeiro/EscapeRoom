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
    public Room createRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new MedievalRoom(id, name, difficulty, clues, decorations);
    }

    @Override
    public Clue createClue(float score, String difficulty) {
        return new MedievalClue(score, difficulty);
    }

    @Override
    public Decoration createDecoration(int id, String material, float value) {
        return new MedievalDecoration(id, material, value);
    }
}
