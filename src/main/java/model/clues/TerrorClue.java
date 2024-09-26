package model.clues;

import model.escape_room.Thematic;

public class TerrorClue extends Clue {
    public TerrorClue(float score, String difficulty, float value) {
        super(score, difficulty, value);
        thematic=Thematic.TERROR;
    }
    
    @Override
    public String toString() {
        return "TerrorClue{} " + super.toString();
    }
}
