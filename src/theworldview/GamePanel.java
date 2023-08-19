package theworldview;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
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

/**
 * This panel consists of visual representation of world image and the
 * information of the game including game status, the result of action in the
 * form of text areas in the panel.
 *
 */

public class GamePanel extends JPanel {

  private final ReadOnlyBoardGameModel readOnlyModel;
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
  private JTextArea turnResultArea;
  private String outputMessage;
  private String turnMessage;
  private int playerIteration;
  private List<String> colorList;
  private Features features;
  private List<PlayerImpl> playerList;
  private JScrollPane imagePane;
  private Font font;
  private JScrollPane infoPane;
  private String playerInfo;
  private String targetInfo;
  private JScrollPane turnInfoPane;
  private JScrollPane turnResultPane;
  private Map<Integer, Consumer<PlayerImpl>> playerMap;

  /**
   * This constructor initializes the readOnlyBoardGameModel for getting the
   * functionality, the BoardGameView to display and the information about the
   * current player and the action to display on the panel.
   *
   * @param readOnlyModel the readOnlyModel that has the functionality.
   * @param outputMessage the message of the action performed during the turn.
   * @param turnMessage   the message of the current turn including player
   *                      location and target location.
   */
  public GamePanel(ReadOnlyBoardGameModel readOnlyModel, String outputMessage, String turnMessage) {

    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }

    if (outputMessage == null) {
      throw new IllegalArgumentException("Output message cannot be null");
    }

    if (turnMessage == null) {
      throw new IllegalArgumentException("Turn Message cannot be null");
    }

    this.readOnlyModel = readOnlyModel;
    this.turnMessage = turnMessage;
    this.outputMessage = outputMessage;
    this.playerMap = new HashMap<Integer, Consumer<PlayerImpl>>();

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

    this.imageLabel = new JLabel(new ImageIcon("rep.jpg"));

    this.imageLabel.setLayout(null);

    this.targetLabel = new JLabel(new ImageIcon(new ImageIcon("targetcharacter.jpg").getImage()
        .getScaledInstance(30, 25, java.awt.Image.SCALE_SMOOTH)));

    this.targetLabel.setBounds(
        readOnlyModel.getTargetCharacterImpl().getCurrentRoom().getRoomLocation().get(1) * 60 + 5,
        readOnlyModel.getTargetCharacterImpl().getCurrentRoom().getRoomLocation().get(0) * 30 + 5,
        20, 20);

    this.playerList = new ArrayList<>(readOnlyModel.getPlayerList());
    this.playerIteration = 1;
    this.playerMap.put(Integer.valueOf(1), s -> {
      this.playerLabel1 = getPlayerJlabel(this.playerLabel1, s, "playerIcon1.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel1);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel1);
      }
    });
    this.playerMap.put(Integer.valueOf(2), s -> {
      this.playerLabel2 = getPlayerJlabel(this.playerLabel2, s, "playerIcon2.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel2);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel2);
      }
    });
    this.playerMap.put(Integer.valueOf(3), s -> {
      this.playerLabel3 = getPlayerJlabel(this.playerLabel3, s, "playerIcon3.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel3);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel3);
      }
    });
    this.playerMap.put(Integer.valueOf(4), s -> {
      this.playerLabel4 = getPlayerJlabel(this.playerLabel4, s, "playerIcon4.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel4);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel4);
      }
    });
    this.playerMap.put(Integer.valueOf(5), s -> {
      this.playerLabel5 = getPlayerJlabel(this.playerLabel5, s, "playerIcon5.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel5);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel5);
      }
    });
    this.playerMap.put(Integer.valueOf(6), s -> {
      this.playerLabel6 = getPlayerJlabel(this.playerLabel6, s, "playerIcon6.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel6);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel6);
      }
    });
    this.playerMap.put(Integer.valueOf(7), s -> {
      this.playerLabel7 = getPlayerJlabel(this.playerLabel7, s, "playerIcon7.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel7);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel7);
      }
    });
    this.playerMap.put(Integer.valueOf(8), s -> {
      this.playerLabel8 = getPlayerJlabel(this.playerLabel8, s, "playerIcon8.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel8);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel8);
      }
    });
    this.playerMap.put(Integer.valueOf(9), s -> {
      this.playerLabel9 = getPlayerJlabel(this.playerLabel9, s, "playerIcon9.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel9);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel9);
      }
    });
    this.playerMap.put(Integer.valueOf(10), s -> {
      this.playerLabel10 = getPlayerJlabel(this.playerLabel10, s, "playerIcon10.png",
          this.ifAnotherPlayer(s.getName(), s.getCurrentRoom().getName()));
      this.imageLabel.add(this.playerLabel10);
      if (s.getName().equals(readOnlyModel.getCurrentPlayerTurn())) {
        this.addPlayerListener(this.playerLabel10);
      }
    });

