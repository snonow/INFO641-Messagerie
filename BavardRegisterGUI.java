import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BavardRegisterGUI extends JFrame {
    private JTextField nomBavardField;
    private JPasswordField motDePasseField;
    private JPasswordField confirmationMotDePasseField;
    private JButton creerBavardButton;

    public BavardRegisterGUI(Batiment batiment) {

        setTitle("Inscription");
        setSize(400, 250);
        setLayout(new GridLayout(5, 1));
        setLocationRelativeTo(rootPane);

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

        // Champ pour confirmer le mot de passe
        JPanel confirmationMotDePassePanel = new JPanel(new FlowLayout());
        confirmationMotDePasseField = new JPasswordField(15);
        confirmationMotDePassePanel.add(new JLabel("Confirmer le mot de passe: "));
        confirmationMotDePassePanel.add(confirmationMotDePasseField);
        add(confirmationMotDePassePanel);

        // Bouton pour créer un Bavard
        JPanel creerBavardPanel = new JPanel(new FlowLayout());
        creerBavardButton = new JButton("Créer Bavard");
        creerBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBavard = nomBavardField.getText();
                String motDePasse = new String(motDePasseField.getPassword());
                String confirmationMotDePasse = new String(confirmationMotDePasseField.getPassword());

                if (nomBavard.isEmpty() || motDePasse.isEmpty() || confirmationMotDePasse.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (!motDePasse.equals(confirmationMotDePasse)) {
                    JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas!", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Bavard bavard = new Bavard(nomBavard, motDePasse);
                if (batiment.getListBavards().contains(bavard)) {
                    JOptionPane.showMessageDialog(null, "Le bavard " + nomBavard + " existe déjà !", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    batiment.addBavard(bavard);
                    JOptionPane.showMessageDialog(null, "Bavard " + nomBavard + " créé avec succès!");
                    setVisible(false);
                    new BavardGUI(bavard, batiment).setVisible(true);
                }

                // Effacer les champs après la création
                nomBavardField.setText("");
                motDePasseField.setText("");
                confirmationMotDePasseField.setText("");
            }
        });
        creerBavardPanel.add(creerBavardButton);
        add(creerBavardPanel);

        setVisible(true);
    }
}