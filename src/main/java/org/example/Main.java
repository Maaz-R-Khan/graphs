package org.example;


import java.util.ArrayList;
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
        System.out.println("Connected Components:");
        System.out.println("Vertex\tComp #");
        for (int i = 0; i < components.length; i++) {
            System.out.println(i + "\t" + components[i]);
        }

        //The following is the Shortest Path and Minimum Spanning Tree
        MyGraph g2 = new MyGraph();

        for (int i = 0; i < 10; i++)
            g2.addVertex(i);

        g2.addEdge(0,1,2);
        g2.addEdge(0,2,13);
        g2.addEdge(0,3,7);

        g2.addEdge(1,3,2);
        g2.addEdge(2,4,2);
        g2.addEdge(2,5,7);

        g2.addEdge(3,5,1);
        g2.addEdge(3,6,5);

        g2.addEdge(4,7,1);

        g2.addEdge(5,7,4);
        g2.addEdge(5,8,1);

        g2.addEdge(6,8,1);
        g2.addEdge(6,9,9);

        g2.addEdge(7,8,2);

        g2.addEdge(8,9,4);

//calls the minimumSpanning tree
        int totalWeight = 0;
        MyGraph minimumSpanningTree = minimumSpanningTree(g2,0);

        for (int i = 0; i < minimumSpanningTree.vertices.size(); i++) {
                List<Edge> edges = minimumSpanningTree.adjList.get(i); // Get the adjacency list for vertex i
                for (int j = 0; j < edges.size(); j++) { // Loop over the edges connected to vertex i
                        totalWeight += edges.get(j).weight;
                }
        }
//divide by 2 because since its counting the edges twice, when doing the adding it messes up
        System.out.println("Total Weight: " + totalWeight/2);



        //Testing Shortest Path
        System.out.println("\n--- Shortest Path from Vertex 0 ---");
        shortestPath(g2, 0);

    }

    /**
     * GetMinFrontierEdge returns the edge that goes from a visited vertex to an unvisited vertex that has a minimum weight.
     */
    public static Edge getMinFrontierEdge(MyGraph g, boolean[] visited) {
        int minEdgeWeight = Integer.MAX_VALUE;
        Edge minEdge = new Edge(0, 0, minEdgeWeight);

        //Outer for loop goes through all vertices in the graph
        for (int v = 0; v < g.vertices.size(); v++) {
            if (visited[v]) {
                List<Edge> edges = g.adjList.get(v);

                //Inner for loop goes through all edges in current adj list.
                for (int e = 0; e < edges.size(); e++) { // e is the index now
                    Edge edge = edges.get(e); // edge is the actual Edge object

                    if (((visited[edge.v1] && !visited[edge.v2]) || (visited[edge.v2] && !visited[edge.v1]))
                            && (edge.weight < minEdge.weight)) {
                        minEdge = edge;
                    }
                } //end of inner for loop
            } //end of if statement
        } //end for outer for loop

        return minEdge;
    }



    public static MyGraph minimumSpanningTree(MyGraph g, int startingVertex) {
        boolean[] visited = new boolean[g.vertices.size()]; //all elements in a boolean array are already set to false in java
        MyGraph minimumSpanningTree = new MyGraph();

        //add all vertices from the graph to the minimum spanning tree
        for (int i = 0; i < g.vertices.size(); i++) {
            minimumSpanningTree.addVertex(g.vertices.get(i));
        }

        //pick the first vertex to be the starting vertex of the minimum spanning tree.
        visited[startingVertex] = true;
        while (true) {
            boolean allVisited = true;
            for (boolean b : visited) {
                if (!b) {
                    allVisited = false;
                    break;
                }
            }
            if (allVisited) {
                break;
            }
            Edge minEdge = getMinFrontierEdge(g, visited);
            visited[minEdge.v1] = true;
            visited[minEdge.v2] = true;
            minimumSpanningTree.addEdge(minEdge.v1, minEdge.v2, minEdge.weight);
        } //end of while loop
        return minimumSpanningTree;
    }



    /** This method searches the list of unvisited vertices for the one that has the minimum distance to the starting vertex. */
    public static int getMinDistVertex(MyGraph g, List<Integer> unvisitedList, int[] dist) {
        int minNeighborVertex = unvisitedList.get(0);
        int minNeighborDist = dist[minNeighborVertex];

        for(int i = 0; i < unvisitedList.size(); i++) {
            int v = unvisitedList.get(i);

            if(dist[v] < minNeighborDist) {
                minNeighborDist = dist[v];
                minNeighborVertex = v;
            }
        }
        return minNeighborVertex;
    }

    /** */
    public static void  shortestPath(MyGraph g, int startingVertex) {
        int[] dist = new int[g.vertices.size()];
        int[] previous = new int[g.vertices.size()];
        boolean[] visited = new boolean[g.vertices.size()];


        for (int i = 0; i < g.vertices.size(); i++) {
            dist[i] = Integer.MAX_VALUE; //setting all vertex distances to the max value.
            previous[i] = -1; //setting all previous to -1
            visited[i] = false; //set all visited to false
        }

        List<Integer> unvisitedList = new ArrayList<>(); //creating the unvisited list.

        //putting all vertices in the unvisited list.
        for (int v : g.vertices) {
            unvisitedList.add(v);
        }

        //pick a starting vertex and settings its distance to 0;
        dist[startingVertex] = 0;

        //while unvisited list is not empty
        while (!unvisitedList.isEmpty()) {
            int currV = getMinDistVertex(g,unvisitedList, dist); // initialize current vertex
            unvisitedList.remove((Integer) currV); // remove current vertex from Unvisited list
            visited[currV] = true; //set current visited vertex to be true.

            //for all unvisited neighbors of the current vertex.
            for (Edge edge : g.adjList.get(currV)) {
                int neighbor;
                if (edge.v1 == currV) {  //if the current vertex is  vertex 1
                    neighbor = edge.v2; } //then the neighbor is vertex 2
                else neighbor = edge.v1; //else if the current vertex is vertex 2, then neighbor is vertex 1.

                if (!visited[neighbor]) { //for all unvisited neighbors of currV
                    int possibleDist = dist[currV] + edge.weight;
                    if (possibleDist < dist[neighbor]) {
                        dist[neighbor] = possibleDist;
                        previous[neighbor] = currV;
                    }
                }
            } //end for loop
        } //end while loop

        System.out.println("\nDistance Array:");
        System.out.println("Vertex\tDistance");
        for (int i = 0; i < g.vertices.size(); i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(i + "\t" + "∞");
            } else {
                System.out.println(i + "\t" + dist[i]);
            }
        }

        System.out.println("\nPrevious Array:");
        System.out.println("Vertex\tPrevious");
        for (int i = 0; i < g.vertices.size(); i++) {
            System.out.println(i + "\t" + previous[i]);
        }
    }


}