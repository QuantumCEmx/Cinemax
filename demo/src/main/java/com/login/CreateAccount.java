package com.login;

import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

import com.storage.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileWriter;
import java.util.InputMismatchException;

import com.storage.FileData;
import com.utilities.Date;

public class CreateAccount {
    public CreateAccount() {
        Scanner sc = new Scanner(System.in);
        Scanner Nums = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        Object a = new Object();

        System.out.println("Enter Your Firstname :");
        String firstname = str.nextLine();
        if(!Validation.getCheckChar(firstname)){
            // System.out.println(" Firstname Fail");
            return; // ไม่ทำการบันทึกข้อมูล
        }
        // Validation.getCheckChar(Firstname);

        System.out.println("Enter Your Lastname : ");
        String lastname = str.nextLine();
        if(!Validation.getCheckChar(lastname)){
            // System.out.println(" lastname Fail");
            return; // ไม่ทำการบันทึกข้อมูล
        }
        // Validation.getCheckChar(lastname);

        System.out.println("Enter Your id :");
        String id = str.nextLine();
        if(!Validation.getCheckCharAndMaxnum(id, 13)){
            System.out.println(" Id Card Fail");
            return; // ไม่ทำการบันทึกข้อมูล
        }
        
        // Validation.getCheckCharAndMaxnum(id, 13);

        System.out.println("Enter Your password :");

        String password = str.nextLine();

        System.out.println("Enter Your Comfirm password :");

        String Cfpassword = str.nextLine();

       if(!Validation.getCheckpasswordequals(password, Cfpassword, 8)){
            System.out.println(" Password Fail");
            return; // ไม่ทำการบันทึกข้อมูล
       }

        System.out.println("Enter Your tel :");
        String tel = str.nextLine();
        if(!Validation.CheckUserInputTel(tel)){
            System.out.println(" Tel Fail");
            return; // ไม่ทำการบันทึกข้อมูล
        }
        // Validation.CheckUserInputTel(tel);

        System.out.println("Enter Your birthdate ('yyyy','mm','dd') :");
        // String birthdate = CheckUserInputNum(scanner);
        String birthdate = sc.nextLine();
        if(!Validation.getCheckBirthdate(birthdate)){
            System.out.println(" birthdate Fail");
            return; // ไม่ทำการบันทึกข้อมูล
        }
        // Validation.ReCheckBirthdate(birthdate);
        // Validation.getCheckBirthdate(birthdate);


        JSONObject newAccount = new JSONObject();

        newAccount.put("id", id);
        newAccount.put("firstname", firstname);
        newAccount.put("lastname", lastname);
        newAccount.put("password", password);
        newAccount.put("birthdate", birthdate);
        newAccount.put("tel", tel);

        newAccount.put("member_type", "0");
        newAccount.put("create_date", new Date().getCurrentTime());
        newAccount.put("expire_date", new Date().getAdjustDate(1, 0, 0, 0, 0, 0));

        
        // System.out.println(newAccount.toString(4));

        new Database().insert(newAccount,new FileData().member,"ME");
       
    }

}
