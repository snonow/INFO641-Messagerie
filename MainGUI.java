import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private Batiment batiment;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;

    public MainGUI() {
        batiment = new Batiment(new Concierge("Insta")); // Assurez-vous que Batiment et Concierge sont bien initialisés
        setTitle("Bienvenue sur " + batiment.getConcierge().getNom());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Création du menu
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuItem = new JMenuItem("Option");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Création du panneau central avec le nom du concierge et le message de bienvenue
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 0, 20)); // 3 lignes, 1 colonne, espacement vertical de 20
        JLabel conciergeLabel = new JLabel("Concierge: " + batiment.getConcierge().getNom(), SwingConstants.CENTER);
        JLabel welcomeLabel = new JLabel("Bienvenue dans l'application de gestion des Bavards!", SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Centré avec espacement horizontal de 20
        JButton loginButton = new JButton("Connexion");
        JButton registerButton = new JButton("Enregistrement");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre de connexion
                new BavardLoginGUI(batiment).setVisible(true);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre d'enregistrement
                new BavardRegisterGUI(batiment).setVisible(true);
            }
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        centerPanel.add(conciergeLabel);
        centerPanel.add(welcomeLabel);
        centerPanel.add(buttonPanel);

        add(centerPanel, BorderLayout.CENTER);

        // On centre la fenêtre
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }
}