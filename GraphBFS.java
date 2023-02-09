package org.example;


import java.util.LinkedList;
import java.util.Queue;

class Vertex {
    char label;
    boolean wasVisited;

    Vertex(char label) {
        this.label = label;
        this.wasVisited = false;
    }
}

class Graph {
    private final int MAX_VERTEX = 10;
    private final Vertex[] vertices;
    private int index;
    private final int adjMatrix[][];

    Graph() {
        vertices = new Vertex[MAX_VERTEX];
        index = 0;
        adjMatrix = new int[MAX_VERTEX][MAX_VERTEX];
    }

    public void createVertex(char label) {
        vertices[index++] = new Vertex(label);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        vertices[0].wasVisited = true;
        displayVertex(0);
        System.out.println();
        while (!queue.isEmpty()) {
            int front = queue.remove();
            int neighbour;
            while ((neighbour = getNeighbours(front)) != -1) {
                queue.add(neighbour);
                vertices[neighbour].wasVisited = true;
                displayVertex(neighbour);
                System.out.println();
            }
        }
    }

    private void displayVertex(int neighbour) {
        System.out.print(vertices[neighbour].label+ " ");
    }

    private int getNeighbours(int front) {
        for (int i = 0; i < adjMatrix[front].length; i++) {
            if (adjMatrix[front][i] == 1 && !vertices[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }
}

public class GraphBFS {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.createVertex('A');
        graph.createVertex('B');
        graph.createVertex('C');
        graph.createVertex('D');

        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,3);

        System.out.println("BFS Traversal");
        graph.bfs();
    }
}
