/**
 * Jacob Davison E01578172
 * COSC 314 Winter semester
 * Lab number 4
 *
 * Finds the shortest path from a given vertex
 * to all other vertices of a weighted connected graph
 * using Dijkstra's algorithm.
 */
package cosc314;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Lab4 {

    /**
     * Driver, reads input from file and calls
     * algorithm.
     * @param args
     */
    public static void main(String[] args) {

        int numVertices;
        int[][] edgeMatrix;
        int startVertex;

        // init scanner
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter an input filename");
        String fileName = keyboard.next();
        Scanner fileIn;

        // open file
        try {
            fileIn = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("A file with that name cannot be found, exiting..");
            return;
        }

        // read number of vertices
        numVertices = fileIn.nextInt();

        // initialize arrays
        edgeMatrix = new int[numVertices][numVertices];

        // read array
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                edgeMatrix[i][j] = fileIn.nextInt();
            }
        }

        // get start vertex
        startVertex = fileIn.nextInt();

        findShortestPaths(numVertices, edgeMatrix, startVertex);
    }

    /**
     * Find and print shortest path to each vertex from the given origin.
     * @param numVertices
     * @param edgeMatrix
     * @param startVertex
     */
    private static void findShortestPaths(int numVertices, int[][] edgeMatrix, int startVertex) {
        LinkedList<Integer> vertexList = new LinkedList();
        int[] distances = new int[numVertices];
        boolean[] visited = new boolean[numVertices];

        int currTotalDist;

        for (int i = 0; i < numVertices; i++) {
            // init all weights to infinity
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;

            // add all vertices to list
            vertexList.add(i);
        }

        // weight for start vertex will be 0
        distances[startVertex] = 0;

        // for every vertex in list
        while( !vertexList.isEmpty() ) {

            int u = getLeastDistance(visited, distances, vertexList);

            int ind = vertexList.indexOf(u);
            vertexList.remove(ind);

            visited[u] = true;

            for (int v = 0; v < numVertices; v++) {
                // only update shortest path if node is unvisited and has a valid edge
                if( !visited[v] && edgeMatrix[u][v] > 0 && distances[u] != Integer.MAX_VALUE ) {
                    currTotalDist = distances[u] + edgeMatrix[u][v];
                    if (currTotalDist < distances[v]) {
                        distances[v] = currTotalDist;
                    }
                }
            }
        }

        for (int i = 0; i < numVertices; i++) {
            if( i != startVertex) {
                System.out.printf("Shortest path from %d to %d = %d\n", startVertex, i, distances[i]);
            }
        }
    }

    /**
     * Gets the vertex with the least distance from the list
     * @param list
     */
    private static int getLeastDistance(boolean[] visited, int[] distances, LinkedList<Integer> list) {
        int least = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < list.size(); i++) {
            if(visited[list.get(i)] == false && distances[list.get(i)] <= least) {
                least = distances[list.get(i)];
                minIndex = list.get(i);
            }
        }
        return minIndex;
    }
}
