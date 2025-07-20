package frontend;

import backend.datamanager;
import backend.student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Student_UI extends JFrame {
    private JPanel mainPanel;
    private JPanel formPanel;
    private JPanel buttonPanel;

    private JLabel titleLabel;
    private JLabel studentIdLabel;
    private JLabel messageLabel;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField contactField;
    private JTextField addressField;

    private JPasswordField passwordField;

    private JComboBox<String> levelComboBox;

    private JButton cancelButton;
    private JButton updateButton;

    private JLabel welcomeTitle;
    private JButton updateProfileButton, logOutButton;
    private JButton viewScheduleButton, sendRequestButton, deleteRequestButton, sendPaymentRequestButton;
    private JPanel studentPanel;
    private static student STUDENT;

    public Student_UI() {
        setTitle("Advanced Tuition Centre");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        studentPanel = new JPanel(new BorderLayout());

        welcomeTitle = new JLabel("Hello, Student!", SwingConstants.CENTER);
        welcomeTitle.setFont(new Font("Arial", Font.BOLD, 24));
        studentPanel.add(welcomeTitle, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 15, 15));

        updateProfileButton = new JButton("Update Profile");
        logOutButton = new JButton("Log Out");
        viewScheduleButton = new JButton("View Class Schedule");
        sendRequestButton = new JButton("Send Subject Change Request");
        deleteRequestButton = new JButton("Delete Pending Request");
        sendPaymentRequestButton = new JButton("Send Payment Request");
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        buttonPanel.add(viewScheduleButton);
        buttonPanel.add(sendRequestButton);
        buttonPanel.add(deleteRequestButton);
        buttonPanel.add(sendPaymentRequestButton);
        buttonPanel.add(updateProfileButton);
        buttonPanel.add(logOutButton);

        studentPanel.add(buttonPanel, BorderLayout.CENTER);
        setContentPane(studentPanel);
        setVisible(true);

        // Log out
        logOutButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure want to log out?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dispose();
                new Login_UI();
            }
        });

        // Update Profile
        updateProfileButton.addActionListener(e -> {
            JDialog dialog = new JDialog(this, "Update Profile", true);
            dialog.setSize(400, 400);
            dialog.setLocationRelativeTo(this);

            JPanel panel = new JPanel(new GridLayout(7, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

            // Fields
            JTextField nameField = new JTextField(STUDENT.getUsername());
            JPasswordField passwordField = new JPasswordField(STUDENT.getPassword());
            JTextField icField = new JTextField(STUDENT.getIc() != null ? STUDENT.getIc() : "");
            JTextField emailField = new JTextField(STUDENT.getEmail() != null ? STUDENT.getEmail() : "");
            JTextField contactField = new JTextField(STUDENT.getContact_number() != null ? STUDENT.getContact_number() : "");
            JTextField addressField = new JTextField(STUDENT.getAddress() != null ? STUDENT.getAddress() : "");

            panel.add(new JLabel("Name:"));
            panel.add(nameField);
            panel.add(new JLabel("Password:"));
            panel.add(passwordField);
            panel.add(new JLabel("IC:"));
            panel.add(icField);
            panel.add(new JLabel("Email:"));
            panel.add(emailField);
            panel.add(new JLabel("Contact No:"));
            panel.add(contactField);
            panel.add(new JLabel("Address:"));
            panel.add(addressField);

            JButton updateBtn = new JButton("Update");
            JButton cancelBtn = new JButton("Cancel");

            JPanel btnPanel = new JPanel();
            btnPanel.add(updateBtn);
            btnPanel.add(cancelBtn);

            JLabel msgLabel = new JLabel("", SwingConstants.CENTER);
            msgLabel.setForeground(Color.RED);

            dialog.setLayout(new BorderLayout());
            dialog.add(panel, BorderLayout.CENTER);
            dialog.add(btnPanel, BorderLayout.SOUTH);
            dialog.add(msgLabel, BorderLayout.NORTH);

            updateBtn.addActionListener(ev -> {
                String name = nameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String ic = icField.getText().trim();
                String email = emailField.getText().trim();
                String contact = contactField.getText().trim();
                String address = addressField.getText().trim();

                if (name.isEmpty() || password.isEmpty() || email.isEmpty()
                        || contact.isEmpty() || address.isEmpty()) {
                    msgLabel.setText("Please fill in all fields!");
                    return;
                }

                try {
                    ArrayList<Object> data = datamanager.getData("users.txt", STUDENT.getId());
                    data.set(1, name);     // username
                    data.set(2, password); // password
                    data.set(5, ic);       // ic
                    data.set(6, email);    // email
                    data.set(7, contact);  // contact_number
                    data.set(8, address);  // address

                    datamanager.updateData("users.txt", data);

                    // update student object
                    STUDENT.setUsername(name);
                    STUDENT.setPassword(password);
                    STUDENT.setIc(ic);
                    STUDENT.setEmail(email);
                    STUDENT.setContact(contact);
                    STUDENT.setAddress(address);

                    welcomeTitle.setText("Hello, " + name + "!");
                    JOptionPane.showMessageDialog(this, "Profile updated successfully!");
                    dialog.dispose();
                } catch (Exception ex) {
                    msgLabel.setText("Update failed: " + ex.getMessage());
                }
            });

            cancelBtn.addActionListener(ev -> dialog.dispose());

            dialog.setVisible(true);
        });

        // ✅ View Class Schedule
        // 🔁 View ALL Classes (ignore student_subjects.txt & active status)
        viewScheduleButton.addActionListener(e -> {
            ArrayList<String> classes = datamanager.loadData("class.txt");
            StringBuilder schedule = new StringBuilder();

            for (String line : classes) {
                String[] parts = line.split(",");
                if (parts.length < 8) continue;

                schedule.append("Subject: ").append(parts[1])
                        .append(", Type: ").append(parts[2])
                        .append(", Room: ").append(parts[3])
                        .append(", Day: ").append(parts[4])
                        .append(", Time: ").append(parts[5])
                        .append(", Mode: ").append(parts[6])
                        .append("\n");
            }

            if (schedule.length() == 0) {
                schedule.append("No classes available.");
            }

            JOptionPane.showMessageDialog(null, schedule.toString(), "Class Schedule", JOptionPane.INFORMATION_MESSAGE);
        });


        // ✅ View Payment Status
        // ✅ Send Payment Request
        sendPaymentRequestButton.addActionListener(e -> {
            String studentId = STUDENT.getId();

            // 🔽 STEP 1: Get all enrolled subjects for the student from student_subjects.txt
            ArrayList<String> subjectLines = datamanager.loadData("student_subjects.txt");
            ArrayList<String> subjectOptions = new ArrayList<>();
            for (String line : subjectLines) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[1].equals(studentId)) {
                    subjectOptions.add(parts[2]); // subject at index 2
                }
            }

            if (subjectOptions.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You are not enrolled in any subjects.");
                return;
            }

            // 🔘 Subject dropdown
            String[] subjectArray = subjectOptions.toArray(new String[0]);
            String selectedSubject = (String) JOptionPane.showInputDialog(
                    null,
                    "Select subject to make payment request:",
                    "Choose Subject",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    subjectArray,
                    subjectArray[0]
            );

            if (selectedSubject == null || selectedSubject.trim().isEmpty()) return;

            selectedSubject = selectedSubject.trim().toLowerCase();

            // ✅ STEP 2: Get total fee for selected subject
            double totalFee = 0;
            for (String line : subjectLines) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[1].equals(studentId) && parts[2].equalsIgnoreCase(selectedSubject)) {
                    totalFee = Double.parseDouble(parts[4]); // total_fees at index 4
                    break;
                }
            }

            // 💰 STEP 3: Calculate paid + pending
            double paid = 0, pending = 0;

            ArrayList<String> receipts = datamanager.loadData("student_receipts.txt");
            for (String line : receipts) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[1].equals(studentId) && parts[3].equalsIgnoreCase(selectedSubject)) {
                    paid += Double.parseDouble(parts[2]);
                }
            }

            ArrayList<String> requests = datamanager.loadData("payment_requests.txt");
            for (String line : requests) {
                String[] parts = line.split(",");
                if (parts.length >= 4 && parts[1].equals(studentId) && parts[3].equalsIgnoreCase(selectedSubject)) {
                    pending += Double.parseDouble(parts[2]);
                }
            }

            double remaining = totalFee - paid - pending;

            if (remaining <= 0) {
                JOptionPane.showMessageDialog(null, "No remaining balance for " + selectedSubject + ".");
                return;
            }

            // ✍️ Input amount to request (validated)
            String input = JOptionPane.showInputDialog("Enter amount to request (Remaining: ₹" + remaining + "):");
            if (input == null || input.trim().isEmpty()) return;

            double requestAmount;
            try {
                requestAmount = Double.parseDouble(input.trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid amount.");
                return;
            }

            if (requestAmount <= 0 || requestAmount > remaining) {
                JOptionPane.showMessageDialog(null, "Amount must be between 0 and ₹" + remaining);
                return;
            }

            // 📝 Save the request
            ArrayList<Object> newRequest = new ArrayList<>();
            newRequest.add(datamanager.generateID("payment_requests.txt")); // id
            newRequest.add(studentId);
            newRequest.add(requestAmount);
            newRequest.add(selectedSubject);

            datamanager.addLine("payment_requests.txt", newRequest);
            JOptionPane.showMessageDialog(null, "Payment request for ₹" + requestAmount + " submitted.");
        });


        // Send Subject Change Request
        sendRequestButton.addActionListener(e -> {
            String studentId = STUDENT.getId();

            // Step 1: Load current subjects of the student
            ArrayList<String> subjects = new ArrayList<>();
            ArrayList<String> studentSubjects = datamanager.loadData("student_subjects.txt");
            for (String line : studentSubjects) {
                String[] parts = line.split(",");
                if (parts[1].equals(studentId)) {
                    subjects.add(parts[2]);
                }
            }

            if (subjects.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You are not enrolled in any subjects.");
                return;
            }

            // Step 2: Choose subject to change (oldSubject)
            String oldSubject = (String) JOptionPane.showInputDialog(
                    null, "Select subject to change:", "Old Subject",
                    JOptionPane.PLAIN_MESSAGE, null, subjects.toArray(), subjects.get(0));

            if (oldSubject == null || oldSubject.trim().isEmpty()) {
                return;
            }

            // Step 3: Input new subject (newSubject)
            String newSubject = JOptionPane.showInputDialog("Enter new subject:");
            if (newSubject == null || newSubject.trim().isEmpty()) {
                return;
            }

            // Step 4: Check if this request already exists
            ArrayList<String> requests = datamanager.loadData("student_requests.txt");
            boolean alreadyExists = false;
            for (String line : requests) {
                String[] parts = line.split(",");
                if (parts[1].equals(studentId) && parts[2].equalsIgnoreCase(oldSubject)) {
                    alreadyExists = true;
                    break;
                }
            }

            if (alreadyExists) {
                JOptionPane.showMessageDialog(null, "You already have a pending request for that subject.");
                return;
            }

            // Step 5: Save main request
            ArrayList<Object> request1 = new ArrayList<>();
            request1.add(datamanager.generateID("student_requests.txt")); // id
            request1.add(studentId);
            request1.add(oldSubject);
            request1.add(newSubject);
            datamanager.addLine("student_requests.txt", request1);



            JOptionPane.showMessageDialog(null, "Subject change request(s) submitted.");
        });

        // Delete Pending Request
        deleteRequestButton.addActionListener(e -> {
            ArrayList<String> requests = datamanager.loadData("student_requests.txt");
            ArrayList<String> userRequests = new ArrayList<>();

            // Build user-specific request display entries
            for (String line : requests) {
                String[] parts = line.split(",");
                if (parts[1].equals(STUDENT.getId())) {
                    // Format: old_subject → new_subject
                    String display = parts[2] + " → " + parts[3];
                    userRequests.add(display);
                }
            }

            if (userRequests.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No pending requests.");
                return;
            }

            // Let student choose which request to delete
            String toDelete = (String) JOptionPane.showInputDialog(null, "Select request to delete:",
                    "Delete Request", JOptionPane.PLAIN_MESSAGE, null,
                    userRequests.toArray(), userRequests.get(0));

            if (toDelete != null) {
                // Extract old_subject and new_subject from selected line
                String[] selectedParts = toDelete.split("→");
                String oldSubject = selectedParts[0].trim();
                String newSubject = selectedParts[1].trim();

                String idToDelete = null;

                for (String line : requests) {
                    String[] parts = line.split(",");

                    if ((parts[1].equals(STUDENT.getId())
                            && parts[2].equalsIgnoreCase(oldSubject)
                            && parts[3].equalsIgnoreCase(newSubject))) {
                        idToDelete = parts[0];
                    }
                }

                datamanager.deleteData("student_requests.txt",idToDelete);
                JOptionPane.showMessageDialog(null, "Request deleted.");
            }
        });

    }

    public void Run(student system) {
        STUDENT = system;
        welcomeTitle.setText("Hello, " + STUDENT.getUsername() + "!");
    }
}
