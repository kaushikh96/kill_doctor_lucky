package theworldview;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;



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
  private List<String> colorList;
  private Features features;
  private List<PlayerImpl> playerList;
  private JScrollPane imagePane;
  private Font font;
  private  JScrollPane infoPane;
  private String playerInfo;
  private String targetInfo;
  private JScrollPane turnInfoPane;
  private JScrollPane turnResultPane;

  /**
   * This constructor initializes the readOnlyBoardGameModel for getting the functionality, the 
   * BoardGameView to display and the information about the current player and the action to display
   * on the panel.
   *
   * @param readOnlyModel the readOnlyModel that has the functionality.
   * @param view the view that holds the panel and displays the panel to the user.
   * @param outputMessage the message of the action performed during the turn.
   * @param turnMessage the message of the current turn including player location 
   and target location.
   * @param features the features interface that responds to the actions done on the view.
   */
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

    this.gamePanel = new JPanel();
    this.gamePanel.setBackground(new Color(137, 207, 240));
    //this.gamePanel.revalidate();

    this.imageLabel = new JLabel(new ImageIcon("res/rep.jpg"));

    this.imageLabel.setLayout(null);

    this.targetLabel = new JLabel(new ImageIcon(new ImageIcon("res/targetcharacter.jpg").getImage()
        .getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH)));

    this.targetLabel.setBounds(
        readOnlyModel.getTargetCharacterImpl().getCurrentRoom().getRoomLocation().get(1) * 60 + 5,
        readOnlyModel.getTargetCharacterImpl().getCurrentRoom().getRoomLocation().get(0) * 30 + 5,
        20, 20);

    this.playerList = new ArrayList<>(readOnlyModel.getPlayerList());

    this.playerList.forEach(s -> {
      if (playerIteration == 1) {
        this.playerLabel1 = getPlayerJLabel(s, "playerIcon1.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel1);
        this.addPlayerListener(this.playerLabel1, this.features, 0);
      } else if (playerIteration == 2) {
        this.playerLabel2 = getPlayerJLabel(s, "playerIcon2.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel2);
        this.addPlayerListener(this.playerLabel2, this.features, 1);
      } else if (playerIteration == 3) {
        this.playerLabel3 = getPlayerJLabel(s, "playerIcon3.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel3);
        this.addPlayerListener(this.playerLabel3, this.features, 2);
      } else if (playerIteration == 4) {
        this.playerLabel4 = getPlayerJLabel(s, "playerIcon4.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel4);
        this.addPlayerListener(this.playerLabel4, this.features, 3);
      } else if (playerIteration == 5) {
        this.playerLabel5 = getPlayerJLabel(s, "playerIcon5.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel5);
        this.addPlayerListener(this.playerLabel5, this.features, 4);
      } else if (playerIteration == 6) {
        this.playerLabel6 = getPlayerJLabel(s, "playerIcon6.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel6);
        this.addPlayerListener(this.playerLabel6, this.features, 5);
      } else if (playerIteration == 7) {
        this.playerLabel7 = getPlayerJLabel(s, "playerIcon7.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel7);
        this.addPlayerListener(this.playerLabel7, this.features, 6);
      } else if (playerIteration == 8) {
        this.playerLabel8 = getPlayerJLabel(s, "playerIcon8.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel8);
        this.addPlayerListener(this.playerLabel8, this.features, 7);
      } else if (playerIteration == 9) {
        this.playerLabel9 = getPlayerJLabel(s, "playerIcon9.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel9);
        this.addPlayerListener(this.playerLabel9, this.features, 8);
      } else if (playerIteration == 10) {
        this.playerLabel10 = getPlayerJLabel(s, "playerIcon10.png",
            this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
        this.imageLabel.add(this.playerLabel10);
        this.addPlayerListener(this.playerLabel10, this.features, 9);
      }
      playerIteration++;
    });
    this.imageLabel.add(this.targetLabel);

    this.gamePanel.add(this.imageLabel);
    this.imagePane = new JScrollPane(this.gamePanel);
    this.add(imagePane, BorderLayout.CENTER);

    this.infoPanel = new JPanel();
    this.infoPanel.setLayout(new BoxLayout(this.infoPanel, BoxLayout.Y_AXIS));
    this.infoPanel.setMaximumSize(new Dimension(500, 500));
    this.infoPanel.setEnabled(false);

    this.playersArea = new JTextArea();
    this.playersArea.setLineWrap(true);
    this.playersArea.setWrapStyleWord(true);
    this.playersArea.setEnabled(false);

    this.playersArea.setText("PLAYERS INDEX:");
    this.playersArea.setText(this.setPlayerIndexText());

    this.font = new Font("Segoe Script", Font.BOLD, 20);
    this.playersArea.setFont(font);
    this.playersArea.setForeground(Color.WHITE);
    this.playersArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.playersArea.setBackground(new Color(37, 190, 175));
    this.playersArea.setMinimumSize(new Dimension(100, 100));
    this.playersArea.setPreferredSize(new Dimension(500, 500));

    this.infoPane = new JScrollPane(this.playersArea);
    this.infoPanel.add(infoPane);

    this.turnInfoArea = new JTextArea();
    this.playerInfo = this.turnMessage.split("Items:")[0];
    this.targetInfo = this.turnMessage.split("Items:")[1].split(";")[1];

    this.turnInfoArea.setText(
        String.format("CURRENT TURN INFO:\n%s\n%s", playerInfo.replace(";", "\n"), targetInfo));
    this.turnInfoArea.setFont(font);
    this.turnInfoArea.setLineWrap(true);
    this.turnInfoArea.setWrapStyleWord(true);
    this.turnInfoArea.setEnabled(false);
    this.turnInfoArea.setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));
    this.turnInfoArea.setPreferredSize(new Dimension(500, 500));

    this.turnInfoArea.setMinimumSize(new Dimension(100, 100));

    this.turnInfoPane = new JScrollPane(this.turnInfoArea);
    this.infoPanel.add(turnInfoPane);

    this.turnResultArea = new JTextArea();
    this.turnResultArea.setText(String.format("RESULT OF THE ACTION:\n %s", this.outputMessage));
    this.turnResultArea.setFont(font);
    this.turnResultArea.setLineWrap(true);
    this.turnResultArea.setWrapStyleWord(true);
    this.turnResultArea.setEnabled(false);
    this.turnResultArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.turnResultArea.setBackground(new Color(37, 190, 175));

    this.turnResultArea.setForeground(Color.WHITE);

    this.turnResultArea.setPreferredSize(new Dimension(500, 500));
    this.turnResultArea.setMinimumSize(new Dimension(100, 100));
    this.turnResultPane = new JScrollPane(this.turnResultArea);
    this.infoPanel.add(turnResultPane);
    //this.infoPanel.revalidate();
    this.add(infoPanel, BorderLayout.EAST);
    this.repaint();
    // this.revalidate();
  }

  /**
   * This method handles the listeners to events registered from the view.
   *
   * @param f the features interface to handle the functionality of the event.
   */
  public void setFeatures(Features f) {
    
    if (f == null) {
      throw new IllegalArgumentException("Features passed cannot be null.\n");
    }
    MouseListener mouse = new MouseClickEvent(f, view);
    this.gamePanel.addMouseListener(mouse);
    setFocusable(true);
  }
  
  /**
   * This method sets the icon of the player added and positions the player in the space.
   *
   * @param player the 
   * @param iconUrl
   * @param ifAnotherPlayer
   * @return
   */

  private JLabel getPlayerJLabel(PlayerImpl player, String iconUrl, boolean ifAnotherPlayer) {
    
    if (player == null || iconUrl == null) {
      throw new IllegalArgumentException("IconUrl and player cannot be null");
    }
    JLabel playerLabel = new JLabel(new ImageIcon(new ImageIcon(String.format("res/%s", iconUrl))
        .getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
    if (ifAnotherPlayer) {
      playerLabel.setBounds(player.getCurrentRoom().getRoomLocation().get(3) * 60 + 5,
          player.getCurrentRoom().getRoomLocation().get(2) * 30 + 5, 22, 22);
    } else {
      playerLabel.setBounds(player.getCurrentRoom().getRoomLocation().get(3) * 60 + 30,
          player.getCurrentRoom().getRoomLocation().get(2) * 30 + 5, 22, 22);
    }
    return playerLabel;
  }

  private boolean ifAnotherPlayer(String playerName, String spaceName) {
    if (spaceName == null || playerName == null) {
      throw new IllegalArgumentException("Player name cannot be null");
    }
    List<PlayerImpl> sameRoomPlayers = readOnlyModel.getPlayerList().stream()
        .filter(s -> s.getCurrentRoom().getName().equalsIgnoreCase(spaceName))
        .collect(Collectors.toList());
    if (sameRoomPlayers.size() == 1) {
      return false;
    } else {
      if (playerName.equalsIgnoreCase(sameRoomPlayers.get(0).getName())) {
        return false;
      } else {
        return true;
      }
    }
  }

  public String setPlayerIndexText() {
    StringBuilder sb = new StringBuilder();

    sb.append("PLAYER INDEX: \n");
    sb.append("T -> ");
    sb.append(readOnlyModel.getTargetCharacterImpl().getName());
    sb.append(" (Target Character)");
    sb.append("\n");
    for (int i = 0; i < this.readOnlyModel.getPlayerList().size(); i++) {
      sb.append(this.readOnlyModel.getPlayerList().get(i).getName());
      sb.append(" -> ");
      sb.append(this.colorList.get(i));
      sb.append("\n");
    }

    return sb.toString();
  }

  public void addPlayerListener(JLabel label, Features f, int index) {
    label.addMouseListener((MouseListener) new MouseAdapter() {
      public void mouseClicked(MouseEvent me) {
        f.handlePlayerMouseClickEvent(readOnlyModel.getPlayerList().get(index).getName());
      }
    });
  }

  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
