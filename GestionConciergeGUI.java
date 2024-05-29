import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GestionConciergeGUI extends JFrame {
    private Batiment batiment;
    private JTextArea conciergeArea;
    private JPanel messagesDisplayPanel;

    public GestionConciergeGUI(Batiment batiment) {
        this.batiment = batiment;

        setTitle("Messages reçus : Concierge " + batiment.getConcierge().getNom());
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(rootPane);

        // Initialisation des composants
        conciergeArea = new JTextArea(10, 30);
        conciergeArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(conciergeArea);
        add(scrollPane, BorderLayout.CENTER);

        // Nom du concierge
        JPanel nomConciergePanel = new JPanel();
        nomConciergePanel.setLayout(new GridLayout(2, 1));
        nomConciergePanel.add(new JLabel("Concierge : " + batiment.getConcierge().getNom(), SwingConstants.CENTER));
        nomConciergePanel.add(new JLabel(""));
        add(nomConciergePanel, BorderLayout.NORTH);

        // Messages disponibles
        JPanel messagesPanel = new JPanel();
        messagesPanel.setLayout(new BorderLayout());

        messagesDisplayPanel = new JPanel();
        messagesDisplayPanel.setLayout(new BoxLayout(messagesDisplayPanel, BoxLayout.Y_AXIS));
        JScrollPane messagesScrollPane = new JScrollPane(messagesDisplayPanel);
        messagesPanel.add(messagesScrollPane, BorderLayout.CENTER);
        add(messagesPanel, BorderLayout.WEST);

        // Afficher les messages reçus par le concierge
        afficherMessages();

        // Menu de navigation
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem gestionBavardsItem = new JMenuItem("Gestion des Bavards");
        gestionBavardsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GestionBavardsGUI(batiment).setVisible(true);
            }
        });
        menu.add(gestionBavardsItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void afficherMessages() {
        messagesDisplayPanel.removeAll();
        List<PapotageEvent> listPapotages = batiment.getConcierge().getListPapotageEvents();
        for (PapotageEvent papotage : listPapotages) {
            JPanel messagePanel = new JPanel(new BorderLayout());
            JLabel messageLabel = new JLabel("<html>" + papotage.toString().replaceAll("\n", "<br>") + "</html>");
            messagePanel.add(messageLabel, BorderLayout.CENTER);
            JButton supprimerButton = new JButton("Supprimer");
            supprimerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    batiment.getConcierge().removePapotageEvent(papotage);
                    afficherMessages();
                }
            });
            messagePanel.add(supprimerButton, BorderLayout.EAST);
            messagesDisplayPanel.add(messagePanel);
        }
        messagesDisplayPanel.revalidate();
        messagesDisplayPanel.repaint();
    }
}
