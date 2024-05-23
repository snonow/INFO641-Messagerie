import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BavardRegisterGUI extends JFrame {
    private JTextField nomBavardField;
    private JPasswordField motDePasseField;
    private JPasswordField confirmationMotDePasseField;
    private JButton creerBavardButton;

    public BavardRegisterGUI(Batiment batiment) {
        // Set up the frame
        setTitle("Inscription");
        setSize(400, 250);
        setLayout(new GridLayout(5, 1));
        setLocationRelativeTo(null);

        // Create and add the Bavard name input field
        nomBavardField = new JTextField(15);
        addInputField("Nom du Bavard:", nomBavardField);

        // Create and add the password input field
        motDePasseField = new JPasswordField(15);
        addInputField("Mot de passe:", motDePasseField);

        // Create and add the password confirmation input field
        confirmationMotDePasseField = new JPasswordField(15);
        addInputField("Confirmer le mot de passe:", confirmationMotDePasseField);

        // Create and add the button to create a Bavard
        creerBavardButton = new JButton("Créer Bavard");
        creerBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCreateBavard(batiment);
            }
        });
        JPanel creerBavardPanel = new JPanel(new FlowLayout());
        creerBavardPanel.add(creerBavardButton);
        add(creerBavardPanel);

        // Add key listener to all input fields to handle Enter key press
        KeyAdapter enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleCreateBavard(batiment);
                }
            }
        };
        nomBavardField.addKeyListener(enterKeyListener);
        motDePasseField.addKeyListener(enterKeyListener);
        confirmationMotDePasseField.addKeyListener(enterKeyListener);

        setVisible(true);
    }

    // Helper method to add input fields to the frame
    private void addInputField(String label, JTextField textField) {
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(new JLabel(label));
        panel.add(textField);
        add(panel);
    }

    // Method to handle the creation of a new Bavard
    private void handleCreateBavard(Batiment batiment) {
        String nomBavard = nomBavardField.getText();
        String motDePasse = new String(motDePasseField.getPassword());
        String confirmationMotDePasse = new String(confirmationMotDePasseField.getPassword());

        // Validate input fields
        if (nomBavard.isEmpty() || motDePasse.isEmpty() || confirmationMotDePasse.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!motDePasse.equals(confirmationMotDePasse)) {
            JOptionPane.showMessageDialog(this, "Les mots de passe ne correspondent pas!", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create and register the new Bavard
        Bavard bavard = new Bavard(nomBavard, motDePasse);
        if (batiment.getListBavards().contains(bavard)) {
            JOptionPane.showMessageDialog(this, "Le bavard " + nomBavard + " existe déjà!", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
            batiment.addBavard(bavard);
            JOptionPane.showMessageDialog(this, "Bavard " + nomBavard + " créé avec succès!");
            setVisible(false);
            new BavardGUI(bavard, batiment).setVisible(true);
        }

        // Clear input fields after creation
        nomBavardField.setText("");
        motDePasseField.setText("");
        confirmationMotDePasseField.setText("");
    }
}
