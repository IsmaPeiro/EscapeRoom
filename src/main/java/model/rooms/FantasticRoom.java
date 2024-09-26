package model.rooms;

import model.clues.Clue;
import model.clues.FantasticClue;
import model.decorations.Decoration;
import model.escape_room.ClueUtils;
import model.escape_room.Thematic;

import java.util.List;

public class FantasticRoom extends Room {
    public FantasticRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.FANTASTIC;
    }

    @Override
    public void addClueToList(Clue clue) {
        clues.add(clue);
    }

    @Override
    public void createClue() {
        float score = ClueUtils.getClueScore();
        String difficulty = ClueUtils.getClueDifficulty();
        float value = ClueUtils.getClueValue();
        FantasticClue fantasticClue = new FantasticClue(score, difficulty, value);
        addClueToList(fantasticClue);
    }

    @Override
    public String toString() {
        return "FantasticRoom{} " + super.toString();
    }
}
