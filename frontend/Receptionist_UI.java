package frontend;

import backend.receptionist;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Receptionist_UI extends JFrame {
    private JPanel ReceptionistPanel;
    private JLabel WelcomeTitle;
    private JPanel Options;
    private JButton UpdateProfileButton;
    private JButton LogOutButton;
    private JPanel Frame;

    private static receptionist RECEPTIONIST;

    public Receptionist_UI() {
        setContentPane(ReceptionistPanel);
        setTitle("Advanced Tuition Centre [Receptionist]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        LogOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure want to log out?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (result == 0) {
                    dispose();
                    Login_UI UI = new Login_UI();
                }
            }
        });
    }

    public void Run(receptionist system){
        RECEPTIONIST = system;
        WelcomeTitle.setText("Hello, " + RECEPTIONIST.getUsername() + " !");
    }
}
