package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registerStudentPanel extends JPanel{
    private JPanel registerPanel;
    private JLabel title;
    private JTextField txtName;
    private JTextField txtIC;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JTextField txtContact;
    private JButton btnSubmit;
    private JFormattedTextField txtDate;
    private JComboBox boxSubject;
    private JList listLevel;



    public registerStudentPanel() {
        setLayout(new BorderLayout());
        add(registerPanel, BorderLayout.CENTER);
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
                String ic = txtIC.getText().trim();
                String email = txtEmail.getText().trim();
                String address = txtAddress.getText().trim();
                String contact = txtContact.getText().trim();
                String date = txtDate.getText().trim();

            }
        });
    }
    public static void main(String[] args) {
            JFrame frame = new JFrame("Register Student");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);

            registerStudentPanel panel = new registerStudentPanel();
            frame.setContentPane(panel);
            frame.setVisible(true);
    }

}
