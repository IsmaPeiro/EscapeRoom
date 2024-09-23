package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.decorations.TerrorDecoration;
import model.escape_room.DecorationUtils;
import model.escape_room.Thematic;

import java.util.List;

public class TerrorRoom extends Room {
    public TerrorRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.TERROR;
    }

    @Override
    public void createDecoration(){
        String name = DecorationUtils.getNameDecoration();
        String material = DecorationUtils.getMaterialDecoration();
        float value = DecorationUtils.getValueDecoration();
        TerrorDecoration terrorDecoration = new TerrorDecoration(name, material, value);
        addDecorationToList(terrorDecoration);
    }

    @Override
    public void addDecorationToList(Decoration decoration){
        decorations.add(decoration);
    }


    @Override
    public String toString() {
        return "TerrorRoom{} " + super.toString();
    }
}
