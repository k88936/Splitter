package org.kvto;

import java.util.*;



public class Analysis {
    private int numVertices;
    private final List<Splittable> nodes;
    private final int targetCost = 800;

    public Analysis(Collection<SplittableUnit> funcs) {
        for (SplittableUnit splittableUnit : funcs) {

            splittableUnit.setFuncId(numVertices);

            this.addSplittable(splittableUnit);
            for (SplittableUnit unit : splittableUnit.dependency) {
                splittableUnit.addNeighbor(unit);
            }


        }
        this.numVertices = 0;
        nodes = new ArrayList<>();
    }

    void addSplittable(Splittable node) {
        node.setID(numVertices);
        numVertices++;
        this.nodes.add(node);
    }

    public int computeCost(Splittable splittable) {
        int cost = splittable.getCost();
        for (Splittable neighbor : splittable.getNeighbors()) {
            cost += computeCost(neighbor);
        }
        splittable.depenCost = cost;
        return cost;
    }


    private List<Splittable> findCycle() {
        boolean[] visited = new boolean[numVertices];
        boolean[] recursionStack = new boolean[numVertices];

        for (int i = 0; i < numVertices; i++) {

            List<Splittable> cycle = findCycleUtil(nodes.get(i), visited, recursionStack);
            if (cycle != null) {
                return cycle;
            }
        }
        return null;
    }

    private List<Splittable> findCycleUtil(Splittable node, boolean[] visited, boolean[] recursionStack) {
        int id = node.getId();

        if (recursionStack[id]) {
            List<Splittable> cycle = new ArrayList<>();
            cycle.add(node);
            return cycle;
        }

        if (visited[id]) {
            return null;
        }

        visited[id] = true;
        recursionStack[id] = true;

        List<Splittable> neighbors = node.getNeighbors();
        for (Splittable neighbor : neighbors) {
            List<Splittable> cycle = findCycleUtil(neighbor, visited, recursionStack);
            if (cycle != null) {
                cycle.add(node);
                return cycle;
            }
        }

        recursionStack[id] = false;

        return null;
    }

    public void replaceCyclesWithSplittables() {
        while (true) {
            List<Splittable> cycle = findCycle();
            if (cycle == null) {
                break; // No more cycles found
            }

            SplittableGroup replacementSplittable = new SplittableGroup(cycle);
//            Splittable replacementSplittable = new Splittable(numVertices); // New node to replace the cycle
//            numVertices++;

            for (Splittable currentSplittable : cycle) {

                List<Splittable> neighbors = currentSplittable.getNeighbors();

                for (Splittable neighbor : neighbors) {
                    if (cycle.contains(neighbor)) continue;
                    replacementSplittable.addNeighbor(neighbor);
                }


                nodes.remove(currentSplittable);
            }
            for (Splittable node : nodes) {
                boolean hasCircleAsNeighbor = false;
                for (Iterator<Splittable> iterator = node.getNeighbors().iterator(); iterator.hasNext(); ) {
                    Splittable neighbor = iterator.next();
                    if (cycle.contains(neighbor)) {
                        iterator.remove();
                        hasCircleAsNeighbor = true;
                    }

                }
                if (hasCircleAsNeighbor) {
                    node.addNeighbor(replacementSplittable);
                }

            }
            nodes.add(replacementSplittable);

            numVertices = nodes.size();

            for (int i = 0; i < nodes.size(); i++) {
                nodes.get(i).setID(i);
            }


        }
    }

    public ArrayList<Segment> split(Splittable root,ArrayList<Segment> select) {
        List<Segment> segments=findCombinations(root);
        // segments.max(Comparator.comparingInt(s -> s.evaluate()));
        Segment segment = segments.stream().max(Comparator.comparingInt(Segment::evaluate)).get();;

        select.add(segment);
        for (Splittable node : segment.root.getNeighbors()) {
        
            split(root, select);
        }



        return select;
        


    }


// 获取所有节点的组合


    // 计算组合的 cost 总和

    public List<Segment> findCombinations(Splittable root) {
        List<Segment> combinations = new ArrayList<>();

        if (root == null) {
            return combinations;
        }

        Queue<List<Splittable>> queue = new LinkedList<>();
        List<Splittable> initialCombination = new ArrayList<>();
        initialCombination.add(root);
        queue.offer(initialCombination);

        while (!queue.isEmpty()) {
            List<Splittable> currCombination = queue.poll();
            Splittable lastSplittable = currCombination.get(currCombination.size() - 1);
            int totalCost = 0;
            HashSet<Splittable> visited = new HashSet<>();
            for (Splittable node : currCombination) {
               visited.add(node);
            }
            for (Splittable node : visited) {
                totalCost+=node.cost;
            }
            if (totalCost >= targetCost) {
                continue; // 如果当前 cost 总和大于等于目标值，跳过该组合
            } else {
                combinations.add(new Segment(currCombination)); // 将当前组合加入结果集
            }

            for (Splittable child : lastSplittable.getNeighbors()) {
                List<Splittable> newCombination = new ArrayList<>(currCombination);
                newCombination.add(child);
                queue.offer(newCombination);
            }
        }


        // 如果没有找到满足条件的组合，则返回所有节点的组合
        if (combinations.isEmpty()) {
            combinations.add(new Segment(nodes));
        }

        return combinations;
    }
}
