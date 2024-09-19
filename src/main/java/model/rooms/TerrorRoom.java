package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;

public class TerrorRoom extends Room {
    public TerrorRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
    }
    
    @Override
    public Thematic getTemathic() {
        return Thematic.TERROR;
    }
    
    @Override
    public String toString() {
        return "TerrorRoom{} " + super.toString();
    }
}
