import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class myAppEmployeeB {
    private int ID;
    private String Name;
    private String Department;
    public double Salary;
    private int Age;
    public String Company;
    public String Address;
    public String Gender;
    public String Nationality;

    public myAppEmployeeB(int ID, String Name, String Department,
                          double Salary, int Age,
                          String Company, String Address,
                          String Gender, String Nationality) {

        this.ID = ID;
        this.Name = Name;
        this.Department = Department;
        this.Age = Age;
        this.Company = Company;
        this.Address = Address;
        this.Gender = Gender;
        this.Nationality = Nationality;

        switch (Department.toLowerCase()) {
            case "marketing":
                this.Salary = Salary + Salary * 1.3;
                break;
            case "finance":
                this.Salary = Salary + Salary * 1.5;
                break;
            case "it":
                this.Salary = Salary + Salary * 1.85;
                break;
            case "ceo":
                this.Salary = Salary + Salary * 1.1;
                break;
            case "administration":
                this.Salary = Salary + Salary * 1.1;
                break;
            case "hr":
                this.Salary = Salary + Salary * 1.4;
                break;
            default:
                this.Salary = Salary;
        }
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getDepartment() {
        return Department;
    }

    public double getSalary() {
        return Salary;
    }

    public int getAge() {
        return Age;
    }

    public String getCompany() {
        return Company;
    }

    public String getAddress() {
        return Address;
    }

    public String getGender() {
        return Gender;
    }

    public String getNationality() {
        return Nationality;
    }

    @Override
    public String toString() {
        return "<html><b>Employee ID:</b> " + ID +
                "<br><b>Name:</b> " + Name +
                "<br><b>Salary:</b> $" + String.format("%.2f", Salary) +
                "<br><b>Age:</b> " + Age + " yrs" +
                "<br><b>Company:</b> " + Company +
                "<br><b>Department:</b> " + Department +
                "<br><b>Address:</b> " + Address +
                "<br><b>Gender:</b> " + Gender +
                "<br><b>Nationality:</b> " + Nationality +
                "</html>";
    }
}

class EmployeeTableFrame extends JFrame {

    private DefaultTableModel model;

    public EmployeeTableFrame() {

        setTitle("Employee Records");
        setSize(1100, 500);
        setLocationRelativeTo(null);

        String[] columns = {
                "ID",
                "Name",
                "Department",
                "Salary",
                "Age",
                "Company",
                "Address",
                "Gender",
                "Nationality"
        };

        model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 14));

        table.setSelectionBackground(
                new Color(255, 153, 51));

        table.setSelectionForeground(Color.BLACK);

        table.setDefaultRenderer(Object.class,
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        Component c =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column);

                        if (!isSelected) {
                            if (row % 2 == 0) {
                                c.setBackground(
                                        new Color(240, 248, 255));
                            } else {
                                c.setBackground(
                                        new Color(255, 250, 205));
                            }
                        }

                        return c;
                    }
                });

        loadData();

        add(new JScrollPane(table));

        setVisible(true);
    }

    private void loadData() {

        File file = new File("employees.csv");

        if (!file.exists()) {
            return;
        }

        try (BufferedReader br =
                     new BufferedReader(
                             new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] row = line.split(",");

                if (row.length == 9) {
                    model.addRow(row);
                }
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error loading records.");
        }
    }
}

public class Employeeform extends JFrame implements ActionListener {

    private JTextField IDField,
            NameField,
            SalaryField,
            AgeField,
            CompanyField,
            DepartmentField,
            AddressField,
            GenderField,
            NationalityField;

    private JButton SubmitBut;
    private JButton ViewButton;
    private JButton NextButton;
    private JButton aboutus;


    private JLabel IDLabel,
            NameLabel,
            SalaryLabel,
            AgeLabel,
            CompanyLabel,
            DepartmentLabel,
            AddressLabel,
            GenderLabel,
            NationalityLabel,
            ResultLabel;

