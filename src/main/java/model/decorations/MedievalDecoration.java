package model.decorations;

import model.escape_room.Thematic;

public class MedievalDecoration extends Decoration {
    public MedievalDecoration(String name, String material, float value) {
        super(name, material, value);
        this.id = id;
        thematic=Thematic.MEDIEVAL;
    }
    
    @Override
    public String toString() {
        return "MedievalDecoration{} " + super.toString();
    }
}
