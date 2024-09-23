package model.decorations;

import model.escape_room.Thematic;

public class FantasticDecoration extends Decoration {
    public FantasticDecoration(String name, String material, float value) {
        super(name, material, value);
        this.id = id;
        thematic=Thematic.FANTASTIC;
    }
    
    @Override
    public String toString() {
        return "FantasticDecoration{} " + super.toString();
    }
}
