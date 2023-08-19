package theworldview;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import theworld.ItemImpl;
import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;
import theworld.SpaceImpl;

/**
 * This class is the view implementation which gives the user a visual
 * representation of the board and listens to events and performs callbacks
 * using features interface.
 */
public class BoardGameViewImpl extends JFrame implements BoardGameView {
  private final ReadOnlyBoardGameModel readOnlyModel;
  private WelcomePanel welcomePanel;
  private AddPlayerPanel addPlayerPanel;
  private GamePanel gamePanel;
  private WorldSelectionPanel worldSelectionPanel;
  private Features features;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem currentWorldItem;
  private JMenuItem newWorldItem;
  private JComboBox items;
  private JMenuItem quit;
  private String outputMessage;
  private String turnMessage;
  private boolean ifTurnsExecuted;
  private String computerPlayerTurnMessage;

  /**
   * Constructor that initializes the readOnlyBoardGameModel to get the
   * functionality to handle an event.
   *
   * @param caption the caption for the view
   * @param model   the ReadOnlyBoardGameModel to perform a callabck functionality
   *                when an event is triggered.
   */
  public BoardGameViewImpl(String caption, ReadOnlyBoardGameModel model) {
    super(caption);
    setSize(600, 600);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.readOnlyModel = model;
    this.setLayout(new BorderLayout());
    this.welcomePanel = new WelcomePanel(model);
    this.add(welcomePanel, BorderLayout.CENTER);
    this.worldSelectionPanel = new WorldSelectionPanel(model);
    this.addPlayerPanel = new AddPlayerPanel(model);
    this.outputMessage = "Game Starts !! Execute the first turn !";
    this.computerPlayerTurnMessage = "";

    this.menuBar = new JMenuBar();

    this.menu = new JMenu("Menu");

    this.currentWorldItem = new JMenuItem("Current World");
    this.newWorldItem = new JMenuItem("New World");
    this.quit = new JMenuItem("Quit");

    this.menu.add(this.currentWorldItem);
    this.menu.add(this.newWorldItem);
    this.menu.add(this.quit);

    this.menuBar.add(this.menu);
    this.add(menuBar, BorderLayout.NORTH);
    this.currentWorldItem.setEnabled(false);
    this.newWorldItem.setEnabled(false);
    this.ifTurnsExecuted = true;
    pack();
    setVisible(true);
  }

  @Override
  public void displayWorldSelectionScreen() {
    this.remove(welcomePanel);
    this.currentWorldItem.setEnabled(true);
    this.newWorldItem.setEnabled(true);
    this.add(worldSelectionPanel, BorderLayout.CENTER);
    worldSelectionPanel.revalidate();
  }

  @Override
  public void displayAddPlayerScreen() {
    this.remove(worldSelectionPanel);
    this.currentWorldItem.setEnabled(false);
    this.newWorldItem.setEnabled(false);
    this.addPlayerPanel = new AddPlayerPanel(this.readOnlyModel);
    this.addPlayerPanel.setFeatures(features);
    this.add(addPlayerPanel, BorderLayout.CENTER);
    addPlayerPanel.revalidate();
  }

  @Override
  public void displayGameScreen() {
    this.remove(addPlayerPanel);
    this.currentWorldItem.setEnabled(false);
    this.newWorldItem.setEnabled(false);
    if (this.outputMessage.contains("Wins")) {
      this.showGameEndPopUp();
    } else {
      if (this.ifTurnsExecuted) {
        if (this.gamePanel != null) {
          this.remove(this.gamePanel);
        }
        features.getTurns(readOnlyModel.getCurrentPlayerTurn());
        String turnmessage = this.turnMessage.split("PlayerType:")[1].trim().substring(0, 4);
        if ("true".equalsIgnoreCase(turnmessage)) {
          features.playComputerPlayer(readOnlyModel.getCurrentPlayerTurn());
          this.outputMessage = String.format("%s\n\n%s", this.outputMessage,
              this.computerPlayerTurnMessage);
          features.getTurns(readOnlyModel.getCurrentPlayerTurn());
        }
      }
    }
    this.gamePanel = new GamePanel(this.readOnlyModel, this.outputMessage, this.turnMessage);
    this.gamePanel.setFeatures(features);
    this.setFocusable(true);
    this.requestFocus();
    this.add(gamePanel, BorderLayout.CENTER);
    gamePanel.revalidate();
    if (readOnlyModel.getTurns() == -1) {
      this.outputMessage = String.format("Turns Exhausted ! Target Character Escapes !!");
      this.showGameEndPopUp();
    }
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }

