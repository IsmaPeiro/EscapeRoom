package model.clues;

import model.escape_room.Thematic;

public class FantasticClue extends Clue {
    public FantasticClue(int id, float value) {
        super(id, value);
    }
    
    @Override
    public Thematic getThematic() {
        return Thematic.FANTASTIC;
    }
    
    @Override
    public String toString() {
        return "FantasticClue{} " + super.toString();
    }
}
