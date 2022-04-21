package theworldview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import theworld.ReadOnlyBoardGameModel;
import theworld.SpaceImpl;

public class GamePanel extends JPanel {

  private BufferedImage bufferedimage;
  private Graphics2D graphics2d;
  private ReadOnlyBoardGameModel readOnlyModel;
  private BoardGameView view;
  private JLabel imageLabel;
  private JLabel playerLabel;
  private JPanel infoPanel;
  private JTextArea playersArea;
  private JTextArea turnInfoArea;
  private GridBagConstraints game;
  private GridBagConstraints player1;
  private JTextArea turnResultArea;
  int width = 25;
  int height = 16;

  public GamePanel(ReadOnlyBoardGameModel readOnlyModel, BoardGameView view) {

    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }

    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.\n");
    }

    this.bufferedimage = new BufferedImage(width * 60, height * 30, BufferedImage.TYPE_INT_RGB);
    this.graphics2d = (Graphics2D) this.bufferedimage.getGraphics();
    this.graphics2d.setColor(Color.WHITE);
    this.graphics2d.fillRect(0, 0, width * 60, height * 30);

    this.readOnlyModel = readOnlyModel;
    this.view = view;

    this.setLayout(new GridBagLayout());
    this.game = new GridBagConstraints();

    this.player1 = new GridBagConstraints();
//    BufferedImage worldImg = null;
//    BufferedImage playerImg = null;
//    URL url1 = null;
//    URL url2 = null;
//    try {
//      url1 = new URL("res/rep.jpg");
//      url2 = new URL("res/person.png");
//    } catch (MalformedURLException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//
//    try {
//      worldImg = ImageIO.read(url1);
//      playerImg = ImageIO.read(url2);
//    } catch (IOException ioe) {
//      ioe.getMessage();
//    }

//    Icon tileIcon = new ImageIcon(tileImg);
//    for (int row = 0; row < 10; row++) {
//      for (int col = 0; col < 10; col++) {
//        gbc.gridx = col;
//        gbc.gridy = row;
//        add(new JLabel(tileIcon), gbc);
//      }
//    }

    // this.imageLabel = new JLabel();
    // this.playerLabel = new JLabel();
    this.imageLabel = new JLabel(new ImageIcon("res/rep.jpg"));

    this.playerLabel = new JLabel(new ImageIcon(new ImageIcon("res/playericon.png").getImage()
        .getScaledInstance(20, 25, java.awt.Image.SCALE_SMOOTH)));
    this.playerLabel.setBounds(10, 50, 200, 200);
    this.playerLabel.setLocation(60, 70);
    // this.imageLabel.setBackground(null);
    // this.playerLabel.setIcon(new ImageIcon("res/person.png"));
    //this.player1.gridx = 0;
   // this.player1.gridy = 0;
    this.player1.weightx = 0.5;
    this.player1.weighty = 0.5;
    this.player1.fill = GridBagConstraints.BOTH;
    this.player1.anchor = GridBagConstraints.NORTHWEST;
    this.player1.insets = new Insets(200, 80, 50, 6);

    JScrollPane world = new JScrollPane(this.imageLabel);
    this.setBackground(new Color(137, 207, 240));

    this.game.gridx = 0;
    this.game.gridy = 0;
    this.game.weightx = 0.1;
    this.game.weighty = 0.1;
    this.game.fill = GridBagConstraints.BOTH;
    this.game.anchor = GridBagConstraints.NORTHWEST;
    this.game.insets = new Insets(10, 10, 10, 10);

    this.add(this.playerLabel, this.player1);
    this.add(world, game);

    this.infoPanel = new JPanel(new GridLayout(3, 0, 10, 10));
    this.playersArea = new JTextArea();
    this.playersArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.playersArea.setBackground(new Color(37, 190, 175));

    this.game.gridx = 1;
    this.game.gridy = 0;
    this.game.weightx = 3.0;
    this.game.weighty = 3.0;
    this.game.anchor = GridBagConstraints.NORTHEAST;
    this.game.insets = new Insets(20, 5, 10, 10);
    this.infoPanel.add(playersArea);

    this.turnInfoArea = new JTextArea();
    this.turnInfoArea.setBorder(BorderFactory.createLineBorder(Color.GREEN));
    this.infoPanel.add(turnInfoArea);

    this.turnResultArea = new JTextArea();
    this.turnResultArea.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    this.turnResultArea.setBackground(new Color(37, 190, 175));
    this.infoPanel.add(turnResultArea);

    this.add(infoPanel, game);
  }

//  @Override
//  public void paintComponent(Graphics graphics) {
//    List<SpaceImpl> roomlist = readOnlyModel.getSpaceList();
//    this.graphics2d.setColor(Color.BLACK);
//    roomlist.stream().forEach(s -> {
//      List<Integer> coord = s.getRoomLocation();
//      this.graphics2d.drawRect(coord.get(1) * 60, coord.get(0) * 30,
//          (coord.get(3) - coord.get(1)) * 60 + 59, (coord.get(2) - coord.get(0)) * 30 + 29);
//      this.graphics2d.drawString(s.getName(), coord.get(1) * 60 + 19, coord.get(0) * 30 + 29);
//    });
//    File newfile = new File("rep.jpg");
//    ImageIO.write(this.bufferedimage, "jpg", newfile);
//    URL urlImage1 = new URL("res/rep.jpg");
//
//    final Image fgImage = ImageIO.read(urlImage1);
//    int w = fgImage.getWidth(null);
//    int h = fgImage.getHeight(null);
//    final BufferedImage bgImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//
//    final BufferedImage finalImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
//    Graphics2D g = finalImage.createGraphics();
//    g.drawImage(bgImage, 0, 0, null);
//    g.drawImage(fgImage, 0, 0, null);
//    g.dispose();
//  }

}
