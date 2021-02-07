package com.ubs.data;

import java.util.Scanner;

/**
 * Human User - using standard input
 */
public class HumanUser implements GeneralUser {

    @Override
    public int getInput() {
        while (true) {
            try {
                Scanner sc= new Scanner(System.in);
                return sc.nextInt();
            } catch (Exception e) {
                System.out.print("Invalid number, please input again: ");
            }
        }
    }
}
