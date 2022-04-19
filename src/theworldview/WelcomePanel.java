package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import theworld.ReadOnlyBoardGameModel;

public class WelcomePanel extends JPanel {

  private ReadOnlyBoardGameModel readonlymodel;
  private JLabel title;
  private JButton b;
  private BoardGameView view;

  /**
   * Constructor for PanelImpl class.
   * 
   * @param readonlymodel the ReadonlyTttModel type
   */
  public WelcomePanel(ReadOnlyBoardGameModel readonlymodel, BoardGameView view) {

    if (readonlymodel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.\n");
    }

    this.readonlymodel = readonlymodel;
    this.view = view;
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    String labelText1 = "<html><font color=#8B8000 size=30>KILL DOCTOR LUCKY</font><br></html>";
    JLabel coloredLabel1 = new JLabel(labelText1, JLabel.CENTER);
    coloredLabel1.setBackground(Color.YELLOW);
    // create a line border with the specified color and width
    Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);

    // set the border of this component
    coloredLabel1.setBorder(border);
    coloredLabel1.setBackground(Color.BLACK);
    this.add(coloredLabel1);
    JLabel emptyLabel = new JLabel("");

    this.add(emptyLabel);
    this.add(emptyLabel);
    this.add(emptyLabel);
    this.add(emptyLabel);
    String labelText = "<html><font color=blue size=5>LETS START THE GAME PLAY !!!</font><br></html>";
    JLabel coloredLabel = new JLabel(labelText, JLabel.CENTER);
    this.add(coloredLabel, BorderLayout.CENTER);

    JPanel jpnl = new JPanel();
    b = new JButton("START");
    b.setBackground(new Color(59, 89, 182));
    b.setForeground(Color.WHITE);
    b.setFocusPainted(false);
    b.setFont(new Font("Tahoma", Font.BOLD, 12));
    b.setPreferredSize(new Dimension(40, 40));
    b.addActionListener(new ButtonListener("Add Player Screen", view));
    jpnl.add(b);
    jpnl.setBackground(new Color(137, 207, 240));
    //jpnl.setAlignmentX(Component.CENTER_ALIGNMENT);
    this.add(jpnl);

    String kaushiktext = "<html><font color=#00008b size=3>KAUSHIK KOMANDURI</font></html>";
    JLabel kaushiklabel = new JLabel(kaushiktext, JLabel.RIGHT);
    this.add(kaushiklabel);

    String sanjanatext = "<html><font color=#00008b size=3>SANJANA KANDUNOORI</font></html>";
    JLabel sanjanaLabel = new JLabel(sanjanatext, JLabel.RIGHT);

    this.add(sanjanaLabel, BorderLayout.SOUTH);
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2d = (Graphics2D) graphics;
    Border border = new LineBorder(Color.BLUE, 4, true);
    this.setBorder(border);
    this.setMinimumSize(new Dimension(600, 600));
    graphics2d.setColor(new Color(137, 207, 240));
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
//
//  @Override
//  public void actionPerformed(ActionEvent e) {
//    b.setBackground(Color.RED);
//  }

}
