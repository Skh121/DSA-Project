
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import gui.CityMapPanel;
import gui.DestinationItem;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class Application extends javax.swing.JFrame {
    private static Application application;
    private Queue<HashMap<Integer, String>> destinationQueue = new LinkedList<>();
    
    public Application() {
        initComponents();
        setTitle("Vehicle Route Optimization");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        
        itemContainer.setLayout(new javax.swing.BoxLayout(itemContainer, javax.swing.BoxLayout.Y_AXIS));
        itemContainer.setBackground(Color.decode("#FFFFFF"));
        
        
        scrollPaneDestinations.setBorder(null);
        scrollPaneDestinations.getViewport().setOpaque(false);
        scrollPaneDestinations.getViewport().setBorder(null);
        scrollPaneDestinations.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cityMapPanel1 = new gui.CityMapPanel();
        jLabel4 = new javax.swing.JLabel();
        selectedDestination = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        selectedAlgorithm = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        selectedVehicle = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        distanceConstraints = new javax.swing.JTextField();
        addToDestinations = new javax.swing.JButton();
        startOptimization = new javax.swing.JButton();
        scrollPaneDestinations = new javax.swing.JScrollPane();
        itemContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Select your destination");

        selectedDestination.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        selectedDestination.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dolakha", "Kathmandu", "Sindupalchowk", "Sindhuli", "Nuwakot", "Bhaktapur", "Dolpa", "Mustang", "Biratnagar", "Kailali", " " }));
        selectedDestination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedDestinationActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Select Algorithm");

        selectedAlgorithm.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        selectedAlgorithm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Djkistra's Algorithm", "Bellman Ford Algorithm" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Select the vehicle");

        selectedVehicle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        selectedVehicle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car • 5 Orders", "Bike • 3 Orders", "Bicycle: 2 Orders" }));
        selectedVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedVehicleActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Distance Constraints");

        distanceConstraints.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        distanceConstraints.setText("Example: 1Km");

        addToDestinations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/add.png"))); // NOI18N
        addToDestinations.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToDestinationsMouseClicked(evt);
            }
        });
        addToDestinations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToDestinationsActionPerformed(evt);
            }
        });

        startOptimization.setBackground(new java.awt.Color(204, 255, 255));
        startOptimization.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 16)); // NOI18N
        startOptimization.setText("Start Optimization");
        startOptimization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startOptimizationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout itemContainerLayout = new javax.swing.GroupLayout(itemContainer);
        itemContainer.setLayout(itemContainerLayout);
        itemContainerLayout.setHorizontalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );
        itemContainerLayout.setVerticalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        scrollPaneDestinations.setViewportView(itemContainer);

        javax.swing.GroupLayout cityMapPanel1Layout = new javax.swing.GroupLayout(cityMapPanel1);
        cityMapPanel1.setLayout(cityMapPanel1Layout);
        cityMapPanel1Layout.setHorizontalGroup(
            cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cityMapPanel1Layout.createSequentialGroup()
                .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cityMapPanel1Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(selectedDestination, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)
                        .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cityMapPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(selectedAlgorithm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(selectedVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(distanceConstraints, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cityMapPanel1Layout.createSequentialGroup()
                        .addGap(756, 756, 756)
                        .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPaneDestinations)
                            .addGroup(cityMapPanel1Layout.createSequentialGroup()
                                .addComponent(addToDestinations, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startOptimization, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(133, 133, 133))
        );
        cityMapPanel1Layout.setVerticalGroup(
            cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cityMapPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedDestination)
                    .addComponent(selectedAlgorithm, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(distanceConstraints, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(selectedVehicle))
                .addGap(18, 18, 18)
                .addGroup(cityMapPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(startOptimization, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addToDestinations, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(166, 166, 166)
                .addComponent(scrollPaneDestinations, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(cityMapPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cityMapPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addToDestinationsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addToDestinationsMouseClicked
    String destination = (String) selectedDestination.getSelectedItem();
    String vehicle = (String) selectedVehicle.getSelectedItem();
    int maxDestinations = getMaxDestinationsForVehicle(vehicle);
 
    if (itemContainer.getComponentCount() < maxDestinations) {
        DestinationItem item = new DestinationItem();
        item.setDestinationCity(destination);
 
        itemContainer.setLayout(new BoxLayout(itemContainer, BoxLayout.Y_AXIS));
 
        String priority = ""+ (itemContainer.getComponentCount() + 1);
        item.setPriorityLevel(priority);
        item.setBackground(Color.decode("#FFFFFF"));
 
        // Create a HashMap for the destination with its priority
        HashMap<Integer, String> destinationMap = new HashMap<>();
        destinationMap.put(itemContainer.getComponentCount() + 1, destination);
 
        destinationQueue.add(destinationMap);
 
        JOptionPane.showMessageDialog(this, "Added to Destinations.", "Warning", JOptionPane.INFORMATION_MESSAGE);
        itemContainer.add(item);
 
        itemContainer.revalidate();
        itemContainer.repaint();
    } else {
        JOptionPane.showMessageDialog(this, "Cannot add more destinations. Max limit reached for selected vehicle.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_addToDestinationsMouseClicked

    private void startOptimizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startOptimizationActionPerformed
        CityMapPanel cityMapPanel = new CityMapPanel();
        if (destinationQueue.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No destinations added for optimization.", "Optimization Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the selected destinations
        List<String> destinationCities = new ArrayList<>();
        destinationQueue.forEach(map -> {
            destinationCities.addAll(map.values());
        });

        // Ensure the algorithm is selected
        String selectedAlgo = (String) selectedAlgorithm.getSelectedItem();
        if (selectedAlgo != null && selectedAlgo.equals("Djkistra's Algorithm")) {
            cityMapPanel1.findOptimalRoutes(destinationCities);
        }
        else if (selectedAlgo != null && selectedAlgo.equals("Bellman Ford Algorithm")){
                        cityMapPanel1.findOptimalRoutes(destinationCities);
        }
        else {
            JOptionPane.showMessageDialog(this, "Only Dijkstra's Algorithm is implemented for now.", "Algorithm Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_startOptimizationActionPerformed

    private void selectedDestinationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedDestinationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedDestinationActionPerformed

    private void selectedVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedVehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedVehicleActionPerformed

    private void addToDestinationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToDestinationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addToDestinationsActionPerformed

    private int getMaxDestinationsForVehicle(String vehicle) {
        return switch (vehicle) {
            case "Car • 5 Orders" -> 5;
            case "Bike • 3 Orders" -> 3;
            case "Bicycle: 2 Orders" -> 2;
            default -> Integer.MAX_VALUE;
        };
    }
    
    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("app.fileconversiongui.theme");
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            application = new Application();
            application.setVisible(true);
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToDestinations;
    private gui.CityMapPanel cityMapPanel1;
    private javax.swing.JTextField distanceConstraints;
    private javax.swing.JPanel itemContainer;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane scrollPaneDestinations;
    private javax.swing.JComboBox<String> selectedAlgorithm;
    private javax.swing.JComboBox<String> selectedDestination;
    private javax.swing.JComboBox<String> selectedVehicle;
    private javax.swing.JButton startOptimization;
    // End of variables declaration//GEN-END:variables
}
