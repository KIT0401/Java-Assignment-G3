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
    private JTextField SubjectBox;
    private JTextField SubjectFeeTextField;
    private JComboBox ModeBox;
    private JPanel subject;
    private JComboBox ClassroomBox;
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
    private JComboBox box;
    private JComboBox ClassNameBox;
    private JComboBox Edit_Class_Type;
    private JComboBox Edit_Week_Box;
    private JComboBox Edit_Classroom;
    private JComboBox Edit_Time_Box;
    private JComboBox Edit_Mode_Class;
    private JButton editButton;
    private JButton deleteButton;
    private JPanel EditDetailFrame;
    private JPanel SearchFrame;
    private JLabel Edit_ID_Text;
    private JLabel Edit_Class_Name;
    private JButton saveButton;
    private JTextField Search_Bar;
    private JButton EditEnterButton;
    private JButton viewStudentButton;
    private JTable Student_Table;
    private JButton returnButton;
    private JPanel Student_Frame;
    private JLabel Student_Class_ID_Text;
    private JLabel Student_Subject_Text;
    private JPanel SearchPanel;

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
        SearchFrame.setVisible(false);
        EditClassFrame.setVisible(false);
        Student_Frame.setVisible(false);

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
                get_subject();
                SearchFrame.setVisible(false);
                EditDetailFrame.setVisible(false);
                Student_Frame.setVisible(false);
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
                SearchFrame.setVisible(false);
                EditDetailFrame.setVisible(false);
                Student_Frame.setVisible(false);
            }
        });

        EditClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddClassFrame.setVisible(false);
                UpdateProfileFrame.setVisible(false);
                EditClassFrame.setVisible(true);
                SearchFrame.setVisible(true);
                EditDetailFrame.setVisible(false);
                saveButton.setVisible(false);
                Student_Frame.setVisible(false);
            }
        });

// add option to class mode box
        ModeBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "< None >", "Online", "Physical", "Hybrid"
        }));

