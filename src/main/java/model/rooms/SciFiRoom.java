package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;

public class SciFiRoom extends Room {
    public SciFiRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(name, difficulty, clues, decorations);
        thematic=Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFiRoom{} " + super.toString();
    }
}
