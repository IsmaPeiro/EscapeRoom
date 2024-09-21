package model.clues;

import model.escape_room.Thematic;

public class FantasticClue extends Clue {
    public FantasticClue(float value) {
        super(value);
        thematic=Thematic.FANTASTIC;
    }
    
    @Override
    public String toString() {
        return "FantasticClue{} " + super.toString();
    }
}
