package com.codsoft.studentbanking;

import java.io.*;
import java.util.*;

class StudentManagementSystem {
    private Map<String, Student> students = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private static final String DATA_FILE = "students.dat";

    public void run() {
        int choice;
        do {
            System.out.println("\n--- SMART STUDENT BANKING SYSTEM ---");
            System.out.println("1. Add Student");
            System.out.println("2. Login Student");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> loginStudent();
                case 3 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid option");
            }
        } while (choice != 3);
    }

    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            students = (Map<String, Student>) ois.readObject();
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("No existing data found. Starting fresh.");
        }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save data: " + e.getMessage());
        }
    }

    private void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNo = scanner.nextLine();
        System.out.print("Enter number of subjects: ");
        int n = scanner.nextInt();
        int[] marks = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks for subject " + (i+1) + ": ");
            marks[i] = scanner.nextInt();
        }
        scanner.nextLine();
        System.out.print("Set password: ");
        String password = scanner.nextLine();

        students.put(rollNo, new Student(name, rollNo, marks, password));
        System.out.println("Student added successfully!");
    }

    private void loginStudent() {
        System.out.print("Enter roll number: ");
        String rollNo = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (students.containsKey(rollNo)) {
            Student student = students.get(rollNo);
            if (student.password.equals(password)) {
                System.out.println("Login successful!");
                studentMenu(student);
            } else {
                System.out.println("Incorrect password");
            }
        } else {
            System.out.println("Student not found");
        }
    }

    private void studentMenu(Student student) {
        int choice;
        do {
            System.out.println("\n--- Student Menu ---");
            System.out.println("1. View Info");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> student.displayInfo();
                case 2 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    student.account.deposit(amount);
                }
                case 3 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    student.account.withdraw(amount);
                }
                case 4 -> System.out.println("Balance: Rs." + student.account.getBalance());
                case 5 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid option");
            }
        } while (choice != 5);
    }
}
