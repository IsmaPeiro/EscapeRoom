package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.decorations.TerrorDecoration;
import model.escape_room.DecorationUtils;
import model.escape_room.Thematic;

import java.util.List;

public class TerrorRoom extends Room {
    public TerrorRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(name, difficulty, clues, decorations);
        thematic=Thematic.TERROR;
    }


    @Override
    public String toString() {
        return "Terror Room: " + "\n" + super.toString();
    }
}
