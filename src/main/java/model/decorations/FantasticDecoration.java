package model.decorations;

import model.escape_room.Thematic;

public class FantasticDecoration extends Decoration {
    public FantasticDecoration(int id, String material, float value) {
        super(id, material, value);
    }
    
    @Override
    public Thematic getThematic() {
        return Thematic.FANTASTIC;
    }
    
    @Override
    public String toString() {
        return "FantasticDecoration{} " + super.toString();
    }
}
