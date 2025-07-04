package frontend;

import backend.datamanager;
import backend.receptionist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class Receptionist_UI extends JFrame {
    private JPanel ReceptionistPanel;
    private JLabel WelcomeTitle;
    private JPanel Options;
    private JButton UpdateProfileButton;
    private JButton LogOutButton;
    private JButton ManageStudentButton;
    private JButton StudentPaymentButton;
    private JTable studentTable;
    private JTextField textField1;
    private JTextField studentPaymentTextField;
    private JPanel AddStudentDetails;
    private JTextField studentnamefield;
    private JComboBox studentlevel;
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
    private JLabel showstudentid;
    private JTextField studentpasswordfield;
    private JTextField studenticfield;
    private JTextField studentemailfield;
    private JTextField studentcontactnumberfield;
    private JTextField studentaddressfield;
    private JScrollPane scrollPane;

    private static receptionist RECEPTIONIST;

    public Receptionist_UI() {
        setContentPane(ReceptionistPanel);
        setTitle("Advanced Tuition Centre [Receptionist]");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        studentTable.getTableHeader().setReorderingAllowed(false);

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
                loadTableData();
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
                UpdateProfile();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = studentnamefield.getText();
                String password = studentpasswordfield.getText();
                String address = studentaddressfield.getText();
                String contact_number = studentcontactnumberfield.getText();
                String email = studentemailfield.getText();
                String ic = studenticfield.getText();
                String selected_level = studentlevel.getSelectedItem().toString();

                ArrayList<String> subjects = new ArrayList<>();

                if (malayCheckBox.isSelected()) {
                    subjects.add("malay");
                }
                if (chineseCheckBox.isSelected()) {
                    subjects.add("chinese");
                }
                if (englishCheckBox.isSelected()) {
                    subjects.add("english");
                }
                if (scienceCheckBox.isSelected()) {
                    subjects.add("science");
                }
                if (mathematicsCheckBox.isSelected()) {
                    subjects.add("mathematics");
                }

                if (subjects.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please select at least one subject.");
                    return;
                }
                if (subjects.size() > 3) {
                    JOptionPane.showMessageDialog(null, "You can only select up to 3 subjects.");
                    return;
                }

                if (showstudentid.getText().equalsIgnoreCase("---")) {

                    if (username.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Username cannot be empty");
                        return ;
                    }

                    for (String each : datamanager.loadData("users.txt")) {
                        String [] afterSplit = each.split(",");

                        if (afterSplit[1].equalsIgnoreCase(username)){
                            JOptionPane.showMessageDialog(null,"This username already exists!");
                            return;
                        }
                    }

                    if (password.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Password cannot be empty");
                        return;
                    }

                    if (ic.isEmpty()) {
                        ic = "null";
                    }

                    if (address.isEmpty()) {
                        address = "null";
                    }

                    if (contact_number.isEmpty()) {
                        contact_number = "null";
                    }

                    if (email.isEmpty()) {
                        email = "null";
                    }

                    ArrayList<Object> userData = new ArrayList<>(Arrays.asList(
                            username,
                            password,
                            true,
                            "student",
                            ic,
                            email,
                            contact_number,
                            address
                    ));

                    String student_new_id = datamanager.addData("users.txt",userData);

                    int total_fees = 0;

                    for (Object each_subject : subjects) {
                        total_fees += 100;
                    }

                    ArrayList<Object> student_Data = new ArrayList<>(Arrays.asList(
                            student_new_id,
                            selected_level,
                            total_fees,
                            0
                    ));

                    datamanager.addLine("students.txt", student_Data);
                    System.out.println(student_Data);

                    for (Object each_subject : subjects) {

                        String currentMonth = LocalDate.now()
                                .getMonth()
                                .getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                                .toLowerCase();

                        System.out.println("Current month: " + currentMonth);

                        ArrayList<Object> subject_Data = new ArrayList<>(Arrays.asList(
                                student_new_id,
                                each_subject,
                                currentMonth
                                ));

                        datamanager.addData("student_subjects.txt",subject_Data);
                        System.out.println(subject_Data);
                    }
                } else {

                }
            }
        });
    }

    public void UpdateProfile(){

    }

    public void loadTableData() {
        String[] columnName = {"ID", "Username", "Level", "Subjects"};

        DefaultTableModel model = new DefaultTableModel(null, columnName) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        for (String student_data : datamanager.loadData("students.txt")) {
            String [] student_Data_split = student_data.split(",");

            String studentID = student_Data_split[0];
            ArrayList<Object> student_personal_Data = datamanager.getData("users.txt",studentID);

            if (student_personal_Data.get(3).equals(false)) {
                continue;
            }

            ArrayList<String> subjects = new ArrayList<>();

            for (String each_student_subject : datamanager.loadData("student_subjects.txt")) {
                String [] each_student_subject_split = each_student_subject.split(",");
                if (each_student_subject_split[1].equalsIgnoreCase(studentID)) {
                    subjects.add(each_student_subject_split[2]);
                }
            }

            int studentLevel = Integer.parseInt(student_Data_split[1]);

            String all_subject_string = String.join(",", subjects);

            model.addRow(new Object[]{studentID,student_personal_Data.get(1),studentLevel,all_subject_string});
        }

//        ArrayList<String> studentSubjects = datamanager.loadData("student_subjects.txt");
//        for (String subjectLine : studentSubjects) {
//            String[] subjectParts = subjectLine.split(",");
//
//            String studentID = subjectParts[1];
//
//            ArrayList<Object> student_personal_Data = datamanager.getData("users.txt",studentID);
//
//            if (student_personal_Data.get(3).equals(false)) {
//                continue;
//            }
//
//            ArrayList<Object> student_data = datamanager.getData("students.txt",studentID);
//
//            String studentSubject = subjectParts[2];
//            int studentLevel = (int) student_data.get(1);
//
//            String student_username = student_personal_Data.get(1).toString();
//
//            model.addRow(new Object[]{studentID,student_personal_Data.get(1),studentLevel,studentSubject});
//        }

        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setResizingAllowed(false);
        studentTable.setModel(model);
        autoResizeTableColumns(studentTable);

    }

    public static void autoResizeTableColumns(JTable table) {
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 100; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 10, width);
            }
            TableColumn tableColumn = table.getColumnModel().getColumn(column);
            tableColumn.setPreferredWidth(width);
        }
    }

    public void Run (receptionist system){
            RECEPTIONIST = system;
            WelcomeTitle.setText("Hello, " + RECEPTIONIST.getUsername() + " !");
    }

    public static void main (String[] args){
            Receptionist_UI ui = new Receptionist_UI();
    }
}
