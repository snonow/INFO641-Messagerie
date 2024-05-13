public class PapotageEvent {
    private String subjet;
    private String corpus;

    // Constructor
    public PapotageEvent(String subjet, String corpus) {
        this.subjet = subjet;
        this.corpus = corpus;
    }

    // Getter
    public String getSubjet() {
        return subjet;
    }
    public String getCorpus() {
        return corpus;
    }
};

