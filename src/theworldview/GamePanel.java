package theworldview;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import theworld.ReadOnlyBoardGameModel;

public class GamePanel extends JPanel implements KeyListener {

  private ReadOnlyBoardGameModel readOnlyModel;
  private BoardGameView view;
  private JLabel imageLabel;
  private JLabel playerLabel;
  private JLabel targetLabel;
  private JPanel infoPanel;
  private JPanel gamePanel;
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

//    this.setLayout(null);
    this.gamePanel = new JPanel();
    this.game = new GridBagConstraints();

    //this.player1 = new GridBagConstraints();
    //this.target = new GridBagConstraints();
    this.imageLabel = new JLabel(new ImageIcon("res/rep.jpg"));
    
    
    this.imageLabel.setLayout(null);
  
    this.targetLabel = new JLabel(new ImageIcon(new ImageIcon("res/targetcharacter.jpg").getImage()
        .getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH)));
    
    this.targetLabel.setBounds(100, 100, 20, 20);
    
//    this.targetLabel.setBounds(readOnlyModel.getTargetCharacterImpl().getCurrentRoom().getRoomLocation().get(1) * 60 + 5, 
//        readOnlyModel.getTargetCharacterImpl().getCurrentRoom().getRoomLocation().get(0) * 30 + 5, 10, 10);

    
    this.playerLabel = new JLabel(new ImageIcon(new ImageIcon("res/playericon.png").getImage()
        .getScaledInstance(20, 25, java.awt.Image.SCALE_SMOOTH)));
    
    this.playerLabel.setBounds(200, 200, 20, 20);
//    this.playerLabel.setBounds(readOnlyModel.getPlayerList().get(0).getCurrentRoom().getRoomLocation().get(1) * 60 + 5, 
//        readOnlyModel.getPlayerList().get(0).getCurrentRoom().getRoomLocation().get(0) * 30 + 5, 2, 2);

//    this.player1.gridx = 0;
//    this.player1.gridy = 0;
//    this.player1.weightx = 2;
//    this.player1.weighty = 2;
//    this.player1.fill = GridBagConstraints.BOTH;
//    this.player1.insets = new Insets(80, 60, 140, 80);
//
//    this.target.gridx = 0;
//    this.target.gridy = 0;
//    this.target.weightx = 1;
//    this.target.weighty = 1;
//    this.target.insets = new Insets(11, 14, 24, 15);
//
//    this.setBackground(new Color(137, 207, 240));
//
//    this.game.gridx = 0;
//    this.game.gridy = 0;
//    this.game.weightx = 1;
//    this.game.weighty = 1;
//    this.game.fill = GridBagConstraints.BOTH;
//    this.game.anchor = GridBagConstraints.NORTHWEST;
//    this.game.insets = new Insets(10, 10, 10, 10);

    this.imageLabel.add(this.targetLabel);
    this.imageLabel.add(this.playerLabel);
    this.gamePanel.add(this.imageLabel);
    this.add(gamePanel);

    this.infoPanel = new JPanel(new GridLayout(3, 0, 10, 10));
    this.playersArea = new JTextArea();

    this.playersArea.setLineWrap(true);
    this.playersArea.setText("PLAYERS INDEX:");

    // Sets JTextArea font and color.
    Font font = new Font("Segoe Script", Font.BOLD, 20);
    this.playersArea.setFont(font);
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
    String playerInfo = this.turnMessage.split("Items:")[0];
    String targetInfo = this.turnMessage.split("Items:")[1].split(";")[1];

    this.turnInfoArea
        .setText(String.format("TURN INFO:\n\n%s\n%s", playerInfo.replace(";", "\n"), targetInfo));
    this.turnInfoArea.setFont(font);
    this.turnInfoArea.setDisabledTextColor(Color.BLUE);
    this.turnInfoArea.setEnabled(false);
    this.turnInfoArea.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    this.infoPanel.add(turnInfoArea);

    this.turnResultArea = new JTextArea();
    this.turnResultArea.setText(String.format("TURN RESULT:\n\n %s", this.outputMessage));
    this.turnResultArea.setFont(font);
    this.turnResultArea.setEnabled(false);
    this.turnResultArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.turnResultArea.setBackground(new Color(37, 190, 175));
    this.infoPanel.add(turnResultArea);

    // this.add(gamePanel, game);
    this.add(infoPanel, game);
    this.addKeyListener(this);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

}
