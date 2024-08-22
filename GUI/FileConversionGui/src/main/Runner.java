package main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import helper.EachFileItem;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import net.coobird.thumbnailator.Thumbnails;
import raven.toast.Notifications;

public class Runner extends javax.swing.JFrame {
    private static Runner runner;
    private final List<EachFileItem> fileItems = new ArrayList<>();
    private int totalFiles;
    private int processedFiles = 0;
    private ExecutorService executorService;
    private List<Future<?>> futures = new ArrayList<>();
    private File destinationFolder;

    public Runner() {
        initComponents();
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        setResizable(false);
        setTitle("Image Resizer");

        Notifications.getInstance().setJFrame(this);

        itemContainer.setLayout(new javax.swing.BoxLayout(itemContainer, javax.swing.BoxLayout.Y_AXIS));
        itemContainer.setBackground(Color.decode("#FFFFFF"));

        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                convertFiles();
            }
        });

        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelAllConversions();
            }
        });

        scrollableItemPane.setBorder(null);
        scrollableItemPane.getViewport().setOpaque(false);
        scrollableItemPane.getViewport().setBorder(null);

        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    private void openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true); // Allow multiple file selection
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            fileItems.clear();
            itemContainer.removeAll(); // Remove all previous items

            for (File file : selectedFiles) {
                EachFileItem fileItem = new EachFileItem();
                fileItem.setFile(file);
                fileItem.setBackground(Color.decode("#FFFFFF"));

                double fileSizeInMB = file.length() / (1024.0 * 1024.0); // Use double for accurate division
                fileItem.setFileSize(String.format("%.2f MB", fileSizeInMB)); // Show two decimal places
                fileItem.setProgress(0);
                fileItem.setImageName(file.getName());

                fileItem.setCancelListener(item -> cancelConversion(item));

                itemContainer.add(fileItem);
                fileItems.add(fileItem);
            }

            itemContainer.revalidate();
            itemContainer.repaint();
        }
    }

    private void convertFiles() {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = folderChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            destinationFolder = folderChooser.getSelectedFile();
            if (destinationFolder != null && !destinationFolder.exists()) {
                destinationFolder.mkdirs(); // Create folder if it does not exist
            }
            totalFiles = fileItems.size();
            processedFiles = 0;
            futures.clear();

            for (EachFileItem fileItem : fileItems) {
                File file = fileItem.getFile();
                if (file != null) {
                    FileConversionTask task = new FileConversionTask(file, fileItem);
                    Future<?> future = executorService.submit(task);
                    futures.add(future);
                }
            }
        }
    }

    private void cancelConversion(EachFileItem fileItem) {
        for (Future<?> future : futures) {
            if (future.isDone() || future.isCancelled()) {
                continue;
            }
            future.cancel(true);
            fileItem.setProgress(0); // Optionally reset progress
            break;
        }
    }

    private void cancelAllConversions() {
        for (Future<?> future : futures) {
            future.cancel(true);
        }
        for (EachFileItem fileItem : fileItems) {
            fileItem.setProgress(0); // Optionally reset progress
        }
        futures.clear();
        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_CENTER, "All conversions have been cancelled.");
    }

    private class FileConversionTask implements Runnable {
        private final File file;
        private final EachFileItem fileItem;

        public FileConversionTask(File file, EachFileItem fileItem) {
            this.file = file;
            this.fileItem = fileItem;
        }

        @Override
        public void run() {
            try {
                BufferedImage bufferedImage = ImageIO.read(file);
                BufferedImage resizedImage = resizeImage(bufferedImage, 100, 100); // Example resize

                if (Thread.currentThread().isInterrupted()) {
                    return;
                }

                String fileName = file.getName(); // You might want to change the file name or extension
                File outputFile = new File(destinationFolder, fileName);

                ImageIO.write(resizedImage, "png", outputFile);

                fileItem.setProgress(100);
                synchronized (Runner.this) {
                    processedFiles++;
                    if (processedFiles == totalFiles) {
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "All images resized successfully!");
                    }
                }
            } catch (IOException e) {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                e.printStackTrace();
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        return Thumbnails.of(originalImage).size(targetWidth, targetHeight).asBufferedImage();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        selectImage = new helper.DottedPanel();
        uploadImageBackground = new helper.PictureBox();
        jButton3 = new javax.swing.JButton();
        titleFirst = new javax.swing.JLabel();
        titleSecond = new javax.swing.JLabel();
        scrollableItemPane = new javax.swing.JScrollPane();
        itemContainer = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        uploadImageBackground.setImage(new javax.swing.ImageIcon(getClass().getResource("/src/upload.png"))); // NOI18N

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton3.setText("Browse Files");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selectImageLayout = new javax.swing.GroupLayout(selectImage);
        selectImage.setLayout(selectImageLayout);
        selectImageLayout.setHorizontalGroup(
            selectImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectImageLayout.createSequentialGroup()
                .addContainerGap(125, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
            .addGroup(selectImageLayout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(uploadImageBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        selectImageLayout.setVerticalGroup(
            selectImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectImageLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(uploadImageBackground, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        titleFirst.setFont(new java.awt.Font("Segoe UI Semibold", 1, 16)); // NOI18N
        titleFirst.setText("Resize Images In Bulk");

        titleSecond.setFont(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N

        itemContainer.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout itemContainerLayout = new javax.swing.GroupLayout(itemContainer);
        itemContainer.setLayout(itemContainerLayout);
        itemContainerLayout.setHorizontalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );
        itemContainerLayout.setVerticalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );

        scrollableItemPane.setViewportView(itemContainer);

        jButton1.setBackground(new java.awt.Color(21, 104, 226));
        jButton1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Convert Now");
        jButton1.setBorderPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 51, 51));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Only support .jpg, .png files");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(selectImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(titleFirst)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(titleSecond))
                            .addComponent(scrollableItemPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleSecond, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleFirst)
                        .addGap(8, 8, 8)
                        .addComponent(selectImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(scrollableItemPane, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        openFileChooser();
    }//GEN-LAST:event_jButton3ActionPerformed

    public static void main(String args[]) {
        FlatLaf.registerCustomDefaultsSource("app.fileconversiongui.theme");
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            runner = new Runner();
            runner.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel itemContainer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrollableItemPane;
    private helper.DottedPanel selectImage;
    private javax.swing.JLabel titleFirst;
    private javax.swing.JLabel titleSecond;
    private helper.PictureBox uploadImageBackground;
    // End of variables declaration//GEN-END:variables
}
