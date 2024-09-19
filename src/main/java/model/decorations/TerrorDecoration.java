package model.decorations;

import model.escape_room.Thematic;

public class TerrorDecoration extends Decoration {
    public TerrorDecoration(int id, String material, float value) {
        super(id, material, value);
    }
    
    @Override
    public Thematic getThematic() {
        return Thematic.TERROR;
    }
    
    @Override
    public String toString() {
        return "TerrorDecoration{} " + super.toString();
    }
}
