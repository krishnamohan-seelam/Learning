package com.cookbook.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateExamples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        
               LocalDate lToday = LocalDate.now();
               LocalTime lTime  =LocalTime.now();
               LocalDateTime lDayTime =LocalDateTime.now();
               System.out.printf("Date: %s \n" ,lToday);
               System.out.printf("Time: %s \n" ,lTime);
               System.out.printf("DateTime: %s \n" ,lDayTime);
	}

}
