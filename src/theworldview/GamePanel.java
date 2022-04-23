package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.Features;
import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;

import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener {

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
  private List<String> colorList;
  private Features features;

  public GamePanel(ReadOnlyBoardGameModel readOnlyModel, BoardGameView view, String outputMessage,
      String turnMessage, Features features) {

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
    this.features = features;
    
    
    this.colorList = new ArrayList<>();
    this.colorList.add("Dark Blue");
    this.colorList.add("Green");
    this.colorList.add("Yellow");
    this.colorList.add("Orange");
    this.colorList.add("Red");
    this.colorList.add("Black");
    this.colorList.add("Purple");
    this.colorList.add("Pink");
    this.colorList.add("Indigo");
    this.colorList.add("Light Blue");

    this.setLayout(new BorderLayout(20, 15));
    //this.setBackground(new Color(137, 207, 240));
    
    this.gamePanel = new JPanel();
    this.gamePanel.setBackground(new Color(137, 207, 240));

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
        this.playerLabel1 = getPlayerJLabel(s, "playerIcon1.png");
        this.imageLabel.add(this.playerLabel1);
        this.addPlayerListener(this.playerLabel1, this.features);
      } else if (playerIteration == 2) {
        this.playerLabel2 = getPlayerJLabel(s, "playerIcon2.png");
        this.imageLabel.add(this.playerLabel2);
        this.addPlayerListener(this.playerLabel2, this.features);
      } else if (playerIteration == 3) {
        this.playerLabel3 = getPlayerJLabel(s, "playerIcon3.png");
        this.imageLabel.add(this.playerLabel3);
        this.addPlayerListener(this.playerLabel3, this.features);
      } else if (playerIteration == 4) {
        this.playerLabel4 = getPlayerJLabel(s, "playerIcon4.png");
        this.imageLabel.add(this.playerLabel4);
        this.addPlayerListener(this.playerLabel4, this.features);
      } else if (playerIteration == 5) {
        this.playerLabel5 = getPlayerJLabel(s, "playerIcon5.png");
        this.imageLabel.add(this.playerLabel5);
        this.addPlayerListener(this.playerLabel5, this.features);
      } else if (playerIteration == 6) {
        this.playerLabel6 = getPlayerJLabel(s, "playerIcon6.png");
        this.imageLabel.add(this.playerLabel6);
        this.addPlayerListener(this.playerLabel6, this.features);
      } else if (playerIteration == 7) {
        this.playerLabel7 = getPlayerJLabel(s, "playerIcon7.png");
        this.imageLabel.add(this.playerLabel7);
        this.addPlayerListener(this.playerLabel7, this.features);
      } else if (playerIteration == 8) {
        this.playerLabel8 = getPlayerJLabel(s, "playerIcon8.png");
        this.imageLabel.add(this.playerLabel8);
        this.addPlayerListener(this.playerLabel8, this.features);
      } else if (playerIteration == 9) {
        this.playerLabel9 = getPlayerJLabel(s, "playerIcon9.png");
        this.imageLabel.add(this.playerLabel9);
        this.addPlayerListener(this.playerLabel9, this.features);
      } else if (playerIteration == 10) {
        this.playerLabel10 = getPlayerJLabel(s, "playerIcon10.png");
        this.imageLabel.add(this.playerLabel10);
        this.addPlayerListener(this.playerLabel10, this.features);
      }
      playerIteration++;
    });
    this.playerLabel1.addMouseListener(this);
    this.imageLabel.add(this.targetLabel);

    this.gamePanel.add(this.imageLabel);
   // this.gamePanel.setMinimumSize(getMinimumSize())
    JScrollPane imagePane = new JScrollPane(this.gamePanel);
    this.add(imagePane, BorderLayout.CENTER);

    this.infoPanel = new JPanel();
    this.infoPanel.setLayout(new BoxLayout(this.infoPanel, BoxLayout.Y_AXIS));
    this.infoPanel.setMaximumSize(new Dimension(500, 500));
    
    this.playersArea = new JTextArea();
    this.playersArea.setLineWrap(true);
    this.playersArea.setWrapStyleWord(true);
    
    this.playersArea.setText(this.setPlayerIndexText());

    Font font = new Font("Segoe Script", Font.BOLD, 30);
    this.playersArea.setFont(font);
    this.playersArea.setForeground(Color.WHITE);
    this.playersArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.playersArea.setBackground(new Color(37, 190, 175));
