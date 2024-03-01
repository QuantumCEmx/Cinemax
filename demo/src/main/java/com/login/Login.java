package com.login;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Login {
    public static final String Validation = null;
    public Login(Scanner scanner, ArrayList<Account> accounts) {

        boolean loginSuccessful = false;
        Account account = null;
        
        while (!loginSuccessful) {
            System.out.println("Enter Your Account ID : ");
            String id = scanner.nextLine();
            scanner.nextLine();

            System.out.println("Enter Your Password : ");
            String password = scanner.nextLine();

         /*    for (Account acc : loadAccountsFromJson()) {
                // System.out.println(acc.getId() + acc.getFirstname() + acc.getPassword() + acc.getTel() );
                if (acc.getId().endsWith(id)&&acc.getPassword().endsWith(password)) {
                    System.out.println("Login successful!");
                    loginSuccessful = true;
                    account = acc;
                    break; 
                }
            }
            if (!loginSuccessful) {
                System.out.println("Invalid ID or password. Please try again.");
            } */
        }
    }
}
