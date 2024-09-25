package model.clients;

public class Ticket {
    private int id;
    private Client client;
    private float value;
    
    public Ticket(int id, Client client, float value) {
        this.id = id;
        this.client = client;
        this.value = value;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public float getValue() {
        return value;
    }
    
    public void setValue(float value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", client=" + client +
                ", score=" + value +
                '}';
    }
}
