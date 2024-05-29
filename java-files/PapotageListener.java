import java.util.EventListener;

// Définition de l'interface PapotageListener
interface PapotageListener extends EventListener {
    public void receivePapotage(PapotageEvent event);
}

