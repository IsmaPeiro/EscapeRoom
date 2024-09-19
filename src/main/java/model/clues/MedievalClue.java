package model.clues;

import model.escape_room.Thematic;

public class MedievalClue extends Clue {
    public MedievalClue(int id, float value) {
        super(id, value);
    }
    
    @Override
    public Thematic getThematic() {
        return Thematic.MEDIEVAL;
    }
    
    @Override
    public String toString() {
        return "MedievalClue{} " + super.toString();
    }
}
