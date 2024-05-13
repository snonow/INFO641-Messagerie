import java.util.ArrayList;
import java.util.List;

class Batiment {
    private Concierge concierge;
    private List<Bavard> listBavards;

    // Constructors
    public Batiment(Concierge concierge) {
        this.concierge = concierge;
        this.listBavards = new ArrayList<>();
    }

    // Ajout de bavard(s) à la liste des bavards
    public void addBavard(List<Bavard> listNouveauxBavards) {
        listBavards.addAll(listNouveauxBavards);
        concierge.addBavardsListening(listNouveauxBavards);
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
}
