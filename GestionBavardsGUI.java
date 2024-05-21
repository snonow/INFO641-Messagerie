import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestionBavardsGUI extends JFrame {
    private Batiment batiment = new Batiment();

    private JTextArea messageArea;
    private JTextField nomBavardField;
    private JButton ajouterBavardButton;
    private JTextField sujetField;
    private JTextField corpsField;
    private JButton envoyerMessageButton;

    public GestionBavardsGUI() {
        setTitle("Gestion : Batiment A");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialisation des composants
        messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel ajoutPanel = new JPanel();
		ajoutPanel.setLayout(new GridLayout(3, 2));
		nomBavardField = new JTextField();
		ajouterBavardButton = new JButton("Ajouter Bavard");
		ajoutPanel.add(new JLabel("Nom du Bavard:", SwingConstants.CENTER));
		ajoutPanel.add(nomBavardField);
		ajoutPanel.add(ajouterBavardButton);
		add(ajoutPanel, BorderLayout.NORTH);


        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(3, 2));
        sujetField = new JTextField();
        corpsField = new JTextField();
        envoyerMessageButton = new JButton("Envoyer Message");
        messagePanel.add(new JLabel("Sujet:"));
        messagePanel.add(sujetField);
        messagePanel.add(new JLabel("Corps:"));
        messagePanel.add(corpsField);
        messagePanel.add(new JLabel(""));
        messagePanel.add(envoyerMessageButton);
        add(messagePanel, BorderLayout.SOUTH);

        // Initialisation des variables
        batiment.setConcierge(new Concierge("Concierge"));
        batiment.setListBavards(new ArrayList<>());

        // Gestion des événements
        ajouterBavardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBavard = nomBavardField.getText();
                Bavard bavard = new Bavard(nomBavard, batiment.getConcierge());
                batiment.addBavard(bavard);
                nomBavardField.setText("");
                afficherMessage("Bavard ajouté: " + nomBavard);
            }
        });

        envoyerMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujet = sujetField.getText();
                String corps = corpsField.getText();
                for (Bavard bavard : batiment.getListBavards()) {
                    bavard.sendPapotage(sujet, corps);
                }
                sujetField.setText("");
                corpsField.setText("");
                afficherMessage("Message envoyé à tous les Bavards");
            }
        });
    }

    private void afficherMessage(String message) {
        messageArea.append(message + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GestionBavardsGUI gui = new GestionBavardsGUI();
                gui.setVisible(true);
            }
        });
    }
}
