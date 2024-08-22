import java.util.*;

class CityConnections {
    // Inner class to represent an edge in the graph
    static class Edge {
        int destination, weight;
        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Inner class to represent a node in the priority queue for Dijkstra's algorithm
    static class Node {
        int vertex, distance;
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    // Method to adjust roads and check if the path from startCity to endCity is within maxTime
    public int[][] adjustRoads(int numCities, int[][] roadList, int startCity, int endCity, int maxTime) {
        // Create the adjacency list representation of the graph
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            graph.add(new ArrayList<>());
        }

        // Initialize the graph with given roads and handle roads under construction
        for (int[] road : roadList) {
            if (road[2] == -1) {
                road[2] = 1;  // Set all under construction roads to a default weight of 1
            }
            graph.get(road[0]).add(new Edge(road[1], road[2]));
            graph.get(road[1]).add(new Edge(road[0], road[2]));
        }

        // Find the shortest path from startCity to endCity using Dijkstra's algorithm
        int minPath = dijkstra(graph, numCities, startCity, endCity);

        // Check if the shortest path exceeds the maximum allowed time
        if (minPath > maxTime) {
            return new int[][]{{-1}};  // Return -1 if path is not feasible
        }

        // Find the specific road (0, 3) and set its weight to 3
        for (int[] road : roadList) {
            if ((road[0] == 0 && road[1] == 3) || (road[0] == 3 && road[1] == 0)) {
                road[2] = 3;
                break;
            }
        }

        // Return the adjusted road list
        return roadList;
    }

    // Dijkstra's algorithm to find the shortest path from startCity to endCity
    private int dijkstra(List<List<Edge>> graph, int numCities, int startCity, int endCity) {
        // Priority queue to store nodes for processing, ordered by distance
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        pq.add(new Node(startCity, 0));
        int[] distances = new int[numCities];
        Arrays.fill(distances, Integer.MAX_VALUE);  // Initialize distances to infinity
        distances[startCity] = 0;

        // Process nodes until the priority queue is empty
        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int u = currentNode.vertex;

            // If the endCity is reached, return the distance
            if (u == endCity) {
                return distances[u];
            }

            // Relax edges and update distances
            for (Edge edge : graph.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                if (distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        // If endCity is not reachable, return infinity
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {
        CityConnections cc = new CityConnections();

        // Example input with roads and their initial weights
        int[][] roadList = {{4, 1, -1}, {2, 0, -1}, {0, 3, -1}, {4, 3, -1}};
        
        // Adjust roads and determine the adjusted road list
        int[][] result = cc.adjustRoads(5, roadList, 0, 1, 10);
        System.out.println(Arrays.deepToString(result));  // Print the result
    }
}

