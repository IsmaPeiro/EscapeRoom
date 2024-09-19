package model.clues;

import model.escape_room.Thematic;

public class TerrorClue extends Clue {
    public TerrorClue(int id, float value) {
        super(id, value);
    }
    
    @Override
    public Thematic getThematic() {
        return Thematic.TERROR;
    }
    
    @Override
    public String toString() {
        return "TerrorClue{} " + super.toString();
    }
}
