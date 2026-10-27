
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class LoginForm extends JFrame {

    private JTextField input1;
    private JPasswordField input2;

    public LoginForm() {

        setTitle("Employee Management System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 248, 255));
        add(panel);

        // Title
        JLabel title = new JLabel(" EMPLOYEE MANAGEMENT ");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(40, 70, 170));
        title.setBounds(70, 25, 350, 35);
        panel.add(title);

        JLabel subtitle = new JLabel("Secure Login");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBounds(185, 60, 120, 20);
        panel.add(subtitle);

        // Username
        JLabel lblUser = new JLabel("Username");
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblUser.setBounds(70, 110, 100, 25);
        panel.add(lblUser);

        input1 = new JTextField();
        input1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        input1.setBounds(70, 135, 340, 38);
        input1.setBorder(new LineBorder(new Color(180,180,180),1,true));
        panel.add(input1);

        // Password
        JLabel lblPwd = new JLabel("Password");
        lblPwd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblPwd.setBounds(70, 190, 100, 25);
        panel.add(lblPwd);

        input2 = new JPasswordField();
        input2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        input2.setBounds(70, 215, 340, 38);
        input2.setBorder(new LineBorder(new Color(180,180,180),1,true));
        panel.add(input2);

        // Login Button
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnLogin.setBackground(new Color(52, 120, 246));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder());
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setBounds(140, 290, 200, 45);
        panel.add(btnLogin);

        // Action
        btnLogin.addActionListener(e -> {

            String username = input1.getText().trim();
            String pwd = new String(input2.getPassword());

            if(username.equals("Aptech123") && pwd.equals("12345")){

                JOptionPane.showMessageDialog(
                        this,
                        "Welcome " + username + "!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                new Employeeform().setVisible(true);
                dispose();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Username or Password",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);

                input2.setText("");
                input2.requestFocus();
            }

        });

        getRootPane().setDefaultButton(btnLogin);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginForm().setVisible(true));
    }
}

