package frontend;

import backend.datamanager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login_UI extends JFrame {
    private JTextField usernamefield;
    private JRadioButton showPasswordButton;
    private JButton logInButton;
    private JPasswordField passwordfield;
    private JPanel LoginPanel;
    private JLabel Title;

    int Attempts;

    datamanager dt = new datamanager();

    public Login_UI() {
        setContentPane(LoginPanel);
        setTitle("Advanced Tuition Centre");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(375,300);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        Attempts = 3;

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String passwordStr = new String(passwordfield.getPassword());

                if (dt.login(usernamefield.getText(), passwordStr)) {
                    dispose();
                    //JOptionPane.showMessageDialog(null,"Login Successful");
                } else {
                    Attempts -= 1;

                    if (Attempts < 0) {
                        dispose();
                        JOptionPane.showMessageDialog(null,
                                "Login failed. You have exceeded the maximum number of attempts."
                        );
                    } else {
                        JOptionPane.showMessageDialog(null,"Invalid Username Or Password");
                    }
                }
            }
        });
        showPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordButton.isSelected()){
                    passwordfield.setEchoChar((char) 0);
                } else {
                    passwordfield.setEchoChar('•');
                }
            }
        });
    }
}
