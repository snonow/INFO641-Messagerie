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