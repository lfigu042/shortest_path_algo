package shortest_path_algo;

import javax.swing.*;

//import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

import java.awt.*;
import java.util.ArrayList;

public class GraphDisplay extends JPanel {


    readGraph myText = new readGraph();
    int[][] matrix = myText.getMatrix();
    int s = myText.getS();
    int t = myText.getT();
    int v = myText.getV();
    public static ArrayList<Integer> path = new ArrayList<Integer>();


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

    /*public ArrayList<Integer> dijktra(){
      int[] shortestDistances = new int[v];
      boolean[] added = new boolean[v];
      shortestDistances[s] = 0; //distance to self is 0
      int[] parents = new int[v];
      parents[s] = -1;  //source node has no parent
      
      //set all distances to infinity and say that we have not traversed any nodes
      for (int i = 0; i < v; i++) {
        shortestDistances[i] = Integer.MAX_VALUE;
        added[i] = false;
      }

      //starting from 1 because ???
      for (int i = 1; i < v; i++)
        {
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int j = 0; j < v; j++)
            {
                if (!added[j] && shortestDistances[j] < shortestDistance) 
                {
                    nearestVertex = j;
                    shortestDistance = shortestDistances[j];
                }
            }
            added[nearestVertex] = true;

            for (int j = 0; i < v; j++) 
            {
                int edgeDistance = matrix[nearestVertex][j];
                  
                if (edgeDistance > 0
                    && ((shortestDistance + edgeDistance) < shortestDistances[j])) 
                {
                    parents[j] = nearestVertex;
                    shortestDistances[j] = shortestDistance + edgeDistance;
                }
            }
        }
        System.out.println(shortestPath(t, parents));
        return shortestPath(t, parents);

    
    }
    public ArrayList<Integer> shortestPath(int currentVertex, int[] parents){
      ArrayList<Integer> path = new ArrayList<Integer>();
      if (currentVertex == -1)
        {
            return path;
        }
        shortestPath(parents[currentVertex], parents);
        path.add(currentVertex);
        System.out.println(path);
        return path;
        
    }
    */
 // Function that implements Dijkstra's
    // single source shortest path
    // algorithm for a graph represented
    // using adjacency matrix
    // representation
    private  void dijkstra()
    {
 
        // shortestDistances[i] will hold the
        // shortest distance from src to i
        int[] shortestDistances = new int[v];

        // added[i] will true if vertex i is
        // included / in shortest path tree
        // or shortest distance from src to
        // i is finalized
        boolean[] added = new boolean[v];
 
        // Initialize all distances as
        // INFINITE and added[] as false
        for (int vertexIndex = 0; vertexIndex < v;
                                            vertexIndex++)
        {
            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
            added[vertexIndex] = false;
        }
       
        // Distance of source vertex from
        // itself is always 0
        shortestDistances[s] = 0;
 
        // Parent array to store shortest
        // path tree
        int[] parents = new int[v];
 
        // The starting vertex does not
        // have a parent
        parents[s] = -1;
       
        // Find shortest path for all
        // vertices
        for (int i = 1; i < v; i++)
        {
 
            // Pick the minimum distance vertex
            // from the set of vertices not yet
            // processed. nearestVertex is
            // always equal to startNode in
            // first iteration.
            int nearestVertex = -1;
            int shortestDistance = Integer.MAX_VALUE;
            for (int vertexIndex = 0;
                     vertexIndex < v;
                     vertexIndex++)
            {
                if (!added[vertexIndex] &&
                    shortestDistances[vertexIndex] <
                    shortestDistance)
                {
                    nearestVertex = vertexIndex;
                    shortestDistance = shortestDistances[vertexIndex];
                }
            }
 
            // Mark the picked vertex as
            // processed
            added[nearestVertex] = true;
           
            // Update dist value of the
            // adjacent vertices of the
            // picked vertex.
            for (int vertexIndex = 0;
                     vertexIndex < v;
                     vertexIndex++)
            {
                int edgeDistance = matrix[nearestVertex][vertexIndex];
                 
                if (edgeDistance > 0
                    && ((shortestDistance + edgeDistance) <
                        shortestDistances[vertexIndex]))
                {
                    parents[vertexIndex] = nearestVertex;
                    shortestDistances[vertexIndex] = shortestDistance +
                                                       edgeDistance;
                }
            }
        }
       
        printPath(t, parents);
        System.out.println(path);

    }
   
    private static void printPath(int currentVertex,
                                  int[] parents)
    {
         
        // Base case : Source node has
        // been processed
        if (currentVertex == -1)
        {
            return;
        }
        printPath(parents[currentVertex], parents);
        path.add(currentVertex);
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
        dijkstra();



        for(int i = 0; i < v; i++){ //draw edges and weights
            for(int j = i+1; j < v; j++){ //symmetric graph
                if(matrix[i][j] > 0){
                    //vertex i goes to vertex j
                    // System.out.println(i + " <- i and j -> " + j +" value: "+matrix[i][j]) ;
                    g.setColor(Color.BLACK);

                    g.drawLine(coordinates[i][0]+ labelX, coordinates[i][1]+ labelY, coordinates[j][0] + labelX, coordinates[j][1]+ labelY);
                    //draw weight
                    wx = 7+labelX + (int)((coordinates[i][0] + coordinates[j][0])/2);
                    wy = 15+labelY + (int)((coordinates[i][1] + coordinates[j][1])/2);
                    // System.out.print("from ->" + coordinates[i][0] +", "+ coordinates[i][1]);
                    // System.out.println(" to ->"   + coordinates[j][0] +", "+ coordinates[j][1]);
                    // System.out.println("weight "+matrix[i][j]+" at: " + wx +" "+ wy );
                    g.setFont(new Font("TimesRoman", Font.BOLD, fontsize));
                    g.drawString(String.valueOf(matrix[i][j]), wx, wy);
                }
            }
        }
        for (int i = 0; i < path.size()-1; i++) {
        	 g.setColor(Color.GREEN);

             g.drawLine(coordinates[path.get(i)][0]+ labelX, coordinates[path.get(i)][1]+ labelY, coordinates[path.get(i+1)][0] + labelX, coordinates[path.get(i+1)][1]+ labelY);
        	
        }
        for (int i = 0; i < v; i++) { // draw the vertices
            x = coordinates[i][0];
            y = coordinates[i][1];
            g.setColor(Color.ORANGE);
            g.fillOval(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, width, height);
            g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
            // System.out.println(x + " " + y);
            g.drawString(String.valueOf(i+1), x + labelX, y + labelY);
        }
    // draw the path 
            g.setFont(new Font("TimesRoman", Font.PLAIN, fontsize));
            g.drawString("Shortest path from " + (s+1) + " to " + (t+1), 270, 15);
            String output = "";
            for(int i = 0; i < path.size(); i++) {
            	if(i == path.size()-1)
            		output += path.get(i)+1;
            	else
            		output += path.get(i)+1 + ", ";
            }
            g.drawString(output, 270, 30);
            

    }
    

}
