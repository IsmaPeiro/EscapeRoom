package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;

public class MedievalRoom extends Room {
    public MedievalRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(name, difficulty, clues, decorations);
        thematic=Thematic.MEDIEVAL;
    }


    @Override
    public String toString() {
        return "Medieval Room: " + "\n" + super.toString();
    }
}
