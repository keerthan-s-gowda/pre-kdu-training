package com.prekdu;

import java.util.Scanner;

public class StringComparison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter first string:");
        String firstString = scanner.nextLine();

        System.out.println("Enter second string:");
        String secondString = scanner.nextLine();

        System.out.println("Length of first string: " + firstString.length());
        System.out.println("Length of second string: " + secondString.length());

        if (firstString.length() == secondString.length()) {
            System.out.println("The lengths match.");
        } else {
            System.out.println("The lengths do not match.");
        }

        if (firstString.equals(secondString)) {
            System.out.println("The two strings are the same.");
        } else {
            System.out.println("The two strings are different.");
        }

        scanner.close();
    }
}