    public Employeeform() {

        setTitle("⭐ Employee Information ⭐");
        setSize(700, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(11, 6, 12, 12));

        getContentPane().setBackground(
                new Color(255, 255, 240));

        Color labelBg = new Color(173, 216, 230);
        Color labelFg = new Color(0, 0, 80);

        Color fieldBg = new Color(255, 255, 204);
        Color fieldFg = new Color(102, 51, 0);

        Color resultBg = new Color(204, 255, 204);
        Color resultFg = new Color(0, 102, 0);

        IDLabel = new JLabel("Employee ID:");
        styleLabel(IDLabel, labelBg, labelFg);

        IDField = new JTextField();
        styleTextField(IDField, fieldBg, fieldFg);

        NameLabel = new JLabel("Name:");
        styleLabel(NameLabel, labelBg, labelFg);

        NameField = new JTextField();
        styleTextField(NameField, fieldBg, fieldFg);

        SalaryLabel = new JLabel("Salary:");
        styleLabel(SalaryLabel, labelBg, labelFg);

        SalaryField = new JTextField();
        styleTextField(SalaryField, fieldBg, fieldFg);

        AgeLabel = new JLabel("Age:");
        styleLabel(AgeLabel, labelBg, labelFg);

        AgeField = new JTextField();
        styleTextField(AgeField, fieldBg, fieldFg);

        CompanyLabel = new JLabel("Company:");
        styleLabel(CompanyLabel, labelBg, labelFg);

        CompanyField = new JTextField();
        styleTextField(CompanyField, fieldBg, fieldFg);

        DepartmentLabel = new JLabel("Department:");
        styleLabel(DepartmentLabel, labelBg, labelFg);

        DepartmentField = new JTextField();
        styleTextField(DepartmentField, fieldBg, fieldFg);

        AddressLabel = new JLabel("Address:");
        styleLabel(AddressLabel, labelBg, labelFg);

        AddressField = new JTextField();
        styleTextField(AddressField, fieldBg, fieldFg);

        GenderLabel = new JLabel("Gender:");
        styleLabel(GenderLabel, labelBg, labelFg);

        GenderField = new JTextField();
        styleTextField(GenderField, fieldBg, fieldFg);

        NationalityLabel = new JLabel("Nationality:");
        styleLabel(NationalityLabel, labelBg, labelFg);

        NationalityField = new JTextField();
        styleTextField(NationalityField, fieldBg, fieldFg);

        SubmitBut = new JButton("SUBMIT");
        styleButton(
                SubmitBut,
                new Color(255, 153, 102));

        SubmitBut.addActionListener(this);

        ViewButton = new JButton("VIEW RECORDS");
        styleButton(
                ViewButton,
                new Color(102, 255, 178));

        ViewButton.addActionListener(
                e -> new EmployeeTableFrame());
        NextButton = new JButton("CRM PAGE");
        styleButton(NextButton, new Color(255, 115, 115));



        //NextButton.addActionListener(ActionEvent e-> new CRM);
        NextButton.addActionListener(e -> {
            new CRMPage().setVisible(true);
        });

        ResultLabel = new JLabel(
                "⭐ Employee info. will appear here ⭐",
                SwingConstants.CENTER);

        styleLabel(ResultLabel,
                resultBg,
                resultFg);

        ResultLabel.setFont(
                new Font("Verdana",
                        Font.BOLD,
                        13));

        add(IDLabel);
        add(IDField);

        add(NameLabel);
        add(NameField);

        add(SalaryLabel);
        add(SalaryField);

        add(AgeLabel);
        add(AgeField);

        add(CompanyLabel);
        add(CompanyField);

        add(DepartmentLabel);
        add(DepartmentField);

        add(AddressLabel);
        add(AddressField);

        add(GenderLabel);
        add(GenderField);

        add(NationalityLabel);
        add(NationalityField);

        add(SubmitBut);
        add(ViewButton);
        add(NextButton);

        add(ResultLabel);

        setVisible(true);
    }

    /*private void saveEmployeeToFile(
            myAppEmployeeB emp) {

        try (FileWriter fw =
                     new FileWriter(
                             "employees.csv",
                             true);

             PrintWriter pw =
                     new PrintWriter(fw)) {

            pw.println(
                    emp.getID() + "," +
                            emp.getName() + "," +
                            emp.getDepartment() + "," +
                            emp.getSalary() + "," +
                            emp.getAge() + "," +
                            emp.getCompany() + "," +
                            emp.getAddress() + "," +
                            emp.getGender() + "," +
                            emp.getNationality()
            );

        } catch (IOException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error saving record!");
        }
    }
*/

    private void saveEmployeeToFile(myAppEmployeeB emp) {

        File file = new File("employees.csv");

        try {

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file, true);
            PrintWriter pw = new PrintWriter(fw);

            String record =
                    emp.getID() + "," +
                            emp.getName() + "," +
                            emp.getDepartment() + "," +
                            emp.getSalary() + "," +
                            emp.getAge() + "," +
                            emp.getCompany() + "," +
                            emp.getAddress() + "," +
                            emp.getGender() + "," +
                            emp.getNationality();

            pw.println(record);

            pw.close();


            System.out.println("Saved at: " + file.getAbsolutePath());

            JOptionPane.showMessageDialog(this, "Record Saved Successfully!");

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving record!");
        }
    }
    private void styleLabel(
            JLabel label,
            Color bg,
            Color fg) {

        label.setOpaque(true);
        label.setBackground(bg);
        label.setForeground(fg);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        label.setHorizontalAlignment(
                SwingConstants.CENTER);
    }

    private void styleTextField(
            JTextField field,
            Color bg,
            Color fg) {

        field.setBackground(bg);
        field.setForeground(fg);

        field.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        field.setCaretColor(Color.BLACK);
    }

    private void styleButton(
            JButton button,
            Color bgColor) {

        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);

        button.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        button.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(
            ActionEvent e) {

        try {

            int ID = Integer.parseInt(
                    IDField.getText().trim());

            String Name =
                    NameField.getText().trim();

            double Salary =
                    Double.parseDouble(
                            SalaryField.getText().trim());

            int Age =
                    Integer.parseInt(
                            AgeField.getText().trim());

            String Company =
                    CompanyField.getText().trim();

            String Department =
                    DepartmentField.getText().trim();

            String Address =
                    AddressField.getText().trim();

            String Gender =
                    GenderField.getText().trim();

            String Nationality =
                    NationalityField.getText().trim();

            myAppEmployeeB emp =
                    new myAppEmployeeB(
                            ID,
                            Name,
                            Department,
                            Salary,
                            Age,
                            Company,
                            Address,
                            Gender,
                            Nationality);

            saveEmployeeToFile(emp);

            ResultLabel.setText(
                    emp.toString());

        } catch (NumberFormatException ex) {

            ResultLabel.setText(
                    "<html><font color='red'>" +
                            "Invalid Input! Enter numbers for ID, Salary and Age." +
                            "</font></html>");
        }

        System.out.println(new File("employees.csv").getAbsolutePath());
    }

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                Employeeform::new);
    }
}
