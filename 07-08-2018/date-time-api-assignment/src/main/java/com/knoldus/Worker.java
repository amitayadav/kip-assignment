package com.knoldus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Worker {
    public static void main(String[] args) {

        System.out.println("Menu:\n 1.LeapYear \n 2.LifeCycle \n 3.DayOfWeek \n 4.ZonedCurrentTime \n 5.StudentAnalysis");
        Scanner scanner=new Scanner(System.in);
        System.out.println("enter your choice");
        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                new LeapYear().getLeapYearList();
                break;
            case 2:
                new LifeCycle().getLifeCycle();
                break;
            case 3:
                new DayOfWeek().getDayOfWeekList();
                break;
            case 4:
                new ZonedCurrentTime().getZoneCurrentTime("UTC");
                break;
            case 5:

               Student student1 = new Student("Amita",1, Optional.of(Arrays.asList("English","hindi")));
               Student student2 = new Student("Pratima",2, Optional.empty());

               Optional<List<Student>> studentList=Optional.of(Arrays.asList(student1,student2));

               ClassRoom classRoom1= new ClassRoom(302,studentList);
               ClassRoom classRoom2= new ClassRoom(303,Optional.empty());

               List<ClassRoom> classRoomList=Arrays.asList(classRoom1,classRoom2);



                StudentAnalysis testStudent= new StudentAnalysis();

                System.out.println(testStudent.getStudentListWithRoomHaveNoSubjects(classRoomList));
                System.out.println(testStudent.getSubjectsOfStudentsWithGivenRoomId(classRoomList,302));
                testStudent.giveMsgIfRoomHasStudents(classRoomList,303);
                testStudent.giveMsgIfRoomHasStudents(classRoomList,302);


                break;

            default:
                System.out.println("Invalid Choice");


        }
    }
}
