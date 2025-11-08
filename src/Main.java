
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static StudentManager manager = new StudentManager();
    private static DefaultTableModel tableModel;

    public static void main(String[] args){
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Table
        String[] columns = {"Name","Age","Grade"};
        tableModel = new DefaultTableModel(columns,0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(5,2,5,5));
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField gradeField = new JTextField();
        JButton addBtn = new JButton("Add Student");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton refreshBtn = new JButton("Refresh Table");
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);
        inputPanel.add(addBtn);
        inputPanel.add(updateBtn);
        inputPanel.add(deleteBtn);
        inputPanel.add(refreshBtn);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String grade = gradeField.getText();
            manager.addStudent(new Student(name, age, grade));
            JOptionPane.showMessageDialog(frame,"Student added!");
            refreshTable();
        });

        updateBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow >=0){
                String oldName = (String)table.getValueAt(selectedRow,0);
                String newName = nameField.getText();
                int newAge = Integer.parseInt(ageField.getText());
                String newGrade = gradeField.getText();
                manager.updateStudent(oldName, new Student(newName,newAge,newGrade));
                JOptionPane.showMessageDialog(frame,"Student updated!");
                refreshTable();
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if(selectedRow >=0){
                String name = (String)table.getValueAt(selectedRow,0);
                manager.deleteStudent(name);
                JOptionPane.showMessageDialog(frame,"Student deleted!");
                refreshTable();
            }
        });

        refreshBtn.addActionListener(e -> refreshTable());

        frame.setVisible(true);
        refreshTable();
    }

    private static void refreshTable(){
        tableModel.setRowCount(0);
        for(Student s : manager.getAllStudents()){
            tableModel.addRow(new Object[]{s.getName(), s.getAge(), s.getGrade()});
        }
    }
}

