import java.util.Stack;

class Vertices {
    char label;
    boolean wasVisited;

    Vertices(char label) {
        this.label = label;
        this.wasVisited = false;
    }
}

class Graph {
    private final int MAX_VERTEX = 10;
    private Vertices[] verticies;
    private int index;
    private int adjMatrix[][];

    Graph() {
        this.verticies = new Vertices[MAX_VERTEX];
        this.index = 0;
        this.adjMatrix = new int[MAX_VERTEX][MAX_VERTEX];
    }

    public void createVertex(char label) {
        this.verticies[index++] = new Vertices(label);
    }

    public void addEdge(int start, int end) {
        adjMatrix[start][end] = 1;
        adjMatrix[end][start] = 1;
    }

    public void dfs() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        verticies[0].wasVisited = true;
        printVertex(0);
        while (!stack.isEmpty()) {
            int vertex = getNeighbours(stack.peek());
            if (vertex == -1) {
                stack.pop();
            } else {
                verticies[vertex].wasVisited = true;
                stack.push(vertex);
                printVertex(vertex);
            }
        }
    }

    private void printVertex(int vertex) {
        System.out.println(this.verticies[vertex].label);
    }

    private int getNeighbours(Integer peek) {
        for (int i = 0; i < adjMatrix[peek].length; i++) {
            if (adjMatrix[peek][i] == 1 && !verticies[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }
}

public class GraphDFS {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.createVertex('A');
        graph.createVertex('B');
        graph.createVertex('C');
        graph.createVertex('D');

        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(0,3);
        graph.addEdge(1,3);

        System.out.println("Depth First Search");
        graph.dfs();
    }
}
