package model.clues;

import model.escape_room.Thematic;

public class FantasticClue extends Clue {
    public FantasticClue(float score, String difficulty) {
        super(score, difficulty);
        thematic=Thematic.FANTASTIC;
    }

    @Override
    public String toString() {
        return "FantasticClue{} " + super.toString();
    }
}
