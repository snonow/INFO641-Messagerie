import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestionBavardsGUI extends JFrame {
    private JTextArea bavardListArea;
    private JTextField nomBavardField;
    private JPasswordField passwordField;
    private JButton ajouterBatimentButton;
    private JButton supprimerBatimentButton;
    private JButton ajouterBavardButton;

    public GestionBavardsGUI(Batiment batiment) {
        setTitle("Gestion des Bavards");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialisation des composants
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel pour ajouter/supprimer un bâtiment
        JPanel gestionBatimentPanel = new JPanel(new GridLayout(2, 3));
        nomBavardField = new JTextField();
        passwordField = new JPasswordField();
        ajouterBatimentButton = new JButton("Ajouter Bâtiment");
        supprimerBatimentButton = new JButton("Supprimer Bâtiment");
        gestionBatimentPanel.add(new JLabel("Nom du Bâtiment:", SwingConstants.CENTER));
        gestionBatimentPanel.add(new JLabel("Mot de passe:", SwingConstants.CENTER));
        gestionBatimentPanel.add(new JLabel(""));
        gestionBatimentPanel.add(nomBavardField);
        gestionBatimentPanel.add(passwordField);
        gestionBatimentPanel.add(ajouterBatimentButton);
        gestionBatimentPanel.add(new JLabel(""));
        gestionBatimentPanel.add(new JLabel(""));
        gestionBatimentPanel.add(supprimerBatimentButton);
        mainPanel.add(gestionBatimentPanel, BorderLayout.NORTH);

        // Panel pour ajouter un bavard
        JPanel ajouterBavardPanel = new JPanel(new FlowLayout());
        nomBavardField = new JTextField();
        ajouterBavardButton = new JButton("Ajouter Bavard");
        ajouterBavardPanel.add(new JLabel("Nom du Bavard:"));
        ajouterBavardPanel.add(nomBavardField);
        ajouterBavardPanel.add(ajouterBavardButton);
        mainPanel.add(ajouterBavardPanel, BorderLayout.SOUTH);

        add(mainPanel);

        ajouterBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBavard = nomBavardField.getText();
                Bavard bavard = new Bavard(nomBavard, batiment.getConcierge());
                batiment.addBavard(bavard);
                afficherBavards(batiment);
            }
        });

        afficherBavards(batiment); // Afficher les bavards pour le premier bâtiment par défaut
    }

    private void afficherBavards(Batiment batiment) {
        if (batiment != null && batiment.getConcierge() != null) {
            StringBuilder bavardList = new StringBuilder("Bavards pour le concierge " + batiment.getConcierge().getNom() + " :\n");
            for (Bavard bavard : batiment.getListBavards()) {
                bavardList.append("- ").append(bavard.getNom()).append("\n");
            }
            bavardListArea.setText(bavardList.toString());
        } else {
            bavardListArea.setText("Aucun bâtiment sélectionné ou aucun concierge pour ce bâtiment.");
        }
    }    
}
