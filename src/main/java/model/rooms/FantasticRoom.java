package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.escape_room.DecorationUtils;
import model.escape_room.Thematic;

import java.util.List;
import java.util.Scanner;

public class FantasticRoom extends Room {
    public FantasticRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(name, difficulty, clues, decorations);
        thematic=Thematic.FANTASTIC;
    }

    @Override
    public String toString() {
        return "Fantastic Room: " + "\n" + super.toString();
    }
}
