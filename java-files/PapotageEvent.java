public class PapotageEvent {
    private String sujet;
    private String corps;
    private Bavard sender;

    // Constructor
    public PapotageEvent(String sujet, String corps, Bavard sender) {
        this.sujet = sujet;
        this.corps = corps;
        this.sender = sender;
    }

    // Getter
    public String getSujet() {
        return sujet;
    }
    public String getCorps() {
        return corps;
    }
    public Bavard getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "Sujet :" + sujet + "\n\nCorps :\n" + corps + "\n";
    }
}
