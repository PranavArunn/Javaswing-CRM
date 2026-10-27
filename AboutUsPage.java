import javax.swing.*;
import java.awt.*;

public class AboutUsPage extends JFrame {

    public AboutUsPage() {

        setTitle("About Us");
        setSize(650, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(245,248,252));

        JLabel title = new JLabel("Customer Relationship Management System", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        JTextArea about = new JTextArea();

        about.setEditable(false);
        about.setLineWrap(true);
        about.setWrapStyleWord(true);

        about.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        about.setText(

                "ABOUT THE PROJECT\n\n"

                        + "The CRM (Customer Relationship Management) System is a Java Swing desktop "
                        + "application designed to help organizations efficiently manage customer "
                        + "information in a simple and user-friendly interface.\n\n"

                        + "FEATURES\n"
                        + "• Add New Customers\n"
                        + "• Update Existing Records\n"
                        + "• Delete Customer Records\n"
                        + "• Search Customers\n"
                        + "• Organized Table View\n"
                        + "• Clean Modern User Interface\n\n"

                        + "ABOUT THE DEVELOPER\n\n"

                        + "Developed by:\n"
                        + "Pranav\n\n"

                        + "Student\n"
                        + "JSS Private School, Dubai\n\n"

                        + "Future Computer Science Undergraduate with a passion for Software "
                        + "Development, Artificial Intelligence, Data Analytics, and building "
                        + "real-world applications using Java and Python.\n\n"

                        + "This CRM project demonstrates the use of Java Swing GUI components, "
                        + "event-driven programming, CRUD operations, and object-oriented design "
                        + "principles.\n\n"

                        + "Version : 1.0\n"
                        + "Year : 2026"

        );

        JScrollPane scroll = new JScrollPane(about);

        JButton close = new JButton("Close");
        close.setFont(new Font("Segoe UI", Font.BOLD, 15));

        close.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.add(close);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        panel.add(bottom, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }
}
