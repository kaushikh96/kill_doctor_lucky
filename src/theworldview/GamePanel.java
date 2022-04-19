package theworldview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import theworld.ReadOnlyBoardGameModel;

public class GamePanel extends JPanel {
  
  private ReadOnlyBoardGameModel readOnlyModel;
  private JLabel imageLabel;
  private JPanel infoPanel;
  private JTextArea playersArea;
  private JTextArea turnInfoArea;
  private GridBagConstraints game;
  private JTextArea turnResultArea;
  
  public GamePanel(ReadOnlyBoardGameModel readOnlyModel) {
    
    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }
    
    this.readOnlyModel = readOnlyModel;
    
    this.setLayout(new GridBagLayout());
    this.game = new GridBagConstraints();
      
    this.imageLabel = new JLabel();
    
    this.imageLabel.setIcon(new ImageIcon("res/rep.jpg"));
    this.setBackground(new Color(137, 207, 240));
    
    this.game.gridx = 0;
    this.game.gridy = 0;
    this.game.weightx = 0.1;
    this.game.weighty = 0.1;
    this.game.fill = GridBagConstraints.BOTH;
    this.game.anchor = GridBagConstraints.NORTHWEST;
    this.game.insets = new Insets(10,10,10,10);
    
    this.add(imageLabel, game);

    this.infoPanel = new JPanel(new GridLayout(3,0,10,10));
    this.playersArea = new JTextArea();
    this.playersArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.playersArea.setBackground(new Color(37, 190, 175));

    this.game.gridx = 1;
    this.game.gridy = 0;
    this.game.weightx = 3.0;
    this.game.weighty = 3.0;
    this.game.anchor = GridBagConstraints.NORTHEAST;
    this.game.insets = new Insets(20,5,10,10);
    this.infoPanel.add(playersArea);
    
    this.turnInfoArea = new JTextArea();
    this.turnInfoArea.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    this.infoPanel.add(turnInfoArea);
    
    this.turnResultArea = new JTextArea();
    this.turnResultArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.turnResultArea.setBackground(new Color(37, 190, 175));
    this.infoPanel.add(turnResultArea);
    
    this.add(infoPanel, game);
    
    
    
    
  }

}