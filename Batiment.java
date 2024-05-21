import java.util.ArrayList;
import java.util.List;

class Batiment {
    private Concierge concierge;
    private List<Bavard> listBavards;

    // Constructors
    public Batiment() {
        this.concierge = null;
        this.listBavards = new ArrayList<>();
    }
    public Batiment(Concierge concierge) {
        this.concierge = concierge;
        this.listBavards = new ArrayList<>();
    }

    // Ajout de bavard(s) à la liste des bavards
    public void addBavard(Bavard bavard) {
        listBavards.add(bavard);
    }
    public void addBavard(List<Bavard> listNouveauxBavards) {
        listBavards.addAll(listNouveauxBavards);
    }

    // Ajout de bavard(s) à la liste des "écouteurs"
    public void addBavardListening(Bavard bavard) {
        concierge.addBavardListening(bavard);
    }
    public void addBavardListening(List<Bavard> listBavard) {
        concierge.addBavardListening(listBavard);
    }

    // Authentication
    public Bavard authenticateBavard(String nomBavard, String passwordBavard) {
        for (Bavard bavard : listBavards) {
            if (bavard.getNom() == nomBavard && bavard.verifPassword(passwordBavard)) {
                return bavard;
            }
        }
        return null;
    }

    // Getters
    public Concierge getConcierge() {
        return concierge;
    }
    public List<Bavard> getListBavards() {
        return listBavards;
    }

    // Setter
    public void setConcierge(Concierge concierge) {
        this.concierge = concierge;
    }
    public void setListBavards(List<Bavard> listBavards) {
        this.listBavards = listBavards;
    }
}
