import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BavardLoginGUI extends JFrame {
    private JTextField nomBavardField;
    private JPasswordField motDePasseField;
    private JButton loginButton;

    public BavardLoginGUI(Batiment batiment) {
        setTitle("Connexion aux bavards");
        setSize(400, 250);
        setLayout(new GridLayout(5, 1));

        // Champ pour saisir le nom du Bavard
        JPanel nomBavardPanel = new JPanel(new FlowLayout());
        nomBavardField = new JTextField(15);
        nomBavardPanel.add(new JLabel("Nom du Bavard: "));
        nomBavardPanel.add(nomBavardField);
        add(nomBavardPanel);

        // Champ pour saisir le mot de passe
        JPanel motDePassePanel = new JPanel(new FlowLayout());
        motDePasseField = new JPasswordField(15);
        motDePassePanel.add(new JLabel("Mot de passe: "));
        motDePassePanel.add(motDePasseField);
        add(motDePassePanel);

        // Bouton pour se connecter en tant que Bavard
        JPanel loginPanel = new JPanel(new FlowLayout());
        loginButton = new JButton("Se Connecter");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBavard = nomBavardField.getText();
                String motDePasse = new String(motDePasseField.getPassword());

                if (nomBavard.isEmpty() || motDePasse.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Bavard bavard = batiment.authenticateBavard(nomBavard, motDePasse);
                if (bavard != null) {
                    JOptionPane.showMessageDialog(null, "Connexion réussie!");
                    new BavardGUI(bavard, batiment).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect!", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginPanel.add(loginButton);
        add(loginPanel);

        setVisible(true);
    }
}