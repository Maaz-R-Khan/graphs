package org.example;

import java.util.*;

public class MyGraph {

    /**
     * A List of vertices */
    List<Integer> vertices;

    /**
     * A map of vertices to lists of edges
     * has Integer as the key and a list of edges. */
    Map<Integer, List<Edge>> adjList;


    /**
     * Default constructor - initializes all member variables*/
    public MyGraph() {
        vertices = new ArrayList<>();
        adjList = new HashMap<>();
    }

    /**
     * The addVertex method adds a vertex to the vertex collection. Add a key/value pair for
     * the adjacnecy list for this new center. */
    void addVertex(int v) {
        vertices.add(v); //add a vertex to the vertex collection
        adjList.put(v, new LinkedList<>()); //add a key/value pair for the adjacency list for this new vertex
    }

    void addEdge(int v1, int v2, int weight) {
        Edge e = new Edge(v1, v2, weight);

        //Get the adjacency lists.
        List<Edge> v1AdjList = adjList.get(v1);
        List<Edge> v2AdjList = adjList.get(v2);


        // Add edge to adjacency lists.
        v1AdjList.add(e);
        v2AdjList.add(e);
    }

    /** The calculateConnectedComponents method calculates the connected components of the graph */
    public static int[] calculateConnectedComponents(MyGraph g) {
        int numComponents = 0; // initialize numComponents to zero
        boolean[] visited = new boolean[g.vertices.size()]; // visited array
        int[] components = new int[g.vertices.size()];

        // Initializing all vertices to unvisited
        for (int i = 0; i < g.vertices.size(); i++) {
            visited[g.vertices.get(i)] = false;
        }

        // Looping through all vertices
        for (int i = 0; i < g.vertices.size(); i++) {
            int vertex = g.vertices.get(i); // get the vertex at index i

            if (!visited[vertex]) { // if currentVertex is unvisited
                numComponents++; // increment numComponents
                Queue<Integer> bfsQueue = new LinkedList<>(); // create bfsQueue
                bfsQueue.add(vertex); // put current vertex in bfsQueue
                visited[vertex] = true; // mark as visited

                // Breadth first search from current vertex
                while (!bfsQueue.isEmpty()) { // while bfsQueue is not empty
                    int compV = bfsQueue.poll();
                    components[g.vertices.indexOf(compV)] = numComponents;

                    for (Edge e : g.adjList.get(compV)) {
                        int adjacentVertex;

                        // Determine the adjacent vertex of compV
                        if (e.v1 == compV)
                            adjacentVertex = e.v2;
                        else
                            adjacentVertex = e.v1;

                        // If adjacent vertex is not visited, visit it and enqueue
                        if (!visited[adjacentVertex]) {
                            visited[adjacentVertex] = true;
                            bfsQueue.add(adjacentVertex);
                        }
                    }
                }
            }
        }

        return components;
    }





}
