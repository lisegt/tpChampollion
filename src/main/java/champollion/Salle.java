package champollion;

public class Salle {
    private String intitule;
    private int capacite;
    
    public Salle(String intitule, int capacite) {
        this.intitule = intitule;
        this.capacite = capacite;
    }
    
    public String getIntitule() {
        return intitule;
    }
    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    public int getCapacite() {
        return capacite;
    }
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    
}
