import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestionBavardsGUI extends JFrame {
    private ArrayList<Batiment> batiments;
    private JComboBox<Batiment> batimentComboBox;
    private JTextArea bavardListArea;
    private JTextField nomBavardField;
    private JPasswordField passwordField;
    private JButton ajouterBatimentButton;
    private JButton supprimerBatimentButton;
    private JButton ajouterBavardButton;

    public GestionBavardsGUI(ArrayList<Batiment> batiments) {
        this.batiments = batiments;

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

        // Liste des bavards pour le bâtiment sélectionné
        batimentComboBox = new JComboBox<>(batiments.toArray(new Batiment[0]));
        batimentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherBavards();
            }
        });
        mainPanel.add(batimentComboBox, BorderLayout.CENTER);

        // Panel pour ajouter un bavard
        JPanel ajouterBavardPanel = new JPanel(new FlowLayout());
        nomBavardField = new JTextField();
        ajouterBavardButton = new JButton("Ajouter Bavard");
        ajouterBavardPanel.add(new JLabel("Nom du Bavard:"));
        ajouterBavardPanel.add(nomBavardField);
        ajouterBavardPanel.add(ajouterBavardButton);
        mainPanel.add(ajouterBavardPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // Gestion des événements
        ajouterBatimentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBatiment = nomBavardField.getText();
                Batiment batiment = new Batiment(new Concierge(nomBatiment));
                batiments.add(batiment);
                batimentComboBox.addItem(batiment);
                nomBavardField.setText("");
                passwordField.setText("");
            }
        });

        supprimerBatimentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Batiment selectedBatiment = (Batiment) batimentComboBox.getSelectedItem();
                batiments.remove(selectedBatiment);
                batimentComboBox.removeItem(selectedBatiment);
                afficherBavards();
            }
        });

        ajouterBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBavard = nomBavardField.getText();
                Batiment selectedBatiment = (Batiment) batimentComboBox.getSelectedItem();
                Bavard bavard = new Bavard(nomBavard, selectedBatiment.getConcierge());
                selectedBatiment.addBavard(bavard);
                afficherBavards();
            }
        });

        afficherBavards(); // Afficher les bavards pour le premier bâtiment par défaut
    }

    private void afficherBavards() {
        Batiment selectedBatiment = (Batiment) batimentComboBox.getSelectedItem();
        if (selectedBatiment != null && selectedBatiment.getConcierge() != null) {
            StringBuilder bavardList = new StringBuilder("Bavards pour le concierge " + selectedBatiment.getConcierge().getNom() + " :\n");
            for (Bavard bavard : selectedBatiment.getListBavards()) {
                bavardList.append("- ").append(bavard.getNom()).append("\n");
            }
            bavardListArea.setText(bavardList.toString());
        } else {
            bavardListArea.setText("Aucun bâtiment sélectionné ou aucun concierge pour ce bâtiment.");
        }
    }    

    public static void main(String[] args) {
        ArrayList<Batiment> batiments = new ArrayList<>();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GestionBavardsGUI(batiments).setVisible(true);
            }
        });
    }
}
