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
    private JButton StudentSubjectRequest;
    private JButton EditStudentButton;
    private JPanel checkboxPanel;
    private JButton cancelButton;
    private JPanel SubjectRequests;
    private JTable SubjectRequestsTable;
    private JButton ApproveRequestButton;
    private JButton RejectRequestButton;
    private JTable PaymentRequestTable;
    private JButton AcceptPaymentButton;

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

        StudentDetailTable.setVisible(false);
        SubjectRequests.setVisible(false);
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
                StudentDetailTable.setVisible(true);
                SubjectRequests.setVisible(false);
                AddStudentDetails.setVisible(false);
                StudentPayment.setVisible(false);
                UpdateProfile.setVisible(false);
                loadStudentTableData();
            }
        });
        StudentSubjectRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentDetailTable.setVisible(false);
                SubjectRequests.setVisible(true);
                AddStudentDetails.setVisible(false);
                StudentPayment.setVisible(false);
                UpdateProfile.setVisible(false);
                loadSubjectRequestsTableData();
            }
        });
        AddStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentDetailTable.setVisible(false);
                SubjectRequests.setVisible(false);
                AddStudentDetails.setVisible(true);
                StudentPayment.setVisible(false);
                UpdateProfile.setVisible(false);

                showstudentid.setText("---");

                studentnamefield.setText("");
                studentpasswordfield.setText("");
                studenticfield.setText("");
                studentemailfield.setText("");
                studentcontactnumberfield.setText("");
                studentaddressfield.setText("");
                studentlevel.setSelectedIndex(0);
                malayCheckBox.setSelected(false);
                chineseCheckBox.setSelected(false);
                englishCheckBox.setSelected(false);
                scienceCheckBox.setSelected(false);
                mathematicsCheckBox.setSelected(false);
            }
        });
        EditStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row from the table first.");
                    return;
                }

                String studentID = studentTable.getValueAt(selectedRow, 0).toString();
                String studentName = studentTable.getValueAt(selectedRow, 1).toString();
                String [] studentSubjects = studentTable.getValueAt(selectedRow, 3).toString().split(",");
                ArrayList<Object> userData = datamanager.getData("users.txt", studentID);
                ArrayList<Object> studentData = datamanager.getData("students.txt", studentID);

                StudentDetailTable.setVisible(false);
                SubjectRequests.setVisible(false);
                AddStudentDetails.setVisible(true);
                StudentPayment.setVisible(false);
                UpdateProfile.setVisible(false);

                showstudentid.setText(studentID);
                studentnamefield.setText(studentName);

                if (userData.get(2) == null || userData.get(2).equals("null")) {
                    studentpasswordfield.setText("");
                } else {
                    studentpasswordfield.setText(userData.get(2).toString());
                }

                if (userData.get(5) == null || userData.get(5).equals("null")) {
                    studenticfield.setText("");
                } else {
                    studenticfield.setText(userData.get(5).toString());
                }

                if (userData.get(6) == null || userData.get(6).equals("null")) {
                    studentemailfield.setText("");
                } else {
                    studentemailfield.setText(userData.get(6).toString());
                }

                if (userData.get(7) == null || userData.get(7).equals("null")) {
                    studentcontactnumberfield.setText("");
                } else {
                    studentcontactnumberfield.setText(userData.get(7).toString());
                }

                if (userData.get(8) == null || userData.get(8).equals("null")) {
                    studentaddressfield.setText("");
                } else {
                    studentaddressfield.setText(userData.get(8).toString());
                }

                studentlevel.setSelectedItem(studentData.get(1).toString());

                malayCheckBox.setSelected(false);
                chineseCheckBox.setSelected(false);
                englishCheckBox.setSelected(false);
                scienceCheckBox.setSelected(false);
                mathematicsCheckBox.setSelected(false);

                for (String subject : studentSubjects){
                    subject = subject.trim().toLowerCase();

                    if (subject.equals("malay")){
                        malayCheckBox.setSelected(true);
                    } else if (subject.equals("chinese")){
                        chineseCheckBox.setSelected(true);
                    } else if (subject.equals("english")){
                        englishCheckBox.setSelected(true);
                    } else if (subject.equals("science")){
                        scienceCheckBox.setSelected(true);
                    } else if (subject.equals("mathematics")){
                        mathematicsCheckBox.setSelected(true);
                    }
                }
            }
        });
        DeleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row from the table first.");
                    return;
                }

                String studentID = studentTable.getValueAt(selectedRow, 0).toString();

                ArrayList<Object> userData = datamanager.getData("users.txt", studentID);
                if (userData.size() < 9 || userData.get(3).equals(false)) {
                    JOptionPane.showMessageDialog(null, "Student data not found or corrupted.");
                    return;
                }

                userData.set(3, false);
                datamanager.updateData("users.txt", userData);

                JOptionPane.showMessageDialog(null, "Student has been deactivated.");
                loadStudentTableData();
                loadSubjectRequestsTableData();
            }
        });
        StudentPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentDetailTable.setVisible(false);
                SubjectRequests.setVisible(false);
                AddStudentDetails.setVisible(false);
                StudentPayment.setVisible(true);
                UpdateProfile.setVisible(false);
                loadPaymentRequestTableData();
            }
        });
        UpdateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentDetailTable.setVisible(false);
                SubjectRequests.setVisible(false);
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

                    ArrayList<Object> student_Data = new ArrayList<>(Arrays.asList(
                            student_new_id,
                            selected_level
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
                                currentMonth,
                                "100"
                        ));

                        datamanager.addData("student_subjects.txt",subject_Data);
                        System.out.println(subject_Data);
                    }
                    JOptionPane.showMessageDialog(null, "Student successfully added!");
                } else {
                    String studentID = showstudentid.getText();

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
                            studentID,
                            username,
                            password,
                            true,
                            "student",
                            ic,
                            email,
                            contact_number,
                            address
                    ));

                    datamanager.updateData("users.txt", userData);

                    ArrayList<Object> studentData = new ArrayList<>(Arrays.asList(
                            studentID,
                            selected_level
                    ));

                    datamanager.updateData("students.txt", studentData);

                    ArrayList<String> oldsubjects = new ArrayList<>();
                    ArrayList<String> newsubjects = subjects;
                    String currentMonth = LocalDate.now()
                            .getMonth()
                            .getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                            .toLowerCase();

                    for (String line : datamanager.loadData("student_subjects.txt")) {
                        String [] subject_data = line.split(",");

                        if (subject_data.length != 5) {
                            continue;
                        }

                        if (subject_data[1].equalsIgnoreCase(studentID)){
                            oldsubjects.add(subject_data[2].toLowerCase());

                            if (!newsubjects.contains(subject_data[2])){
                                datamanager.deleteData("student_subjects.txt", subject_data[0]);
                            }
                        }
                    }

                    for (String subject : newsubjects) {
                        if (!oldsubjects.contains(subject)) {
                            ArrayList<Object> subject_Data = new ArrayList<>(Arrays.asList(
                                    studentID,
                                    subject,
                                    currentMonth,
                                    "100"
                            ));
                            datamanager.addData("student_subjects.txt",subject_Data);
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Student successfully updated!");
                }

                StudentDetailTable.setVisible(true);
                SubjectRequests.setVisible(false);
                AddStudentDetails.setVisible(false);
                StudentPayment.setVisible(false);
                UpdateProfile.setVisible(false);
                loadStudentTableData();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentDetails.setVisible(false);
                StudentDetailTable.setVisible(true);

                showstudentid.setText("---");
                studentnamefield.setText("");
                studentpasswordfield.setText("");
                studentaddressfield.setText("");
                studentcontactnumberfield.setText("");
                studentemailfield.setText("");
                studenticfield.setText("");
                malayCheckBox.setSelected(false);
                chineseCheckBox.setSelected(false);
                englishCheckBox.setSelected(false);
                scienceCheckBox.setSelected(false);
                mathematicsCheckBox.setSelected(false);
            }
        });

        ApproveRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = SubjectRequestsTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row from the table first.");
                    return;
                }

                String RequestID = SubjectRequestsTable.getValueAt(selectedRow, 0).toString();
                String StudentUsername = SubjectRequestsTable.getValueAt(selectedRow, 1).toString();
                String OldSubject = SubjectRequestsTable.getValueAt(selectedRow, 2).toString();
                String NewSubject = SubjectRequestsTable.getValueAt(selectedRow, 3).toString();

                String StudentID = "";
                for (String user_line : datamanager.loadData("users.txt")) {
                    String[] user_data = user_line.split(",");

                    if (user_data[1].equalsIgnoreCase(StudentUsername)) {
                        StudentID = user_data[0];
                        break;
                    }
                }

                if (StudentID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Student not found.");
                    return;
                }

                String student_subjects_id = "";
                String student_subjects_month = "";

                for (String subject_line : datamanager.loadData("student_subjects.txt")) {
                    String[] subject_data = subject_line.split(",");

                    if (subject_data.length == 5 && subject_data[1].equals(StudentID) && subject_data[2].equalsIgnoreCase(OldSubject)) {
                        student_subjects_id = subject_data[0];
                        student_subjects_month = subject_data[3];
                    }
                }

                if (student_subjects_id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Subject not found, request will be deleted.");
                    datamanager.deleteData("student_requests.txt", RequestID);
                    return;
                }

                datamanager.deleteData("student_subjects.txt", student_subjects_id);

                ArrayList<Object> newSubjectLine  = new ArrayList<>(Arrays.asList(
                        student_subjects_id,
                        StudentID,
                        NewSubject,
                        student_subjects_month
                ));

                datamanager.addLine("student_subjects.txt", newSubjectLine);
                datamanager.deleteData("student_requests.txt", RequestID);

                JOptionPane.showMessageDialog(null, "Subject successfully approved!");
                loadSubjectRequestsTableData();
                loadStudentTableData();
            }
        });
        RejectRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = SubjectRequestsTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row from the table first.");
                    return;
                }

                String RequestID = SubjectRequestsTable.getValueAt(selectedRow, 0).toString();

                datamanager.deleteData("student_requests.txt", RequestID);
                JOptionPane.showMessageDialog(null, "Subject successfully rejected!");
                loadSubjectRequestsTableData();

            }
        });
        AcceptPaymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = PaymentRequestTable.getSelectedRow();

                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a row from the table first.");
                    return;
                }

                String RequestID = PaymentRequestTable.getValueAt(selectedRow, 0).toString();
                String StudentUsername = PaymentRequestTable.getValueAt(selectedRow, 1).toString();
                String Amount = PaymentRequestTable.getValueAt(selectedRow, 2).toString();
                String Subject = PaymentRequestTable.getValueAt(selectedRow, 3).toString();

                String StudentID = "";
                for (String user_line : datamanager.loadData("users.txt")) {
                    String[] user_data = user_line.split(",");

                    if (user_data[1].equalsIgnoreCase(StudentUsername)) {
                        StudentID = user_data[0];
                        break;
                    }
                }

                if (StudentID.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Student not found.");
                    return;
                }

                int amount = Integer.parseInt(Amount);
                int total_paid = 0;
                int total_fees = 0;

                for (String subject_line : datamanager.loadData("student_subjects.txt")) {
                    String[] subject_data = subject_line.split(",");

                    if (subject_data.length == 5 && subject_data[1].equals(StudentID) && subject_data[2].equalsIgnoreCase(Subject)) {
                        total_fees = Integer.parseInt(subject_data[4]);
                        break;
                    }
                }

                for (String receipt_line : datamanager.loadData("student_receipts.txt")) {
                    String[] receipt_data = receipt_line.split(",");

                    if (receipt_data.length == 4 && receipt_data[1].equals(StudentID) && receipt_data[3].equalsIgnoreCase(Subject)) {
                        total_paid += Integer.parseInt(receipt_data[2]);
                    }
                }

                if (total_paid + amount > total_fees) {
                    JOptionPane.showMessageDialog(null, "Payment exceeds owed fees for subject "+Subject);
                    return;
                }

                ArrayList<Object> receipt  = new ArrayList<>(Arrays.asList(
                        StudentID,
                        amount,
                        Subject
                ));

                datamanager.addData("student_receipts.txt", receipt);
                datamanager.deleteData("payment_requests.txt", RequestID);

                JOptionPane.showMessageDialog(null, "Payment successfully accepted!");
                loadPaymentRequestTableData();
            }
        });
        UpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = RECEPTIONIST.getId();
                String username = ReceptionistUsernameTF.getText().trim();
                String password = ReceptionistPasswordTF.getText().trim();
                String ic = ReceptionistICTF.getText().trim();
                String address = ReceptionistAddressTF.getText().trim();
                String email = ReceptionistEmailTF.getText().trim();
                String contact = ReceptionistContactTF.getText().trim();

                String profile = RECEPTIONIST.UpdateProfile(
                        username,
                        password,
                        ic,
                        email,
                        contact,
                        address
                );

            }
        });
        CancelUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReceptionistUsernameTF.setVisible(false);
                ReceptionistPasswordTF.setVisible(false);
                ReceptionistICTF.setVisible(false);
                ReceptionistAddressTF.setVisible(false);
                ReceptionistEmailTF.setVisible(false);
                ReceptionistContactTF.setVisible(false);
                UpdateCancelButtons.setVisible(false);

                UsernameField.setVisible(true);
                PasswordField.setVisible(true);
                ICField.setVisible(true);
                AddressField.setVisible(true);
                EmailField.setVisible(true);
                ContactField.setVisible(true);
            }
        });
    }

    public void UpdateProfile(){
        String id = RECEPTIONIST.getId();
        ArrayList<Object> receptionist_data = datamanager.getData("users.txt", id);

        if (receptionist_data.size() < 9 || receptionist_data.get(3).equals(false)) {
            JOptionPane.showMessageDialog(null, "Receptionist data not found or corrupted.");
            return;
        }

        String username = receptionist_data.get(1).toString();
        String password = receptionist_data.get(2).toString();
        String ic = receptionist_data.get(5).toString();
        String email = receptionist_data.get(6).toString();
        String contact = receptionist_data.get(7).toString();
        String address = receptionist_data.get(8).toString();

        if (UpdateButton.isVisible()){
            ReceptionistUsernameTF.setText(username);
            ReceptionistPasswordTF.setText(password);

            if (ic.equalsIgnoreCase("null")) {
                ReceptionistICTF.setText("");
            } else {
                ReceptionistICTF.setText(ic);
            }
            if (address.equalsIgnoreCase("null")) {
                ReceptionistAddressTF.setText("");
            } else {
                ReceptionistICTF.setText(address);
            }
            if (email.equalsIgnoreCase("null")) {
                ReceptionistEmailTF.setText("");
            } else {
                ReceptionistEmailTF.setText(email);
            }
            if (contact.equalsIgnoreCase("null")) {
                ReceptionistContactTF.setText("");
            } else {
                ReceptionistContactTF.setText(contact);
            }
        } else {
            UsernameField.setText(username);
            PasswordField.setText(password);

            if (ic.equalsIgnoreCase("null")) {
                ICField.setText("----");
            } else {
                ICField.setText(ic);
            }
            if (address.equalsIgnoreCase("null")) {
                AddressField.setText("----");
            } else {
                AddressField.setText(address);
            }
            if (email.equalsIgnoreCase("null")) {
                EmailField.setText("----");
            } else {
                EmailField.setText(email);
            }
            if (contact.equalsIgnoreCase("null")) {
                ContactField.setText("----");
            } else {
                ContactField.setText(contact);
            }
        }

    }

    public void loadStudentTableData() {
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

            if (student_personal_Data.size() < 4 || student_personal_Data.get(3).equals(false)) {
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

        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.getTableHeader().setResizingAllowed(false);
        studentTable.setModel(model);
        autoResizeTableColumns(studentTable);

    }

    public void loadSubjectRequestsTableData() {
        String[] columnName = {"ID", "Student Name", "Old Subject", "New Subject"};

        DefaultTableModel model = new DefaultTableModel(null, columnName) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (String subject_data : datamanager.loadData("student_requests.txt")) {
            String[] subject_data_split = subject_data.split(",");

            if (subject_data_split.length == 4) {
                String RequestID = subject_data_split[0];
                String StudentID = subject_data_split[1];
                String OldSubject = subject_data_split[2];
                String NewSubject = subject_data_split[3];

                ArrayList<Object> student_personal_Data = datamanager.getData("users.txt", StudentID);

                if (student_personal_Data.size() < 9 || student_personal_Data.get(3).equals(false)) {
                    JOptionPane.showMessageDialog(null, "Student data not found or corrupted.");
                    return;
                }

                String StudentUsername = student_personal_Data.get(1).toString();
                model.addRow(new Object[]{RequestID, StudentUsername, OldSubject, NewSubject});
            }
        }

        SubjectRequestsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        SubjectRequestsTable.getTableHeader().setResizingAllowed(false);
        SubjectRequestsTable.setModel(model);
        autoResizeTableColumns(SubjectRequestsTable);
    }

    public void loadPaymentRequestTableData() {
        String[] columnName = {"ID", "Student Name", "Amount", "Subject"};

        DefaultTableModel model = new DefaultTableModel(null, columnName) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (String request_data : datamanager.loadData("payment_requests.txt")) {
            String[] request_data_split = request_data.split(",");

            if (request_data_split.length == 4) {
                String RequestID = request_data_split[0];
                String StudentID = request_data_split[1];
                String Amount = request_data_split[2];
                String Subject = request_data_split[3];

                ArrayList<Object> student_Data = datamanager.getData("users.txt", StudentID);

                if (student_Data.size() < 9 || student_Data.get(3).equals(false)) {
                    JOptionPane.showMessageDialog(null, "Student data not found or corrupted.");
                    return;
                }

                String StudentUsername = student_Data.get(1).toString();
                model.addRow(new Object[]{RequestID, StudentUsername, Amount, Subject});
            }
        }

        PaymentRequestTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        PaymentRequestTable.getTableHeader().setResizingAllowed(false);
        PaymentRequestTable.setModel(model);
        autoResizeTableColumns(PaymentRequestTable);
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