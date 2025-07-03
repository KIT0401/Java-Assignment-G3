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
    private JButton ManageStudentButton;
    private JButton StudentPaymentButton;
    private JTable table1;
    private JTextField textField1;
    private JTextField studentPaymentTextField;
    private JPanel AddStudentDetails;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JCheckBox malayCheckBox;
    private JCheckBox chineseCheckBox;
    private JCheckBox englishCheckBox;
    private JCheckBox scienceCheckBox;
    private JCheckBox mathematicsCheckBox;
    private JButton submitButton;
    private JButton DeleteStudentButton;
    private JButton AddStudentButton;
    private JPanel StudentDetailTable;
    private JPanel StudentPayment;
    private JPanel TestLabel;
    private JPanel UpdateProfile;
    private JTextField ReceptionistUsernameTF;
    private JTextField ReceptionistPasswordTF;
    private JTextField ReceptionistAddressTF;
    private JTextField ReceptionistContactTF;
    private JTextField ReceptionistEmailTF;
    private JPanel UpdateEmail;
    private JPanel UpdateContact;
    private JPanel UpdateAddress;
    private JPanel UpdateIC;
    private JPanel UpdatePassword;
    private JPanel UpdateUsername;
    private JButton CancelUpdateButton;
    private JButton UpdateButton;
    private JTextField ReceptionistICTF;
    private JButton EditProfileButton;
    private JPanel UpdateCancelButtons;
    private JLabel UsernameField;
    private JLabel PasswordField;
    private JLabel ICField;
    private JLabel AddressField;
    private JLabel EmailField;
    private JLabel ContactField;

    private static receptionist RECEPTIONIST;

    public Receptionist_UI() {
        setContentPane(ReceptionistPanel);
        setTitle("Advanced Tuition Centre [Receptionist]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        TestLabel.setVisible(false);
        StudentDetailTable.setVisible(false);
        AddStudentDetails.setVisible(false);
        StudentPayment.setVisible(false);
        UpdateProfile.setVisible(false);

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

        ManageStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestLabel.setVisible(false);
                StudentDetailTable.setVisible(true);
                AddStudentDetails.setVisible(false);
                StudentPayment.setVisible(false);
                UpdateProfile.setVisible(false);
            }
        });
        AddStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestLabel.setVisible(false);
                StudentDetailTable.setVisible(false);
                AddStudentDetails.setVisible(true);
                StudentPayment.setVisible(false);
                UpdateProfile.setVisible(false);
            }
        });
        StudentPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestLabel.setVisible(false);
                StudentDetailTable.setVisible(false);
                AddStudentDetails.setVisible(false);
                StudentPayment.setVisible(true);
                UpdateProfile.setVisible(false);
            }
        });
        UpdateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestLabel.setVisible(false);
                StudentDetailTable.setVisible(false);
                AddStudentDetails.setVisible(false);
                StudentPayment.setVisible(false);
                UpdateProfile.setVisible(true);

                ReceptionistUsernameTF.setVisible(false);
                ReceptionistPasswordTF.setVisible(false);
                ReceptionistICTF.setVisible(false);
                ReceptionistAddressTF.setVisible(false);
                ReceptionistEmailTF.setVisible(false);
                ReceptionistContactTF.setVisible(false);
                UpdateCancelButtons.setVisible(false);
            }
        });

        EditProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReceptionistUsernameTF.setVisible(true);
                ReceptionistPasswordTF.setVisible(true);
                ReceptionistICTF.setVisible(true);
                ReceptionistAddressTF.setVisible(true);
                ReceptionistEmailTF.setVisible(true);
                ReceptionistContactTF.setVisible(true);
                UpdateCancelButtons.setVisible(true);

                UsernameField.setVisible(false);
                PasswordField.setVisible(false);
                ICField.setVisible(false);
                AddressField.setVisible(false);
                EmailField.setVisible(false);
                ContactField.setVisible(false);
            }
        });
    }

    public void UpdateProfile(){

    }

    public void Run(receptionist system){
        RECEPTIONIST = system;
        WelcomeTitle.setText("Hello, " + RECEPTIONIST.getUsername() + " !");
    }
}
