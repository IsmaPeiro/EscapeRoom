package model.rooms;

import model.clues.Clue;
import model.decorations.Decoration;
import model.escape_room.Thematic;

import java.util.List;

public abstract class Room {
    protected int id;
    protected Thematic thematic;
    protected String name;
    protected Difficulty difficulty;
    protected List<Clue> clues;
    protected List<Decoration> decorations;
    
    public Room(int id, String name, Difficulty difficulty, List<Clue> clues, List<Decoration> decorations) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.clues = clues;
        this.decorations = decorations;
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
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Difficulty getDifficulty() {
        return difficulty;
    }
    
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    public List<Clue> getClues() {
        return clues;
    }
    
    public void setClues(List<Clue> clues) {
        this.clues = clues;
    }
    
    public List<Decoration> getDecorations() {
        return decorations;
    }
    
    public void setDecorations(List<Decoration> decorations) {
        this.decorations = decorations;
    }
    
    public void addClueList (Clue clue) {
        clues.add(clue);
    }

    public void addDecorationList (Decoration decoration){ decorations.add(decoration);}

    public abstract Decoration createDecoration();
    
    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", clues=" + clues +
                ", decorations=" + decorations +
                '}';
    }
}


