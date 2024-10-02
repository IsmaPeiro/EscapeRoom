package model.clues;

import model.escape_room.Thematic;

public class MedievalClue extends Clue {
    public MedievalClue(float value) {
        super(value);
        thematic=Thematic.MEDIEVAL;
    }
    
    @Override
    public String toString() {
        return "Medieval Clue: " + super.toString();
    }
}