// add option to student level box
        ClassroomBox.setModel(new DefaultComboBoxModel<>(new String[]{
                "< None >", "Class 1", "Class 2", "Class 3", "Class 4","Class 5"
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

        ClassNameBox.setModel(new DefaultComboBoxModel<>(new String[]{

        }));


        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSub.setText("");
                checkLevel.setText("");
                checkType.setText("");
                checkMode.setText("");
                checkSchedule.setText("");

                boolean pass = true;

                if ("< None >".equals(ClassNameBox.getSelectedItem())){
                    checkSub.setText("*");
                    pass=false;
                }

                if ("< Class Type >".equals(ClassTypeBox.getSelectedItem())){
                    checkType.setText("*");
                    pass = false;
                }

                if ("< None >".equals(ClassroomBox.getSelectedItem())){
                    checkLevel.setText("*");
                    pass = false;
                }

                if ("< Week >".equals(WeekBox.getSelectedItem()) || "< Time >".equals(TimeBox.getSelectedItem())){
                    checkSchedule.setText("*");
                    pass =false;
                }

                if ("< None >".equals(ModeBox.getSelectedItem())){
                    checkMode.setText("*");
                    pass=false;
                }


                if (pass){
                    String Subject = (String) ClassNameBox.getSelectedItem();
                    String Class_type = (String) ClassTypeBox.getSelectedItem();
                    String Student_level = (String) ClassroomBox.getSelectedItem();
                    String Week = (String) WeekBox.getSelectedItem();
                    String Time = (String) TimeBox.getSelectedItem();
                    String Mode_Class = (String) ModeBox.getSelectedItem();
                    String Status = "true" ;
                    String Teacher_id = TUTOR.getId();

                    String Subject_lower = Subject.toLowerCase();

                    ArrayList<Object> newClass = new ArrayList<>(Arrays.asList(
                            Subject_lower,
                            Class_type,
                            Student_level,
                            Week,
                            Time,
                            Mode_Class,
                            Status,
                            Teacher_id
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

        EditClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        set_edit_table();
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    saveButton.setVisible(true);
                    EditDetailFrame.setVisible(true);
                    ArrayList<String> data = new ArrayList<>();

                    EditDetailFrame.setVisible(true);
                    ArrayList<String> data_array = get_row_data();

                    Edit_ID_Text.setText(data_array.get(0));
                    Edit_Class_Name.setText(data_array.get(1));

                    String[] option_class_type = {"Lecture","Tutorial","Lab"};
                    data.add(data_array.get(2).trim());
                    for (String need : option_class_type) {
                        if (!need.equalsIgnoreCase(data_array.get(2))){
                            data.add(need);
                        }
                    }
                    Edit_Class_Type.setModel(new DefaultComboBoxModel<>(data.toArray(new String[0])));
                    data.clear();

                    String[] option_classroom = {"Class 1","Class 2","Class 3","Class 4","Class 5"};
                    data.add(data_array.get(3).trim());
                    for (String need : option_classroom) {
                        if (!need.equalsIgnoreCase(data_array.get(3))) {
                            data.add(need);
                        }
                    }
                    Edit_Classroom.setModel(new DefaultComboBoxModel<>(data.toArray(new String[0])));
                    data.clear();

                    String[] option_week = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
                    data.add(data_array.get(4).trim());
                    for (String need : option_week) {
                        if (!need.equalsIgnoreCase(data_array.get(4))) {
                            data.add(need);
                        }
                    }

                    Edit_Week_Box.setModel(new DefaultComboBoxModel<>(data.toArray(new String[0])));
                    data.clear();

                    String[] option_time = {"8.00-10.00", "10.00-12.00", "13.00-14.00", "14.00-16.00", "16.00-18.00"};
                    data.add(data_array.get(5).trim());
                    for (String need : option_time) {
                        if (!need.equalsIgnoreCase(data_array.get(5))) {
                            data.add(need);
                        }
                    }

                    Edit_Time_Box.setModel(new DefaultComboBoxModel<>(data.toArray(new String[0])));
                    data.clear();

                    String[] option_mode_class = {"Online", "Physical", "Hybrid"};
                    data.add(data_array.get(6).trim());
                    for (String need : option_mode_class) {
                        if (!need.equalsIgnoreCase(data_array.get(6))) {
                            data.add(need);
                        }
                    }

                    Edit_Mode_Class.setModel(new DefaultComboBoxModel<>(data.toArray(new String[0])));
                    data.clear();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int data_place = EditTable.getSelectedRow();
                String class_id = EditTable.getValueAt(data_place,0).toString();

                String class_name = Edit_Class_Name.getText().toLowerCase();
                String class_type = (String) Edit_Class_Type.getSelectedItem();
                String classroom = (String) Edit_Classroom.getSelectedItem();
                String week = (String) Edit_Week_Box.getSelectedItem();
                String time = (String) Edit_Time_Box.getSelectedItem();
                String class_mode = (String) Edit_Mode_Class.getSelectedItem();
                Boolean active = true;
                String tutor_id = TUTOR.getId();

                datamanager.updateData("class.txt", new ArrayList<>(Arrays.asList(
                        class_id,
                        class_name,
                        class_type,
                        classroom,
                        week,
                        time,
                        class_mode,
                        active,
                        tutor_id
                )));
                JOptionPane.showMessageDialog(null, "Update Successful");
                saveButton.setVisible(false);
                set_edit_table();
                EditDetailFrame.setVisible(false);

            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> data_array = get_row_data();
                if (data_array ==null){
                    return;
                }

                int user_result = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete this record ?",
                        "Confirm delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (user_result == JOptionPane.YES_OPTION){
                    String class_id =data_array.get(0);
                    String class_name = data_array.get(1);
                    String class_type = data_array.get(2);
                    String classroom = data_array.get(3);
                    String week = data_array.get(4);
                    String time =data_array.get(5);
                    String class_mode = data_array.get(6);
                    Boolean active = false;
                    String tutor_id = TUTOR.getId();
                    datamanager.updateData("class.txt",new ArrayList<>(Arrays.asList(
                            class_id,
                            class_name,
                            class_type,
                            classroom,
                            week,
                            time,
                            class_mode,
                            active,
                            tutor_id
                    )));
                    JOptionPane.showMessageDialog(null,
                            "Class ID: " + class_id + "\n" +
                                    "Subject: " + class_name + "\n" +
                                    "has been deleted !");
                    set_edit_table();
                } else{
                    JOptionPane.showMessageDialog(null,"Delete canceled !");
                    set_edit_table();
                }
            }
        });

        // set the word to gray and disappear when user type
        Search_Bar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (Search_Bar.getText().equals("Class ID / Subject")){
                    Search_Bar.setText("");
                    Search_Bar.setForeground(Color.BLACK);
                }
            }
        });

        Search_Bar.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (Search_Bar.getText().isEmpty()){
                    Search_Bar.setText("Class ID / Subject");
                    Search_Bar.setForeground(Color.lightGray);
                }
            }
        });

        EditEnterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = Search_Bar.getText();
                String input_lower = input.toLowerCase();
                search_set_edit_table(input_lower);


            }
        });
        viewStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditClassFrame.setVisible(false);
                Student_Frame.setVisible(true);
                String username ="";
                String user_id ="";
                String class_id ="";
                String student_id ="";
                String stu_level ="";

                String [] element = {"Student ID", "Student Name" , "Student Level"};
                DefaultTableModel table = new DefaultTableModel(null, element) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                ArrayList<String> row_data = get_row_data();
                ArrayList<String> data_tutor_sub = datamanager.loadData("tutor_subjects.txt");
                ArrayList<String> data_student_subject = datamanager.loadData("student_subjects.txt");
                ArrayList<String> data_user = datamanager.loadData("users.txt");
                ArrayList<String> data_tutor = datamanager.loadData("tutors.txt");
                ArrayList<String> data_student = datamanager.loadData("students.txt");
                ArrayList<String> data_class =datamanager.loadData("class.txt");
                System.out.println(row_data);


                for (String da_stu : data_student) {
                    String[] student = da_stu.split(",");
                    student_id = student[0];
                    stu_level = student[1];

                    boolean first_pass = false;
                    for (String stu_sub : data_student_subject) {
                        String[] student_subject = stu_sub.split(",");
                        if (student_subject[2].equalsIgnoreCase(row_data.get(1)) && student_subject[1].equals(student[0])) {
                            first_pass = true;
                            break;
                        }
                    }

                    if (!first_pass) continue;



                    boolean second_pass =false;
                    for (String da_tutor : data_tutor) {
                        String[] tutor = da_tutor.split(",");
                        if (tutor[1].equals(student[1])) {
                            second_pass = true;
                            break;
                        }
                    }

                    if (!second_pass)continue;

                    boolean third_pass = false;
                    for (String da_class : data_class) {
                        String[] classes = da_class.split(",");
                        class_id = classes[0];
                        if (TUTOR.getId().equals(classes[8])) {
                            third_pass = true;
                            break;
                        }
                    }

                    if (!third_pass)continue;

                    boolean fifth_pass = false;
                    for (String sub: data_tutor_sub) {
                        String[] tutor_sub = sub.split(",");
                        if (!tutor_sub[2].equalsIgnoreCase(row_data.get(1))) continue;

                        for (String da_user : data_user) {
                            String[] user = da_user.split(",");

                            if (tutor_sub[2].equalsIgnoreCase(row_data.get(1))) {
                                if (student_id.equals(user[0])) {
                                    username = user[1];
                                    user_id = user[0];

                                    ArrayList<Object> data_classes = datamanager.getData("class.txt", class_id);
                                    if (data_classes.get(7).equals(true)) {
                                        table.addRow(new String[]{user_id, username, stu_level});
                                        fifth_pass = true;
                                        break;
                                    }

                                }
                            }

                        }
                        if(!fifth_pass)break;
                    }
                }

                Student_Class_ID_Text.setText(row_data.get(0));
                Student_Subject_Text.setText(row_data.get(1));
                Student_Table.getTableHeader().setReorderingAllowed(false);
                Student_Table.setModel(table);
            }
        });


        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditClassFrame.setVisible(true);
                Student_Frame.setVisible(false);
                set_edit_table();
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

