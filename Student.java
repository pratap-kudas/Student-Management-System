package com.codsoft.studentbanking;

import java.io.Serializable;
import java.util.Arrays;

class Student implements Serializable {
    String name;
    String rollNo;
    int[] marks;
    String grade;
    BankAccount account;
    String password;

    public Student(String name, String rollNo, int[] marks, String password) {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
        this.password = password;
        this.grade = calculateGrade();
        this.account = new BankAccount();
    }

    public String calculateGrade() {
        int total = Arrays.stream(marks).sum();
        double avg = total / (double) marks.length;
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else return "F";
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Grade: " + grade);
        System.out.println("Account Balance: Rs." + account.getBalance());
    }
}


