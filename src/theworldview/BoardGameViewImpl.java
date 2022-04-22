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
    this.addPlayerPanel = new AddPlayerPanel(model, this);
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
    pack();
    setVisible(true);
  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    this.welcomePanel.addActionListener(actionListener);
    this.currentWorldItem.addActionListener(actionListener);
    this.newWorldItem.addActionListener(actionListener);
    this.quit.addActionListener(new ButtonListener());
    this.addPlayerPanel.addActionListener(actionListener);
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
    this.add(addPlayerPanel, BorderLayout.CENTER);
    addPlayerPanel.revalidate();
  }

  @Override
  public void displayGameScreen() {
    this.remove(addPlayerPanel);
    this.turnMessage = this.getTurnsofPlayers(readOnlyModel.getCurrentPlayerTurn());
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

  @Override
  public void setOutputMessage(String outputMessage) {
    this.outputMessage = outputMessage;
  }
}