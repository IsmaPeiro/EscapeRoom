package model.decorations;

import model.escape_room.Thematic;

public class TerrorDecoration extends Decoration {
    public TerrorDecoration(String name, String material, float value) {
        super(name, material, value);
        this.id = id;
        thematic=Thematic.TERROR;
    }
    
    @Override
    public String toString() {
        return "TerrorDecoration{} " + super.toString();
    }
}
