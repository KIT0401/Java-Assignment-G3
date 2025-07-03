package frontend;

import backend.datamanager;
import backend.tutor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Tutor_UI extends JFrame {
    private JLabel WelcomeTitle;
    private JPanel Options;
    private JButton UpdateProfileButton;
    private JButton LogOutButton;
    private JPanel Frame;
    private JPanel TutorPanel;
    private JButton AddClassButton;
    private JPanel UpdateProfileFrame;
    private JPanel AddClassFrame;
    private JTextField SubTextBox;
    private JTextField SubjectFeeTextField;
    private JComboBox ModeBox;
    private JPanel subject;
    private JComboBox StudentLevelBox;
    private JPanel level;
    private JButton createButton;
    private JComboBox WeekBox;
    private JComboBox TimeBox;
    private JComboBox ClassTypeBox;
    private JLabel checkSub;
    private JLabel checkType;
    private JLabel checkLevel;
    private JLabel checkCos;
    private JLabel checkSchedule;
    private JLabel checkMode;
    private JTextField UserNameText;
    private JTextField PasswordText;
    private JTextField ICText;
    private JTextField EmailText;
    private JTextField ContactNumberText;
    private JTextField AddressText;
    private JLabel ShowUserName;
    private JLabel ShowPassword;
    private JLabel ShowEmail;
    private JLabel ShowContactNumber;
    private JLabel ShowAddress;
    private JButton SaveButton;
    private JButton EditButton;
    private JLabel ShowIC;
    private JButton EditClassButton;
    private JPanel EditClassFrame;
    private JTable EditTable;

    private static tutor TUTOR;

    public Tutor_UI() {
        setContentPane(TutorPanel);
        setTitle("Advanced Tuition Centre");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        AddClassFrame.setVisible(false);
        UpdateProfileFrame.setVisible(false);

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

        AddClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClassFrame.setVisible(true);
                UpdateProfileFrame.setVisible(false);
                EditClassFrame.setVisible(false);
            }
        });

        UpdateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClassFrame.setVisible(false);
                UpdateProfileFrame.setVisible(true);
                SaveButton.setVisible(false);
                EditClassFrame.setVisible(false);
                UpdateProfile();
            }
        });

        EditClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClassFrame.setVisible(false);
                UpdateProfileFrame.setVisible(false);
                EditClassFrame.setVisible(true);
                insert_data_table();
            }
        });

// add option to class mode box
        ModeBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "< None >", "Online", "Physical", "Hybrid"
        }));

// add option to student level box
        StudentLevelBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "< None >", "Level 1", "Level 2 ", "Level 3"
        }));

// add option to Week box
        WeekBox.setModel(new DefaultComboBoxModel<>(new String[]{
            "< Week >", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
        }));

// add option to time box (is that need to create a function that tutor or admin can add new time by themself?)
        TimeBox.setModel(new DefaultComboBoxModel<>(new String[]{
            "< Time >", "8.00-10.00", "10.00-12.00", "13.00-14.00", "14.00-16.00", "16.00-18.00"
        }));

// add option to class type box
        ClassTypeBox.setModel(new DefaultComboBoxModel<>(new String[]{
            "< Class Type >","Lecture","Tutorial", "Lab"
        }));
// set the word to gray and disappear when user type
        SubTextBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (SubTextBox.getText().equals("Enter Subject Name")){
                    SubTextBox.setText("");
                    SubTextBox.setForeground(Color.BLACK);
                }
            }

        });
        SubTextBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (SubTextBox.getText().isEmpty()){
                    SubTextBox.setText("Enter Subject Name");
                    SubTextBox.setForeground(Color.LIGHT_GRAY);
                }

            }
        });

