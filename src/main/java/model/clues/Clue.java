package model.clues;

import model.escape_room.Thematic;

public abstract class Clue {
    protected int id;
    protected Thematic thematic;
    protected float value;
    protected Integer idRoom;
    
    public Clue(float value) {
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
    
    public Integer getIdRoom() {
        return idRoom;
    }
    
    public void setIdRoom(Integer idRoom) {
        this.idRoom = idRoom;
    }
    
    @Override
    public String toString() {
        return "Clue{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
