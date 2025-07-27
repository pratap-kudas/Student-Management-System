package com.codsoft.studentbanking;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.loadData(); // Load existing student data
        sms.run();
        sms.saveData(); // Save student data on exit
    }
}

