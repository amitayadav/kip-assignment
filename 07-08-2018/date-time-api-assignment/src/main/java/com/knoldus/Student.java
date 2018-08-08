package com.knoldus;

import java.util.List;
import java.util.Optional;

class Student {
    String studentName;
    int rollNumber;
    Optional<List<String>> subject;

    Student(String studentName, int rollNumber, Optional<List<String>> subject) {
        this.studentName = studentName;
        this.rollNumber = rollNumber;
        this.subject = subject;
    }

    public Integer getRollNumber() {
        return this.rollNumber;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public Optional<List<String>> getSubjectList() {
        return this.subject;
    }


}
