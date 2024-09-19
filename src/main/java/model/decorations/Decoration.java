package model.decorations;

import model.escape_room.Thematic;

public abstract class Decoration {
    private int id;
    private String material;
    private float value;
    
    public Decoration(int id, String material, float value) {
        this.id = id;
        this.material = material;
        this.value=value;
    }
    
    public abstract Thematic getThematic();
    
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
    
    @Override
    public String toString() {
        return "Decoration{" +
                "id=" + id +
                ", material='" + material + '\'' +
                '}';
    }
}
