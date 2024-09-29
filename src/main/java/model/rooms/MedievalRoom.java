package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.decorations.MedievalDecoration;
import model.escape_room.DecorationUtils;
import model.escape_room.Thematic;

import java.util.List;

public class MedievalRoom extends Room {
    public MedievalRoom(String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(name, difficulty, clues, decorations);
        thematic=Thematic.MEDIEVAL;
    }

    @Override
    public void createDecoration(){
        String name = DecorationUtils.getNameDecoration();
        String material = DecorationUtils.getMaterialDecoration();
        float value = DecorationUtils.getValueDecoration();
        MedievalDecoration medievalDecoration = new MedievalDecoration(name, material, value);
        addDecorationToList(medievalDecoration);
    }

    @Override
    public void addDecorationToList(Decoration decoration){
        decorations.add(decoration);
    }

    @Override
    public String toString() {
        return "Medieval Room: " + "\n" + super.toString();
    }
}
