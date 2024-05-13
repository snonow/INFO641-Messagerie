import java.util.EventListener;
import java.util.List;

// Définition de l'interface PapotageListener
interface PapotageListener extends EventListener {
    public void receivePapotage(PapotageEvent event);
}

// Définition de la classe Bavard
class Bavard implements PapotageListener {
    private String nom;
    private Concierge ecouteConcierge;
    
    // Méthode pour recevoir les événements PapotageEvent
    public void receivePapotage(PapotageEvent event) {
        // Receptions des messages
        System.out.println("Bavard a reçu un message sur le sujet : " + event.getSujet());
        System.out.println("Contenu du message : " + event.getCorps());
    }
    
    // Méthode pour créer et émettre des événements PapotageEvent
    public void sendPapotage(String sujet, String corps) {
        PapotageEvent event = new PapotageEvent(sujet, corps);
        if (ecouteConcierge != null) {
            ecouteConcierge.receivePapotage(event);
        }
    }

    // Constructors
    public Bavard(String nom, Concierge ecouteConcierge) {
        this.nom = nom;
        this.ecouteConcierge = ecouteConcierge;
    }
    public Bavard(String nom) {
        this.nom = nom;
        this.ecouteConcierge = null;
    }

    // Getter
    public String getNom() {
        return nom;
    }
    public String getConcierge() {
        return ecouteConcierge.toString();
    }

    // Setter
    public void setEcouteConcierge(Concierge ecouteConcierge) {
        this.ecouteConcierge = ecouteConcierge;
    }

    // To-String
    @Override
    public String toString() {
        return "Bavard [nom=" + nom + ", ecouteConcierge=" + ecouteConcierge + "]";
    }
    
}

// Définition de la classe Concierge
public class Concierge implements PapotageListener {

    private String nom;
    private List<Bavard> listBavardsListening;

    // Méthode pour recevoir les événements PapotageEvent
    public void receivePapotage(PapotageEvent event) {
        // Renvoie des messages reçues au bavards "écoutant"
        for (Bavard bavard : listBavardsListening) {
            bavard.receivePapotage(event);
        }
    }

    // Ajout de nouveaux bavards à la liste des "écoutants"
    public void addBavardsListening(List<Bavard> listNouveauxBavards) {
        for (Bavard bavard : listNouveauxBavards) {
            this.listBavardsListening.add(bavard);
        }
    }

    // Constructor
    public Concierge(String nom, List<Bavard> bavardsListening) {
        this.nom = nom;
        this.listBavardsListening = bavardsListening;
    }

    // Getter
    public String getNom() {
        return nom;
    }
    public String getBavardsListening() {
        return listBavardsListening.toString();
    }

    // To-String
    @Override
    public String toString() {
        return "Concierge [nom=" + nom + ", bavardsListening=" + listBavardsListening + "]";
    }

}