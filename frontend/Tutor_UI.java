package frontend;

import backend.tutor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tutor_UI extends JFrame {
    private JLabel WelcomeTitle;
    private JPanel Options;
    private JButton UpdateProfileButton;
    private JButton LogOutButton;
    private JPanel Frame;
    private JPanel TutorPanel;

    private static tutor TUTOR;

    public Tutor_UI() {
        setContentPane(TutorPanel);
        setTitle("Advanced Tuition Centre");
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

    public void Run(tutor system){
        TUTOR = system;
        WelcomeTitle.setText("Hello, " + TUTOR.getUsername() + " !");
    }
}
