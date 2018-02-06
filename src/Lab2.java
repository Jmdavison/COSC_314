/**
 * Jacob Davison
 * COSC 314 Winter semester
 * Lab number 2
 *
 * This program checks an adjacency matrix for various
 * properties of relations.
 */

import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {

        int n;
        // these are default true because we are looking for counter examples
        boolean transitive = true;
        boolean reflexive = true;
        boolean symmetric = true;
        boolean antisymmetric = true;

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the dimension of the matrix");
        n = scan.nextInt();

        int[][] matrix = new int[n][n];

        System.out.println("Please enter each element of the matrix");

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = scan.nextInt();

        // reflexive check
        for (int i = 0; i < n; i++) {
            if(matrix[i][i] != 1){
                reflexive = false;
            }
        }

        // Transitive check (Warshalls Algorithm)
        int[][] t = new int[n][n]; //transitive closure matrix
        t = matrix;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(t[j][i] == 1){
                    for (int k = 0; k < n; k++) {
                        if(t[i][k] == 1 && t[j][k] == 0) {
                            transitive = false;
                        }
                    }
                }
            }
        }

        // symmetric - antisymmetric test
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(j != i){
                    if(matrix[i][j] == 1){
                        if(matrix[j][i] == 1){
                            antisymmetric = false;
                        }else{
                            symmetric = false;
                        }
                    }
                }
            }
        }

        if(transitive == false){
            System.out.println("The matrix is not transitive because:");
            System.out.println("the matrix is not equal to its transitive closure");
        }else{
            System.out.println("This matrix is transitive");
        }

        if(reflexive == false){
            System.out.println("Matrix is not reflexive because the diagonal is not complete");
        }else{
            System.out.println("This matrix is reflexive");
        }

        if(symmetric == false){
            System.out.println("This matrix is not symmetric because the 1s are not mirrored over the diagonal");
        }else{
            System.out.println("This matrix is symmetric");
        }

        if(antisymmetric == false){
            System.out.println("This matrix is not antisymmetric becuase there are 1s that are mirrored over the diagonal");
        }else{
            System.out.println("This matrix is antisymmetric");
        }
    }
}


/**
 * OUTPUT 1
 * ------------------------

 Please enter the dimension of the matrix
 3
 Please enter each element of the matrix
 0 1 0
 0 1 1
 1 1 0
 The matrix is not transitive because:
 the matrix is not equal to its transitive closure
 Matrix is not reflexive because the diagonal is not complete
 This matrix is not symmetric because the 1s are not mirrored over the diagonal
 This matrix is not antisymmetric becuase there are 1s that are mirrored over the diagonal

 -----------------------------
 OUTPUT 2
------------------------------
 Please enter the dimension of the matrix
 4
 Please enter each element of the matrix
 0 1 0 1
 1 0 1 0
 0 1 0 1
 1 0 1 0
 The matrix is not transitive because:
 the matrix is not equal to its transitive closure
 Matrix is not reflexive because the diagonal is not complete
 This matrix is symmetric
 This matrix is not antisymmetric becuase there are 1s that are mirrored over the diagonal

 -----------------------------------
 OUTPUT 3
 -----------------------------------
 Please enter the dimension of the matrix
 6
 Please enter each element of the matrix
 1 1 0 0 0 1
 0 1 0 1 0 0
 0 0 1 0 0 0
 1 0 0 1 1 0
 0 1 1 0 1 0
 0 0 1 0 1 1
 The matrix is not transitive because:
 the matrix is not equal to its transitive closure
 This matrix is reflexive
 This matrix is not symmetric because the 1s are not mirrored over the diagonal
 This matrix is antisymmetric

 */
