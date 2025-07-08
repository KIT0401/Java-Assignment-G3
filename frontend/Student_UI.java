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
    private JLabel welcomeTitle;
    private JButton updateProfileButton, logOutButton;
    private JButton viewScheduleButton, sendRequestButton, deleteRequestButton, viewPaymentButton;
    private JPanel studentPanel;
    private static student STUDENT;

    public Student_UI() {
        setTitle("Advanced Tuition Centre");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        setResizable(false);

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
        viewPaymentButton = new JButton("View Payment Status");

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        buttonPanel.add(viewScheduleButton);
        buttonPanel.add(sendRequestButton);
        buttonPanel.add(deleteRequestButton);
        buttonPanel.add(viewPaymentButton);
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
            String newName = JOptionPane.showInputDialog("Enter new full name:");
            if (newName != null && !newName.trim().isEmpty()) {
                ArrayList<Object> data = datamanager.getData("users.txt", STUDENT.getId());
                data.set(1, newName); // fullname = index 1
                datamanager.updateData("users.txt", data);
                JOptionPane.showMessageDialog(null, "Profile updated.");
            }
        });

        // ✅ View Class Schedule
        viewScheduleButton.addActionListener(e -> {
            String studentId = STUDENT.getId();
            ArrayList<String> subjects = new ArrayList<>();

            ArrayList<String> studentSubjects = datamanager.loadData("student_subjects.txt");
            for (String line : studentSubjects) {
                String[] parts = line.split(",");
                if (parts[1].equals(studentId)) {
                    subjects.add(parts[2].toLowerCase());
                }
            }

            ArrayList<String> classes = datamanager.loadData("class.txt");
            StringBuilder schedule = new StringBuilder();
            for (String line : classes) {
                String[] parts = line.split(",");
                if (parts.length < 8) continue;

                String subject = parts[1].toLowerCase();
                String active = parts[7];

                if (subjects.contains(subject) && active.equalsIgnoreCase("true")) {
                    schedule.append("Subject: ").append(parts[1])
                            .append(", Type: ").append(parts[2])
                            .append(", Room: ").append(parts[3])
                            .append(", Day: ").append(parts[4])
                            .append(", Time: ").append(parts[5])
                            .append(", Mode: ").append(parts[6])
                            .append("\n");
                }
            }

            if (schedule.length() == 0) {
                schedule.append("No classes scheduled.");
            }
            JOptionPane.showMessageDialog(null, schedule.toString(), "Class Schedule", JOptionPane.INFORMATION_MESSAGE);
        });

        // ✅ View Payment Status
        viewPaymentButton.addActionListener(e -> {
            double totalFee = 0;
            double paid = 0;
            double pending = 0;
            String studentId = STUDENT.getId();

            ArrayList<String> studentData = datamanager.loadData("students.txt");
            for (String line : studentData) {
                String[] parts = line.split(",");
                if (parts[0].equals(studentId)) {
                    totalFee = Double.parseDouble(parts[2]);
                    break;
                }
            }

            ArrayList<String> receipts = datamanager.loadData("student_receipts.txt");
            for (String line : receipts) {
                String[] parts = line.split(",");
                if (parts[1].equals(studentId)) {
                    paid += Double.parseDouble(parts[2]);
                }
            }

            ArrayList<String> requests = datamanager.loadData("payment_requests.txt");
            for (String line : requests) {
                String[] parts = line.split(",");
                if (parts[1].equals(studentId)) {
                    pending += Double.parseDouble(parts[2]);
                }
            }

            double balance = totalFee - paid - pending;

            String message = "Total Fee: ₹" + totalFee +
                    "\nPaid: ₹" + paid +
                    "\nPending Requests: ₹" + pending +
                    "\nOutstanding Balance: ₹" + balance;

            JOptionPane.showMessageDialog(null, message, "Payment Status", JOptionPane.INFORMATION_MESSAGE);
        });

        // Send Subject Change Request
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

                ArrayList<String> updated = new ArrayList<>();
                for (String line : requests) {
                    String[] parts = line.split(",");
                    // Keep only lines NOT matching student_id + old + new subject
                    if (!(parts[1].equals(STUDENT.getId())
                            && parts[2].equalsIgnoreCase(oldSubject)
                            && parts[3].equalsIgnoreCase(newSubject))) {
                        updated.add(line);
                    }
                }

                // Overwrite file with updated list
                try (BufferedWriter writer = new BufferedWriter(
                        new FileWriter(datamanager.getFileLocation("student_requests.txt").toFile()))) {
                    for (String l : updated) {
                        writer.write(l + "\n");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "Request deleted.");
            }
        });

    }

    public void Run(student system) {
        STUDENT = system;
        welcomeTitle.setText("Hello, " + STUDENT.getUsername() + "!");
    }
}
