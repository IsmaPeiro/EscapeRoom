package model.decorations;

import model.escape_room.Thematic;

public class MedievalDecoration extends Decoration {
    public MedievalDecoration(int id, String material, float value) {
        super(id, material, value);
    }
    
    @Override
    public Thematic getThematic() {
        return Thematic.MEDIEVAL;
    }
    
    @Override
    public String toString() {
        return "MedievalDecoration{} " + super.toString();
    }
}
