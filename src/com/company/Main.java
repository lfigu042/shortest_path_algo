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
        dijkstra algorithm implementation from textbook ch 8 pg 259
        defines a shortest-path spanning tree rooted in s.
        provides the shortest path from s to all other vertices, not just t.

     **/
//    int dijkstra(int graph *g, int start) {
//        int i;                      /* counter */
//        edgenode * p;               /* temporary pointer */
//        boolean intree[ MAXV + 1];  /* is the vertex in the tree yet? */
//        int distance[ MAXV + 1];    /* cost of adding to tree */
//        int v;                      /* current vertex to process */
//        int w;                      /* candidate next vertex */
//        int dist;                   /* cheapest cost to enlarge tree */
//        int weight = 0;             /* tree weight */
//        for (i = 1; i <= g -> nvertices; i++) {
//            intree[i] = false;
//            distance[i] = MAXINT;
//            parent[i] = -1;
//        }
//        distance[start] = 0;
//        v = start;
//
//        while (!intree[v]) {
//            intree[v] = true;
//            if (v != start) {
//                printf("edge (%d,%d) in tree \n", parent[v], v);
//                weight = weight + dist;
//            }
//            p = g -> edges[v];
//            while (p != NULL) {
//                w = p -> y;
//                if (distance[w] > (distance[v] + p -> weight)) { /* CHANGED */
//                    distance[w] = distance[v] + p -> weight; /* CHANGED */
//                    parent[w] = v; /* CHANGED */
//                }
//                p = p -> next;
//            }
//            dist = MAXINT;
//            for (i = 1; i <= g -> nvertices; i++) {
//                if ((!intree[i]) && (dist > distance[i])) {
//                    dist = distance[i];
//                    v = i;
//                }
//            }
//        }
//        return (weight);
//    }

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
        while (in.hasNextLine()) {
            numberOfVertices = in.nextInt();
            System.out.println("Number of vertices: " + numberOfVertices);
            System.out.println("Matrix: ");
            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {
                    mat_i_j = in.nextInt();
                    System.out.print(mat_i_j + " ");
                }
                System.out.println();
            }
            int s = in.nextInt();
            int t = in.nextInt();
            System.out.print("S and T are:  ");
            System.out.println(s + " " + t);
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
            g.drawString("1", leftX + labelX, topY + labelY);        //draw vertex 2
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


