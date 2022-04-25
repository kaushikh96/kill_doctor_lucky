package theworldview;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import theworld.ReadOnlyBoardGameModel;

/**
 * The Panel that is the welcome screen for the game which provides us with the
 * button to start the game.
 *
 */
public class WelcomePanel extends JPanel {

  private final ReadOnlyBoardGameModel readonlymodel;
  private JButton startButton;
  private GridBagConstraints welcome;
  private JPanel welcomePanel;
  private Features features;
  private String labelText1;
  private JLabel coloredLabel1;
  private Border border;
  private String labelText;
  private JLabel coloredLabel;
  private String kaushikText;
  private JLabel kaushikLabel;
  private StringBuilder sb1;
  private StringBuilder sb2;

  /**
   * Constructor for welcome panel class that initializes the ReadOnlyBoardModel to get
   * the functionality.
   *
   * @param readonlymodel the ReadOnlyBoardModel type for getting the
   *                      functionality.
   */
  public WelcomePanel(ReadOnlyBoardGameModel readonlymodel) {
    if (readonlymodel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }
    this.readonlymodel = readonlymodel;
    this.setBackground(new Color(137, 207, 240));

    this.setLayout(new BorderLayout());

    this.welcomePanel = new JPanel(new GridBagLayout());
    this.welcomePanel.setBackground(new Color(137, 207, 240));
    this.welcome = new GridBagConstraints();

    this.labelText1 = "<html><font color=#8B8000 size=500>KILL DOCTOR LUCKY</font><br></html>";
    this.coloredLabel1 = new JLabel(labelText1, JLabel.CENTER);
    this.coloredLabel1.setPreferredSize(new Dimension(550, 80));
    this.coloredLabel1.setBackground(Color.YELLOW);
    this.border = BorderFactory.createLineBorder(Color.YELLOW, 2);

    coloredLabel1.setBorder(border);
    this.welcome.gridx = 0;
    this.welcome.gridy = 0;
    this.welcome.anchor = GridBagConstraints.NORTH;
    this.welcome.weightx = 5.0;
    this.welcome.weighty = 5.0;
    this.welcome.insets = new Insets(80, 10, 10, 10);
    this.welcomePanel.add(coloredLabel1, welcome);
    this.add(welcomePanel, BorderLayout.NORTH);

    this.sb1 = new StringBuilder();

    this.sb1.append("<html><font color=blue size=15>LET'S START THE GAME PLAY !!!");
    this.sb1.append("</font><br></html>");

    this.labelText = this.sb1.toString();
    this.coloredLabel = new JLabel(labelText, JLabel.CENTER);

    this.welcome.gridx = 0;
    this.welcome.gridy = 1;
    this.welcome.anchor = GridBagConstraints.NORTH;
    this.welcome.weightx = 5.0;
    this.welcome.weighty = 5.0;
    this.welcome.insets = new Insets(40, 10, 10, 10);
    this.welcomePanel.add(coloredLabel, welcome);
    this.add(welcomePanel, BorderLayout.NORTH);

    startButton = new JButton("START");
    startButton.setBackground(new Color(59, 89, 182));
    startButton.setForeground(Color.WHITE);
    startButton.setFocusPainted(false);
    startButton.setFont(new Font("Tahoma", Font.BOLD, 12));
    startButton.setPreferredSize(new Dimension(80, 40));

    this.welcome.gridx = 0;
    this.welcome.gridy = 2;
    this.welcome.anchor = GridBagConstraints.NORTH;
    this.welcome.weightx = 5.0;
    this.welcome.weighty = 5.0;
    this.welcome.insets = new Insets(50, 10, 10, 10);
    this.welcomePanel.add(startButton, welcome);
    this.add(welcomePanel, BorderLayout.NORTH);

    this.sb2 = new StringBuilder();
    this.sb2.append("<html><font color=#00008b size=6>KAUSHIK KOMANDURI<br>");
    this.sb2.append("SANJANA KANDUNOORI</font></html>");
    this.kaushikText = this.sb2.toString();
    this.kaushikLabel = new JLabel(kaushikText);
    this.kaushikLabel.setBorder(new EmptyBorder(10, 30, 40, 10));

    this.add(kaushikLabel, BorderLayout.SOUTH);
  }

  /**
   * This method implements the functionalities for different events so that the
   * listener responds accordingly.
   *
   * @param features the callbacks sent as a feature object.
   */
  public void setFeatures(Features features) {

    if (features == null) {
      throw new IllegalArgumentException("Features object cannot be null\n");
    }
    this.features = features;
    this.startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        features.moveToWorldSelectionScreen();
      }
    });
  }
}
