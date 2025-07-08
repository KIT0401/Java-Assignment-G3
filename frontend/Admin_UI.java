package frontend;

import backend.admin;
import backend.datamanager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
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
    private JComboBox TutorLevelComboBox;
    private JRadioButton malayTutorRadio;
    private JButton DeleteTutorButton;
    private JButton EditTutorButton;
    private JButton AddTutorButton;
    private JButton SaveTutorButton;
    private JRadioButton chineseTutorRadio;
    private JRadioButton englishTutorRadio;
    private JRadioButton mathematicsTutorRadio;
    private JRadioButton scienceTutorRadio;
    private JTextField tutorpasswordfield;
    private JTextField tutorusernamefield;
    private JLabel showtutorid;
    private JTable TutorTable;
    private JComboBox MonthComboBox;
    private JComboBox LevelComboBox;
    private JComboBox SubjectComboBox;
    private JButton ViewIncomeButton;
    private JLabel ShowIncomeMonth;
    private JLabel ShowIncomeLevel;
    private JLabel ShowIncomeSubject;
    private JLabel ShowTotalIncome;
    private JLabel ShowIncomeOutstanding;

    private DefaultTableModel Receptionistmodel;
    private DefaultTableModel Tutormodel;

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

        TutorLevelComboBox.setModel(new DefaultComboBoxModel<>(new Integer[]{
                1,2,3,4,5
        }));

        RefreshManageTutor();
        RefreshMonthlyIncome();

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

                RefreshManageTutor();
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

        AddTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> subjects = new ArrayList<>();

                if (malayTutorRadio.isSelected()){
                    subjects.add("malay");
                }
                if (chineseTutorRadio.isSelected()){
                    subjects.add("chinese");
                }
                if (englishTutorRadio.isSelected()){
                    subjects.add("english");
                }
                if (scienceTutorRadio.isSelected()){
                    subjects.add("science");
                }
                if (mathematicsTutorRadio.isSelected()){
                    subjects.add("mathematics");
                }

                int level = (int) TutorLevelComboBox.getSelectedItem();

                ArrayList<Object> result = ADMIN.addTutor(
                        tutorusernamefield.getText(),
                        tutorpasswordfield.getText(),
                        level,
                        subjects
                );

                if (result.getFirst().equals(true)) {

                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < subjects.size(); i++) {
                        String subject = subjects.get(i);

                        String capitalized = subject.substring(0, 1).toUpperCase() + subject.substring(1).toLowerCase();
                        sb.append(capitalized);
                        if (i < subjects.size() - 1) {
                            sb.append(",");
                        }
                    }
                    String subjects_string = sb.toString();

                    Tutormodel.addRow(new Object[]{result.get(2), tutorusernamefield.getText(), tutorpasswordfield.getText(), level,subjects_string});

                    int newRow = Tutormodel.getRowCount() - 1;
                    TutorTable.setRowSelectionInterval(newRow, newRow);
                    TutorTable.scrollRectToVisible(TutorTable.getCellRect(newRow, 0, true));

                    showtutorid.setText(result.get(2).toString());

                    SaveReceptionistButton.setVisible(true);
                    DeleteReceptionistButton.setVisible(true);
                    EditReceptionistButton.setVisible(true);
                }

                JOptionPane.showMessageDialog(null, result.get(1));
            }
        });

        EditTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = TutorTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = Tutormodel.getValueAt(selectedRow, 0).toString();
                    String username = Tutormodel.getValueAt(selectedRow, 1).toString();
                    String password = Tutormodel.getValueAt(selectedRow, 2).toString();
                    int level = (int) Tutormodel.getValueAt(selectedRow, 3);
                    String [] subjects = Tutormodel.getValueAt(selectedRow, 4).toString().split(",");

                    showtutorid.setText(id);
                    tutorusernamefield.setText(username);
                    tutorpasswordfield.setText(password);
                    TutorLevelComboBox.setSelectedIndex(level - 1);

                    malayTutorRadio.setSelected(false);
                    chineseTutorRadio.setSelected(false);
                    scienceTutorRadio.setSelected(false);
                    englishTutorRadio.setSelected(false);
                    mathematicsTutorRadio.setSelected(false);

                    for (String each_subject : subjects) {
                        if (each_subject.equalsIgnoreCase("malay")) {
                            malayTutorRadio.setSelected(true);
                        } else if (each_subject.equalsIgnoreCase("chinese")) {
                            chineseTutorRadio.setSelected(true);
                        } else if (each_subject.equalsIgnoreCase("science")) {
                            scienceTutorRadio.setSelected(true);
                        } else if (each_subject.equalsIgnoreCase("english")) {
                            englishTutorRadio.setSelected(true);
                        } else if (each_subject.equalsIgnoreCase("mathematics")) {
                            mathematicsTutorRadio.setSelected(true);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select a row first!");
                }
            }
        });

        DeleteTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = TutorTable.getSelectedRow();
                if (selectedRow != -1) {
                    String id = Tutormodel.getValueAt(selectedRow, 0).toString();
                    String username = Tutormodel.getValueAt(selectedRow, 1).toString();

                    int result = JOptionPane.showConfirmDialog(null,"Are you sure want delete "+ username +"'s data?","Confirm",JOptionPane.YES_NO_OPTION);

                    if (result == 0) {
                        boolean result2 = ADMIN.deleteTutor(id);

                        if (result2) {
                            for (int i = 0; i < Tutormodel.getRowCount(); i++) {
                                String rowId = Tutormodel.getValueAt(i, 0).toString();
                                if (Objects.equals(rowId, id)) {
                                    Tutormodel.removeRow(i);

                                    if (showtutorid.getText().equalsIgnoreCase(id)) {
                                        showtutorid.setText("---");
                                        tutorusernamefield.setText("");
                                        tutorpasswordfield.setText("");
                                        TutorLevelComboBox.setSelectedIndex(0);

                                        malayTutorRadio.setSelected(false);
                                        chineseTutorRadio.setSelected(false);
                                        englishTutorRadio.setSelected(false);
                                        scienceTutorRadio.setSelected(false);
                                        mathematicsTutorRadio.setSelected(false);
                                    }

                                    JOptionPane.showMessageDialog(null,"Successful Deleted Tutor " + username + "'s Data");
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select a row to delete data");
                }
            }
        });

        SaveTutorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!showtutorid.getText().equalsIgnoreCase("---")) {
                    ArrayList<String> subjects = new ArrayList<>();

                    if (malayTutorRadio.isSelected()) {
                        subjects.add("malay");
                    }
                    if (chineseTutorRadio.isSelected()) {
                        subjects.add("chinese");
                    }
                    if (englishTutorRadio.isSelected()) {
                        subjects.add("english");
                    }
                    if (scienceTutorRadio.isSelected()) {
                        subjects.add("science");
                    }
                    if (mathematicsTutorRadio.isSelected()) {
                        subjects.add("mathematics");
                    }

                    if (ADMIN.saveTutor(showtutorid.getText(),tutorusernamefield.getText(),tutorpasswordfield.getText(), (Integer) TutorLevelComboBox.getSelectedItem(),subjects)) {
                        RefreshManageTutor();
                        JOptionPane.showMessageDialog(null,"Successful Saved Tutor ID" + showtutorid.getText() + "'s Data");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select an row and press edit button first.");
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
                    Receptionistmodel.addRow(new Object[]{result.get(2), ReceptionistUsernameField.getText(), ReceptionistPasswordField.getText()});

                    int newRow = Receptionistmodel.getRowCount() - 1;
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
                    String id = Receptionistmodel.getValueAt(selectedRow, 0).toString();
                    String username = Receptionistmodel.getValueAt(selectedRow, 1).toString();
                    String password = Receptionistmodel.getValueAt(selectedRow, 2).toString();

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
                    String id = Receptionistmodel.getValueAt(selectedRow, 0).toString();
                    String username = Receptionistmodel.getValueAt(selectedRow, 1).toString();
                    // String password = model.getValueAt(selectedRow, 2).toString();

                    int result = JOptionPane.showConfirmDialog(null,"Are you sure want delete "+ username +"'s data?","Confirm",JOptionPane.YES_NO_OPTION);

                    if (result == 0) {
                        boolean result2 = ADMIN.deleteReceptionist(id);

                        if (result2) {
                            for (int i = 0; i < Receptionistmodel.getRowCount(); i++) {
                                String rowId = Receptionistmodel.getValueAt(i, 0).toString();
                                if (Objects.equals(rowId, id)) {
                                    Receptionistmodel.removeRow(i);

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
                    JOptionPane.showMessageDialog(null,"Please select a row to delete data!");
                }
            }
        });

        SaveReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ShowReceptionistID.getText().equalsIgnoreCase("---")) {
                    if (ADMIN.saveReceptionist(ShowReceptionistID.getText(),ReceptionistUsernameField.getText(),ReceptionistPasswordField.getText())) {
                        RefreshManageReceptionist();
                        JOptionPane.showMessageDialog(null,"Successful Saved Receptionist ID" + ShowReceptionistID.getText() + "'s Data");
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Please select an row and press edit button first.");
                }
            }
        });

        ViewIncomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ViewMonthlyIncome();
            }
        });
    }

    public void RefreshManageTutor(){
        String [] columnNames = {"ID","Username","Password","Level","Subjects"};

        Tutormodel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<String> tutorData = datamanager.loadData("tutors.txt");

        for (String tutorLine : tutorData) {
            String [] split_data = tutorLine.split(",");
            String tutorID = split_data[0];

            ArrayList<Object> userData = datamanager.getData("users.txt",tutorID);

            if (userData.get(3).equals(false)) {
                continue;
            }

            ArrayList<String> subjects = new ArrayList<>();

            ArrayList<String> tutor_subjects = datamanager.loadData("tutor_subjects.txt");

            for (String each_tutor_subject : tutor_subjects) {
                String [] each_tutor_subject_split = each_tutor_subject.split(",");

                if (each_tutor_subject_split[1].equalsIgnoreCase(tutorID)) {
                    subjects.add(each_tutor_subject_split[2]);
                }
            }

            String subjects_string;

            if (subjects.size() <= 0) {
                subjects_string = "";
            } else {
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < subjects.size(); i++) {
                    String subject = subjects.get(i);

                    String capitalized = subject.substring(0, 1).toUpperCase() + subject.substring(1).toLowerCase();
                    result.append(capitalized);
                    if (i < subjects.size() - 1) {
                        result.append(",");
                    }
                }
                subjects_string = result.toString();
            }

            Tutormodel.addRow(new Object[]{tutorID,userData.get(1),userData.get(2),Integer.parseInt(split_data[1]),subjects_string});
        }


        TutorTable.setModel(Tutormodel);
        TutorTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        TutorTable.getTableHeader().setResizingAllowed(false);
        TutorTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = TutorTable.getColumnModel();
        TutorTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        columnModel.getColumn(0).setPreferredWidth(25);
        columnModel.getColumn(1).setPreferredWidth(75);
        columnModel.getColumn(2).setPreferredWidth(75);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(250);
    }

    public void RefreshManageReceptionist(){
        String [] columnNames = {"ID","Username","Password"};

        Receptionistmodel = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<String> receptionistData = datamanager.loadData("receptionists.txt");

        for (String tutorLine : receptionistData) {
            String [] split_data = tutorLine.split(",");
            ArrayList<Object> userData = datamanager.getData("users.txt",split_data[0]);

            if (userData.get(3).equals(false)) {
                continue;
            }

            Receptionistmodel.addRow(new Object[]{split_data[0],userData.get(1),userData.get(2)});
        }

        ReceptionistTable.setModel(Receptionistmodel);
        ReceptionistTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        ReceptionistTable.getTableHeader().setResizingAllowed(false);
        ReceptionistTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = ReceptionistTable.getColumnModel();
        ReceptionistTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(225);
        columnModel.getColumn(2).setPreferredWidth(225);
    }

    public void RefreshMonthlyIncome() {
        LevelComboBox.setModel(new DefaultComboBoxModel<>(new Integer[]{
                1,2,3,4,5
        }));

        SubjectComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "English",
                "Malay",
                "Chinese",
                "Science",
                "Mathematics",
        }));

        ArrayList<String> months = new ArrayList<>();

        for (String each_student_subject : datamanager.loadData("student_subjects.txt")) {
            String [] each_student_subject_split = each_student_subject.split(",");

            if (!months.contains(each_student_subject_split[3])) {
                months.add(each_student_subject_split[3]);
            }
        }

        if (months.size() <= 0) {
            MonthComboBox.setModel(new DefaultComboBoxModel<>(new String[]{
                    "None",
            }));
        } else {
            String[] monthsArray = months.stream()
                    .map(month -> month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase())
                    .toArray(String[]::new);

            MonthComboBox.setModel(new DefaultComboBoxModel<>(monthsArray));
        }

    }

    public void ViewMonthlyIncome() {
        if (MonthComboBox.getSelectedItem().toString().equalsIgnoreCase("None")) {
            JOptionPane.showMessageDialog(null,"You don't have a student enrolled a subject!");
            return;
        }
        int total_income = 0;
        int total_fees = 0;
        int total_outstanding = 0;

        String selected_month = MonthComboBox.getSelectedItem().toString().toLowerCase();
        int selected_level = (int) LevelComboBox.getSelectedItem();
        String selected_subject = SubjectComboBox.getSelectedItem().toString().toLowerCase();

        for (String each_student_subject : datamanager.loadData("student_subjects.txt")) {
            String[] each_student_subject_split = each_student_subject.split(",");

            if (each_student_subject_split[3].equalsIgnoreCase(selected_month) && each_student_subject_split[2].equalsIgnoreCase(selected_subject)
            && datamanager.getData("students.txt",each_student_subject_split[1]).get(1).equals(selected_level)
            ) {
                total_fees += Integer.parseInt(each_student_subject_split[4]);

                for (String each_student_receipts : datamanager.loadData("student_receipts.txt")) {
                    String[] each_student_receipts_split = each_student_receipts.split(",");

                    if (each_student_receipts_split[1].equalsIgnoreCase(each_student_subject_split[1]) && each_student_receipts_split[3].equalsIgnoreCase(selected_subject)) {
                        total_income += Integer.parseInt(each_student_receipts_split[2]);
                    }
                }
            }

        }

        total_outstanding = total_fees - total_income;

        ShowIncomeLevel.setText(String.valueOf(selected_level));
        ShowIncomeMonth.setText(selected_month.substring(0, 1).toUpperCase() + selected_month.substring(1).toLowerCase());
        ShowIncomeSubject.setText(selected_subject.substring(0, 1).toUpperCase() + selected_subject.substring(1).toLowerCase());
        ShowIncomeOutstanding.setText(String.format("%,d", total_outstanding));
        ShowTotalIncome.setText(String.format("%,d", total_income));
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
