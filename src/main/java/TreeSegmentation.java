import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int value;
    List<Node> children;

    Node(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }
}

public class TreeSegmentation {

    public static void main(String[] args) {
        // 构建示例树
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.children.add(node2);
        node1.children.add(node3);

        node2.children.add(node4);
        node2.children.add(node5);

        node3.children.add(node6);
        node3.children.add(node7);
        node3.children.add(node8);

        List<List<Node>> segments = segmentTree(node1);

        // 输出切割后的组合
        for (List<Node> segment : segments) {
            System.out.print("Segment: ");
            for (Node node : segment) {
                System.out.print(node.value + " ");
            }
            System.out.println();
        }
    }

    public static List<List<Node>> segmentTree(Node root) {
        List<List<Node>> segments = new ArrayList<>();

        Queue<List<Node>> queue = new LinkedList<>();
        queue.offer(new ArrayList<>()); // 初始队列中加入空集合

        while (!queue.isEmpty()) {
            List<Node> currentSegment = queue.poll();
            Node lastNode = currentSegment.isEmpty() ? null : currentSegment.get(currentSegment.size() - 1);


            int sum = 0;
            for (Node node : currentSegment) {
                sum += node.value;
            }
            if (sum < 50) {
                segments.add(new ArrayList<>(currentSegment));
            } else {
                continue;
            }
            for (Node child : lastNode != null ? lastNode.children : root.children) {
                List<Node> newSegment = new ArrayList<>(currentSegment);
                newSegment.add(child);
                queue.offer(newSegment);
            }
        }

        return segments;
    }
}
