package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, welcome to our COP 4534 project");
        ReadInputFromTextFile();
    }

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
}

