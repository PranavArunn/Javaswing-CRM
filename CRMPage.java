import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class CRMPage extends JFrame {
    Color bgColor = new Color(245, 247, 250);
    Color panelColor = new Color(255, 255, 255);
    Color primary = new Color(0, 123, 255);
    Color danger = new Color(220, 53, 69);
    Color success = new Color(40, 167, 69);
    Color warning = new Color(255, 193, 7);

    private JMenuBar menuBar;
    private JMenu fileMenu, helpMenu;

    private JMenuItem openItem;
    private JMenuItem saveItem;
    private JMenuItem exportItem;
    private JMenuItem exitItem;
    private JMenuItem aboutItem;
    private JMenuItem ThankYou;


    private JFileChooser fileChooser;

    private String fileName = "crm_data.csv";




    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
    }

    private void createMenuBar() {

        menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        fileMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        helpMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exportItem = new JMenuItem("Export CSV");
        exitItem = new JMenuItem("Exit");
        aboutItem = new JMenuItem("About Us");
        ThankYou = new JMenuItem("Thank You");


        openItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        saveItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        exportItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        exitItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        aboutItem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        ThankYou.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exportItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        helpMenu.add(aboutItem);
        helpMenu.add(ThankYou);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();

    }



    private void styleButton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }


    private JTextField idField, nameField, phoneField, emailField;
    private JTextArea addressArea;
    private JComboBox<String> categoryBox;
    private DefaultTableModel model;
    private JTable table;

    public CRMPage() {



        setTitle("CRM System");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        createMenuBar();
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton aboutButton = new JButton("About Us");

        aboutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));


        JPanel left = new JPanel(new GridLayout(10, 2, 10, 10));
        left.setBackground(new Color(255, 193, 7));
        left.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        idField = new JTextField();
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        addressArea = new JTextArea(3, 10);

        styleField(idField);
        styleField(nameField);
        styleField(phoneField);
        styleField(emailField);

        categoryBox = new JComboBox<>(new String[]{"Regular", "Premium", "VIP"});

        left.add(new JLabel("Customer ID"));
        left.add(idField);

        left.add(new JLabel("Name"));
        left.add(nameField);

        left.add(new JLabel("Phone"));
        left.add(phoneField);

        left.add(new JLabel("Email"));
        left.add(emailField);

        left.add(new JLabel("Category"));
        left.add(categoryBox);

        left.add(new JLabel("Address"));
        left.add(new JScrollPane(addressArea));

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");

        styleButton(addBtn, success);
        styleButton(updateBtn, primary);
        styleButton(deleteBtn, danger);
        styleButton(clearBtn, Color.cyan);
        styleButton(aboutButton, Color.GREEN);


        left.add(addBtn);
        left.add(updateBtn);
        left.add(deleteBtn);
        left.add(clearBtn);



        add(left, BorderLayout.WEST);

        JLabel title = new JLabel("CRM Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        add(title, BorderLayout.NORTH);


        String[] cols = {"ID", "Name", "Phone", "Email", "Category", "Address"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadFromFile();


        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                idField.setText(model.getValueAt(row, 0).toString());
                nameField.setText(model.getValueAt(row, 1).toString());
                phoneField.setText(model.getValueAt(row, 2).toString());
                emailField.setText(model.getValueAt(row, 3).toString());
                categoryBox.setSelectedItem(model.getValueAt(row, 4));
                addressArea.setText(model.getValueAt(row, 5).toString());
            }
        });


       /* addBtn.addActionListener(e -> {

            String id = idField.getText();
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String category = (String) categoryBox.getSelectedItem();
            String address = addressArea.getText();

            if (id.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fill required fields!");
                return;
            }

            model.addRow(new Object[]{id, name, phone, email, category, address});
            saveToFile();
            clearFields();
        });
*/

        addBtn.addActionListener(e -> {

            try {

                validateInput();

                for(int i = 0; i < model.getRowCount(); i++) {

                    if(model.getValueAt(i,0).toString().equals(idField.getText())) {

                        throw new IllegalArgumentException("Customer ID already exists.");

                    }
                }

                model.addRow(new Object[]{

                        idField.getText(),
                        nameField.getText(),
                        phoneField.getText(),
                        emailField.getText(),
                        categoryBox.getSelectedItem(),
                        addressArea.getText()

                });

                saveToFile();

                clearFields();

                JOptionPane.showMessageDialog(this,
                        "Customer Added Successfully!");

            }

            catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

            finally{

                System.out.println("Add Operation Finished");

            }

        });

        /*updateBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to update!");
                return;
            }

            model.setValueAt(idField.getText(), row, 0);
            model.setValueAt(nameField.getText(), row, 1);
            model.setValueAt(phoneField.getText(), row, 2);
            model.setValueAt(emailField.getText(), row, 3);
            model.setValueAt(categoryBox.getSelectedItem(), row, 4);
            model.setValueAt(addressArea.getText(), row, 5);

            saveToFile();
            JOptionPane.showMessageDialog(this, "Updated!");
        });
*/
        updateBtn.addActionListener(e -> {

            try{

                int row = table.getSelectedRow();

                if(row==-1){

                    throw new IllegalArgumentException("Please select a customer.");

                }

                validateInput();

                model.setValueAt(idField.getText(),row,0);
                model.setValueAt(nameField.getText(),row,1);
                model.setValueAt(phoneField.getText(),row,2);
                model.setValueAt(emailField.getText(),row,3);
                model.setValueAt(categoryBox.getSelectedItem(),row,4);
                model.setValueAt(addressArea.getText(),row,5);

                saveToFile();

                JOptionPane.showMessageDialog(this,
                        "Customer Updated Successfully.");

            }

            catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            }

            finally{

                System.out.println("Update Finished");

            }

        });




        /*deleteBtn.addActionListener(e -> {

            int row = table.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a row to delete!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Delete this record?");
            if (confirm == 0) {
                model.removeRow(row);
                saveToFile();
            }
        });

         */
        deleteBtn.addActionListener(e -> {

            try{

                int row = table.getSelectedRow();

                if(row==-1){

                    throw new IllegalArgumentException("Select a customer first.");

                }

                int choice = JOptionPane.showConfirmDialog(

                        this,

                        "Delete selected customer?",

                        "Confirm",

                        JOptionPane.YES_NO_OPTION

                );

                if(choice==JOptionPane.YES_OPTION){

                    model.removeRow(row);

                    saveToFile();

                    JOptionPane.showMessageDialog(this,
                            "Customer Deleted.");

                }

            }

            catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            }

            finally{

                System.out.println("Delete Finished");

            }

        });


        clearBtn.addActionListener(e -> clearFields());
        getContentPane().setBackground(bgColor);

        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        table.getTableHeader().setBackground(primary);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        table.setSelectionBackground(new Color(255, 204, 102));
        table.setSelectionForeground(Color.BLACK);

        table.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(240,248,255) : Color.WHITE);
                }

                return c;
            }
        });

        openItem.addActionListener(e -> {

            int result = fileChooser.showOpenDialog(this);

            if(result == JFileChooser.APPROVE_OPTION){

                fileName = fileChooser.getSelectedFile().getAbsolutePath();

                loadFromFile();

                JOptionPane.showMessageDialog(this,
                        "Records Loaded Successfully!");

            }

        });
        saveItem.addActionListener(e -> {

            saveToFile();

            JOptionPane.showMessageDialog(this,
                    "Records Saved Successfully!");

        });

        exportItem.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            chooser.setSelectedFile(new File("Customers.csv"));

            int result = chooser.showSaveDialog(this);

            if(result == JFileChooser.APPROVE_OPTION){

                try{

                    File exportFile = chooser.getSelectedFile();

                    PrintWriter pw = new PrintWriter(exportFile);

                    pw.println("ID,Name,Phone,Email,Category,Address");

                    for(int i=0;i<model.getRowCount();i++){

                        for(int j=0;j<model.getColumnCount();j++){

                            pw.print(model.getValueAt(i,j));

                            if(j<model.getColumnCount()-1)
                                pw.print(",");

                        }

                        pw.println();

                    }

                    pw.close();

                    JOptionPane.showMessageDialog(this,
                            "CSV Exported Successfully!");

                }

                catch(Exception ex){

                    JOptionPane.showMessageDialog(this,
                            "Export Failed!");

                }

            }

        });

        exitItem.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(

                    this,

                    "Save before exiting?",

                    "Exit",

                    JOptionPane.YES_NO_CANCEL_OPTION

            );

            if(option == JOptionPane.YES_OPTION){

                saveToFile();

                System.exit(0);

            }

            else if(option == JOptionPane.NO_OPTION){

                System.exit(0);


            }

        });

        aboutItem.addActionListener(e -> new AboutUsPage());
        ThankYou.addActionListener(e-> new ThankYouPage().setVisible(true));




        setVisible(true);




    }

    private void validateInput() {

        if(idField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer ID cannot be empty.");
        }

        if(nameField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer Name cannot be empty.");
        }

        if(!phoneField.getText().trim().matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must contain exactly 10 digits.");
        }

        if(!emailField.getText().trim().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email address.");
        }
    }



    private void saveToFile() {

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {

            for (int i = 0; i < model.getRowCount(); i++) {

                for (int j = 0; j < model.getColumnCount(); j++) {
                    pw.print(model.getValueAt(i, j));
                    if (j < model.getColumnCount() - 1) pw.print(",");
                }

                pw.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void loadFromFile() {

        File file = new File(fileName);

        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) {
                model.addRow(line.split(","));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressArea.setText("");
        categoryBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new CRMPage();
    }
}


