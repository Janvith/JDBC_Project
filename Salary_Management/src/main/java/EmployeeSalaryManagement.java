import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeSalaryManagement extends JFrame implements ActionListener {

    JLabel label1, label2, label3, label4, label5, label6, label7, label8;
    JTextField txtId, txtName, txtDesignation, txtBasic, txtHra, txtDa, txtGross;
    JButton btnAdd, btnUpdate, btnDelete, btnClear;
    Connection con=null;

    public EmployeeSalaryManagement() {

        setTitle("Employee Salary Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        label1 = new JLabel("Employee ID");
        label2 = new JLabel("Name");
        label3 = new JLabel("Designation");
        label4 = new JLabel("Basic Salary");
        label5 = new JLabel("HRA");
        label6 = new JLabel("DA");
        label7 = new JLabel("Gross Salary");
        label8 = new JLabel("");

        txtId = new JTextField();
        txtName = new JTextField();
        txtDesignation = new JTextField();
        txtBasic = new JTextField();
        txtHra = new JTextField();
        txtDa = new JTextField();
        txtGross = new JTextField();

        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnClear = new JButton("Clear");

        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);

        JPanel panel1 = new JPanel(new GridLayout(7, 2));
        JPanel panel2 = new JPanel(new FlowLayout());

        panel1.add(label1);
        panel1.add(txtId);
        panel1.add(label2);
        panel1.add(txtName);
        panel1.add(label3);
        panel1.add(txtDesignation);
        panel1.add(label4);
        panel1.add(txtBasic);
        panel1.add(label5);
        panel1.add(txtHra);
        panel1.add(label6);
        panel1.add(txtDa);
        panel1.add(label7);
        panel1.add(txtGross);

        panel2.add(btnAdd);
        panel2.add(btnUpdate);
        panel2.add(btnDelete);
        panel2.add(btnClear);

        add(panel1, BorderLayout.CENTER);
        add(panel2, BorderLayout.SOUTH);
        add(label8, BorderLayout.NORTH);

        setVisible(true);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "Royal@2003");
            label8.setText("Connected to database.");
        } catch (Exception ex) {
            label8.setText("Failed to connect to database: " + ex.getMessage());
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            addEmployee();
        } else if (e.getSource() == btnUpdate) {
            updateEmployee();
        } else if (e.getSource() == btnDelete) {
            deleteEmployee();
        } else if (e.getSource() == btnClear) {
            clearForm();
        }
    }

    public void addEmployee() {
        String id = txtId.getText();
        String name = txtName.getText();
        String designation = txtDesignation.getText();
        String basic = txtBasic.getText();
        String hra = txtHra.getText();
        String da = txtDa.getText();
        String gross = txtGross.getText();

        try {
            PreparedStatement stmt = con.prepareStatement("insert into employees values (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, designation);
            stmt.setString(4, basic);
            stmt.setString(5, hra);
            stmt.setString(6, da);
            stmt.setString(7, gross);
            int result = stmt.executeUpdate();
            if (result > 0) {
            JOptionPane.showMessageDialog(this, "Employee added successfully.");
            } else {
            JOptionPane.showMessageDialog(this, "Failed to add employee.");
            }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to add employee: " + ex.getMessage());
            }
            }
    public void updateEmployee() {
        String id = txtId.getText();
        String name = txtName.getText();
        String designation = txtDesignation.getText();
        String basic = txtBasic.getText();
        String hra = txtHra.getText();
        String da = txtDa.getText();
        String gross = txtGross.getText();

        try {
            PreparedStatement stmt = con.prepareStatement("update employees set name=?, designation=?, basic=?, hra=?, da=?, gross=? where id=?");
            stmt.setString(1, name);
            stmt.setString(2, designation);
            stmt.setString(3, basic);
            stmt.setString(4, hra);
            stmt.setString(5, da);
            stmt.setString(6, gross);
            stmt.setString(7, id);
            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update employee.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to update employee: " + ex.getMessage());
        }
    }

    public void deleteEmployee() {
        String id = txtId.getText();

        try {
            PreparedStatement stmt = con.prepareStatement("delete from employees where id=?");
            stmt.setString(1, id);
            int result = stmt.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete employee.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to delete employee: " + ex.getMessage());
        }
    }

    public void clearForm() {
        txtId.setText("");
        txtName.setText("");
        txtDesignation.setText("");
        txtBasic.setText("");
        txtHra.setText("");
        txtDa.setText("");
        txtGross.setText("");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        new EmployeeSalaryManagement();
    }
}


