package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import theworld.ReadOnlyBoardGameModel;

public class BoardGamePanel extends JPanel {
  private ReadOnlyBoardGameModel readonlymodel;
  private JLabel title;
  private JButton b;

  /**
   * Constructor for PanelImpl class.
   * 
   * @param readonlymodel the ReadonlyTttModel type
   */
  public BoardGamePanel(ReadOnlyBoardGameModel readonlymodel) {
    this.readonlymodel = readonlymodel;
    title = new JLabel("I love stackoverflow!", JLabel.CENTER);

    title.setForeground(Color.white);

    this.add(title, BorderLayout.NORTH);

    b = new JButton("Click Here");
    b.setBounds(50, 100, 95, 30);
    this.add(b, BorderLayout.SOUTH);
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2d = (Graphics2D) graphics;
    Border border = new LineBorder(Color.BLUE, 4, true);
    this.setBorder(border);
    this.setMinimumSize(new Dimension(100, 100));
    graphics2d.setColor(Color.CYAN);
    graphics2d.fillRect(0, 0, getWidth(), getHeight());
    graphics2d.setColor(Color.BLACK);

//    graphics2d.drawLine(0, 200, 600, 200);
//    graphics2d.drawLine(0, 400, 600, 400);
//    graphics2d.drawLine(200, 0, 200, 600);
//    graphics2d.drawLine(400, 0, 400, 600);

    graphics2d.setFont(new Font(Font.SERIF, Font.PLAIN, 40));

//    Player[][] playerBoard = readonlymodel.getBoard();
//    for (int i = 0; i < 3; i++) {
//      for (int j = 0; j < 3; j++) {
//        if (playerBoard[i][j] != null) {
//          graphics2d.drawString(playerBoard[i][j].toString(), (i * 200) + 50, (j * 200) + 50);
//        }
//      }
//    }
//    if (readonlymodel.isGameOver()) {
//      if (readonlymodel.getWinner() != null) {
//        graphics2d.drawString("GAME OVER ! Winner is " + readonlymodel.getWinner(), 200, 200);
//      } else {
//        graphics2d.drawString("GAME TIE", 200, 200);
//      }
//    }
  }

}
