package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphDisplay extends JPanel {

    //  readGraph object to import the details from the text file
    readGraph myText = new readGraph();
    int[][] matrix = myText.getMatrix();
    int s = myText.getS();
    int t = myText.getT();
    int v = myText.getV();

    //    Arraylist that stores the path
    public static ArrayList<Integer> path = new ArrayList<Integer>();

    // method to generate coordinates based on the amount of vertices given
    public int[][] getCoordinates(int v) {
        int x = 250; //x position of start
        int y = 250; //y position of start
        int r = 170; //radius of circle
        int coordinates[][] = new int[v][2]; //store [x][y] position of each vertex

        for (int i = 0; i < v; i++) {
            coordinates[i][0] = (int) (x + r * Math.cos(Math.PI * 2 * i / v)); //math equation to evenly space vertices in circle
            coordinates[i][1] = (int) (y + r * Math.sin(Math.PI * 2 * i / v));
        }

        return coordinates;
    }

    // Function that implements Dijkstra's single source shortest path
    // algorithm for a graph represented using adjacency matrix representation
    private void dijkstra() {
        // shortestDistances[i] will hold the shortest distance from src to i
        int[] shortestDistances = new int[v];

        // added[i] is true if vertex i is included / in shortest path tree
        // or shortest distance from src to i is finalized
        boolean[] added = new boolean[v];

        // Initialize all distances as INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < v; vertexIndex++) {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }

        // Distance of source vertex from itself is always 0
        shortestDistances[s] = 0;

        // Parent array to store shortest path tree
        int[] parents = new int[v];

        // The starting vertex does not have a parent
        parents[s] = -1;

        // Find shortest path for all vertices
        for (int i = 1; i < v; i++) {
            // Pick the minimum distance vertex from the set of vertices not yet
            // processed. nearestVertex is always equal to startNode in first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0; vertexIndex < v; vertexIndex++) {
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }

            // Mark the picked vertex as processed
            added[nearestVertex] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            for (int vertexIndex = 0; vertexIndex < v; vertexIndex++) {
                int edgeDistance = matrix[nearestVertex][vertexIndex];

                if (edgeDistance > 0
                        && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex])) {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                            edgeDistance;
                }
            }
        }

        printPath(t, parents);
        System.out.println(path);

    }

    private static void printPath(int currentVertex, int[] parents) {
        // Base case : Source node has been processed
        if (currentVertex == -1) return;
        printPath(parents[currentVertex], parents);

//      stop circular path (also happens when this class is called multiple times)
        if (!path.contains(currentVertex)) {
            path.add(currentVertex); //only add a vertex if it isn't in path already
        } else {
            System.out.println(currentVertex+" already in: " + path);
            return; //that vertex cant appear twice in path
        }
    }

    public void paint(Graphics g) {
        int coordinates[][] = getCoordinates(v);
        int x1, y1,x2, y2, wx, wy;
        int width = 50;
        int height = 50;
        int labelX = 19;
        int labelY = 34;
        int fontsize = 14;
        String weight;
        dijkstra();

        for (int i = 0; i < v; i++) { //draw edges and weights
            for (int j = i + 1; j < v; j++) { //symmetric graph, only traverse half of matrix
                if (matrix[i][j] > 0) {
//                    variables to store coordinates
                    x1 = coordinates[i][0] + labelX;
                    y1 = coordinates[i][1] + labelY;
                    x2 = coordinates[j][0] + labelX;
                    y2 = coordinates[j][1] + labelY;
                    weight = String.valueOf(matrix[i][j]); //weight value of an edge

                    //vertex i goes to vertex j
                    // System.out.println(i + " <- i and j -> " + j +" value: "+matrix[i][j]) ;
                    g.setColor(Color.BLACK);
                    g.drawLine(x1, y1, x2, y2);

                    //draw weight in the middle point of each edge
                    wx = 7 + labelX + (int) ((coordinates[i][0] + coordinates[j][0]) / 2);
                    wy = 15 + labelY + (int) ((coordinates[i][1] + coordinates[j][1]) / 2);
                    // System.out.print("from ->" + coordinates[i][0] +", "+ coordinates[i][1]);
                    // System.out.println(" to ->"   + coordinates[j][0] +", "+ coordinates[j][1]);
                    // System.out.println("weight "+matrix[i][j]+" at: " + wx +" "+ wy );
                    g.setFont(new Font("TimesRoman", Font.BOLD, fontsize));
                    g.drawString(weight, wx, wy);
                }
            }
        }
        for (int i = 0; i < path.size() - 1; i++) { //draw the shortest path in GREEN
            x1 = coordinates[path.get(i)][0] + labelX;
            y1 = coordinates[path.get(i)][1] + labelY;
            x2 = coordinates[path.get(i + 1)][0] + labelX;
            y2 = coordinates[path.get(i + 1)][1] + labelY;
//            System.out.println(x1 + ", " + y1 +" to " + x2 +", "+y2);
            g.setColor(Color.GREEN);
            g.drawLine(x1, y1, x2, y2);

        }
        for (int i = 0; i < v; i++) { // draw the vertices
            x1 = coordinates[i][0];
            y1 = coordinates[i][1];
            g.setColor(Color.ORANGE);
            g.fillOval(x1, y1, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(x1, y1, width, height);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            // System.out.println(x + " " + y);
            g.drawString(String.valueOf(i + 1), x1 + labelX, y1 + labelY);
        }
        // draw the path
        x1 = 25;
        y1 = 30;
        y2 = y1 + 20;
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontsize));
        g.drawString("Shortest path from " + (s + 1) + " to " + (t + 1), x1, y1);
        String output = "";
        for (int i = 0; i < path.size(); i++) {
            if (i == path.size() - 1)
                output += path.get(i) + 1;
            else
                output += path.get(i) + 1 + " -> ";
        }
        g.drawString(output, x1, y2);


    }


}