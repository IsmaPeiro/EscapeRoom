package model.clues;

import model.escape_room.Thematic;

public class SciFiClue extends Clue {
    public SciFiClue(int id, float value) {
        super(id, value);
        thematic=Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFiClue{} " + super.toString();
    }
}
