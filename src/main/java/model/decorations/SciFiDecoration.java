package model.decorations;

import model.escape_room.Thematic;

public class SciFiDecoration extends Decoration {
    public SciFiDecoration(int id, String material, float value) {
        super(id, material, value);
    }
    
    @Override
    public Thematic getThematic() {
        return Thematic.SCIFI;
    }
    
    @Override
    public String toString() {
        return "SciFiDecoration{} " + super.toString();
    }
}
