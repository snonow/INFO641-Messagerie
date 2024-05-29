import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class MessagePopUpGUI extends JFrame {
    private JTextArea messageArea;

    public MessagePopUpGUI(String sujet, String corps) {
        setTitle(sujet);
        setSize(300, 200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(rootPane);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        messageArea.setText("Corps:\n" + corps);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // Fermer la fenêtre automatiquement si elle perd le focus
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                dispose();
            }
        });
    }
}
