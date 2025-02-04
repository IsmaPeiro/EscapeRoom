package model.decorations;

import model.escape_room.Thematic;

public class MedievalDecoration extends Decoration {
    public MedievalDecoration(String name, String material, float value) {
        super(name, material, value);
        thematic=Thematic.MEDIEVAL;
    }
    
    @Override
    public String toString() {
        return "Medieval Decoration: " + super.toString();
    }
}
