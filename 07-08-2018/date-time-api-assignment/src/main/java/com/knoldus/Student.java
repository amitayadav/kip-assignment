package com.knoldus;

import java.util.List;
import java.util.Optional;

public class Student {
    String studentName;
    int rollNumber;
    Optional<List<String>> subject;

    Student(String studentName,int rollNumber,Optional<List<String
            >> subject){
        this.studentName=studentName;
        this.rollNumber=rollNumber;
        this.subject=subject;
    }



}
