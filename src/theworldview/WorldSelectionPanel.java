package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.Features;
import theworld.ReadOnlyBoardGameModel;

public class WorldSelectionPanel extends JPanel {

  private ReadOnlyBoardGameModel readOnlyModel;
  private BoardGameView view;
  private GridBagConstraints worldScreen;
  private JLabel imageLabel;
  private JLabel fileLabel;
  private JButton currentWorld;
  private JButton newWorld;
  private Features f;

  public WorldSelectionPanel(ReadOnlyBoardGameModel readOnlyModel) {

    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }

    this.readOnlyModel = readOnlyModel;
    this.view = view;

    this.setLayout(new GridBagLayout());
    this.setBackground(new Color(137, 207, 240));
    this.worldScreen = new GridBagConstraints();

    this.imageLabel = new JLabel();

    this.imageLabel.setIcon(new ImageIcon(new ImageIcon("res/KillDoctorLucky_image.jpg").getImage()
        .getScaledInstance(1200, 700, java.awt.Image.SCALE_SMOOTH)));

    this.worldScreen.gridx = 0;
    this.worldScreen.gridy = 0;
    this.worldScreen.weightx = 1.0;
    this.worldScreen.weighty = 1.0;
    this.worldScreen.anchor = GridBagConstraints.NORTH;
    this.worldScreen.insets = new Insets(50, 60, 0, 10);

    this.fileLabel = new JLabel();

    this.worldScreen.gridx = 0;
    this.worldScreen.gridy = 1;
    this.worldScreen.weightx = 1.0;
    this.worldScreen.weighty = 1.0;
    this.worldScreen.anchor = GridBagConstraints.NORTH;
    this.worldScreen.insets = new Insets(50, 60, 0, 10);
    this.add(imageLabel, worldScreen);
  }

  public void setFeatures(Features f) {
    this.f = f;
  }
}
