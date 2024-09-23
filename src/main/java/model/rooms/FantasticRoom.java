package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.decorations.FantasticDecoration;
import model.escape_room.DecorationUtils;
import model.escape_room.Thematic;

import java.util.List;
import java.util.Scanner;

public class FantasticRoom extends Room {
    public FantasticRoom(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        super(id, name, difficulty, clues, decorations);
        thematic=Thematic.FANTASTIC;
    }

    @Override
    public void createDecoration(){
    String name = DecorationUtils.getNameDecoration();
    String material = DecorationUtils.getMaterialDecoration();
    float value = DecorationUtils.getValueDecoration();
        FantasticDecoration fantasticDecoration = new FantasticDecoration(name, material, value);
        addDecorationToList(fantasticDecoration);
    }

    @Override
    public void addDecorationToList(Decoration decoration){
        decorations.add(decoration);
    }




    
    @Override
    public String toString() {
        return "FantasticRoom{} " + super.toString();
    }
}
