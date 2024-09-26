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
    public Room createRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        return new FantasticRoom(id, name, difficulty, clues, decorations);
    }

    @Override
    public Clue createClue(float score, String difficulty, float value) {
        return new FantasticClue(score, difficulty, value);
    }

    @Override
    public Decoration createDecoration(int id, String material, float value) {
        return new FantasticDecoration(id, material, value);
    }
}
