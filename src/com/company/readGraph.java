package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readGraph {
    private int[][] matrix;
    private int s, t, v;

    public void setMatrix(int[][] m){ this.matrix = m; }
    public void set_target(int s, int t, int v){
        this.s = s;
        this.t = t;
        this.v = v;
    }
    public int[][] getMatrix() { return this.matrix; }
    public int getT() { return this.t; }
    public int getS() { return this.s; }
    public int getV() { return this.v; }
    /**
     * Reads graph from text file (entries as given in COP4534 3rd assignment)
     * * and prints it
     **/

    public readGraph(){
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

        while (in.hasNextLine()) {
            numberOfVertices = in.nextInt();
//            System.out.println("Number of vertices: " + numberOfVertices);

            graph_matrix = new int[numberOfVertices][numberOfVertices]; //creating 2d array for file matrix

//            System.out.println("Matrix: ");
            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {
                    mat_i_j = in.nextInt();
//                    System.out.print(mat_i_j + " ");
                    graph_matrix[i][j] = mat_i_j;
                }
                System.out.println();
            }
            s = in.nextInt();
            t = in.nextInt();
//            System.out.print("S and T are:  ");
//            System.out.println(s + " " + t);

            set_target(s,t,numberOfVertices);
            setMatrix(graph_matrix);
        }

        in.close();
    }

}
