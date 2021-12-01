package champollion;

import java.util.Date;

public class Intervention {
    private Date debut;
    private int duree;
    private boolean annulee;
    private int heureDebut;
    private TypeIntervention type;
    private UE matiere;
    private Salle lieu;
    
    public Intervention(Date debut, int duree, int heureDebut, TypeIntervention type, UE matiere, Salle lieu) {
        this.debut = debut;
        this.duree = duree;
        this.annulee = false;
        this.heureDebut = heureDebut;
        this.type = type;
        this.matiere = matiere;
        this.lieu = lieu;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public void setAnnulee(boolean annulee) {
        this.annulee = annulee;
    }

    private int getHeureDebut() {
        return heureDebut;
    }

    private void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public TypeIntervention getType() {
        return type;
    }

    public void setType(TypeIntervention type) {
        this.type = type;
    }

    public UE getMatiere() {
        return matiere;
    }

    public Salle getLieu() {
        return lieu;
    }

    public void setLieu(Salle lieu) {
        this.lieu = lieu;
    }

    

    
}
