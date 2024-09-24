package model.decorations;

import model.escape_room.Thematic;

public class FantasticDecoration extends Decoration {
    public FantasticDecoration(String material, float value) {
        super(material, value);
        thematic=Thematic.MEDIEVAL;
    }
    
    @Override
    public String toString() {
        return "Fantastic Decoration: " + super.toString();
    }
}
