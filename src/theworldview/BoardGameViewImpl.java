package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import controller.BoardGameController;
import theworld.ReadOnlyBoardGameModel;

public class BoardGameViewImpl extends JFrame implements BoardGameView {
  //private final BoardGamePanel boardGamePanel;
  private final AddPlayerPanel addPlayerPanel;

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
    //this.boardGamePanel = new BoardGamePanel(model);
    
    this.addPlayerPanel = new AddPlayerPanel(model);
//    boardgamepanel.setBackground(Color.CYAN);
//    boardgamepanel.setBorder(new EmptyBorder(100, 100, 100, 100));
    //this.add(boardGamePanel, BorderLayout.CENTER);
    
    this.add(addPlayerPanel, BorderLayout.CENTER);
    

    pack();
    setVisible(true);

  }

  @Override
  public void addClickListener(BoardGameController listener) {

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
