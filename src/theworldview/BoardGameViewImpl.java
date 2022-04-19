package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import controller.BoardGameController;
import theworld.ReadOnlyBoardGameModel;

public class BoardGameViewImpl extends JFrame implements BoardGameView {
  private final WelcomePanel boardGamePanel;
  private final AddPlayerPanel addPlayerPanel;
  private final GamePanel gamePanel;
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
    this.setLayout(new BorderLayout());
    // this.boardGamePanel = null;
    this.boardGamePanel = new WelcomePanel(model, this);
    // this.boardGamePanel.add(b);
    this.add(boardGamePanel, BorderLayout.CENTER);
    this.addPlayerPanel = new AddPlayerPanel(model, this);
    this.gamePanel = new GamePanel(model, this);

    // this.add(gamePanel, BorderLayout.CENTER);

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
  public void refresh() {
    repaint();
  }

  @Override
  public void makeVisible() {
    setVisible(true);
  }
}
