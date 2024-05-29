import java.util.ArrayList;
import java.util.List;

// Définition de la classe Concierge
public class Concierge implements PapotageListener {
    private String nom;
    private List<Bavard> listBavardsListening;
    private List<PapotageEvent> listPapotageEvents;

    // Constructors
    public Concierge(String nom, List<Bavard> bavardsListening) {
        this.nom = nom;
        this.listBavardsListening = bavardsListening;
        listPapotageEvents = new ArrayList<>();
    }
    public Concierge(String nom) {
        this.nom = nom;
        this.listBavardsListening = new ArrayList<>();
        listPapotageEvents = new ArrayList<>();
    }

    // Méthode pour recevoir les événements PapotageEvent
    public void receivePapotage(PapotageEvent event) {
        // Enregistrement des messages dans la liste des papotages
        listPapotageEvents.add(event);

        // Renvoie des messages reçues au bavards "écoutant"
        for (Bavard bavard : listBavardsListening) {
            bavard.receivePapotage(event);
        }
    }

    // Supression d'un bavardage
    public void removePapotageEvent(PapotageEvent event) {
        listPapotageEvents.remove(event);
    }

    // Ajout de nouveaux bavards à la liste des "écoutants"
    public void addBavardListening(Bavard nouveauBavard) {
        this.listBavardsListening.add(nouveauBavard);
    }
    public void addBavardListening(List<Bavard> listNouveauxBavards) {
        for (Bavard bavard : listNouveauxBavards) {
            this.listBavardsListening.add(bavard);
        }
    }

    // Getter
    public String getNom() {
        return nom;
    }
    public String getBavardsListening() {
        return listBavardsListening.toString();
    }
    public List<PapotageEvent> getListPapotageEvents() {
        return listPapotageEvents;
    }

    // To-String
    @Override
    public String toString() {
        return "Concierge [nom=" + nom + ", bavardsListening=" + listBavardsListening + "]";
    }

}