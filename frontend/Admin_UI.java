package frontend;

import backend.admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Admin_UI extends JFrame {
    private JPanel AdminPanel;
    private JButton manageTutorButton;
    private JButton manageReceptionistButton;
    private JButton incomeReportButton;
    private JButton ProfileButton;
    private JButton LogOutButton;
    private JPanel Options;
    private JLabel WelcomeTitle;
    private JButton SaveButton;
    private JTextField UsernameField;
    private JTextField PasswordField;
    private JTextField ICField;
    private JTextField EmailField;
    private JTextField ContactNumberField;
    private JTextField AddressField;
    private JButton EditButton;
    private JPanel ProfileFrame;
    private JPanel ManageTutorFrame;
    private JPanel ManageReceptionistFrame;
    private JPanel IncomeReportFrame;
    private JLabel ShowUsername;
    private JLabel ShowPassword;
    private JLabel ShowAddress;
    private JLabel ShowContactNumber;
    private JLabel ShowEmail;
    private JLabel ShowICPassport;

    private static admin ADMIN;

    public Admin_UI(){
        setContentPane(AdminPanel);
        setTitle("Advanced Tuition Centre [Admin]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650,450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        LogOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,"Are you sure want to log out?","Confirm",JOptionPane.YES_NO_OPTION);

                if (result == 0) {
                    dispose();
                    Login_UI UI = new Login_UI();
                }
            }
        });
        incomeReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeReportFrame.setVisible(true);
                ManageReceptionistFrame.setVisible(false);
                ManageTutorFrame.setVisible(false);
                ProfileFrame.setVisible(false);
            }
        });
        manageTutorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeReportFrame.setVisible(false);
                ManageReceptionistFrame.setVisible(false);
                ManageTutorFrame.setVisible(true);
                ProfileFrame.setVisible(false);
            }
        });
        manageReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeReportFrame.setVisible(false);
                ManageReceptionistFrame.setVisible(true);
                ManageTutorFrame.setVisible(false);
                ProfileFrame.setVisible(false);
            }
        });
        ProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeReportFrame.setVisible(false);
                ManageReceptionistFrame.setVisible(false);
                ManageTutorFrame.setVisible(false);
                ProfileFrame.setVisible(true);

                RefreshProfile();
            }
        });
        EditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SaveButton.isVisible()){
                    SaveButton.setVisible(false);
                    EditButton.setText("Edit");
                } else {
                    SaveButton.setVisible(true);
                    EditButton.setText("Cancel");
                }

                RefreshProfile();
            }
        });

        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String NewUsername = UsernameField.getText();
                String NewPassword = PasswordField.getText();
                String NewIC = ICField.getText();
                String NewEmail = EmailField.getText();
                String NewContactNumber = ContactNumberField.getText();
                String NewAddress = AddressField.getText();

                String result = ADMIN.UpdateProfile(
                        NewUsername,
                        NewPassword,
                        NewIC,
                        NewEmail,
                        NewContactNumber,
                        NewAddress
                );

                JOptionPane.showMessageDialog(null,result);

                SaveButton.setVisible(false);
                EditButton.setText("Edit");

                RefreshProfile();
            }
        });

    }

    public void RefreshProfile(){
        String username = ADMIN.getUsername();
        String password = ADMIN.getPassword();

        String ic = ADMIN.getIc();
        String email = ADMIN.getEmail();
        String contact_number = ADMIN.getContact_number();
        String address = ADMIN.getAddress();

        if (SaveButton.isVisible()) {
            UsernameField.setText(username);
            PasswordField.setText(password);

            if (ic.equalsIgnoreCase("null")) {
                ICField.setText("");
            } else {
                ICField.setText(ic);
            }

            if (email.equalsIgnoreCase("null")) {
                EmailField.setText("");
            } else {
                EmailField.setText(email);
            }

            if (contact_number.equalsIgnoreCase("null")) {
                ContactNumberField.setText("");
            } else {
                ContactNumberField.setText(contact_number);
            }

            if (address.equalsIgnoreCase("null")) {
                AddressField.setText("");
            } else {
                AddressField.setText(address);
            }

            ShowUsername.setVisible(false);
            ShowPassword.setVisible(false);
            ShowICPassport.setVisible(false);
            ShowEmail.setVisible(false);
            ShowContactNumber.setVisible(false);
            ShowAddress.setVisible(false);

            UsernameField.setVisible(true);
            PasswordField.setVisible(true);
            ICField.setVisible(true);
            EmailField.setVisible(true);
            ContactNumberField.setVisible(true);
            AddressField.setVisible(true);

        } else {
            ShowUsername.setText(username);
            ShowPassword.setText("*".repeat(password.length()));

            if (ic.equalsIgnoreCase("null")){
                ShowICPassport.setText("----");
            } else {
                ShowICPassport.setText(ic);
            }

            if (email.equalsIgnoreCase("null")){
                ShowEmail.setText("----");
            } else {
                ShowEmail.setText(email);
            }

            if (contact_number.equalsIgnoreCase("null")){
                ShowContactNumber.setText("----");
            } else {
                ShowContactNumber.setText(contact_number);
            }

            if (address.equalsIgnoreCase("null")){
                ShowAddress.setText("----");
            } else {
                ShowAddress.setText(address);
            }

            ShowUsername.setVisible(true);
            ShowPassword.setVisible(true);
            ShowICPassport.setVisible(true);
            ShowEmail.setVisible(true);
            ShowContactNumber.setVisible(true);
            ShowAddress.setVisible(true);

            UsernameField.setVisible(false);
            PasswordField.setVisible(false);
            ICField.setVisible(false);
            EmailField.setVisible(false);
            ContactNumberField.setVisible(false);
            AddressField.setVisible(false);
        }

    }

    public void Run(admin system){
        ADMIN = system;
        WelcomeTitle.setText("Hello, " + ADMIN.getUsername() + " !");

        IncomeReportFrame.setVisible(false);
        ManageReceptionistFrame.setVisible(true);
        ManageTutorFrame.setVisible(false);
        ProfileFrame.setVisible(false);

        SaveButton.setVisible(false);
    }

    public static void main(String[] args) {
        Admin_UI UI = new Admin_UI();
    }
}
