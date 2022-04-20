package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import theworld.ReadOnlyBoardGameModel;

public class WelcomePanel extends JPanel {

  private ReadOnlyBoardGameModel readonlymodel;
  private JLabel title;
  private JButton b;
  private BoardGameView view;
  private GridBagConstraints welcome;
  private JPanel welcomePanel;
  
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
    //this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    this.setLayout(new BorderLayout());
    
    this.welcomePanel = new JPanel(new GridBagLayout());
    this.welcomePanel.setBackground(new Color(137, 207, 240));
    this.welcome = new GridBagConstraints();
    
    String labelText1 = "<html><font color=#8B8000 size=300>KILL DOCTOR LUCKY</font><br></html>";
    JLabel coloredLabel1 = new JLabel(labelText1, JLabel.CENTER);
    coloredLabel1.setPreferredSize(new Dimension(550, 80));
    coloredLabel1.setBackground(Color.YELLOW);
    // create a line border with the specified color and width
    Border border = BorderFactory.createLineBorder(Color.YELLOW, 2);

    // set the border of this component
    coloredLabel1.setBorder(border);
    this.welcome.gridx = 0;
    this.welcome.gridy = 0;
    this.welcome.anchor = GridBagConstraints.NORTH;   
    this.welcome.weightx = 5.0;
    this.welcome.weighty = 5.0;
    this.welcome.insets = new Insets(80, 10, 10, 10);
    this.welcomePanel.add(coloredLabel1, welcome);
    this.add(welcomePanel, BorderLayout.NORTH);
    
    String labelText = "<html><font color=blue size=15>LET'S START THE GAME PLAY !!!</font><br></html>";
    JLabel coloredLabel = new JLabel(labelText, JLabel.CENTER);
    
    this.welcome.gridx = 0;
    this.welcome.gridy = 1;
    this.welcome.anchor = GridBagConstraints.NORTH;
    this.welcome.weightx = 5.0;
    this.welcome.weighty = 5.0;
    this.welcome.insets = new Insets(40, 10, 10, 10);
    this.welcomePanel.add(coloredLabel, welcome);
    this.add(welcomePanel, BorderLayout.NORTH);

    b = new JButton("START");
    b.setBackground(new Color(59, 89, 182));
    b.setForeground(Color.WHITE);
    b.setFocusPainted(false);
    b.setFont(new Font("Tahoma", Font.BOLD, 12));
    b.setPreferredSize(new Dimension(80, 40));
    b.addActionListener(new ButtonListener(view));

    this.welcome.gridx = 0;
    this.welcome.gridy = 2;
    this.welcome.anchor = GridBagConstraints.NORTH;
    this.welcome.weightx = 5.0;
    this.welcome.weighty = 5.0;
    this.welcome.insets = new Insets(50, 10, 10, 10);
    this.welcomePanel.add(b, welcome);
    this.add(welcomePanel, BorderLayout.NORTH);
    
    
    String kaushikText = "<html><font color=#00008b size=6>KAUSHIK KOMANDURI<br>SANJANA KANDUNOORI</font></html>";
    JLabel kaushikLabel = new JLabel(kaushikText);
    kaushikLabel.setBorder(new EmptyBorder(10, 30, 40, 10));
    
    this.add(kaushikLabel, BorderLayout.SOUTH);
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
