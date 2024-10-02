package model.clues;

import model.escape_room.Thematic;

public class SciFiClue extends Clue {
    public SciFiClue(float value) {
        super(value);
        thematic=Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFi Clue: " + super.toString();
    }
}
