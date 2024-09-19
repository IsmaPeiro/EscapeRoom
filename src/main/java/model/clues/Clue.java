package model.clues;

import model.escape_room.Thematic;

public abstract class Clue {
    protected int id;
    protected Thematic thematic;
    protected float value;
    
    public Clue(int id, float value) {
        this.id = id;
        this.value = value;
    }
    
    public Thematic getThematic() {
        return thematic;
    }
    
    public void setThematic(Thematic thematic) {
        this.thematic = thematic;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public float getValue() {
        return value;
    }
    
    public void setValue(float value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Clue{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
