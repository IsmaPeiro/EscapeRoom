package model.clues;

import model.escape_room.Thematic;

public class SciFiClue extends Clue {
    public SciFiClue(float score, String difficulty) {
        super(score, difficulty);
        thematic=Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFiClue{} " + super.toString();
    }
}
