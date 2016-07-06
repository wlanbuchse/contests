import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private boolean visited = false;
    private HashMap neighbours = new HashMap();
    private int distance = -1; // A distance of -1 is equivalent to a distance of infinity
    private Node previousNode = null;
    private int number;

    public static Node getNode(ArrayList<Node> graph, int number) {
        for(Node node : graph) {
            if(node.number == number)
                return node;
        }

        return null; // No node with number "number" found
    }

    public static boolean allNodesVisited(ArrayList<Node> graph) {
        for(Node node : graph) {
            if(node.isVisited() == false)
                return false;
        }

        return true;
    }

    public static Node getClosestNode(ArrayList<Node> graph) {
        Node result = null;

        for(Node node : graph) {
            if(node.isVisited())
                continue;

            if(node.getDistance() == -1)
                continue;

            if(result == null || node.getDistance() < result.getDistance())
                result = node;
        }

        return result;
    }

    public Node(int number) {
        if(number > 0)
            this.number = number;
        else
            this.number = 1;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        visited = true;
    }

    public HashMap getNeighbours() {
        return neighbours;
    }

    public void addNeighbour(Node neighbour, int distance) {
        neighbours.put(neighbour, distance);
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        if(distance >= 0)
            this.distance = distance;
        else
            this.distance = 0;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node node) {
        previousNode = node;
    }

    public int getNumber() {
        return number;
    }

}
