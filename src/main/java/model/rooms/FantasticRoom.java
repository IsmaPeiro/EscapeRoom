package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;

public class FantasticRoom extends Room {
    public FantasticRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.FANTASTIC;
    }
    
    @Override
    public String toString() {
        return "FantasticRoom{} " + super.toString();
    }
}
