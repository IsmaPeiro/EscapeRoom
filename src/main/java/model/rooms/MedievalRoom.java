package model.rooms;

import model.clues.Clue;
import model.clues.FantasticClue;
import model.clues.MedievalClue;
import model.decorations.Decoration;
import model.escape_room.ClueUtils;
import model.escape_room.Thematic;

import java.util.List;

public class MedievalRoom extends Room {
    public MedievalRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.MEDIEVAL;
    }

    @Override
    public void addClueToList(Clue clue) {
        clues.add(clue);
    }

    @Override
    public void createClue() {
        float score = ClueUtils.getClueScore();
        String difficulty = ClueUtils.getClueDifficulty();
        MedievalClue medievalClue = new MedievalClue(score, difficulty);
        addClueToList(medievalClue);
    }

    @Override
    public String toString() {
        return "MedievalRoom{} " + super.toString();
    }
}
