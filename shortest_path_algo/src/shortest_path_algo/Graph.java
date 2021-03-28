package shortest_path_algo;

import java.util.Arrays;

/**
 * Implements a Graph. Uses an adjacency matrix to represent the graph. *  * @author Prof. Antonio Hernandez
 */
public class Graph implements GraphInterface {
    private int verticesNumber;
    private int[][] matrix; //adjacency matrix

    public Graph() {
        verticesNumber = 5;
        matrix = new int[verticesNumber][verticesNumber];
    }

    public Graph(int n) {
        verticesNumber = n;
        matrix = new int[verticesNumber][verticesNumber];
    }

    public void addEdge(int v, int w) {
        matrix[v][w] = 1;
        matrix[w][v] = 1;
    }

    public void removeEdge(int v, int w) {
        matrix[v][w] = 0;
        matrix[w][v] = 0;
    }

    /**
     * Finds vertices adjacent to a given vertex.
     * *     * @param v given vertex
     * *     @return list of vertices adjacent to v stored in an array;
     * *     size of array = number of adjacent vertices
     */
    public int[] findAdjacencyVertices(int v) {
        int[] vert = new int[verticesNumber];
        int total = 0;
        for (int i = 0; i < verticesNumber; i++) {
            if (matrix[v][i] != 0) {
                vert[total] = i;
                total++;
            }
        }
        return Arrays.copyOf(vert, total);
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < verticesNumber; i++) {
            for (int j = 0; j < verticesNumber; j++) {
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }
}
