package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import controller.BoardGameController;
import controller.BoardGameControllerImpl;
import controller.Features;
import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;

public class BoardGameViewImpl extends JFrame implements BoardGameView {
  private final ReadOnlyBoardGameModel readOnlyModel;
  private final WelcomePanel boardGamePanel;
  private AddPlayerPanel addPlayerPanel;
  private final GamePanel gamePanel;
  private Features f;
  private JButton b;

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
    this.gamePanel = new GamePanel(model, this);
    pack();
    setVisible(true);
  }

  @Override
  public void addClickListener(BoardGameController listener) {
//    MouseListener mouse = new ClickEventMouse(listener);
//    boardGamePanel.addMouseListener(mouse);
//    setFocusable(true);
  }

  @Override
  public void displayAddPlayerScreen() {
    this.remove(boardGamePanel);
    this.add(addPlayerPanel, BorderLayout.CENTER);
    addPlayerPanel.revalidate();
  }

  @Override
  public void displayGameScreen() {
    this.remove(addPlayerPanel);
    this.add(gamePanel, BorderLayout.CENTER);
    gamePanel.revalidate();
  }

  @Override
  public void addPlayers(String playerName, String roomName, int itemCapacity,
      boolean isComputerPlayer) {
    f.addPlayer(playerName, roomName, itemCapacity, isComputerPlayer);
    this.addPlayerPanel = new AddPlayerPanel(readOnlyModel, this);
    //this.add(addPlayerPanel, BorderLayout.CENTER);
    addPlayerPanel.revalidate();
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
  public void setFeatures(Features f) {
    this.f = f;
  }
}