  @Override
  public void closeWindow() {
    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }

  @Override
  public void setFeatures(Features features) {

    if (features == null) {
      throw new IllegalArgumentException("Features object cannot be null\n");
    }
    this.features = features;
    this.welcomePanel.setFeatures(features);
    this.worldSelectionPanel.setFeatures(features);
    this.addPlayerPanel.setFeatures(features);
    //this.gamePanel.setFeatures(features);
    this.currentWorldItem.addActionListener(l -> features.moveToAddPlayerScreen());
    this.newWorldItem.addActionListener(l -> {
      String fileMessage = showFileUploadDialog();
      if (!fileMessage.contains("Invalid")) {
        features.moveToAddPlayerScreen();
      }
    });
    this.quit.addActionListener(l -> closeWindow());

    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();
    
    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyReleasedMap(keyReleases);

    keyPresses.put(KeyEvent.VK_P, () -> {
      String itemName = this.showPickDialog();
      features.handleKeyPressEvent("PickItem", readOnlyModel.getCurrentPlayerTurn(), itemName);
    });

    keyPresses.put(KeyEvent.VK_L, () -> {
      features.handleKeyPressEvent("LookAround", readOnlyModel.getCurrentPlayerTurn(), "");
    });

    keyPresses.put(KeyEvent.VK_A, () -> {
      String itemname = this.showAttackDialog();
      features.handleKeyPressEvent("Attack", readOnlyModel.getCurrentPlayerTurn(), itemname);
    });

    keyPresses.put(KeyEvent.VK_M, () -> {
      String roomName = this.showSpaceDialog();
      features.handleKeyPressEvent("MovePet", readOnlyModel.getCurrentPlayerTurn(), roomName);
    });
    
    kbd.setKeyPressedMap(keyPresses);

    this.addKeyListener(kbd);
  }

  @Override
  public String showPickDialog() {
    String itemName = null;

    PlayerImpl player = this.readOnlyModel.getPlayerList().stream()
        .filter(p -> p.getName().trim().equals(this.readOnlyModel.getCurrentPlayerTurn().trim()))
        .collect(Collectors.toList()).get(0);

    String[] itemList = player.getCurrentRoom().getItems().stream().map(ItemImpl::getName)
        .collect(Collectors.toList()).toArray(new String[0]);
    if (itemList == null || itemList.length == 0) {
      this.outputMessage = String.format("No items in the room", itemList);
    }
    JComboBox items = new JComboBox(itemList);
    items.setPreferredSize(new Dimension(200, 30));
    items.setSelectedIndex(0);

    int result = JOptionPane.showConfirmDialog(null, items, "Pick an Item",
        JOptionPane.DEFAULT_OPTION);
    if (items.getSelectedItem() == null) {
      this.showPickDialog();
    }
    if (result == JOptionPane.OK_OPTION) {
      itemName = (String) items.getSelectedItem();
    }

    return itemName;

  }

  private String showSpaceDialog() {
    String spaceName = null;

    String[] spaceList = this.readOnlyModel.getSpaceList().stream().map(SpaceImpl::getName)
        .collect(Collectors.toList()).toArray(new String[0]);

    if (spaceList == null || spaceList.length == 0) {
      this.outputMessage = String.format("No spaces in the world", spaceList);
    }
    JComboBox items = new JComboBox(spaceList);
    items.setPreferredSize(new Dimension(200, 30));
    items.setSelectedIndex(0);

    int result = JOptionPane.showConfirmDialog(null, items, "Move Pet", JOptionPane.DEFAULT_OPTION);
    if (items.getSelectedItem() == null) {
      this.showPickDialog();
    }
    if (result == JOptionPane.OK_OPTION) {
      spaceName = (String) items.getSelectedItem();
    }

    return spaceName;

  }
  
