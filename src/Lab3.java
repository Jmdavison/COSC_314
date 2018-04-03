
/**
 * Jacob Davison E01578172
 * COSC 314 Winter semester
 * Lab number 3
 *
 * This program checks an adjacency matrix for the
 * bipartite quality
 */
package cosc314;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Lab3 {

    // matrix size
    static final int MSIZE = 6;

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter an input filename");
        String fileName = keyboard.next();
        Scanner fileIn;

        try {
            fileIn = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("A file with that name cannot be found, exiting..");
            return;
        }

        // adjacency matrix
        int[][] adjMatrix = new int[MSIZE][MSIZE];

        // load adjacency matrix
        for (int i = 0; i < MSIZE; i++) {
            for (int j = 0; j < MSIZE; j++) {
                adjMatrix[i][j] = fileIn.nextInt();
            }
        }

        checkBipartite(adjMatrix);
    }


    /**
     * Checks a connected graph for the "bipartite" quality
     * if true, it will print the bi-partition sets.
     * @param adjMatrix
     * @return {boolean} - whether the graph is bipartite or not
     */
    private static boolean checkBipartite(int[][] adjMatrix) {

        // create partition sets
        LinkedList<Integer> redSet = new LinkedList();
        LinkedList<Integer> blueSet = new LinkedList();
        LinkedList<Integer> visited = new LinkedList();
        boolean vertexBlue = false;
        boolean vertexRed = false;
        boolean bipartite = true;

        for (int i = 0; i < MSIZE; i++) {
            for (int j = 0; j < MSIZE; j++) {
                // if we have an edge
                if(adjMatrix[i][j] == 1) {
                    // have not visited i
                    if(!visited.contains(i)) {
                        redSet.add(i);
                        visited.add(i);
                        vertexRed = true;
                    }else{
                        // determin if vertex is red or blue
                        if(redSet.contains(i))
                            vertexRed = true;
                        else
                            vertexBlue = true;
                    }

                    // if i is red add j to blue
                    if(vertexRed) {
                        if(blueSet.contains(j))
                            continue;
                        else if (redSet.contains(j)) {
                            System.out.println("ERROR, TWO RED VERTICES ARE NEEDED");
                            System.out.println("between " + i + "->" + j);
                            bipartite = false;
                        } else {
                            blueSet.add(j);
                            visited.add(j);
                        }
                    }

                    // if i is blue add j to red
                    if(vertexBlue) {
                        if(redSet.contains(j)){
                            continue;
                        }else if(blueSet.contains(j)){
                            System.out.println("ERROR, GRAPH DISCREPANCY, TWO BLUE VERTICES ARE NEEDED");
                            System.out.println("between " + i + "->" + j);
                            bipartite = false;
                        } else {
                            redSet.add(j);
                            visited.add(j);
                        }
                    }
                }
            }
            vertexRed = false;
            vertexBlue = false;
        }

        if(bipartite) {
            printPartitions(redSet, blueSet);
        }else {
            System.out.println("This graph is not bipartite, see problem vertices above");
        }

        return bipartite;
    }

    static void printPartitions(LinkedList<Integer> red, LinkedList<Integer> blue) {
        System.out.println("This graph is bipartite!\n");
        System.out.print("The set of red vertices is: \n {");
        red.stream().forEach( elem -> System.out.print(" " + elem) );
        System.out.print( " } \n\n");
        System.out.print("The set of blue vertices is: \n {");
        blue.stream().forEach( elem -> System.out.print(" " + elem) );
        System.out.print(" } \n\n");
    }
}

/********** OUTPUT FOR MATRIX 1**************/
/*
contents of input1.txt
0 0 0 1 1 0
0 0 0 1 1 1
0 0 0 0 1 1
1 1 0 0 0 0
1 1 1 0 0 0
0 1 1 0 0 0
-----------------------------------
Please enter an input filename
input1.txt
This graph is bipartite!

The set of red vertices is:
 { 0 1 2 }

The set of blue vertices is:
 { 3 4 5 }
 */

/********** OUTPUT FOR MATRIX 2**************/
/*
contents of input2.txt
0 1 0 1 0 0
1 0 1 0 0 1
0 1 0 1 0 0
1 0 1 0 1 0
0 0 0 1 0 1
0 1 0 0 1 0
-----------------------------------
Please enter an input filename
input2.txt
ERROR, TWO RED VERTICES ARE NEEDED
between 4->5
ERROR, TWO RED VERTICES ARE NEEDED
between 5->4
This graph is not bipartite, see problem vertices above
 */
