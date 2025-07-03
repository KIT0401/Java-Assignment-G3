package frontend;

import backend.admin;
import backend.datamanager;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

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
    private JPanel ManageReceptionistFrame;
    private JPanel ManageTutorFrame;
    private JPanel IncomeReportFrame;
    private JLabel ShowUsername;
    private JLabel ShowPassword;
    private JLabel ShowAddress;
    private JLabel ShowContactNumber;
    private JLabel ShowEmail;
    private JLabel ShowICPassport;
    private JTextField ReceptionistUsernameField;
    private JButton AddReceptionistButton;
    private JTable ReceptionistTable;
    private JButton EditReceptionistButton;
    private JButton DeleteReceptionistButton;
    private JLabel ShowReceptionistID;
    private JTextField ReceptionistPasswordField;
    private JButton SaveReceptionistButton;
    private JComboBox comboBox1;
    private JRadioButton malayRadioButton;
    private JButton DeleteTutorButton;
    private JButton EditTutorButton;
    private JButton AddTutorButton;
    private JButton SaveTutorButton;

    private DefaultTableModel model;

    private static admin ADMIN;

    public Admin_UI(){
        setContentPane(AdminPanel);
        setTitle("Advanced Tuition Centre [Admin]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650,450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        IncomeReportFrame.setVisible(false);
        ManageTutorFrame.setVisible(true);
        ManageReceptionistFrame.setVisible(false);
        ProfileFrame.setVisible(false);

        SaveButton.setVisible(false);
        DeleteReceptionistButton.setVisible(true);
        EditReceptionistButton.setVisible(true);

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
                ManageTutorFrame.setVisible(false);
                ManageReceptionistFrame.setVisible(false);
                ProfileFrame.setVisible(false);
            }
        });
        manageTutorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeReportFrame.setVisible(false);
                ManageTutorFrame.setVisible(true);
                ManageReceptionistFrame.setVisible(false);
                ProfileFrame.setVisible(false);
            }
        });
        manageReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeReportFrame.setVisible(false);
                ManageTutorFrame.setVisible(false);
                ManageReceptionistFrame.setVisible(true);
                ProfileFrame.setVisible(false);

                RefreshManageReceptionist();
            }
        });
        ProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IncomeReportFrame.setVisible(false);
                ManageTutorFrame.setVisible(false);
                ManageReceptionistFrame.setVisible(false);
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

        ReceptionistTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = ReceptionistTable.getSelectedRow();

                    if (selectedRow != -1) {
//                        String id = model.getValueAt(selectedRow, 0).toString();
//                        String name = model.getValueAt(selectedRow, 1).toString();
//                        String dept = model.getValueAt(selectedRow, 2).toString();
//                        System.out.println(id + name + dept);

                        EditReceptionistButton.setVisible(true);
                        DeleteReceptionistButton.setVisible(true);
                    }
                }
            }
        });

        AddReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Object> result = ADMIN.addReceptionist(
                        ReceptionistUsernameField.getText(),
                        ReceptionistPasswordField.getText()
                );

                if (result.getFirst().equals(true)) {
                    model.addRow(new Object[]{result.get(2), ReceptionistUsernameField.getText(), ReceptionistPasswordField.getText()});

                    int newRow = model.getRowCount() - 1;
                    ReceptionistTable.setRowSelectionInterval(newRow, newRow);
                    ReceptionistTable.scrollRectToVisible(ReceptionistTable.getCellRect(newRow, 0, true));

                    ShowReceptionistID.setText(result.get(2).toString());

                    SaveReceptionistButton.setVisible(true);
                    DeleteReceptionistButton.setVisible(true);
                    EditReceptionistButton.setVisible(true);
                }

                JOptionPane.showMessageDialog(null, result.get(1));
            }
        });

        EditReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ReceptionistTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = model.getValueAt(selectedRow, 0).toString();
                    String username = model.getValueAt(selectedRow, 1).toString();
                    String password = model.getValueAt(selectedRow, 2).toString();

                    ShowReceptionistID.setText(id);
                    ReceptionistUsernameField.setText(username);
                    ReceptionistPasswordField.setText(password);

                } else {
                    JOptionPane.showMessageDialog(null,"Please select a row first!");
                }
            }
        });

        DeleteReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ReceptionistTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = model.getValueAt(selectedRow, 0).toString();
                    String username = model.getValueAt(selectedRow, 1).toString();
                    // String password = model.getValueAt(selectedRow, 2).toString();

                    int result = JOptionPane.showConfirmDialog(null,"Are you sure want delete "+ username +"?","Confirm",JOptionPane.YES_NO_OPTION);

                    if (result == 0) {
                        boolean result2 = ADMIN.deleteReceptionist(id);

                        if (result2) {
                            for (int i = 0; i < model.getRowCount(); i++) {
                                String rowId = model.getValueAt(i, 0).toString();
                                if (Objects.equals(rowId, id)) {
                                    model.removeRow(i);

                                    if (ShowReceptionistID.getText().equalsIgnoreCase(id)) {
                                        ShowReceptionistID.setText("---");
                                        ReceptionistUsernameField.setText("");
                                        ReceptionistPasswordField.setText("");
                                    }

                                    JOptionPane.showMessageDialog(null,"Successful Deleted Receptionist " + username + "'s Data");
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select a row and press edit button first!");
                }
            }
        });

        SaveReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ShowReceptionistID.getText().equalsIgnoreCase("---")) {
                    if (ADMIN.saveReceptionist(ShowReceptionistID.getText(),ReceptionistUsernameField.getText(),ReceptionistPasswordField.getText())) {
                        JOptionPane.showMessageDialog(null,"Successful Saved Receptionist ID" + ShowReceptionistID.getText() + "'s Data");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select an row and press edit button first.");
                }
            }
        });
    }

    public void RefreshManageReceptionist(){
        String [] columnNames = {"ID","Username","Password"};

        model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ReceptionistTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ReceptionistTable.getTableHeader().setResizingAllowed(false);

        ArrayList<String> receptionistData = datamanager.loadData("receptionists.txt");

        for (String tutorLine : receptionistData) {
            String [] split_data = tutorLine.split(",");
            ArrayList<Object> userData = datamanager.getData("users.txt",split_data[0]);

            if (userData.get(3).equals(false)) {
                continue;
            }

            model.addRow(new Object[]{split_data[0],userData.get(1),userData.get(2)});
        }

        ReceptionistTable.setModel(model);
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
    }

    public static void main(String[] args) {

        Admin_UI UI = new Admin_UI();
    }
}