  @Override
  public void ifPlayerAdded() {
    this.addPlayerPanel.addDataToTable();
    this.addPlayerPanel.resetFields();
  }

  @Override
  public String showFileUploadDialog() {
    try {
      JFileChooser chooser = new JFileChooser();
      chooser.showOpenDialog(null);
      File file = chooser.getCurrentDirectory();
      chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

      File filepath = chooser.getSelectedFile();
      String file1 = filepath.getAbsolutePath();

      StringBuilder inputdata = new StringBuilder();
      FileReader fr1 = new FileReader(file1);
      int data;
      while ((data = fr1.read()) != -1) {
        inputdata.append((char) data);
      }
      features.updateWorld(inputdata.toString());
      return String.format("File Successfully Uploaded");
    } catch (FileNotFoundException fnf) {
      throw new IllegalStateException("File not Found");
    } catch (IOException io) {
      this.showErrorPopup();
      return io.getMessage();
    } catch (IllegalStateException ise) {
      this.showErrorPopup();
      return ise.getMessage();
    }
  }

  private void showErrorPopup() {
    JOptionPane.showMessageDialog(null, "Specified file is in Invalid format");
  }

  @Override
  public void setOutputMessage(String outputMessage) {
    
    if (outputMessage == null) {
      throw new IllegalArgumentException("Output Message is not null");
    }
    this.outputMessage = outputMessage;
  }

  @Override
  public String getCurrentPlayerName() {
    return this.readOnlyModel.getCurrentPlayerTurn();
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public String showAttackDialog() {
    String itemName = null;

    List<ItemImpl> itemsOnPlayer = this.readOnlyModel.getPlayerList().stream()
        .filter(p -> p.getName().trim().equals(this.readOnlyModel.getCurrentPlayerTurn().trim()))
        .collect(Collectors.toList()).get(0).getItems();
    if (itemsOnPlayer.stream().filter(s -> "Poke".equals(s.getName())).collect(Collectors.toList())
        .isEmpty()) {
      itemsOnPlayer.add(new ItemImpl(1, "Poke"));
    }
    String[] itemList = itemsOnPlayer.stream().map(ItemImpl::getName).collect(Collectors.toList())
        .toArray(new String[0]);

    items = new JComboBox(itemList);
    items.setPreferredSize(new Dimension(200, 30));
    items.setSelectedIndex(0);

    int result = JOptionPane.showConfirmDialog(null, items, "Choose an Item to Attack",
        JOptionPane.DEFAULT_OPTION);
    if (result == JOptionPane.OK_OPTION) {
      itemName = (String) items.getSelectedItem();
    }
    return itemName;
  }

  private void showGameEndPopUp() {
    JOptionPane.showMessageDialog(null, this.outputMessage);
    this.closeWindow();
  }

  @Override
  public void setTurnMessage(String turnMessage) {
    if (turnMessage == null) {
      throw new IllegalArgumentException("Turn Message can't be null");
    }
    this.turnMessage = turnMessage;
  }

  @Override
  public void setIfTurnExecuted(boolean ifTurnExecuted) {
    this.ifTurnsExecuted = ifTurnExecuted;
  }

  @Override
  public void setComputerPlayerMessage(String computerPlayerTurnMessage) {
    if (this.computerPlayerTurnMessage == null) {
      throw new IllegalArgumentException("computerPlayerTurnMessage can't be null");
    }
    this.computerPlayerTurnMessage = computerPlayerTurnMessage;
  }

  @Override
  public void setPlayerInfoDialog(String output) {
    if (output == null || "".equals(output)) {
      throw new IllegalArgumentException("Output Message is null\n");
    }
    JOptionPane.showMessageDialog(null, output);
  }

}
