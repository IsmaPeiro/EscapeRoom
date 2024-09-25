package model.clues;

import model.escape_room.Thematic;

public abstract class Clue {
    protected int id;
    protected Thematic thematic;
    protected float score;
    protected String difficulty;
    
    public Clue(float score, String difficulty) {
        this.score = score;
        this.difficulty = difficulty;
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
    
    public float getScore() {
        return score;
    }
    
    public void setScore(float score) {
        this.score = score;
    }

    public String getClueDifficulty (){
        return difficulty;
    }

    public void setClueDifficulty(){
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Clue{" +
                "id=" + id +
                ", score=" + score +
                ", difficulty=" + difficulty +
                '}';
    }
}
