package model.rooms;

import model.clues.Clue;
import model.clues.MedievalClue;
import model.clues.SciFiClue;
import model.decorations.Decoration;
import model.escape_room.ClueUtils;
import model.escape_room.Thematic;

import java.util.List;

public class SciFiRoom extends Room {
    public SciFiRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.SCIFI;
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
        SciFiClue sciFiClue = new SciFiClue(score, difficulty, value);
        addClueToList(sciFiClue);
    }

    @Override
    public String toString() {
        return "SciFiRoom{} " + super.toString();
    }
}
