package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, welcome to our COP 4534 project");
        ReadInputFromTextFile();
        FrameDisplay frame = new FrameDisplay();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /**
     START OF DIJKSTRAS
     https://www.softwaretestinghelp.com/dijkstras-algorithm-in-java/
     **/
    static void algo_dijkstra(int graph[][], int src_node, int num_Vertices)  {
        int path_array[] = new int[num_Vertices]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // spt (shortest path set) contains vertices that have shortest path
        Boolean sptSet[] = new Boolean[num_Vertices];

        // Initially all the distances are INFINITE and stpSet[] is set to false
        for (int i = 0; i < num_Vertices; i++) {
            path_array[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Path between vertex and itself is always 0
        path_array[src_node] = 0;
        // now find shortest path for all vertices
        for (int count = 0; count < num_Vertices - 1; count++) {
            // call minDistance method to find the vertex with min distance
            int u = minDistance(path_array, sptSet, num_Vertices);
            // the current vertex u is processed
            sptSet[u] = true;
            // process adjacent nodes of the current vertex
            for (int v = 0; v < num_Vertices; v++)

                // if vertex v not in sptset then update it
                if (!sptSet[v] && graph[u][v] != 0 && path_array[u] !=
                        Integer.MAX_VALUE && path_array[u]
                        + graph[u][v] < path_array[v])
                    path_array[v] = path_array[u] + graph[u][v];
        }

        // print the path array
        printMinpath(path_array, num_Vertices);
    }
    // print the array of distances (path_array)
    static void printMinpath(int path_array[], int num_Vertices)   {
        System.out.println("Vertex# \t Minimum Distance from Source");
        for (int i = 0; i < num_Vertices; i++)
            System.out.println(i + " \t\t\t " + path_array[i]);
    }
    static int minDistance(int path_array[], Boolean sptSet[], int num_Vertices)   {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < num_Vertices; v++)
            if (sptSet[v] == false && path_array[v] <= min) {
                min = path_array[v];
                min_index = v;
            }

        return min_index;
    }
    /**
     END OF DIJKSTRAS
     https://www.softwaretestinghelp.com/dijkstras-algorithm-in-java/
     **/
    public static void ReadInputFromTextFile() { //added 'void' to stop error
        readGraph();
    }

    /**
     * Reads graph from text file (entries as given in COP4534 3rd assignment)
     * * and prints it
     **/
    public static void readGraph() {
        File input = new File("graph.txt");
        Scanner in = null;
        try {
            in = new Scanner(input);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        int numberOfVertices = 0;
        int mat_i_j = 0;
        int[][] graph_matrix;
        int s,t; //start and target
        Graph my_graph; //create graph with parameters from file
        while (in.hasNextLine()) {
            numberOfVertices = in.nextInt();
            System.out.println("Number of vertices: " + numberOfVertices);
            graph_matrix = new int[numberOfVertices][numberOfVertices]; //creating 2d array for file matrix

            System.out.println("Matrix: ");
            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {
                    mat_i_j = in.nextInt();
                    System.out.print(mat_i_j + " ");
                    graph_matrix[i][j] = mat_i_j;
                }
                System.out.println();
            }
            s = in.nextInt();
            t = in.nextInt();
            System.out.print("S and T are:  ");
            System.out.println(s + " " + t);
            my_graph = new Graph(numberOfVertices, graph_matrix); //how to send this off?
            algo_dijkstra(graph_matrix, s, t);
        }

        in.close();
    }

    public interface GraphInterface {
        public void addEdge(int v, int w);

        public void removeEdge(int v, int w);

        public int[] findAdjacencyVertices(int v);

        public String toString();
    }

    /**
     * Implements a Graph. Uses an adjacency matrix to represent the graph. *
     * * @author Prof. Antonio Hernandez
     */
    public static class Graph implements GraphInterface {
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

        public Graph(int n, int[][] m) {
            verticesNumber = n;
            matrix = m;
        }
        public int getVertices(){
            return verticesNumber;
        }
        public int[][] getMatrix(){
            return matrix;
        }
        public void addEdge(int i, int j ) {
            matrix[i][j] = 1;
            matrix[j][i] = 1;
        }
        public void addEdge(int i, int j, int w ) { // w is weight of Edge
            matrix[i][j] = w;
            matrix[j][i] = w;
        }

        public void removeEdge(int i, int j) {
            matrix[i][j] = 0;
            matrix[j][i] = 0;
        }
        public void printMatrix(){
            System.out.println("Printing matrix...");
            for (int i = 0; i < verticesNumber; i++) {
                for (int j = 0; j < verticesNumber; j++) {
                    System.out.print(matrix[i][j]+ " ");
                }
                System.out.println();
            }
        }
        /**
         * Finds vertices adjacent to a given vertex.
         * * @param v given vertex
         * * @return list of vertices adjacent to v stored in an array;
         * * size of array = number of adjacent vertices
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
    }

    public String toString(int[][] matrix, int verticesNumber) {
        String s = "";
        for (int i = 0; i < verticesNumber; i++) {
            for (int j = 0; j < verticesNumber; j++) {
                s += matrix[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    public static class FrameDisplay extends JFrame {
        int WIDTH = 450;
        int HEIGHT = 450;

        public FrameDisplay() {
            setTitle("Graph Display");
            setSize(WIDTH, HEIGHT);
            GraphDisplay panel = new GraphDisplay();
            add(panel);
        }
    }

    public static class GraphDisplay extends JPanel {
        public void paint(Graphics g) {
            int leftX = 100;
            int topY = 100;
            int width = 50;
            int height = 50;
            int labelX = 17;
            int labelY = 31;
            int gridWidth = 150;        //draw vertex 1
            g.setColor(Color.ORANGE);
            g.fillOval(leftX, topY, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(leftX, topY, width, height);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            g.drawString("1", leftX + labelX, topY + labelY);       //draw vertex 2
            g.setColor(Color.ORANGE);
            g.fillOval(leftX + gridWidth, topY, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(leftX + gridWidth, topY, width, height);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            g.drawString("2", leftX + gridWidth + labelX, topY + labelY);        //draw edge (1, 2)
            g.setColor(Color.BLACK);
            g.drawLine(leftX + width, topY + height / 2, leftX + gridWidth, topY + height / 2);
        }
    }
}


