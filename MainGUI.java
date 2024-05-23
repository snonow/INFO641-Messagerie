import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private Batiment batiment;
    private JMenuBar menuBar;
    
    public MainGUI() {
        // Initialize Batiment and Concierge
        batiment = new Batiment(new Concierge("Insta"));
        
        // Set up the main GUI frame
        setTitle("Welcome to " + batiment.getConcierge().getNom());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create the menu bar and menu items
        createMenuBar();

        // Create the central panel with the concierge's name and welcome message
        createCenterPanel();

        // Make the frame visible
        setVisible(true);
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        // Define menu items and their actions
        String[] menuItemNames = {"Gestion du concierge", "Gestion des bavards", "Option"};
        ActionListener[] menuActions = {
            e -> new GestionConciergeGUI(batiment).setVisible(true),
            e -> new GestionBavardsGUI(batiment).setVisible(true),
            e -> showMessage("Option is not yet implemented.")
        };

        // Add menu items to the menu bar
        for (int i = 0; i < menuItemNames.length; i++) {
            JMenuItem menuItem = new JMenuItem(menuItemNames[i]);
            menuItem.addActionListener(menuActions[i]);
            menuBar.add(menuItem);
        }

        setJMenuBar(menuBar);
    }

    private void createCenterPanel() {
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 0, 20));

        JLabel conciergeLabel = new JLabel("Concierge: " + batiment.getConcierge().getNom(), SwingConstants.CENTER);
        JLabel welcomeLabel = new JLabel("Welcome to the Bavards management application!", SwingConstants.CENTER);

        // Create buttons for login and registration
        JButton loginButton = new JButton("Connexion");
        loginButton.addActionListener(e -> new BavardLoginGUI(batiment).setVisible(true));

        JButton registerButton = new JButton("Enregistrement");
        registerButton.addActionListener(e -> new BavardRegisterGUI(batiment).setVisible(true));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        centerPanel.add(conciergeLabel);
        centerPanel.add(welcomeLabel);
        centerPanel.add(buttonPanel);

        add(centerPanel, BorderLayout.CENTER);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
