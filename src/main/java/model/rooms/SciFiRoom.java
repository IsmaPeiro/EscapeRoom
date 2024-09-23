package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.decorations.SciFiDecoration;
import model.escape_room.DecorationUtils;
import model.escape_room.Thematic;

import java.util.List;

public class SciFiRoom extends Room {
    public SciFiRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.SCIFI;
    }

    @Override
    public void createDecoration(){
        String name = DecorationUtils.getNameDecoration();
        String material = DecorationUtils.getMaterialDecoration();
        float value = DecorationUtils.getValueDecoration();
        SciFiDecoration sciFiDecoration = new SciFiDecoration(name, material, value);
        addDecorationToList(sciFiDecoration);
    }

    @Override
    public void addDecorationToList(Decoration decoration){
        decorations.add(decoration);
    }


    @Override
    public String toString() {
        return "SciFiRoom{} " + super.toString();
    }
}
