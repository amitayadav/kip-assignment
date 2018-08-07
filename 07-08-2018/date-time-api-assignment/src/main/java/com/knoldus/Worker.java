package com.knoldus;

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
                new ZonedCurrentTime().getZoneCurrentTime();
                break;
            case 5:

            default:
                System.out.println("Invalid Choice");


        }
    }
}
