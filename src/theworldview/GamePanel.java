package theworldview;

import java.awt.Color;
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
  private BoardGameView view;
  private JLabel imageLabel;
  private JLabel playerLabel;
  private JLabel targetLabel;
  private JPanel infoPanel;
  private JTextArea playersArea;
  private JTextArea turnInfoArea;
  private GridBagConstraints game;
  private GridBagConstraints player1;
  private GridBagConstraints target;
  private JTextArea turnResultArea;
  private String outputMessage;
  private String turnMessage;

  public GamePanel(ReadOnlyBoardGameModel readOnlyModel, BoardGameView view, String outputMessage,
      String turnMessage) {

    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }

    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.\n");
    }

    this.readOnlyModel = readOnlyModel;
    this.view = view;
    this.turnMessage = turnMessage;
    this.outputMessage = outputMessage;

    this.setLayout(new GridBagLayout());
    this.game = new GridBagConstraints();

    this.player1 = new GridBagConstraints();
    this.target = new GridBagConstraints();
    this.imageLabel = new JLabel(new ImageIcon("res/rep.jpg"));

    this.targetLabel = new JLabel(new ImageIcon(new ImageIcon("res/targetcharacter.jpg").getImage()
        .getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH)));

    this.playerLabel = new JLabel(new ImageIcon(new ImageIcon("res/playericon.png").getImage()
        .getScaledInstance(20, 25, java.awt.Image.SCALE_SMOOTH)));

    this.player1.weightx = 0.1;
    this.player1.weighty = 0.1;
    this.player1.insets = new Insets(50, 80, 20, 6);

    this.target.weightx = 0.1;
    this.target.weighty = 0.1;
    this.target.insets = new Insets(0, 80, 50, 6);

    this.setBackground(new Color(137, 207, 240));

    this.game.gridx = 0;
    this.game.gridy = 0;
    this.game.weightx = 0.1;
    this.game.weighty = 0.1;
    this.game.fill = GridBagConstraints.BOTH;
    this.game.anchor = GridBagConstraints.NORTHWEST;
    this.game.insets = new Insets(10, 10, 10, 10);

    this.add(this.targetLabel, this.target);
    this.add(this.playerLabel, this.player1);
    this.add(this.imageLabel, game);

    this.infoPanel = new JPanel(new GridLayout(3, 0, 10, 10));
    this.playersArea = new JTextArea();
    this.playersArea.setEnabled(false);
    this.playersArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.playersArea.setBackground(new Color(37, 190, 175));

    this.game.gridx = 1;
    this.game.gridy = 0;
    this.game.weightx = 3.0;
    this.game.weighty = 3.0;
    this.game.anchor = GridBagConstraints.NORTHEAST;
    this.game.insets = new Insets(20, 5, 10, 10);
    this.infoPanel.add(playersArea);

    this.turnInfoArea = new JTextArea();
    this.turnInfoArea.setText(this.turnMessage);
    this.turnInfoArea.setDisabledTextColor(Color.BLUE);
    this.turnInfoArea.setEnabled(false);
    this.turnInfoArea.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    this.infoPanel.add(turnInfoArea);

    this.turnResultArea = new JTextArea();
    this.turnResultArea.setText(this.outputMessage);
    this.turnResultArea.setEnabled(false);
    this.turnResultArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.turnResultArea.setBackground(new Color(37, 190, 175));
    this.infoPanel.add(turnResultArea);

    this.add(infoPanel, game);
  }

}
