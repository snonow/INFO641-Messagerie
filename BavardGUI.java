import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BavardGUI extends JFrame {
    private JTextArea messageArea;
    private JTextField sujetField;
    private JTextField corpsField;
    private JButton envoyerMessageButton;
    private JButton abonnerConciergeButton; // Bouton pour s'abonner au concierge du bâtiment

    public BavardGUI(Bavard bavard, Batiment batiment) {
        setTitle("Bavard: " + bavard.getNom());
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialisation des composants
        messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.CENTER);
        JPanel messagePanel = new JPanel();
        sujetField = new JTextField();
        corpsField = new JTextField();
        envoyerMessageButton = new JButton("Envoyer Message");
        messagePanel.setLayout(new GridLayout(4, 2));
        messagePanel.add(new JLabel("Sujet:"));
        messagePanel.add(sujetField);
        messagePanel.add(new JLabel("Corps:"));
        messagePanel.add(corpsField);
        messagePanel.add(new JLabel(""));
        messagePanel.add(envoyerMessageButton);
        messagePanel.add(new JLabel(""));
        // Ajout du bouton pour s'abonner au concierge si pas déjà fait
        if (bavard.getConcierge() == null) {
            abonnerConciergeButton = new JButton("S'abonner au Concierge");
            messagePanel.add(abonnerConciergeButton); // Ajout du bouton pour s'abonner
        }
        add(messagePanel, BorderLayout.SOUTH);

        // Gestion des événements
        envoyerMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujet = sujetField.getText();
                String corps = corpsField.getText();
                if (batiment.getConcierge() != null) {
                    bavard.sendPapotage(sujet, corps);
                } else {
                    bavard.sendPapotage(sujet, corps, batiment.getConcierge());
                }
                afficherMessage("Message envoyé au Concierge");
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
