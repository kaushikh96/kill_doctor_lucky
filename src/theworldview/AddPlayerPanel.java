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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
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
    c.gridx = 0;
    c.gridy = 0;
    c.anchor = GridBagConstraints.NORTHWEST;
    c.weightx = 0.1;  
    c.weighty = 0.1;  
    c.insets = new Insets(35,160,15,15);
    
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
    cname.weightx = 0.1;   
    cname.weighty = 0.1;
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(8,65,20,0);
    
    name.add(coloredLabelName, cname);
    
    this.add(name, BorderLayout.WEST);
    
    JTextField nameText = new JTextField(20);
    nameText.setPreferredSize(new Dimension(20,30));
    
    cname.gridx = 1;
    cname.gridy = 0;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.1; 
    cname.weighty = 0.1;
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(22,15,5,5);
    
    name.add(nameText, cname);
    
    this.add(name, BorderLayout.WEST);
    
    String labelTextSpaceName = "<html><font color=#000000 size=7>Space Name</font><br></html>";
    JLabel coloredLabelSpaceName = new JLabel(labelTextSpaceName,SwingConstants.LEFT);
    
    cname.gridx = 0;
    cname.gridy = 1;
    cname.anchor = GridBagConstraints.NORTHWEST;  
    cname.weightx = 0.1;   
    cname.weighty = 0.1;
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(8,65,20,0);
    
    name.add(coloredLabelSpaceName, cname);
    
    this.add(name, BorderLayout.WEST);
    
    JTextField spaceNameText = new JTextField(20);
    spaceNameText.setPreferredSize(new Dimension(20,30));
    
    cname.gridx = 1;
    cname.gridy = 1;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.1; 
    cname.weighty = 0.1;
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(22,15,5,5);
    
    name.add(spaceNameText, cname);
    
    this.add(name, BorderLayout.WEST);
    
    String labelTextItemLimit = "<html><font color=#000000 size=7>Item Limit</font><br></html>";
    JLabel coloredLabelItemLimit = new JLabel(labelTextItemLimit,SwingConstants.LEFT);
    
    cname.gridx = 0;
    cname.gridy = 2;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.1; 
    cname.weighty = 0.1;
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(8,65,20,0);
    
    name.add(coloredLabelItemLimit, cname);
    
    this.add(name, BorderLayout.WEST);
    
    JTextField itemLimitText = new JTextField(20);
    itemLimitText.setPreferredSize(new Dimension(20,30));
    
    cname.gridx = 1;
    cname.gridy = 2;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.1; 
    cname.weighty = 0.1;
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(22,15,5,5);
    
    name.add(itemLimitText, cname);
    
    this.add(name, BorderLayout.WEST);
    
    String labelTextPlayerType = "<html><font color=#000000 size=7>Type</font><br></html>";
    JLabel coloredLabelPlayerType = new JLabel(labelTextPlayerType,SwingConstants.LEFT);
    
    cname.gridx = 0;
    cname.gridy = 3;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.1;  
    cname.weighty = 0.1;  
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(8,65,20,0);
    
    name.add(coloredLabelPlayerType, cname);
    
    this.add(name, BorderLayout.WEST);
    
    
    JRadioButton humanType = new JRadioButton("Human");
    humanType.setBackground(new Color(137, 207, 240));
    humanType.setFont(humanType.getFont().deriveFont(18.0f));
    
    cname.gridx = 1;
    cname.gridy = 3;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.1;
    cname.weighty = 0.1;
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(22,16,5,5);
    
    name.add(humanType, cname);
    
    this.add(name, BorderLayout.WEST);
    
    JRadioButton computerType = new JRadioButton("Computer");
    computerType.setBackground(new Color(137, 207, 240));
    computerType.setFont(computerType.getFont().deriveFont(18.0f));
    
    cname.gridx = 1;
    cname.gridy = 3;
    cname.anchor = GridBagConstraints.NORTHWEST;
    cname.weightx = 0.1; 
    cname.weighty = 5.1;
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(60,16,5,5);
    
    name.add(computerType, cname);
    
    this.add(name, BorderLayout.WEST);
    
    ButtonGroup groupType = new ButtonGroup(); 
    groupType.add(humanType);
    groupType.add(computerType);
    
    JPanel buttonPanel = new JPanel(new GridBagLayout());
    JButton addButton = new JButton("ADD");
    addButton.setBackground(new Color(59, 89, 182));
    addButton.setForeground(Color.WHITE);
    addButton.setFocusPainted(false);
    addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
    addButton.setPreferredSize(new Dimension(10, 10));
    
    cname.gridx = 1;
    cname.gridy = 4;
    cname.anchor = GridBagConstraints.PAGE_END;
    cname.weightx = 0.1;  
    cname.weighty = 5.1;  
    cname.gridwidth = 1;
    cname.gridheight = 1;
    cname.insets = new Insets(8,45,20,15);
    
    name.add(addButton, cname);
    
    this.add(addButton, BorderLayout.PAGE_END);
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
