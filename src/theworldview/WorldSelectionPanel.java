package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import theworld.ReadOnlyBoardGameModel;

public class WorldSelectionPanel extends JPanel {
  
  private ReadOnlyBoardGameModel readOnlyModel;
  private BoardGameView view;
  private GridBagConstraints worldScreen;
  private JLabel imageLabel;
  private JButton currentWorld;
  private JButton newWorld;

  public WorldSelectionPanel(ReadOnlyBoardGameModel readOnlyModel, BoardGameView view) {

    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }
    
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.\n");
      
    }
    
    this.readOnlyModel = readOnlyModel;
    this.view = view;
    
    this.setLayout(new GridBagLayout());
    this.setBackground(new Color(137, 207, 240));
    this.worldScreen = new GridBagConstraints();
    
    this.imageLabel = new JLabel();
    
    this.imageLabel.setIcon(new ImageIcon(new ImageIcon("res/KillDoctorLucky_image.jpg")
        .getImage().getScaledInstance(1000, 500,  java.awt.Image.SCALE_SMOOTH)));
    
    this.worldScreen.gridx = 0;
    this.worldScreen.gridy = 0;
    this.worldScreen.weightx = 1.0;
    this.worldScreen.weighty = 1.0;
    this.worldScreen.anchor = GridBagConstraints.NORTH;
    this.worldScreen.insets = new Insets(50, 60, 0, 10);
    this.add(imageLabel, worldScreen);
    
    this.currentWorld = new JButton("CurrentWorld");
    this.currentWorld.setBackground(new Color(59, 89, 182));
    this.currentWorld.setForeground(Color.WHITE);
    this.currentWorld.setFocusPainted(false);
    this.currentWorld.setFont(new Font("Tahoma", Font.BOLD, 12));
    this.currentWorld.setPreferredSize(new Dimension(120, 40));
    
    this.worldScreen.gridx = 0;
    this.worldScreen.gridy = 1;
    this.worldScreen.weightx = 2.0;
    this.worldScreen.weighty = 2.0;
    this.worldScreen.anchor = GridBagConstraints.NORTH;
    this.worldScreen.insets = new Insets(20, 0, 10, 0);
    this.add(currentWorld, worldScreen);
    this.currentWorld.addActionListener(new ButtonListener(view));
    
    this.newWorld = new JButton("NewWorld");
    this.newWorld.setBackground(new Color(59, 89, 182));
    this.newWorld.setForeground(Color.WHITE);
    this.newWorld.setFocusPainted(false);
    this.newWorld.setFont(new Font("Tahoma", Font.BOLD, 12));
    this.newWorld.setPreferredSize(new Dimension(120, 40));
    
    this.worldScreen.gridx = 0;
    this.worldScreen.gridy = 1;
    this.worldScreen.weightx = 1.0;
    this.worldScreen.weighty = 1.0;
    this.worldScreen.anchor = GridBagConstraints.NORTH;
    this.worldScreen.insets = new Insets(20, 300, 10, 10);
    this.add(newWorld, worldScreen);
    
    this.newWorld.addActionListener(new ButtonListener(view));
  }
}
