package gui;

import algorithm.Dijkstra;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CityMapPanel extends JPanel {

    private final Map<String, Point> cities;
    private final Map<String, String[]> connections;
    private final Map<String, Double> distances; // Store distances between cities
    private final Map<String, Integer> cityIndices;
    private int[] optimalPath; // Store the optimal path here
    private String optimalPathString; // To hold the string representation of the path

    public CityMapPanel() {
        this.cities = new HashMap<>();
        this.connections = new HashMap<>();
        this.distances = new HashMap<>();
        this.cityIndices = new HashMap<>();
        this.optimalPath = new int[0]; // Initialize with an empty path
        this.optimalPathString = ""; // Initialize with an empty path string

        // Initialize cities and their locations
        cities.put("Kathmandu", new Point(2, 2));
        cities.put("Pokhara", new Point(5, 3));
        cities.put("Lalitpur", new Point(3, 3));
        cities.put("Biratnagar", new Point(6, 5));
        cities.put("Bharatpur", new Point(4, 6));
        cities.put("Janakpur", new Point(2, 5));
        cities.put("Hetauda", new Point(3, 4));
        cities.put("Dhangadhi", new Point(6, 6));
        cities.put("Butwal", new Point(5, 5));
        cities.put("Nepalgunj", new Point(7, 4));

        // Initialize connections between cities
        connections.put("Kathmandu", new String[]{"Pokhara", "Lalitpur", "Janakpur", "Hetauda"});
        connections.put("Pokhara", new String[]{"Biratnagar", "Butwal"});
        connections.put("Lalitpur", new String[]{"Hetauda"});
        connections.put("Biratnagar", new String[]{"Bharatpur", "Dhangadhi"});
        connections.put("Bharatpur", new String[]{"Janakpur"});
        connections.put("Janakpur", new String[]{"Hetauda"});
        connections.put("Hetauda", new String[]{"Butwal"});
        connections.put("Dhangadhi", new String[]{"Nepalgunj"});
        connections.put("Butwal", new String[]{"Nepalgunj"});

        // Assign an index to each city for graph representation
        int index = 0;
        for (String city : cities.keySet()) {
            cityIndices.put(city, index++);
        }

        // Calculate and store distances (weights) between cities
        calculateDistances();
    }

    private void calculateDistances() {
        for (Map.Entry<String, String[]> entry : connections.entrySet()) {
            String city1 = entry.getKey();
            Point point1 = cities.get(city1);
            if (point1 != null) {
                for (String city2 : entry.getValue()) {
                    Point point2 = cities.get(city2);
                    if (point2 != null) {
                        double distance = Math.sqrt(Math.pow(point2.x - point1.x, 2) + Math.pow(point2.y - point1.y, 2));
                        distances.put(city1 + "-" + city2, distance);
                        distances.put(city2 + "-" + city1, distance); // Bidirectional distance
                    }
                }
            }
        }
    }

    public void findOptimalRoute(String sourceCity, String destinationCity) {
        if (!cityIndices.containsKey(sourceCity) || !cityIndices.containsKey(destinationCity)) {
            JOptionPane.showMessageDialog(this, "Invalid city names!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int V = cities.size();
        double[][] graph = new double[V][V];

        // Initialize the graph with Double.MAX_VALUE to represent no direct path
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (i != j) {
                    graph[i][j] = Double.MAX_VALUE;
                }
            }
        }

        // Fill the graph with distances for direct connections
        for (Map.Entry<String, Double> entry : distances.entrySet()) {
            String[] citiesPair = entry.getKey().split("-");
            if (citiesPair.length == 2) {
                String city1 = citiesPair[0];
                String city2 = citiesPair[1];
                double distance = entry.getValue();
                int city1Index = cityIndices.get(city1);
                int city2Index = cityIndices.get(city2);
                graph[city1Index][city2Index] = distance;
                graph[city2Index][city1Index] = distance; // Ensure bidirectional
            }
        }

        int sourceIndex = cityIndices.get(sourceCity);
        int destinationIndex = cityIndices.get(destinationCity);

        // Get the optimal path between the source and destination
        this.optimalPath = Dijkstra.optimizeRoute(graph, sourceIndex, destinationIndex);
        this.optimalPathString = getPathString(); // Convert the path to a string

        System.out.println("Optimal Path (Indices): " + java.util.Arrays.toString(optimalPath));
        System.out.println("Optimal Path (String): " + optimalPathString);

        repaint(); // Trigger a repaint to visualize the path
    }

    private String getPathString() {
        if (optimalPath == null || optimalPath.length == 0) {
            return "No path found";
        }

        return cityIndices.entrySet().stream()
                .filter(entry -> {
                    for (int i : optimalPath) {
                        if (entry.getValue() == i) {
                            return true;
                        }
                    }
                    return false;
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(" -> "));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Set background color
        this.setBackground(new Color(240, 248, 255)); // Alice Blue

        // Define grid size and node radius
        int gridSize = 80;
        int nodeRadius = 15;

        // Draw the grid
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < getWidth(); i += gridSize) {
            for (int j = 0; j < getHeight(); j += gridSize) {
                g.drawRect(i, j, gridSize, gridSize);
            }
        }

        // Draw cities
        g.setFont(new Font("Arial", Font.BOLD, 14));
        cities.forEach((name, point) -> {
            int x = point.x * gridSize + gridSize / 2;
            int y = point.y * gridSize + gridSize / 2;

            // Draw city name
            g.setColor(Color.BLACK);
            g.drawString(name, x - nodeRadius, y - nodeRadius - 5);

            // Draw city node
            g.setColor(Color.RED);
            g.fillOval(x - nodeRadius / 2, y - nodeRadius / 2, nodeRadius, nodeRadius);
        });

        // Draw connections (edges) between cities
        g.setColor(Color.BLACK);
        connections.forEach((city1, neighbors) -> {
            for (String city2 : neighbors) {
                drawConnection(g, city1, city2, gridSize);
            }
        });
        
        // Draw highlighted optimal path
        if (optimalPath != null && optimalPath.length > 1) {
            g.setColor(Color.GREEN); // Highlighted path color
            for (int i = 0; i < optimalPath.length - 1; i++) {
                String fromCity = getCityNameByIndex(optimalPath[i]);
                String toCity = getCityNameByIndex(optimalPath[i + 1]);
                drawHighlightedConnection(g, fromCity, toCity, gridSize);
            }
        }

        // Print the optimal path as a string only if it is set
        if (optimalPathString != null && !optimalPathString.isEmpty()) {
            g.setColor(Color.BLACK);
            g.drawString("Optimal Path: " + optimalPathString, 10, getHeight() - 30);
        }
    }

    private void drawConnection(Graphics g, String city1, String city2, int gridSize) {
        Point point1 = cities.get(city1);
        Point point2 = cities.get(city2);

        if (point1 != null && point2 != null) {
            int x1 = point1.x * gridSize + gridSize / 2;
            int y1 = point1.y * gridSize + gridSize / 2;
            int x2 = point2.x * gridSize + gridSize / 2;
            int y2 = point2.y * gridSize + gridSize / 2;

            g.drawLine(x1, y1, x2, y2);
            double distance = distances.get(city1 + "-" + city2);
            g.drawString(String.format("%.1f", distance), (x1 + x2) / 2, (y1 + y2) / 2);
        }
    }

    private void drawHighlightedConnection(Graphics g, String city1, String city2, int gridSize) {
        Point point1 = cities.get(city1);
        Point point2 = cities.get(city2);

        if (point1 != null && point2 != null) {
            int x1 = point1.x * gridSize + gridSize / 2;
            int y1 = point1.y * gridSize + gridSize / 2;
            int x2 = point2.x * gridSize + gridSize / 2;
            int y2 = point2.y * gridSize + gridSize / 2;

            g.setColor(Color.BLUE); // Color for the highlighted path
            g.drawLine(x1, y1, x2, y2);
            double distance = distances.get(city1 + "-" + city2);
            g.drawString(String.format("%.1f", distance), (x1 + x2) / 2, (y1 + y2) / 2);
        }
    }

    private String getCityNameByIndex(int index) {
        for (Map.Entry<String, Integer> entry : cityIndices.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return null;
    }
}
