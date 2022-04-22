package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.EmptyBorder;

import controller.BoardGameController;
import controller.BoardGameControllerImpl;
import controller.Features;
import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;

public class BoardGameViewImpl extends JFrame implements BoardGameView {
  private final ReadOnlyBoardGameModel readOnlyModel;
  private WelcomePanel boardGamePanel;
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
    this.boardGamePanel = new WelcomePanel(model, this);
    this.add(boardGamePanel, BorderLayout.CENTER);
    this.addPlayerPanel = new AddPlayerPanel(model, this);
    // this.gamePanel = new GamePanel(model, this);
    this.worldSelectionPanel = new WorldSelectionPanel(model, this);

    this.menuBar = new JMenuBar();

    this.menu = new JMenu("Menu");

    this.currentWorldItem = new JMenuItem("Current World");
    this.newWorldItem = new JMenuItem("New World");
    this.quit = new JMenuItem("Quit");

    this.menu.add(this.currentWorldItem);
    this.currentWorldItem.addActionListener(new ButtonListener(this));
    this.menu.add(this.newWorldItem);
    this.newWorldItem.addActionListener(new ButtonListener(this));
    this.menu.add(this.quit);
    this.quit.addActionListener(new ButtonListener(this));

    this.menuBar.add(this.menu);

    this.add(menuBar, BorderLayout.NORTH);
    pack();
    setVisible(true);
  }

//  @Override
//  public void addClickListener(Features listener) {
//    MouseListener mouse = new MouseClickEvent(listener);
//    gamePanel
//    setFocusable(true);
//  }

  @Override
  public void displayWorldSelectionScreen() {
    this.remove(boardGamePanel);
    this.add(worldSelectionPanel, BorderLayout.CENTER);
    worldSelectionPanel.revalidate();
  }

  @Override
  public void displayAddPlayerScreen() {
    this.remove(worldSelectionPanel);
    this.add(addPlayerPanel, BorderLayout.CENTER);
    addPlayerPanel.revalidate();
  }

  @Override
  public void displayGameScreen(String playername) {
    this.remove(addPlayerPanel);
    this.turnMessage = this.getTurnsofPlayers(playername);
    this.gamePanel = new GamePanel(this.readOnlyModel, this, this.outputMessage, this.turnMessage);
    // MouseListener mouse = new MouseClickEvent(f);
    this.gamePanel.setFeatures(f);
    setFocusable(true);
    this.add(gamePanel, BorderLayout.CENTER);
    gamePanel.revalidate();
  }

  @Override
  public void addPlayers(String playerName, String roomName, int itemCapacity,
      boolean isComputerPlayer) {
    f.addPlayer(playerName, roomName, itemCapacity, isComputerPlayer);
    // this.addPlayerPanel = new AddPlayerPanel(readOnlyModel, this);
    // this.add(addPlayerPanel, BorderLayout.CENTER);
    // addPlayerPanel.revalidate();
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
    // this.addPlayerPanel.setFeatures(f);
  }
}
//