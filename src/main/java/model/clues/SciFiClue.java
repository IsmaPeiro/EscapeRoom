package model.clues;

import model.escape_room.Thematic;

public class SciFiClue extends Clue {
    public SciFiClue(int id, float value) {
        super(id, value);
    }
    
    @Override
    public Thematic getThematic() {
        return Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFiClue{} " + super.toString();
    }
}
