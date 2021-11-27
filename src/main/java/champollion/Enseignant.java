package champollion;

import java.util.ArrayList;
import java.util.List;

public class Enseignant extends Personne {

    // TODO : rajouter les autres méthodes présentes dans le diagramme UML

    public Enseignant(String nom, String email) {
        super(nom, email);
    }
    List<ServicePrevu> myServicesPrevus = new ArrayList<ServicePrevu>();
    List<Intervention> myInterventions = new ArrayList<Intervention>();
    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        float total=0;
        for (ServicePrevu s : myServicesPrevus){
            total+=s.getVolumeCM()*1.5;
            total+=s.getVolumeTD();
            total+=s.getVolumeTP()*0.75;
        }
        return Math.round(total);
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        float total=0;
        for (ServicePrevu s : myServicesPrevus){
            if (s.getMyUE().equals(ue)){
                total+=s.getVolumeCM()*1.5;
                total+=s.getVolumeTD();
                total+=s.getVolumeTP()*0.75;
            }
        }
        return Math.round(total);
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        //si l'enseignant n'a pas de service prévu, on l'ajoute à la liste
        if (myServicesPrevus.size()==0){
            myServicesPrevus.add(new ServicePrevu(this,ue, volumeCM, volumeTD, volumeTP));
        } else {
            for (ServicePrevu s : myServicesPrevus){
                //si l'enseignant possède déjà un service prévu pour l'UE passée en paramètre, on additionne les valeurs
                if (s.getMyUE().equals(ue)){
                    s.setVolumeCM(s.getVolumeCM()+volumeCM);
                    s.setVolumeTD(s.getVolumeTD()+volumeTD);
                    s.setVolumeTP(s.getVolumeTP()+volumeTP);
                } else { //l'enseignant enseigne une nouvelle UE
                    myServicesPrevus.add(new ServicePrevu(this,ue, volumeCM, volumeTD, volumeTP));
                }
            }
        }
    }

    /**
     * Compare le nombre d'heures prévues d'un enseignant au service "classique" de 192 h.
     *
     * @return un boolean : 
     * true si le nombre d'heures est inférieur à 192 h
     * false si le nombre d'heures est supérieur à 192 h
     *
     */
    public boolean enSousService() {
        if (this.heuresPrevues()<192){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Compare le nombre d'heures prévues d'un enseignant au service "classique" de 192 h.
     *
     * @return un boolean : 
     * true si le nombre d'heures est inférieur à 192 h
     * false si le nombre d'heures est supérieur à 192 h
     *
     */
    public void ajouteIntervention(Intervention inter) {
        myInterventions.add(inter);
    }

}
