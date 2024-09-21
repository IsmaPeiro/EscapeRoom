package model.decorations;

import model.escape_room.Thematic;

public abstract class Decoration {
    protected int id;
    protected Thematic thematic;
    protected String material;
    protected float value;
    protected Integer idRoom;
    
    public Decoration(String material, float value) {
        this.material = material;
        this.value=value;
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
    
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
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
        return "Decoration{" +
                "id=" + id +
                ", material='" + material + '\'' +
                '}';
    }
}
