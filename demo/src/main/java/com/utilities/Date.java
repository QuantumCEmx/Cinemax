package com.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    
       public String getCurrentTime(){
            LocalDateTime currentTime = LocalDateTime.now();

            // Define the desired format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Format the current time using the defined format
            String formattedTime = currentTime.format(formatter);

            // Print the formatted time
            System.out.println("Current time: " + formattedTime);

            return formattedTime;
       }

       public void compareDateTime(String dateTimeString) {
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  
          LocalDateTime targetDateTime = LocalDateTime.parse(dateTimeString, formatter);
          LocalDateTime currentDateTime = LocalDateTime.now();
  
          if (currentDateTime.isAfter(targetDateTime)) {
              System.out.println("Current date and time is after the targetDateTime");
          } else if (currentDateTime.isBefore(targetDateTime)) {
              System.out.println("Current date and time is before the targetDateTime");
          } else {
              System.out.println("Current date and time is equal to the targetDateTime");
          }
      }
}

   // Set interval
        /* ---------------------------------------------------- */
 /*        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {

                System.out.println(new Date().getCurrentTime());

            }
        };
 */

    // Schedule the task to run at fixed intervals
        /* timer.scheduleAtFixedRate(task, 0, 1000); */ // Delay in milliseconds, interval in milliseconds
    