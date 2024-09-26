package model.rooms;

import model.clues.Clue;
import model.clues.MedievalClue;
import model.clues.TerrorClue;
import model.decorations.Decoration;
import model.escape_room.ClueUtils;
import model.escape_room.Thematic;

import java.util.List;

public class TerrorRoom extends Room {
    public TerrorRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.TERROR;
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
        TerrorClue terrorClue = new TerrorClue(score, difficulty, value);
        addClueToList(terrorClue);
    }

    @Override
    public String toString() {
        return "TerrorRoom{} " + super.toString();
    }
}
