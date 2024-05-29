// Définition de la classe Bavard
class Bavard implements PapotageListener {
    private String nom;
    private String password;
    private Concierge ecouteConcierge;
    
    // Méthode pour recevoir les événements PapotageEvent
    public void receivePapotage(PapotageEvent event) {
        // Receptions des messages
        System.out.println("Bavard a reçu un message sur le sujet : " + event.getSujet());
        System.out.println("Contenu du message : " + event.getCorps());
    }
    
    // Méthode pour créer et émettre des événements PapotageEvent
    public void sendPapotage(String sujet, String corps, Concierge concierge) {
        PapotageEvent event = new PapotageEvent(sujet, corps, this);
        concierge.receivePapotage(event);
    }
    public void sendPapotage(String sujet, String corps) {
        PapotageEvent event = new PapotageEvent(sujet, corps, this);
        if (ecouteConcierge != null) {
            ecouteConcierge.receivePapotage(event);
        }
    }

    // Vérification de si le mot de passe émis est correspondant ou non
    public boolean verifPassword(String password) {
        if (this.password.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    // Constructors
    public Bavard(String nom, String password, Concierge ecouteConcierge) {
        this.nom = nom;
        this.password = password;
        this.ecouteConcierge = ecouteConcierge;
    }
    public Bavard(String nom, String password) {
        this.nom = nom;
        this.password = password;
        this.ecouteConcierge = null;
    }
    public Bavard(String nom, Concierge ecouteConcierge) {
        this.nom = nom;
        this.password = null;
        this.ecouteConcierge = ecouteConcierge;
    }
    public Bavard(String nom) {
        this.nom = nom;
        this.password = null;
        this.ecouteConcierge = null;
    }

    // Getter
    public String getNom() {
        return nom;
    }
    public Concierge getConcierge() {
        return ecouteConcierge;
    }

    // Setter
    public void setPassword(String password_1, String password_2) {
        if (password_1 == password_2) {
            this.password = password_1;
        }
    }
    public void setEcouteConcierge(Concierge ecouteConcierge) {
        this.ecouteConcierge = ecouteConcierge;
    }

    @Override
    public String toString() {
        return "Bavard [nom=" + nom + ", ecouteConcierge=" + ecouteConcierge + "]";
    }

    
    
}