import javax.swing.*;
import java.awt.*;

public class ThankYouPage extends JFrame {

    public ThankYouPage() {

        setTitle("Thank You");
        setSize(550, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 248, 255));
        panel.setLayout(null);
        add(panel);

        JLabel icon = new JLabel("✔");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        icon.setForeground(new Color(34, 139, 34));
        icon.setBounds(240, 20, 80, 80);
        panel.add(icon);

        JLabel title = new JLabel("THANK YOU!");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(new Color(25, 70, 150));
        title.setBounds(155, 90, 250, 40);
        panel.add(title);

        JLabel msg1 = new JLabel("Thank you for using the Employee CRM System.");
        msg1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        msg1.setBounds(95, 145, 370, 25);
        panel.add(msg1);

        JLabel msg2 = new JLabel("We appreciate your time. Have a wonderful day!");
        msg2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        msg2.setBounds(90, 170, 380, 25);
        panel.add(msg2);

        JLabel footer = new JLabel("Developed by Pranav");
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        footer.setForeground(Color.GRAY);
        footer.setBounds(205, 215, 150, 20);
        panel.add(footer);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        exitButton.setBackground(new Color(52, 120, 246));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBounds(200, 255, 120, 40);

        exitButton.addActionListener(e -> System.exit(0));

        panel.add(exitButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThankYouPage().setVisible(true));
    }
}