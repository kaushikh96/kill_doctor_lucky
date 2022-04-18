package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import theworld.ReadOnlyBoardGameModel;

public class AddPlayerPanel extends JPanel {
  
  private ReadOnlyBoardGameModel readOnlyModel;
  
  public AddPlayerPanel(ReadOnlyBoardGameModel readOnlyModel) {
    
    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }
    
    this.readOnlyModel = readOnlyModel;
    
    this.setLayout(new BorderLayout());
    
    JPanel addP = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    addP.setBackground(new Color(137, 207, 240));
    
    String labelTextAdd = "<html><font color=#000000 size=20>ADD PLAYER</font><br></html>";
    JLabel coloredLabelAdd = new JLabel(labelTextAdd,SwingConstants.LEFT);
    c.anchor = GridBagConstraints.WEST;
    c.weightx = 0.5;  
    c.weighty = 0.5;  
    c.insets = new Insets(35,65,15,15);
    
    addP.add(coloredLabelAdd, c);
    
    this.add(addP, BorderLayout.NORTH);
    
    JPanel name = new JPanel(new GridBagLayout());
    GridBagConstraints cname = new GridBagConstraints();
    name.setBackground(new Color(137, 207, 240));
    
    String labelTextName = "<html><font color=#000000 size=7>Name</font><br></html>";
    JLabel coloredLabelName = new JLabel(labelTextName);

    cname.gridx = 0;
    cname.gridy = 0;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.5;    
    cname.insets = new Insets(5,45,15,15);
    
    name.add(coloredLabelName, cname);
    
    String labelTextSpaceName = "<html><font color=#000000 size=7>Space Name</font><br></html>";
    JLabel coloredLabelSpaceName = new JLabel(labelTextSpaceName,SwingConstants.LEFT);
    
    cname.gridx = 0;
    cname.gridy = 1;
    cname.anchor = GridBagConstraints.NORTHWEST;  
    cname.weightx = 0.5;   
    cname.insets = new Insets(5,45,15,15);
    
    name.add(coloredLabelSpaceName, cname);
    
    String labelTextItemLimit = "<html><font color=#000000 size=7>Item Limit</font><br></html>";
    JLabel coloredLabelItemLimit = new JLabel(labelTextItemLimit,SwingConstants.LEFT);
    
    cname.gridx = 0;
    cname.gridy = 2;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.5;    
    cname.insets = new Insets(5,45,15,15);
    
    name.add(coloredLabelItemLimit, cname);
    
    String labelTextPlayerType = "<html><font color=#000000 size=7>Type</font><br></html>";
    JLabel coloredLabelPlayerType = new JLabel(labelTextPlayerType,SwingConstants.LEFT);
    
    cname.gridx = 0;
    cname.gridy = 3;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.5;  
    cname.weighty = 5.5;  
    cname.insets = new Insets(5,45,15,15);
    
    name.add(coloredLabelPlayerType, cname);
    
    this.add(name, BorderLayout.WEST);
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

    graphics2d.setFont(new Font(Font.SERIF, Font.PLAIN, 40));
  }
  

}
