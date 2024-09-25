package factory;

import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Difficulty;
import model.rooms.Room;

import java.util.List;

public interface RoomAbstractFactory {
    Room createRoom (int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations);
    Clue createClue (float score, String difficulty);
    Decoration createDecoration (int id, String material, float value);
}