public void get_subject(){
    String id = TUTOR.getId();
    ArrayList<String> data_string = new ArrayList<>();
    data_string.add("< None >");
    ArrayList<String> data = datamanager.loadData("tutor_subjects.txt");
    for (String data_sub:data){
        String [] data_need = data_sub.split(",");
        if (data_need[1].equals(id)) {
            String subject = data_need[2];
            String subject_upper = (Character.toUpperCase(subject.charAt(0)) + subject.substring(1));
            data_string.add(subject_upper);

        }
    }
    String [] element_sub = data_string.toArray(new String[0]);
    ClassNameBox.setModel(new DefaultComboBoxModel<>(element_sub));
}

    public void set_edit_table(){
    String [] element = {"Class ID", "Subject","Class Type", "Classroom","Week", "Time","Class Mode"};
    DefaultTableModel table = new DefaultTableModel(null, element) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
    }
    };

    String id = TUTOR.getId();
    ArrayList<String> class_data = datamanager.loadData("class.txt");
        for (String data_string_class : class_data) {
            String[] data_class = data_string_class.split(",");
            if (data_class[8].equals(id)) {

                ArrayList<Object> data_needed = datamanager.getData("class.txt",data_class[0]);

                if (data_needed.get(7).equals(true)) {
                    String sub = data_class[1];
                    String sub_upper = (Character.toUpperCase(sub.charAt(0)) + sub.substring(1));
                    table.addRow(new Object[]{data_needed.get(0),sub_upper,data_needed.get(2),data_needed.get(3),data_needed.get(4),data_needed.get(5),data_needed.get(6)});
                }
             }
        }
        EditTable.getTableHeader().setReorderingAllowed(false);
        EditTable.setModel(table);
}

