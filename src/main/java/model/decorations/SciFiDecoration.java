package model.decorations;

import model.escape_room.Thematic;

public class SciFiDecoration extends Decoration {
    public SciFiDecoration(String name, String material, float value) {
        super(name, material, value);
        thematic=Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFi Decoration: " + super.toString();
    }
}
