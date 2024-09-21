package model.decorations;

import model.escape_room.Thematic;

public class TerrorDecoration extends Decoration {
    public TerrorDecoration(String material, float value) {
        super(material, value);
        thematic=Thematic.TERROR;
    }
    
    @Override
    public String toString() {
        return "TerrorDecoration{} " + super.toString();
    }
}
