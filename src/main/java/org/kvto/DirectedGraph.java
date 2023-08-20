package org.kvto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DirectedGraph {
    private int numVertices;
    private final List<Node> nodes;

    public DirectedGraph() {
        this.numVertices = 0;
        nodes = new ArrayList<>();
//        for (int i = 0; i < numVertices; i++) {
//            nodes.add(new Node(i));
//        }
    }


    //    public void addEdge(int source, int destination) {
//        Node sourceNode = nodes.get(source);
//        Node destinationNode = nodes.get(destination);
//        sourceNode.addNeighbor(destinationNode);
//    }
//    public void addEdge(Node source, Node destination){
//        source.addNeighbor(destination);
//    }
    void addNode(Node node) {
        node.setID(numVertices);
        numVertices++;
        this.nodes.add(node);
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

//    public boolean hasCycle() {
//        boolean[] visited = new boolean[numVertices];
//        boolean[] recursionStack = new boolean[numVertices];
//
//        for (int i = 0; i < numVertices; i++) {
//            if (hasCycleUtil(nodes.get(i), visited, recursionStack)) {
//                return true;
//            }
//        }
//
//        return false;
//    }

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

            SplittableGroup replacementNode = new SplittableGroup(cycle);
//            Node replacementNode = new Node(numVertices); // New node to replace the cycle
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