    this.playerList.forEach(s -> {
      this.playerMap.get(playerIteration).accept(s);
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
    if (!"".equals(this.turnMessage)) {
      playerInfo = this.turnMessage.split("Items:")[0];
      targetInfo = this.turnMessage.split("Items:")[1].split(";")[1];

      this.turnInfoArea.setText(
          String.format("CURRENT TURN INFO:\n%s\n%s", playerInfo.replace(";", "\n"), targetInfo));
    }
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

    this.turnResultArea.setPreferredSize(new Dimension(500, 900));
    this.turnResultArea.setMinimumSize(new Dimension(100, 100));
    this.turnResultPane = new JScrollPane(this.turnResultArea);
    this.infoPanel.add(turnResultPane);
    this.add(infoPanel, BorderLayout.EAST);
    this.repaint();
    this.resetFocus();
    this.gamePanel.setFocusable(true);
    this.gamePanel.requestFocus();
    this.infoPanel.setFocusable(true);
    this.infoPanel.requestFocus();
    this.revalidate();
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

    this.features = f;
    MouseListener mouse = new MouseClickEvent(f);
    this.gamePanel.addMouseListener(mouse);
    setFocusable(true);
  }

  /**
   * This method sets the icon of the player added and positions the player in the
   * space.
   *
   * @param playerLabel     the label of the player which needs to be associated
   *                        to an icon and needs to be positioned.
   * @param player          the playerImpl object to the room coordinates the
   *                        player is currently at for positioning.
   * @param iconUrl         the url that consists of the icon image that
   *                        represents the player.
   * @param ifAnotherPlayer this checks if another player is there in the current
   *                        space to adjust the player's positioning without
   *                        overlap.
   * @return the label that positions the player icon and adds it to the label.
   */

  private JLabel getPlayerJlabel(JLabel playerLabel, PlayerImpl player, String iconUrl,
      boolean ifAnotherPlayer) {

    if (player == null || iconUrl == null) {
      throw new IllegalArgumentException("IconUrl and player cannot be null");
    }
    playerLabel = new JLabel(new ImageIcon(new ImageIcon(String.format("%s", iconUrl)).getImage()
        .getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
    if (ifAnotherPlayer) {
      playerLabel.setBounds(player.getCurrentRoom().getRoomLocation().get(3) * 60 + 5,
          player.getCurrentRoom().getRoomLocation().get(2) * 30 + 5, 22, 22);
    } else {
      playerLabel.setBounds(player.getCurrentRoom().getRoomLocation().get(3) * 60 + 30,
          player.getCurrentRoom().getRoomLocation().get(2) * 30 + 5, 22, 22);
    }
    return playerLabel;
  }

  /**
   * This method checks if there is another player in the space of the current
   * player.
   *
   * @param playerName the current name of the player playing the game.
   * @param spaceName  the space that the current player is at.
   * @return the boolean true if there is another player else returns false.
   */

  private boolean ifAnotherPlayer(String playerName, String spaceName) {

    if (playerName == null || spaceName == null) {
      throw new IllegalArgumentException("Player name and the space name passed cannot be null");
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

  /**
   * This method displays the indexing of the player i.e., this provides user with
   * the information of which colored icon is what player.
   *
   * @return the text information of the player indexing to be displayed on the
   *         text area in the panel
   */

  private String setPlayerIndexText() {
    StringBuilder sb = new StringBuilder();
    sb.append("PLAYER INDEX: \n");
    sb.append("T -> ");
    sb.append(readOnlyModel.getTargetCharacterImpl().getName());
    sb.append(" (Target Character)");
    sb.append("\n");
    for (int i = 0; i < this.readOnlyModel.getPlayerList().size(); i++) {
      if (this.readOnlyModel.getPlayerList().get(i).isComputerPlayer()) {
        sb.append(this.readOnlyModel.getPlayerList().get(i).getName());
        sb.append(" - (COMPUTER) -> ");
        sb.append(this.colorList.get(i));
        sb.append("\n");
      } else {
        sb.append(this.readOnlyModel.getPlayerList().get(i).getName());
        sb.append(" -> ");
        sb.append(this.colorList.get(i));
        sb.append("\n");
      }
    }
    return sb.toString();
  }

  /**
   * This method handles the Mouse CLick events for moving the player and
   * displaying player information when clicked on the player graphical
   * representation using a set of call backs from features interface.
   *
   * @param label the JLabel to which the listener should listen to for mouse
   *              clicks.
   */

  public void addPlayerListener(JLabel label) {

    if (label == null) {
      throw new IllegalArgumentException("Label cannot be null\n");
    }

    label.removeMouseListener((MouseListener) new MouseAdapter() {
      public void mouseClicked(MouseEvent me) {
        features.handleGetPlayerInfo(readOnlyModel.getCurrentPlayerTurn());
      }
    });
    label.addMouseListener((MouseListener) new MouseAdapter() {
      public void mouseClicked(MouseEvent me) {
        features.handleGetPlayerInfo(readOnlyModel.getCurrentPlayerTurn());
      }
    });
  }

  /**
   * This sets the focus of the panel for the user.
   */
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }
}
