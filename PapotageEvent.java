public class PapotageEvent {
    private String sujet;
    private String corps;

    // Constructor
    public PapotageEvent(String sujet, String corps) {
        this.sujet = sujet;
        this.corps = corps;
    }

    // Getter
    public String getSujet() {
        return sujet;
    }
    public String getCorps() {
        return corps;
    }
}
