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



}
