package com.company;

import javax.swing.*;
import java.awt.*;

public class GraphDisplay extends JPanel {


    readGraph myText = new readGraph();
    int[][] matrix = myText.getMatrix();
    int v = myText.getV();

    public int[][] getCoordinates(int v){
        int x = 150; //x position of start
        int y = 150; //y position of start
        int r = 150; //radius of circle
        int coordinates[][] = new int[v][2]; //store [x][y] position of each vertex

        for (int i = 0; i < v; i++) {
            coordinates[i][0] = (int)(x + r * Math.cos(Math.PI * 2 * i / v));
            coordinates[i][1] = (int)(y + r * Math.sin(Math.PI * 2 * i / v));
        }

        return coordinates;
    }

    public void paint(Graphics g) {
        int coordinates[][] = getCoordinates(v);
        int x,y,wx,wy;
        int width = 50;
        int height = 50;
        int gridWidth = 150;
        int labelX = 19;
        int labelY = 34;
        int fontsize = 14;



        for(int i = 0; i < v; i++){ //draw edges and weights
            for(int j = i+1; j < v; j++){ //symmetric graph
                if(matrix[i][j] > 0){
                    //vertex i goes to vertex j
                    System.out.println(i + " <- i and j -> " + j +" value: "+matrix[i][j]) ;
                    g.setColor(Color.BLACK);

                    g.drawLine(coordinates[i][0]+ labelX, coordinates[i][1]+ labelY, coordinates[j][0] + labelX, coordinates[j][1]+ labelY);
                    //draw weight
                    wx = 7+labelX + (int)((coordinates[i][0] + coordinates[j][0])/2);
                    wy = 15+labelY + (int)((coordinates[i][1] + coordinates[j][1])/2);
                    System.out.print("from ->" + coordinates[i][0] +", "+ coordinates[i][1]);
                    System.out.println(" to ->"   + coordinates[j][0] +", "+ coordinates[j][1]);
                    System.out.println("weight "+matrix[i][j]+" at: " + wx +" "+ wy );
                    g.setFont(new Font("TimesRoman", Font.BOLD, fontsize));
                    g.drawString(String.valueOf(matrix[i][j]), wx, wy);
                }
            }
        }
        for (int i = 0; i < v; i++) { // draw the vertices
            x = coordinates[i][0];
            y = coordinates[i][1];
            g.setColor(Color.ORANGE);
            g.fillOval(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, width, height);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            System.out.println(x + " " + y);
            g.drawString(String.valueOf(i+1), x + labelX, y + labelY);
        }
    }
}
