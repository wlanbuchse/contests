import java.util.*;

public class HikingTest {

    public void start() {
        Scanner stdin = new Scanner(System.in);
        int t = stdin.nextInt(); // Number of test cases

        for (int i = 1; i <= t; i++) {
            ArrayList<Node> graph = new ArrayList<Node>();

            int n = stdin.nextInt(); // Number of intersections (i.e. nodes)
            int m = stdin.nextInt(); // Number of walking trails (i.e. edges)

            // Create n nodes
            for (int j = 1; j <= n; j++) {
                Node node = new Node(j);
                graph.add(node);
            }

            for (int j = 0; j < m; j++) {
                // Intersections
                int v = stdin.nextInt();
                int w = stdin.nextInt();
                // Distance between those intersections
                int c = stdin.nextInt();

                Node n1 = Node.getNode(graph, v);
                Node n2 = Node.getNode(graph, w);

                n1.addNeighbour(n2, c);
                n2.addNeighbour(n1, c);
            }

            int result = dijkstra(graph, n);
            System.out.println("Case #" + t + ": " + result);
        }
    }

    public int dijkstra(ArrayList<Node> graph, int n) {
        if(graph.size() == 0)
            return 0;

        Node firstNode = Node.getNode(graph, 1);
        firstNode.setDistance(0);
        firstNode.setPreviousNode(firstNode);

        Node currentNode;

        while(Node.allNodesVisited(graph) == false) {
            currentNode = Node.getClosestNode(graph);
            currentNode.setVisited();

            Iterator<Map.Entry<Node, Integer>> it = currentNode.getNeighbours().entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry<Node, Integer> entry = (Map.Entry) it.next();
                Node neighbour = entry.getKey();
                int distance = entry.getValue();

                if(neighbour.isVisited())
                    continue;

                int newDistance = currentNode.getDistance() + distance;
                if(neighbour.getDistance() == -1 || newDistance < neighbour.getDistance()) {
                    neighbour.setDistance(newDistance);
                    neighbour.setPreviousNode(currentNode);
                }
            }
        }

        return Node.getNode(graph, n).getDistance();
    }

}
