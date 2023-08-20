package org.kvto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Node {
    private int id;
    private final List<Node> neighbors;

    public Node(int id) {
        this.id = id;
        neighbors = new ArrayList<>();
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public int getId() {
        return id;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setID(int i) {
        this.id = i;
    }

    @Override
    public String toString() {
        return "Node " + id;
    }
}

public class DirectedGraph {
    private int numVertices;
    private final List<Node> nodes;

    public DirectedGraph(int numVertices) {
        this.numVertices = numVertices;
        nodes = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            nodes.add(new Node(i));
        }
    }

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.addEdge(5, 0);


        if (graph.hasCycle()) {
            System.out.println("Graph contains cycles.");
            graph.replaceCyclesWithNodes();
        } else {
            System.out.println("Graph does not contain any cycles.");
        }

        // Print the modified graph
        for (Node node : graph.nodes) {
            System.out.print(node + " -> ");
            List<Node> neighbors = node.getNeighbors();
            for (Node neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void addEdge(int source, int destination) {
        Node sourceNode = nodes.get(source);
        Node destinationNode = nodes.get(destination);
        sourceNode.addNeighbor(destinationNode);
    }

    private List<Node> findCycle() {
        boolean[] visited = new boolean[numVertices];
        boolean[] recursionStack = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {

            List<Node> cycle = findCycleUtil(nodes.get(i), visited, recursionStack);
            if (cycle != null) {
                return cycle;
            }
        }

        return null;
    }

    private List<Node> findCycleUtil(Node node, boolean[] visited, boolean[] recursionStack) {
        int id = node.getId();

        if (recursionStack[id]) {
            List<Node> cycle = new ArrayList<>();
            cycle.add(node);
            return cycle;
        }

        if (visited[id]) {
            return null;
        }

        visited[id] = true;
        recursionStack[id] = true;

        List<Node> neighbors = node.getNeighbors();
        for (Node neighbor : neighbors) {
            List<Node> cycle = findCycleUtil(neighbor, visited, recursionStack);
            if (cycle != null) {
                cycle.add(node);
                return cycle;
            }
        }

        recursionStack[id] = false;

        return null;
    }

    public boolean hasCycle() {
        boolean[] visited = new boolean[numVertices];
        boolean[] recursionStack = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {
            if (hasCycleUtil(nodes.get(i), visited, recursionStack)) {
                return true;
            }
        }

        return false;
    }

    private boolean hasCycleUtil(Node node, boolean[] visited, boolean[] recursionStack) {
        int id = node.getId();

        if (recursionStack[id]) {
            return true;
        }

        if (visited[id]) {
            return false;
        }

        visited[id] = true;
        recursionStack[id] = true;

        List<Node> neighbors = node.getNeighbors();
        for (Node neighbor : neighbors) {
            if (hasCycleUtil(neighbor, visited, recursionStack)) {
                return true;
            }
        }

        recursionStack[id] = false;

        return false;
    }

    public void replaceCyclesWithNodes() {
        while (true) {
            List<Node> cycle = findCycle();
            if (cycle == null) {
                break; // No more cycles found
            }

            Node replacementNode = new Node(numVertices); // New node to replace the cycle
//            numVertices++;

            for (Node currentNode : cycle) {

                List<Node> neighbors = currentNode.getNeighbors();

                for (Node neighbor : neighbors) {
                    if (cycle.contains(neighbor)) continue;
                    replacementNode.addNeighbor(neighbor);
                }


                nodes.remove(currentNode);
            }
            for (Node node : nodes) {
                boolean hasCircleAsNeighbor = false;
                for (Iterator<Node> iterator = node.getNeighbors().iterator(); iterator.hasNext(); ) {
                    Node neighbor = iterator.next();
                    if (cycle.contains(neighbor)) {
                        iterator.remove();
                        hasCircleAsNeighbor = true;
                    }

                }
                if (hasCircleAsNeighbor) {
                    node.addNeighbor(replacementNode);
                }

            }
            nodes.add(replacementNode);

            numVertices = nodes.size();

            for (int i = 0; i < nodes.size(); i++) {
                nodes.get(i).setID(i);
            }


        }
    }
}
