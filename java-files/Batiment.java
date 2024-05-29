import java.util.ArrayList;
import java.util.List;

class Batiment {
    private Concierge concierge;
    private List<Bavard> listBavards;
    private List<Bavard> listOnlineBavards;

    // Constructors
    public Batiment() {
        this.concierge = null;
        this.listBavards = new ArrayList<>();
        this.listOnlineBavards = new ArrayList<>();
    }
    public Batiment(Concierge concierge) {
        this.concierge = concierge;
        this.listBavards = new ArrayList<>();
        this.listOnlineBavards = new ArrayList<>();
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

    // Ajout un bavard à la liste des bavards en ligne
    public void addOnlineBavard(Bavard bavard) {
        listOnlineBavards.add(bavard);
    }

    // Suppression d'un bavard en ligne
    public void removeOnlineBavard(Bavard bavard) {
        listOnlineBavards.remove(bavard);
    }

    // Authentication
    public Bavard authenticateBavard(String nomBavard, String passwordBavard) {
        for (Bavard bavard : listBavards) {
            if (bavard.getNom().equals(nomBavard) && bavard.verifPassword(passwordBavard)) {
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
    public List<Bavard> getOnlineBavards() {
        return listOnlineBavards;
    }

    // Setter
    public void setConcierge(Concierge concierge) {
        this.concierge = concierge;
    }
    public void setListBavards(List<Bavard> listBavards) {
        this.listBavards = listBavards;
    }
}
