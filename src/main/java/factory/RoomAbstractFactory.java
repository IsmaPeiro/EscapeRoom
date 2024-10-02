package factory;

import model.clues.Clue;
import model.decorations.Decoration;
import model.rooms.Difficulty;
import model.rooms.Room;

import java.util.List;

public interface RoomAbstractFactory {
    Room createRoom (String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations);
    Clue createClue (float value);
    Decoration createDecoration (String name, String material, float value);
}