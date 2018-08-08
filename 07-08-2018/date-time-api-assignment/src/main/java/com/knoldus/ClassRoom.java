package com.knoldus;

import java.util.List;
import java.util.Optional;

class ClassRoom {

    int roomID;
    Optional<List<Student>> studentList;

    ClassRoom(int roomID, Optional<List<Student>> studentList) {
        this.roomID = roomID;
        this.studentList = studentList;
    }

    public Integer getRoomId() {
        return this.roomID;
    }

    public Optional<List<Student>> getStudentList() {
        return this.studentList;
    }

}
