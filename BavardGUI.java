import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class BavardGUI extends JFrame {
    private Batiment batiment;
    private JTextField sujetField;
    private JTextField corpsField;
    private JButton envoyerMessageButton;
    private JButton abonnerConciergeButton;
    private Timer updateTimer;
    private JPanel onlineUsersPanel;
    private JPanel otherMessagesPanel;
    private JPanel myMessagesPanel;

    public BavardGUI(Bavard bavard, Batiment batiment) {
        this.batiment = batiment;
        batiment.addOnlineBavard(bavard);
        setTitle("Bavard: " + bavard.getNom());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the panel for input fields and buttons
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        sujetField = new JTextField();
        corpsField = new JTextField();
        envoyerMessageButton = new JButton("Envoyer Message");

        // Add components to the input panel
        inputPanel.add(new JLabel("Subject:")); // Label for subject field
        inputPanel.add(sujetField);
        inputPanel.add(new JLabel("Body:")); // Label for message body field
        inputPanel.add(corpsField);
        inputPanel.add(new JLabel("")); // Empty cell for alignment
        inputPanel.add(envoyerMessageButton);
        inputPanel.add(new JLabel("")); // Empty cell for alignment

        // Add subscription button if not already subscribed to the concierge
        if (bavard.getConcierge() == null) {
            abonnerConciergeButton = new JButton("Subscribe to Concierge");
            inputPanel.add(abonnerConciergeButton);
        }

        // Panel for online users
        onlineUsersPanel = new JPanel();
        onlineUsersPanel.setLayout(new BoxLayout(onlineUsersPanel, BoxLayout.Y_AXIS));
        JScrollPane onlineUsersScrollPane = new JScrollPane(onlineUsersPanel);
        onlineUsersScrollPane.setPreferredSize(new Dimension(200, 600));

        // Panel for other users' messages
        otherMessagesPanel = new JPanel();
        otherMessagesPanel.setLayout(new BoxLayout(otherMessagesPanel, BoxLayout.Y_AXIS));
        JScrollPane otherMessagesScrollPane = new JScrollPane(otherMessagesPanel);
        otherMessagesScrollPane.setPreferredSize(new Dimension(400, 300));

        // Panel for current user's messages
        myMessagesPanel = new JPanel();
        myMessagesPanel.setLayout(new BoxLayout(myMessagesPanel, BoxLayout.Y_AXIS));
        JScrollPane myMessagesScrollPane = new JScrollPane(myMessagesPanel);
        myMessagesScrollPane.setPreferredSize(new Dimension(400, 300));

        // Main panel to hold everything
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Add labels for each section
        JLabel onlineUsersLabel = new JLabel("Online Users");
        JLabel messagesLabel = new JLabel("Messages");
        JLabel inputLabel = new JLabel("Input");

        // Align labels
        onlineUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add labels to main panel
        mainPanel.add(onlineUsersLabel, BorderLayout.WEST);
        mainPanel.add(messagesLabel, BorderLayout.CENTER);
        mainPanel.add(inputLabel, BorderLayout.SOUTH);

        // Add subpanels to main panel
        mainPanel.add(onlineUsersScrollPane, BorderLayout.WEST);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        JPanel messagesContainer = new JPanel(new GridLayout(2, 1));
        messagesContainer.add(otherMessagesScrollPane);
        messagesContainer.add(myMessagesScrollPane);
        mainPanel.add(messagesContainer, BorderLayout.EAST);

        add(mainPanel);

        // Action listener for sending messages
        envoyerMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujet = sujetField.getText();
                String corps = corpsField.getText();
                bavard.sendPapotage(sujet, corps, batiment.getConcierge());
                updateMessageArea(bavard);
            }
        });

        // Action listener for subscribing to the concierge
        if (abonnerConciergeButton != null) {
            abonnerConciergeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bavard.setEcouteConcierge(batiment.getConcierge());
                    JOptionPane.showMessageDialog(null, "Subscribed to Building Concierge!\n\nReconnect to apply changes.");
                    dispose(); // Close the current window
                }
            });
        }

        // Timer to periodically refresh the message list if subscribed to the concierge
        updateTimer = new Timer(250, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (bavard.getConcierge() != null) {
                    updateMessageArea(bavard);
                    updateOnlineUsers();
                }
            }
        });
        updateTimer.start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                batiment.removeOnlineBavard(bavard); // Remove the Bavard from the online list
                dispose(); // Close the window
            }

            @Override
            public void windowClosed(WindowEvent e) {
                batiment.removeOnlineBavard(bavard); // Ensure the Bavard is removed when window is closed
            }
        });

        setVisible(true);
    }

    // Update the message area with the latest messages from the concierge
    private void updateMessageArea(Bavard bavard) {
        otherMessagesPanel.removeAll(); // Clear other users' messages area
        myMessagesPanel.removeAll(); // Clear current user's messages area

        for (PapotageEvent papotage : batiment.getConcierge().getListPapotageEvents()) {
            JButton messageButton = new JButton(papotage.getSujet() + ": " + papotage.getCorps());
            // Add action listener to open MessagePopUpGUI on button click
            messageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new MessagePopUpGUI(papotage.getSujet(), papotage.getCorps()).setVisible(true);
                }
            });

            if (papotage.getSender().equals(bavard)) {
                myMessagesPanel.add(messageButton);
            } else {
                otherMessagesPanel.add(messageButton);
            }
        }
        otherMessagesPanel.revalidate();
        otherMessagesPanel.repaint();
        myMessagesPanel.revalidate();
        myMessagesPanel.repaint();
    }

    // Update the online users panel with the list of online users
    private void updateOnlineUsers() {
        onlineUsersPanel.removeAll();
        List<Bavard> onlineBavards = batiment.getOnlineBavards();
        for (Bavard onlineBavard : onlineBavards) {
            onlineUsersPanel.add(new JLabel(onlineBavard.getNom()));
        }
        onlineUsersPanel.revalidate();
        onlineUsersPanel.repaint();
    }
}
