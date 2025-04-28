package org.example;


import java.util.Arrays;
import java.util.List;

import static org.example.MyGraph.calculateConnectedComponents;

public class Main {
    public static void main(String[] args) {

        MyGraph g = new MyGraph();

        // Add vertices
        for (int i = 0; i <= 9; i++) {
            g.addVertex(i);
        }

        // Add edges
        g.addEdge(0, 2, 13);
        g.addEdge(0, 1, 2);
        g.addEdge(4, 7, 1);
        g.addEdge(3, 5, 1);
        g.addEdge(6, 8, 1);
        g.addEdge(8, 9, 4);
        g.addEdge(6, 9, 9);

        // Calculate connected components
        int[] components = calculateConnectedComponents(g);

        // Print the result
        System.out.println("Connected Components");
        System.out.println("Vertex\tComp #");
        for (int i = 0; i < components.length; i++) {
            System.out.println(i + "\t" + components[i]);
        }
    }
}


    /**  */

    public static Edge getMinFrontierEdge(MyGraph g, boolean[] visited) {
        int minEdgeWeight = Integer.MAX_VALUE;
        Edge minEdge = new Edge(0, 0, minEdgeWeight);

        //Outer for loop goes through all vertices in the graph
        for(int v = 0; v <  g.vertices.size(); v++ ) {
              if(visited[v] == true) {
                  g.adjList.get(v);
              }

              //Inner for loop goes through all edges in current adj list.
          for(Edge e : g.adjList.get(v)) {
              if(((visited[e.v1] && !visited[e.v2]) || (visited[e.v2] && !visited[e.v1])) && (e.weight < minEdge.weight)) {
                  minEdge = e;
              }
          } //end of inner for loop
        } //end for outer for loop

        return minEdge;
    }

    /** Create a visited array of Boolean and set all elements to false.
     Add all vertices to the MST
     Pick any vertex to be the starting vertex of the MST.
     Set visited[startVertex] to true
     While (there is an unvisited vertex)
     Set minEdge to GetMinFrontierEdge()
     Set visited[minEdge.source] to true
     Set visited[minEdge.dest] to true
     Add minEdge to MST
     EndWhile
     return minimumSpanningTree
     */


    public static MyGraph  minimumSpanningTree(MyGraph g, int startingVertex) {
        boolean[] visited = new boolean[g.vertices.size()]; //all elements in a boolean array are already set to false in java
        MyGraph minimumSpanningTree = new MyGraph();

        for(int v = 0; v <  g.vertices.size(); v++ ) {

        }
    }



    public static int getMinDistVertex(MyGraph g, List<Integer> unvisitedList, int[] dist) {}

    public static void  shortestPath(MyGraph g, int startingVertex) {}


