package theworldview;

import controller.Features;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import theworld.ReadOnlyBoardGameModel;

/**
 * This class displays the panel with information that allows user to choose a world for
 * the game.
 *
 */
public class WorldSelectionPanel extends JPanel {

  private final ReadOnlyBoardGameModel readOnlyModel;
  private GridBagConstraints worldScreen;
  private JLabel imageLabel;
  private Features features;
  private JLabel textLabel;

  /**
   * The constructor for the class that initializes the ReadOnlyBoardGameModel for the 
   * functionality.
   *
   * @param readOnlyModel the ReadOnlyBoardGameModel to get the functionality for the events.
   */
  public WorldSelectionPanel(ReadOnlyBoardGameModel readOnlyModel) {

    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }

    this.readOnlyModel = readOnlyModel;

    this.setLayout(new GridBagLayout());
    this.setBackground(new Color(137, 207, 240));
    this.worldScreen = new GridBagConstraints();

    this.imageLabel = new JLabel();

    this.imageLabel.setIcon(new ImageIcon(new ImageIcon("KillDoctorLucky_image.jpg").getImage()
        .getScaledInstance(1200, 700, java.awt.Image.SCALE_SMOOTH)));

    this.worldScreen.gridx = 0;
    this.worldScreen.gridy = 0;
    this.worldScreen.weightx = 1.0;
    this.worldScreen.weighty = 1.0;
    this.worldScreen.anchor = GridBagConstraints.NORTH;
    this.worldScreen.insets = new Insets(50, 60, 0, 10);

    this.add(imageLabel, worldScreen);

    this.textLabel = new JLabel("Please click on the menu on the TOP-LEFT to choose the world");
    
    this.worldScreen.gridx = 0;
    this.worldScreen.gridy = 1;
    this.worldScreen.weightx = 1.0;
    this.worldScreen.weighty = 1.0;
    this.worldScreen.anchor = GridBagConstraints.NORTH;
    this.worldScreen.insets = new Insets(50, 60, 0, 10);

    this.add(textLabel, worldScreen);
  }

  /**
   * This method sets the call back for the features object.
   *
   * @param f the features object for callbacks from the view.
   */
  public void setFeatures(Features f) {
    
    if (f == null) {
      throw new IllegalArgumentException("Features object cannot be null\n");
    }
    this.features = f;
  }
}
