package theworldview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Features;
import theworld.ItemImpl;
import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;

public class BoardGameViewImpl extends JFrame implements BoardGameView {
  private ReadOnlyBoardGameModel readOnlyModel;
  private WelcomePanel welcomePanel;
  private AddPlayerPanel addPlayerPanel;
  private GamePanel gamePanel;
  private WorldSelectionPanel worldSelectionPanel;
  private Features f;
  private JButton b;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem currentWorldItem;
  private JMenuItem newWorldItem;
  private JMenuItem quit;
  private String outputMessage;
  private String turnMessage;
  private boolean ifTurnsExecuted;
  private ActionListener listener;

  /**
   * Constructor for TicTacToeViewImpl.
   * 
   * @param caption the caption for the view
   * @param model   the ReadonlyTttModel type
   */
  public BoardGameViewImpl(String caption, ReadOnlyBoardGameModel model) {
    super(caption);
    setSize(600, 600);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.readOnlyModel = model;
    this.setLayout(new BorderLayout());
    this.welcomePanel = new WelcomePanel(model, this);
    this.add(welcomePanel, BorderLayout.CENTER);
    //this.addPlayerPanel = new AddPlayerPanel(this.readOnlyModel, this);
    this.worldSelectionPanel = new WorldSelectionPanel(model, this);

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
    this.ifTurnsExecuted = true;
    this.listener = null;
    pack();
    setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    this.welcomePanel.addActionListener(actionListener);
    this.currentWorldItem.addActionListener(actionListener);
    this.newWorldItem.addActionListener(actionListener);
    this.quit.addActionListener(new ButtonListener());
   // this.addPlayerPanel.addActionListener(actionListener);
    this.listener = actionListener;
  }

  @Override
  public void displayWorldSelectionScreen() {
    this.remove(welcomePanel);
    this.add(worldSelectionPanel, BorderLayout.CENTER);
    worldSelectionPanel.revalidate();
  }

  @Override
  public void displayAddPlayerScreen() {
    this.remove(worldSelectionPanel);
    this.addPlayerPanel = new AddPlayerPanel(this.readOnlyModel, this);
    this.addPlayerPanel.addActionListener(this.listener);
    this.add(addPlayerPanel, BorderLayout.CENTER);
    addPlayerPanel.revalidate();
  }

  @Override
  public void displayGameScreen() {
    this.remove(addPlayerPanel);
    if (this.ifTurnsExecuted) {
      this.turnMessage = this.getTurnsofPlayers(readOnlyModel.getCurrentPlayerTurn());
    }
    this.gamePanel = new GamePanel(this.readOnlyModel, this, this.outputMessage, this.turnMessage);
    this.gamePanel.setFeatures(f);
    setFocusable(true);
    this.add(gamePanel, BorderLayout.CENTER);
    gamePanel.revalidate();
  }

  @Override
  public void addPlayers() {

    this.f.addPlayer(this.addPlayerPanel.getPlayerName(), this.addPlayerPanel.getSpace(),
        this.addPlayerPanel.itemCapacity(), this.addPlayerPanel.getPlayerType());

    this.addPlayerPanel.resetFields();
  }

  @Override
  public String getTurnsofPlayers(String playerName) {
    return f.getTurns(playerName);
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
  public void setFeatures(Features f) {
    this.f = f;
  }

  public String showPickDialog() {
    String itemName = null;

    PlayerImpl player = this.readOnlyModel.getPlayerList().stream()
        .filter(p -> p.getName().trim().equals(this.readOnlyModel.getCurrentPlayerTurn().trim()))
        .collect(Collectors.toList()).get(0);

    String[] itemList = player.getCurrentRoom().getItems().stream().map(ItemImpl::getName)
        .collect(Collectors.toList()).toArray(new String[0]);

    JComboBox items = new JComboBox(itemList);
    items.setPreferredSize(new Dimension(200, 30));

    int result = JOptionPane.showConfirmDialog(null, items, "Pick an Item",
        JOptionPane.DEFAULT_OPTION);

    if (result == JOptionPane.OK_OPTION) {
      itemName = (String) items.getSelectedItem();
    }

    return itemName;

  }

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
      this.readOnlyModel = readOnlyModel.updateWorld(inputdata.toString());
    } catch (FileNotFoundException fnf) {
      throw new IllegalStateException("File not Found");
    } catch (IOException io) {
      throw new IllegalStateException("Invalid Read");
    }

    return "";

  }

  @Override
  public void setOutputMessage(String outputMessage) {
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
    itemsOnPlayer.add(new ItemImpl(1, "Poke"));
    String[] itemList = itemsOnPlayer.stream().map(ItemImpl::getName).collect(Collectors.toList())
        .toArray(new String[0]);

    JComboBox items = new JComboBox(itemList);
    items.setPreferredSize(new Dimension(200, 30));

    int result = JOptionPane.showConfirmDialog(null, items, "Choose an Item to Attack",
        JOptionPane.DEFAULT_OPTION);

    if (result == JOptionPane.OK_OPTION) {
      itemName = (String) items.getSelectedItem();
    }

    return itemName;
  }

  @Override
  public void setIfTurnExecuted(boolean ifTurnExecuted) {
    this.ifTurnsExecuted = ifTurnExecuted;
  }

}
