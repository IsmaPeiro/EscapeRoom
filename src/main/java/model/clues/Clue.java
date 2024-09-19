package model.clues;

import model.escape_room.Thematic;

public abstract class Clue {
    private int id;
    private float value;
    
    public Clue(int id, float value) {
        this.id = id;
        this.value = value;
    }
    
    public abstract Thematic getThematic();
    
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
