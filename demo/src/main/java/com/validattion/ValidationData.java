package com.validattion;

public class ValidationData {
 
    
    public int alphabets(char target){
        char[] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

        int index = -1; // Initialize to -1, indicating not found
        for (int i = 0; i < alpha.length; i++) {
            if (alpha[i] == target) {
                index = i; // Update index if the character is found
                break; // Exit the loop once the character is found
            }
        }

        // Check if the character was found and print the result
        if (index != -1) {
            return index;
        } else {
            System.out.println("Character '" + target + "' not found in the array.");
            return -1;
        }
    }
}
