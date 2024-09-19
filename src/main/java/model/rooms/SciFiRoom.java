package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;

public class SciFiRoom extends Room {
    public SciFiRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
    }
    
    @Override
    public Thematic getTemathic() {
        return Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFiRoom{} " + super.toString();
    }
}
