package model.clues;

import model.escape_room.Thematic;

public class MedievalClue extends Clue {
    public MedievalClue(float score, String difficulty, float value) {
        super(score, difficulty, value);
        thematic=Thematic.MEDIEVAL;
    }

    @Override
    public String toString() {
        return "MedievalClue{} " + super.toString();
    }
}
