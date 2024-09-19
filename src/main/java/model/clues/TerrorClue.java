package model.clues;

import model.escape_room.Thematic;

public class TerrorClue extends Clue {
    public TerrorClue(int id, float value) {
        super(id, value);
        thematic=Thematic.TERROR;
    }
    
    @Override
    public String toString() {
        return "TerrorClue{} " + super.toString();
    }
}
