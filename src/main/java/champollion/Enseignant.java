package champollion;

import java.util.ArrayList;
import java.util.List;

public class Enseignant extends Personne {

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
        boolean existant= false;
        for (ServicePrevu s : myServicesPrevus){
            //si l'enseignant possède déjà un service prévu pour l'UE passée en paramètre, on additionne les valeurs
            if (s.getMyUE().equals(ue)){
                s.setVolumeCM(s.getVolumeCM()+volumeCM);
                s.setVolumeTD(s.getVolumeTD()+volumeTD);
                s.setVolumeTP(s.getVolumeTP()+volumeTP);
                existant=true;
            }
        }
        if (existant==false){
            myServicesPrevus.add(new ServicePrevu(this,ue, volumeCM, volumeTD, volumeTP));
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
     * Ajoute une intervention aux interventions effectuées d'un enseignant.
     *
     * @param inter l'intervention concernée
     *
     */
    public void ajouteIntervention(Intervention inter) {
        for (ServicePrevu sPrevu : myServicesPrevus){
            //si on veut ajouter une intervention à une UE et qu'un enseignant n'a pas d'enseignements planifiés dans cet UE, on lève une exception
            if (!(sPrevu.getMyUE().equals(inter.getMatiere()))){
                throw new IllegalArgumentException("L'UE n'est pas planifiée pour cet enseignant");
            }
            myInterventions.add(inter);
        }
        
    }

    /**
     * Compare le service prévu d'un d'un enseignant à ses interventions (heures effectuées)
     *
     * @return un int : le nombre d'heures restantes à planifier
     * 
     * @param ue l'ue de l'intervention concernée
     * @param type le type de l'intervention concernée
     * 
     */
    public int resteAPlanifier(UE ue, TypeIntervention type) {

        int heuresPrevues=0;
        for (ServicePrevu sPrevu : myServicesPrevus){
            //si on veut rechercher le nombre d'heures à planifier dans une UE et qu'un enseignant n'a pas d'enseignements planifiés dans cet UE, on lève une exception
            if (!(sPrevu.getMyUE().equals(ue))){
                throw new IllegalArgumentException("L'UE n'est pas planifiée pour cet enseignant");
            }

            else {
                switch (type) {
                    case CM:
                        heuresPrevues=sPrevu.getVolumeCM();
                        break;
                    case TD:
                        heuresPrevues=sPrevu.getVolumeTD();
                        break;
                    case TP:
                        heuresPrevues=sPrevu.getVolumeTP();
                        break;
                    default:
                        break;
                }
            }
        }
        int heuresEffect=0;
        for (Intervention inter : myInterventions){
            if (inter.getMatiere().equals(ue) && inter.getType().equals(type)){
                heuresEffect=inter.getDuree();
            }
        }
        return (heuresPrevues-heuresEffect);
    }


}
