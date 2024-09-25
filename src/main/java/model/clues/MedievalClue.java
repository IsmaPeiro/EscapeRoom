package model.clues;

import model.escape_room.Thematic;

public class MedievalClue extends Clue {
    public MedievalClue(float score, String difficulty) {
        super(score, difficulty);
        thematic=Thematic.MEDIEVAL;
    }

    @Override
    public String toString() {
        return "MedievalClue{} " + super.toString();
    }
}