// set the word to gray and disappear when user type
        SubjectFeeTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (SubjectFeeTextField.getText().equals("e.g. 20.50")){
                    SubjectFeeTextField.setText("");
                    SubjectFeeTextField.setForeground(Color.BLACK);
                }
            }
        });
        SubjectFeeTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (SubjectFeeTextField.getText().isEmpty()){
                    SubjectFeeTextField.setText("e.g. 20.50");
                    SubjectFeeTextField.setForeground(Color.LIGHT_GRAY);
                }
            }
        });


        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSub.setText("");
                checkLevel.setText("");
                checkCos.setText("");
                checkType.setText("");
                checkMode.setText("");
                checkSchedule.setText("");

                boolean pass = true;

                if (SubTextBox.getText().equals("Enter Subject Name")){
                    checkSub.setText("*");
                    pass = false;
                }

                if ("< Class Type >".equals(ClassTypeBox.getSelectedItem())){
                    checkType.setText("*");
                    pass = false;
                }

                if ("< None >".equals(StudentLevelBox.getSelectedItem())){
                    checkLevel.setText("*");
                    pass = false;
                }

                if (SubjectFeeTextField.getText().equals("e.g. 20.50") || !SubjectFeeTextField.getText().matches("^\\d+(\\.\\d{1,2})?$")){
                    checkCos.setText("*");
                    pass = false;
                }

                if ("< Week >".equals(WeekBox.getSelectedItem()) || "< Time >".equals(TimeBox.getSelectedItem())){
                    checkSchedule.setText("*");
                    pass =false;
                }

                if ("< None >".equals(ModeBox.getSelectedItem())){
                    checkMode.setText("*");
                    pass = false;
                }

                if (pass){
                    String Subject = SubTextBox.getText();
                    String Class_type = (String) ClassTypeBox.getSelectedItem();
                    String Student_level = (String) StudentLevelBox.getSelectedItem();
                    Double Cos = Double.parseDouble(SubjectFeeTextField.getText());
                    String Week = (String) WeekBox.getSelectedItem();
                    String Time = (String) TimeBox.getSelectedItem();
                    String Mode_Class = (String) ModeBox.getSelectedItem();
                    String Status = "true" ;
                    String Teacher = TUTOR.getUsername();

                    ArrayList<Object> newClass = new ArrayList<>(Arrays.asList(
                            Subject,
                            Class_type,
                            Student_level,
                            Cos,
                            Week,
                            Time,
                            Mode_Class,
                            Status,
                            Teacher
                    ));

                    datamanager.addData("class.txt",newClass);
                    JOptionPane.showMessageDialog(null, "Create Successful");
                }else {
                    JOptionPane.showMessageDialog(null, "Create Unsuccessful");
                }
            }

        });
        EditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (SaveButton.isVisible()){
                    EditButton.setText("Edit");
                    SaveButton.setVisible(false);
                }else {
                    EditButton.setText("Cancel");
                    SaveButton.setVisible(true);
                }
                UpdateProfile();
            }
        });
        SaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserNameText.getText();
                String password = PasswordText.getText();
                String ic = ICText.getText();
                String contact_number = ContactNumberText.getText();
                String email = EmailText.getText();
                String address = AddressText.getText();

                TUTOR.UpdateProfile(username, password, ic, email, contact_number, address);
                JOptionPane.showMessageDialog(null, "Update Successful");

                SaveButton.setVisible(false);
                EditButton.setText("Edit");
                UpdateProfile();
            }
        });



    }

    public void UpdateProfile(){

        String username = TUTOR.getUsername();
        String password = TUTOR.getPassword();
        String ic = TUTOR.getIc();
        String email = TUTOR.getEmail();
        String contact_number = TUTOR.getContact_number();
        String address = TUTOR.getAddress();

        if (SaveButton.isVisible()){

            UserNameText.setText(username);

            PasswordText.setText(password);

            if (ic.equals("null")){
                ICText.setText("");
            } else {
                ICText.setText(ic);
            }

            if (email.equals("null")){
                EmailText.setText("");
            } else {
                EmailText.setText(email);
            }

            if (contact_number.equals("null")){
                ContactNumberText.setText("");
            } else {
                ContactNumberText.setText(contact_number);
            }

            if (address.equals("null")){
                AddressText.setText("");
            } else {
                AddressText.setText(address);
            }

            ShowUserName.setVisible(false);
            ShowPassword.setVisible(false);
            ShowIC.setVisible(false);
            ShowEmail.setVisible(false);
            ShowContactNumber.setVisible(false);
            ShowAddress.setVisible(false);

            UserNameText.setVisible(true);
            PasswordText.setVisible(true);
            ICText.setVisible(true);
            EmailText.setVisible(true);
            ContactNumberText.setVisible(true);
            AddressText.setVisible(true);

        } else {

            ShowUserName.setText(username);

            ShowPassword.setText("*".repeat(password.length()));

            ShowIC.setText("----");

            ShowEmail.setText("----");

            ShowAddress.setText("----");

            if (contact_number.equals("null")){
                ShowContactNumber.setText("----");
            } else {
                String blur = blur_ph_number();
                ShowContactNumber.setText(blur);
            }

            ShowUserName.setVisible(true);
            ShowPassword.setVisible(true);
            ShowIC.setVisible(true);
            ShowEmail.setVisible(true);
            ShowContactNumber.setVisible(true);
            ShowAddress.setVisible(true);

            UserNameText.setVisible(false);
            PasswordText.setVisible(false);
            ICText.setVisible(false);
            EmailText.setVisible(false);
            ContactNumberText.setVisible(false);
            AddressText.setVisible(false);
        }
    }

public String blur_ph_number(){
    String ph_number = TUTOR.getContact_number();

    String First3 = ph_number.substring(0,3);
    String Last2 = ph_number.substring(ph_number.length() -2);
    String blur = "*".repeat(ph_number.length()-5);
    String result = First3 + blur + Last2;

    return result;
}

public void insert_data_table(){
    DefaultTableModel tableModel =(DefaultTableModel) EditTable.getModel();
    String [] element = {"Class ID", "Class Name","Class type", "Lecture", "Student Level", "Week", "Time", "Class Mode" };
    tableModel.setColumnIdentifiers(element);

}

    public void Run(tutor system){
        TUTOR = system;
        WelcomeTitle.setText("Hello, " + TUTOR.getUsername() + " !");
    }

    public static void main(String[] args) {
        Tutor_UI ui = new Tutor_UI();
    }


}
