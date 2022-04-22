package theworldview;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.Features;
import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;

import java.util.List;

public class GamePanel extends JPanel {

  private ReadOnlyBoardGameModel readOnlyModel;
  private BoardGameView view;
  private JLabel imageLabel;
  private JLabel playerLabel1;
  private JLabel playerLabel2;
  private JLabel playerLabel3;
  private JLabel playerLabel4;
  private JLabel playerLabel5;
  private JLabel playerLabel6;
  private JLabel playerLabel7;
  private JLabel playerLabel8;
  private JLabel playerLabel9;
  private JLabel playerLabel10;
  private JLabel targetLabel;
  private JPanel infoPanel;
  private JPanel gamePanel;
  private JTextArea playersArea;
  private JTextArea turnInfoArea;
  private GridBagConstraints game;
  private JTextArea turnResultArea;
  private String outputMessage;
  private String turnMessage;
  private int playerIteration = 1;

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

    this.gamePanel = new JPanel();
    this.game = new GridBagConstraints();

    this.imageLabel = new JLabel(new ImageIcon("res/rep.jpg"));

    this.imageLabel.setLayout(null);

    this.targetLabel = new JLabel(new ImageIcon(new ImageIcon("res/targetcharacter.jpg").getImage()
        .getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH)));

    this.targetLabel.setBounds(
        readOnlyModel.getTargetCharacterImpl().getCurrentRoom().getRoomLocation().get(1) * 60 + 5,
        readOnlyModel.getTargetCharacterImpl().getCurrentRoom().getRoomLocation().get(0) * 30 + 5,
        20, 20);

    List<PlayerImpl> playerList = readOnlyModel.getPlayerList();

    playerList.forEach(s -> {
      if (playerIteration == 1) {
        this.playerLabel1 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel1);
      } else if (playerIteration == 2) {
        this.playerLabel2 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel2);
      } else if (playerIteration == 3) {
        this.playerLabel3 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel3);
      } else if (playerIteration == 4) {
        this.playerLabel4 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel4);
      } else if (playerIteration == 5) {
        this.playerLabel5 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel5);
      } else if (playerIteration == 6) {
        this.playerLabel6 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel6);
      } else if (playerIteration == 7) {
        this.playerLabel7 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel7);
      } else if (playerIteration == 8) {
        this.playerLabel8 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel8);
      } else if (playerIteration == 9) {
        this.playerLabel9 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel9);
      } else if (playerIteration == 10) {
        this.playerLabel10 = getPlayerJLabel(s, "playericon.png");
        this.imageLabel.add(this.playerLabel10);
      }
      playerIteration++;
    });

    this.imageLabel.add(this.targetLabel);

    this.gamePanel.add(this.imageLabel);
    this.add(gamePanel);

    this.infoPanel = new JPanel(new GridLayout(3, 0, 10, 10));
    this.playersArea = new JTextArea();

    this.playersArea.setLineWrap(true);
    this.playersArea.setText("PLAYERS INDEX:");

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
    this.infoPanel.add(turnResultArea, game);

    this.add(infoPanel, game);
  }

  public void setFeatures(Features f) {
    MouseListener mouse = new MouseClickEvent(f, view);
    this.gamePanel.addMouseListener(mouse);
    setFocusable(true);
  }
  
  public JLabel getPlayerJLabel(PlayerImpl player, String iconUrl) {
    JLabel playerLabel = new JLabel(new ImageIcon(new ImageIcon(String.format("res/%s", iconUrl))
        .getImage().getScaledInstance(20, 25, java.awt.Image.SCALE_SMOOTH)));
    playerLabel.setBounds(player.getCurrentRoom().getRoomLocation().get(3) * 60 + 5,
        player.getCurrentRoom().getRoomLocation().get(2) * 30 + 5, 22, 22);
    return playerLabel;
  }

//  public void showPickDialog() {
//    
//    PlayerImpl player = this.readOnlyModel.getPlayerList().stream()
//        .filter(p -> p.getName().trim().equals(this.readOnlyModel.getCurrentPlayerTurn().trim())).collect(Collectors.toList()).get(0);
//    System.out.println(player);
//    
//    String[] itemList = player.getCurrentRoom().getItems().stream().map(ItemImpl::getName)
//        .collect(Collectors.toList()).toArray(new String[0]);
//    
//    System.out.print(itemList);
//    
//    JComboBox items = new JComboBox(itemList);
//    items.setPreferredSize(new Dimension(200, 30));
//    
//    UIManager.put("OptionPane.okButtonText", "Pick"); 
//    JOptionPane.showMessageDialog(null, items);
//    
//  }
  
  public void resetFocus() {
    this.setFocusable(true);
  }
}
