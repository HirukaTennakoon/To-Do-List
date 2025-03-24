import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;

    public ToDoListGUI() {
        // Create JFrame (Main Window)
        JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());

        // Task List (JList)
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(taskList);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Input Field and Buttons
        JPanel panel = new JPanel();
        taskField = new JTextField(18);
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearAllButton = new JButton("Clear All");

        panel.add(taskField);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(clearAllButton);
        frame.add(panel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> addTask());
        updateButton.addActionListener(e -> updateTask());
        deleteButton.addActionListener(e -> deleteTask());
        clearAllButton.addActionListener(e -> clearAllTasks());

        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskField.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Task cannot be empty!");
        }
    }

    private void updateTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            String newTask = taskField.getText().trim();
            if (!newTask.isEmpty()) {
                taskListModel.set(selectedIndex, newTask);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Task cannot be empty!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a task to update.");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete this task?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskListModel.remove(selectedIndex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a task to delete.");
        }
    }

    private void clearAllTasks() {
        if (!taskListModel.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete all tasks?", "Confirm Clear All", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                taskListModel.clear();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tasks to clear.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListGUI::new);
    }
}