// get selected row data
public ArrayList<String> get_row_data(){
        int data_place = EditTable.getSelectedRow();

        if(data_place == -1){
            JOptionPane.showMessageDialog(null, "Please select a row");
            return null;
        }

        String class_id = EditTable.getValueAt(data_place,0).toString();
        String class_name = EditTable.getValueAt(data_place,1).toString();
        String class_type = EditTable.getValueAt(data_place,2).toString();
        String classroom = EditTable.getValueAt(data_place,3).toString();
        String week =EditTable.getValueAt(data_place,4).toString();
        String time = EditTable.getValueAt(data_place,5).toString();
        String class_mode = EditTable.getValueAt(data_place, 6).toString();

        ArrayList<String> data_array = new ArrayList<>();

        data_array.add(class_id);
        data_array.add(class_name);
        data_array.add(class_type);
        data_array.add(classroom);
        data_array.add(week);
        data_array.add(time);
        data_array.add(class_mode);

    System.out.println(data_array);

        return data_array;
    }

    public void search_set_edit_table(String input){
        String [] element = {"Class ID", "Subject","Class Type", "Classroom","Week", "Time","Class Mode"};
        DefaultTableModel table = new DefaultTableModel(null, element) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<String> class_data = datamanager.loadData("class.txt");

        if (input.isEmpty() || input.equalsIgnoreCase("Class ID / Subject")){
            set_edit_table();
            return;
        }

        for (String data_string_class : class_data) {
            String[] data_class = data_string_class.split(",");
            if (data_class[0].equals(input) || data_class[1].equalsIgnoreCase(input)) {

                ArrayList<Object> data_needed = datamanager.getData("class.txt",data_class[0]);

                if (data_needed.get(7).equals(true)) {
                    String sub = data_class[1];
                    String sub_upper = (Character.toUpperCase(sub.charAt(0)) + sub.substring(1));
                    table.addRow(new Object[]{data_needed.get(0),sub_upper,data_needed.get(2),data_needed.get(3),data_needed.get(4),data_needed.get(5),data_needed.get(6)});
                }
            }
        }
        EditTable.getTableHeader().setReorderingAllowed(false);
        EditTable.setModel(table);
    }

    public void Run(tutor system){
        TUTOR = system;
        WelcomeTitle.setText("Hello, " + TUTOR.getUsername() + " !");
    }

    public static void main(String[] args) {
        Tutor_UI ui = new Tutor_UI();
    }


}
