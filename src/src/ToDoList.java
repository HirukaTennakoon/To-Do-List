import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ToDoList {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: addTask(); break;
                    case 2: viewTasks(); break;
                    case 3: updateTask(); break;
                    case 4: deleteTask(); break;
                    case 5: System.out.println("Exiting..."); return;
                    default: System.out.println("Invalid option! Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task: ");
        String task = scanner.nextLine().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            System.out.println("Task added successfully!");
        } else {
            System.out.println("Task cannot be empty!");
        }
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("\nYour Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void updateTask() {
        viewTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Enter task number to update: ");
            if (scanner.hasNextInt()) {
                int index = scanner.nextInt() - 1;
                scanner.nextLine();

                if (index >= 0 && index < tasks.size()) {
                    System.out.print("Enter new task: ");
                    String newTask = scanner.nextLine().trim();
                    if (!newTask.isEmpty()) {
                        tasks.set(index, newTask);
                        System.out.println("Task updated successfully!");
                    } else {
                        System.out.println("Task cannot be empty!");
                    }
                } else {
                    System.out.println("Invalid task number! Please enter a valid number.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void deleteTask() {
        viewTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Enter task number to delete: ");
            if (scanner.hasNextInt()) {
                int index = scanner.nextInt() - 1;
                scanner.nextLine();

                if (index >= 0 && index < tasks.size()) {
                    tasks.remove(index);
                    System.out.println("Task deleted successfully!");
                } else {
                    System.out.println("Invalid task number! Please enter a valid number.");
                }
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }
}