//    this.playersArea.setMaximumSize(new Dimension(50, 50));
    this.playersArea.setMinimumSize(new Dimension(100, 100));
    this.playersArea.setPreferredSize(new Dimension(500, 500));

//    this.game.gridx = 1;
//    this.game.gridy = 0;
//    this.game.weightx = 3.0;
//    this.game.weighty = 3.0;
//    this.game.anchor = GridBagConstraints.NORTHEAST;
//    this.game.insets = new Insets(20, 5, 10, 10);
    
    JScrollPane infoPane = new JScrollPane(this.playersArea);
    this.infoPanel.add(infoPane);    

    this.turnInfoArea = new JTextArea();
    String playerInfo = this.turnMessage.split("Items:")[0];
    String targetInfo = this.turnMessage.split("Items:")[1].split(";")[1];

    this.turnInfoArea.setText(
        String.format("CURRENT TURN INFO:\n%s\n%s", playerInfo.replace(";", "\n"), targetInfo));
    this.turnInfoArea.setFont(font);
    this.turnInfoArea.setLineWrap(true);
    this.turnInfoArea.setWrapStyleWord(true);
    //this.turnInfoArea.setDisabledTextColor(Color.BLUE);
    //this.turnInfoArea.setEnabled(false);
    this.turnInfoArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
    this.turnInfoArea.setPreferredSize(new Dimension(500, 500));
    
    //this.turnInfoArea.setMaximumSize(new Dimension(50, 50));
    this.turnInfoArea.setMinimumSize(new Dimension(100, 100));
    
    JScrollPane turnInfoPane = new JScrollPane(this.turnInfoArea);
    this.infoPanel.add(turnInfoPane);

    this.turnResultArea = new JTextArea();
    this.turnResultArea.setText(String.format("PREVIOUS TURN RESULT:\n %s", this.outputMessage));
    this.turnResultArea.setFont(font);
    this.turnResultArea.setLineWrap(true);
    this.turnResultArea.setWrapStyleWord(true);
    this.turnResultArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.turnResultArea.setBackground(new Color(37, 190, 175));
    this.turnResultArea.setForeground(Color.WHITE);
    
    this.turnResultArea.setPreferredSize(new Dimension(500, 500));
    //this.turnResultArea.setMaximumSize(new Dimension(50, 50));
    this.turnResultArea.setMinimumSize(new Dimension(100, 100));
    JScrollPane turnResultPane = new JScrollPane(this.turnResultArea);
    this.infoPanel.add(turnResultPane);

    this.add(infoPanel, BorderLayout.EAST);
    this.repaint();
  }

  public void setFeatures(Features f) {
    MouseListener mouse = new MouseClickEvent(f, view);
    this.gamePanel.addMouseListener(mouse);
    setFocusable(true);
  }

  public JLabel getPlayerJLabel(PlayerImpl player, String iconUrl) {
    JLabel playerLabel = new JLabel(new ImageIcon(new ImageIcon(String.format("res/%s", iconUrl))
        .getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
    playerLabel.setBounds(player.getCurrentRoom().getRoomLocation().get(3) * 60 + 5,
        player.getCurrentRoom().getRoomLocation().get(2) * 30 + 5, 22, 22);

    return playerLabel;
  }
  
  public String setPlayerIndexText() {
    
   StringBuilder sb = new StringBuilder();
   
   sb.append("PLAYER INDEX: \n");
   
   for (int i = 0; i < this.readOnlyModel.getPlayerList().size(); i++) {
     sb.append(this.readOnlyModel.getPlayerList().get(i).getName());
     sb.append(" -> ");
     sb.append(this.colorList.get(i));
     sb.append("\n");
   }
   
   return sb.toString();
    
  }
  
  public void addPlayerListener(JLabel label, Features f) {
      label.addMouseListener((MouseListener) new MouseAdapter() {
      public void mouseClicked(MouseEvent me) {
      f.handlePlayerMouseClickEvent(readOnlyModel.getCurrentPlayerTurn());    
    }});
  }

  public void resetFocus() {
    this.setFocusable(true);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Component c = e.getComponent();
    System.out.println("mocuse clikc");

  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    Component c = e.getComponent();
    System.out.println("mocuse press");
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }
}
