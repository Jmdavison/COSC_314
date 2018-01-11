import java.util.Scanner;

/**
 * COSC 314 assignment 1
 * @author Jacob Davison
 *
 * Prints all 'k' element subsets in S where
 * S is all integers from 1 to 'n'.
 *
 * 'k' and 'n' are defined by user input
 */

public class P1 {
    public static void main(String[] args) {

        int n;
        int k;

        Scanner scan = new Scanner(System.in);

        // get n and k from user
        System.out.println("Please enter a value for n");
        n = scan.nextInt();
        System.out.println("Please enter a value for k");
        k = scan.nextInt();

        // for i in 0 - 2^n ( each possible subset )
        for (int i = 0; i < Math.pow(2, n); i++) {
            // reset variables
            int numElems = 0;
            String subset = "{";
            int quotient = i;
            // for each bit position
            for (int j = 0; j < n; j++) {
                int remainder = quotient % 2;
                quotient = quotient / 2;
                // determine bit values
                if(remainder == 1) {
                    numElems++;
                    subset += (j+1) + ",";
                }
            }
            // replace trailing comma with curly brace (split & join)
            String[] arr = subset.split("");
            arr[arr.length-1] = "}";
            subset = String.join("", arr);
            // if number of elements in this subset == k, print the subset
            if(numElems == k)
                System.out.println(subset);
        }
    }
}