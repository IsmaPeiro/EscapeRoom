package model.decorations;

import model.escape_room.Thematic;

public class SciFiDecoration extends Decoration {
    public SciFiDecoration(String material, float value) {
        super(material, value);
        thematic=Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFiDecoration{} " + super.toString();
    }
}
