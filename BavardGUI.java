import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BavardGUI extends JFrame {
    private Bavard bavard;
    private Batiment batiment;

    private JTextArea messageArea;
    private JTextField sujetField;
    private JTextField corpsField;
    private JButton envoyerMessageButton;
    private JButton abonnerConciergeButton; // Bouton pour s'abonner au concierge du bâtiment

    public BavardGUI(Bavard bavard, Batiment batiment) {
        this.bavard = bavard;
        this.batiment = batiment;

        setTitle("Bavard: " + bavard.getNom());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialisation des composants
        messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(4, 2)); // Ajout du bouton pour s'abonner au concierge
        sujetField = new JTextField();
        corpsField = new JTextField();
        envoyerMessageButton = new JButton("Envoyer Message");
        abonnerConciergeButton = new JButton("S'abonner au Concierge"); // Initialisation du bouton pour s'abonner
        messagePanel.add(new JLabel("Sujet:"));
        messagePanel.add(sujetField);
        messagePanel.add(new JLabel("Corps:"));
        messagePanel.add(corpsField);
        messagePanel.add(new JLabel(""));
        messagePanel.add(envoyerMessageButton);
        messagePanel.add(new JLabel(""));
        messagePanel.add(abonnerConciergeButton); // Ajout du bouton pour s'abonner
        add(messagePanel, BorderLayout.SOUTH);

        // Gestion des événements
        envoyerMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujet = sujetField.getText();
                String corps = corpsField.getText();
                if (bavard.getConcierge() != null) {
                    bavard.sendPapotage(sujet, corps);
                    afficherMessage("Message envoyé au Concierge");
                } else {
                    JOptionPane.showMessageDialog(null, "Le Bavard n'a pas de Concierge associé !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Gestion de l'événement pour s'abonner au concierge
        abonnerConciergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bavard.setEcouteConcierge(batiment.getConcierge());
                JOptionPane.showMessageDialog(null, "Abonnement réussi au Concierge du bâtiment !");
            }
        });
    }

    private void afficherMessage(String message) {
        messageArea.append(message + "\n");
    }
}
