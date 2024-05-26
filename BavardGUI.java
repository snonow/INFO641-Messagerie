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

        // Initialize panels
        JPanel inputPanel = createInputPanel(bavard);
        JPanel mainPanel = createMainPanel(inputPanel);
        JPanel messagesContainer = createMessagesContainer();
        JScrollPane onlineUsersScrollPane = createScrollPaneForPanel(onlineUsersPanel);
        JScrollPane otherMessagesScrollPane = createScrollPaneForPanel(otherMessagesPanel);
        JScrollPane myMessagesScrollPane = createScrollPaneForPanel(myMessagesPanel);

        // Add components to main panel
        mainPanel.add(onlineUsersScrollPane, BorderLayout.WEST);
        mainPanel.add(inputPanel, BorderLayout.SOUTH);
        mainPanel.add(messagesContainer, BorderLayout.EAST);
        add(mainPanel);

        // Register action listeners
        registerActionListeners(bavard);

        // Start update timer
        startUpdateTimer(bavard);

        // Register window closing event
        addWindowClosingListener(bavard);

        setVisible(true);
    }

    private JPanel createInputPanel(Bavard bavard) {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        sujetField = new JTextField();
        corpsField = new JTextField();
        envoyerMessageButton = new JButton("Send Message");

        // Add components to the input panel
        inputPanel.add(new JLabel("Subject:"));
        inputPanel.add(sujetField);
        inputPanel.add(new JLabel("Body:"));
        inputPanel.add(corpsField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(envoyerMessageButton);

        // Add subscription button if not already subscribed to the concierge
        if (bavard.getConcierge() == null) {
            abonnerConciergeButton = new JButton("Subscribe to Concierge");
            inputPanel.add(abonnerConciergeButton);
        }
        return inputPanel;
    }

    private JPanel createMainPanel(JPanel inputPanel) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel onlineUsersLabel = new JLabel("Online Users");
        JLabel messagesLabel = new JLabel("Messages");
        JLabel inputLabel = new JLabel("Input");
        onlineUsersPanel = new JPanel();
        onlineUsersPanel.setLayout(new BoxLayout(onlineUsersPanel, BoxLayout.Y_AXIS));

        onlineUsersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messagesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        inputLabel.setHorizontalAlignment(SwingConstants.CENTER);

        mainPanel.add(onlineUsersLabel, BorderLayout.WEST);
        mainPanel.add(messagesLabel, BorderLayout.CENTER);
        mainPanel.add(inputLabel, BorderLayout.SOUTH);
        return mainPanel;
    }

    private JPanel createMessagesContainer() {
        JPanel messagesContainer = new JPanel(new GridLayout(2, 1));
        otherMessagesPanel = new JPanel();
        otherMessagesPanel.setLayout(new BoxLayout(otherMessagesPanel, BoxLayout.Y_AXIS));
        myMessagesPanel = new JPanel();
        myMessagesPanel.setLayout(new BoxLayout(myMessagesPanel, BoxLayout.Y_AXIS));
        messagesContainer.add(createScrollPaneForPanel(otherMessagesPanel));
        messagesContainer.add(createScrollPaneForPanel(myMessagesPanel));
        return messagesContainer;
    }

    private JScrollPane createScrollPaneForPanel(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        return scrollPane;
    }

    private void registerActionListeners(Bavard bavard) {
        envoyerMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sujet = sujetField.getText();
                String corps = corpsField.getText();
                bavard.sendPapotage(sujet, corps, batiment.getConcierge());
                updateMessageArea(bavard);
            }
        });

        if (abonnerConciergeButton!= null) {
            abonnerConciergeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bavard.setEcouteConcierge(batiment.getConcierge());
                    JOptionPane.showMessageDialog(null, "Subscribed to Building Concierge!\n\nReconnect to apply changes.");
                    dispose();
                }
            });
        }
        
        private void startUpdateTimer(Bavard bavard) {
            updateTimer = new Timer(250, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateMessageArea(bavard);
                    updateOnlineUsers();
                }
            });
            updateTimer.start();
        }
        
        private void addWindowClosingListener(Bavard bavard) {
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    batiment.removeOnlineBavard(bavard);
                    dispose();
                }
            });
        }
        
        private void updateMessageArea(Bavard bavard) {
            otherMessagesPanel.removeAll();
            myMessagesPanel.removeAll();
            Boolean resultat_bavard = (bavard.getConcierge()!= null);
        
            for (PapotageEvent papotage : batiment.getConcierge().getListPapotageEvents()) {
                JButton messageButton = new JButton(papotage.getSujet() + ": " + papotage.getCorps());
                messageButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new MessagePopUpGUI(papotage.getSujet(), papotage.getCorps()).setVisible(true);
                    }
                });
        
                if (papotage.getSender().equals(bavard)) {
                    myMessagesPanel.add(messageButton);
                } else if (resultat_bavard) {
                    otherMessagesPanel.add(messageButton);
                }
            }
            otherMessagesPanel.revalidate();
            otherMessagesPanel.repaint();
            myMessagesPanel.revalidate();
            myMessagesPanel.repaint();
        }
        
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