package com.knoldus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentAnalysis {

    public List<String> getStudentListWithRoomHaveNoSubjects(List<ClassRoom> classRoomList) {

        return classRoomList.stream().filter(room -> room.getStudentList().isPresent())//list of classrooms
                .map(list -> list.getStudentList().get())//list of list of student
                .flatMap(students -> students.stream())
                .filter(student -> !student.getSubjectList().isPresent())
                .map(student -> student.getStudentName())
                .collect(Collectors.toList());

    }

    public List<Optional<List<String>>> getSubjectsOfStudentsWithGivenRoomId(List<ClassRoom> classRoomList, int roomId) {

        return classRoomList.stream()
                .filter(room -> room.getStudentList().isPresent() && room.getRoomId() == roomId)//List of classroom
                .map(list -> list.getStudentList().get())//List of list of student
                .flatMap(students -> students.stream())
                .filter(student -> student.getSubjectList().isPresent())
                .map(student -> student.getSubjectList())
                .collect(Collectors.toList());


    }

    public void giveMsgIfRoomHasStudents(List<ClassRoom> classRoomList, int roomId) {

        classRoomList.stream()
                .filter(room -> room.getStudentList().isPresent() && room.getRoomId() == roomId)
                .forEach(e -> System.out.println("hello student"));

    }

}
