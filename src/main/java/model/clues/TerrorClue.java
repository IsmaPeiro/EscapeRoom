package model.clues;

import model.escape_room.Thematic;

public class TerrorClue extends Clue {
    public TerrorClue(float value) {
        super(value);
        thematic=Thematic.TERROR;
    }
    
    @Override
    public String toString() {
        return "Terror Clue: " + super.toString();
    }
}
