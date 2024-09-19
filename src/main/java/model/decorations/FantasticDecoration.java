package model.decorations;

import model.escape_room.Thematic;

public class FantasticDecoration extends Decoration {
    public FantasticDecoration(int id, String material, float value) {
        super(id, material, value);
        thematic=Thematic.MEDIEVAL;
    }
    
    @Override
    public String toString() {
        return "FantasticDecoration{} " + super.toString();
    }
}
