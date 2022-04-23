package theworldview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;
import theworld.SpaceImpl;

public class AddPlayerPanel extends JPanel implements ItemListener {

  private ReadOnlyBoardGameModel readOnlyModel;
  private BoardGameView view;
  private JPanel addP;
  private JLabel coloredLabelAdd;
  private JLabel coloredLabelPlayerDetails;
  private JLabel coloredLabelName;
  private JLabel coloredLabelSpaceName;
  private JLabel coloredLabelItemLimit;
  private JLabel coloredLabelPlayerType;
  private JTextField nameText;
  private JComboBox spaceName;
  private JTextField itemLimitText;
  private GridBagConstraints cAdd;
  private JPanel name;
  private GridBagConstraints cname;
  private JButton addButton;
  private JButton nextButton;
  private JRadioButton humanType;
  private JRadioButton computerType;
  private ButtonGroup groupType;
  private JTable playerTable;
  private List<PlayerImpl> playerinfo;
  private String[] spaceNames;
  private String space;
  private List<PlayerImpl> playerlist;
  private DefaultTableModel model;

  public AddPlayerPanel(ReadOnlyBoardGameModel readOnlyModel, BoardGameView view) {

    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }
    if (view == null) {
      throw new IllegalArgumentException("View cannot be null.\n");
    }

    this.readOnlyModel = readOnlyModel;
    this.view = view;

    this.setLayout(new BorderLayout());

    this.addP = new JPanel(new GridBagLayout());
    this.cAdd = new GridBagConstraints();
    this.addP.setBackground(new Color(137, 207, 240));

    String labelTextAdd = "<html><font color=#000000 size=20>ADD PLAYER</font><br></html>";
    this.coloredLabelAdd = new JLabel(labelTextAdd, SwingConstants.LEFT);

    this.cAdd.gridx = 0;
    this.cAdd.gridy = 0;
    this.cAdd.anchor = GridBagConstraints.NORTHWEST;
    this.cAdd.weightx = 0.1;
    this.cAdd.weighty = 0.1;
    this.cAdd.insets = new Insets(35, 160, 15, 15);

    this.addP.add(coloredLabelAdd, cAdd);

    this.add(addP, BorderLayout.NORTH);

    String labelTextPlayerDetails = "<html><font color=#000000 size=20>Player Details</font><br></html>";
    this.coloredLabelPlayerDetails = new JLabel(labelTextPlayerDetails, SwingConstants.LEFT);

    this.cAdd.gridx = 1;
    this.cAdd.gridy = 0;
    this.cAdd.anchor = GridBagConstraints.NORTHWEST;
    this.cAdd.weightx = 0.1;
    this.cAdd.weighty = 0.1;
    this.cAdd.insets = new Insets(35, 160, 35, 15);

    this.addP.add(coloredLabelPlayerDetails, cAdd);

    this.add(addP, BorderLayout.NORTH);

    this.name = new JPanel(new GridBagLayout());
    this.cname = new GridBagConstraints();
    this.name.setBackground(new Color(137, 207, 240));

    String labelTextName = "<html><font color=#000000 size=7>Name</font><br></html>";
    this.coloredLabelName = new JLabel(labelTextName);

    this.cname.gridx = 0;
    this.cname.gridy = 0;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(8, 65, 20, 30);

    this.name.add(coloredLabelName, cname);

    this.add(name, BorderLayout.WEST);

    this.nameText = new JTextField(20);
    this.nameText.setPreferredSize(new Dimension(20, 30));

    this.cname.gridx = 1;
    this.cname.gridy = 0;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(22, 20, 5, 5);

    this.name.add(nameText, cname);

    this.add(name, BorderLayout.WEST);

    String labelTextSpaceName = "<html><font color=#000000 size=7>Space Name</font><br></html>";
    this.coloredLabelSpaceName = new JLabel(labelTextSpaceName, SwingConstants.LEFT);
    
    this.cname.gridx = 0;
    this.cname.gridy = 1;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(8, 65, 20, 30);

    this.name.add(coloredLabelSpaceName, cname);

    this.add(name, BorderLayout.WEST);

    this.spaceNames = readOnlyModel.getSpaceList().stream().map(SpaceImpl::getName)
        .collect(Collectors.toList()).toArray(new String[0]);

    this.spaceName = new JComboBox(this.spaceNames);
    this.spaceName.setSelectedIndex(-1);
    this.spaceName.setPreferredSize(new Dimension(200, 30));

    this.cname.gridx = 1;
    this.cname.gridy = 1;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(22, 20, 5, 5);

    this.name.add(spaceName, cname);

    this.add(name, BorderLayout.WEST);
    this.spaceName.addItemListener(this);

    String labelTextItemLimit = "<html><font color=#000000 size=7>Item Limit</font><br></html>";
    this.coloredLabelItemLimit = new JLabel(labelTextItemLimit, SwingConstants.LEFT);

    this.cname.gridx = 0;
    this.cname.gridy = 2;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(8, 65, 20, 30);

    this.name.add(coloredLabelItemLimit, cname);

    this.add(name, BorderLayout.WEST);

    this.itemLimitText = new JTextField(20);
//    this.itemLimitText.addKeyListener(new KeyAdapter() {
//      public void keyPressed(KeyEvent ke) {
//        String value = this.itemLimitText.getText();
//        int l = value.length();
//        if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
//          this.itemLimitText.setEditable(true);
//           //label.setText("");
//        } else {
//          this.itemLimitText.setEditable(false);
//          // label.setText("* Enter only numeric digits(0-9)");
//        }
//     }
//    });
    itemLimitText.setPreferredSize(new Dimension(20, 30));

    this.cname.gridx = 1;
    this.cname.gridy = 2;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(22, 20, 5, 5);

    this.name.add(itemLimitText, cname);

    this.add(name, BorderLayout.WEST);

    String labelTextPlayerType = "<html><font color=#000000 size=7>Type</font><br></html>";
    this.coloredLabelPlayerType = new JLabel(labelTextPlayerType, SwingConstants.LEFT);

    this.cname.gridx = 0;
    this.cname.gridy = 3;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(8, 65, 20, 20);

    this.name.add(coloredLabelPlayerType, cname);

    this.add(name, BorderLayout.WEST);

    this.addButton = new JButton("ADD");
    this.addButton.setBackground(new Color(59, 89, 182));
    this.addButton.setForeground(Color.WHITE);
    this.addButton.setFocusPainted(false);
    this.addButton.setFont(new Font("Tahoma", Font.BOLD, 12));
    this.addButton.setPreferredSize(new Dimension(80, 40));

    this.cname.gridx = 1;
    this.cname.gridy = 4;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 1.0;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(25, 0, 20, 15);

    this.name.add(addButton, cname);

    this.add(name, BorderLayout.WEST);

    this.humanType = new JRadioButton("Human");
    this.humanType.setActionCommand("Human");
    this.humanType.setBackground(new Color(137, 207, 240));
    this.humanType.setFont(humanType.getFont().deriveFont(18.0f));

    this.cname.gridx = 1;
    this.cname.gridy = 3;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(22, 16, 5, 5);

    this.name.add(humanType, cname);

    this.add(name, BorderLayout.WEST);

    this.computerType = new JRadioButton("Computer");
    this.computerType.setActionCommand("Computer");
    this.computerType.setBackground(new Color(137, 207, 240));
    this.computerType.setFont(computerType.getFont().deriveFont(18.0f));

    this.cname.gridx = 1;
    this.cname.gridy = 3;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(60, 16, 5, 5);

    this.name.add(computerType, cname);

    this.add(name, BorderLayout.WEST);

    this.groupType = new ButtonGroup();
    this.groupType.add(this.humanType);
    this.groupType.add(this.computerType);

    this.nextButton = new JButton("CONTINUE");
    this.nextButton.setBackground(new Color(59, 89, 182));
    this.nextButton.setForeground(Color.WHITE);
    this.nextButton.setFocusPainted(false);
    this.nextButton.setFont(new Font("Tahoma", Font.BOLD, 12));
    this.nextButton.setPreferredSize(new Dimension(120, 40));
    this.nextButton.setEnabled(false);

    this.cname.gridx = 2;
    this.cname.gridy = 4;
    this.cname.anchor = GridBagConstraints.NORTHWEST;
    this.cname.weightx = 0.1;
    this.cname.weighty = 0.1;
    this.cname.gridwidth = 1;
    this.cname.gridheight = 1;
    this.cname.insets = new Insets(60, 16, 5, 5);

    this.name.add(nextButton, cname);

    this.add(name, BorderLayout.WEST);
    this.playerlist = readOnlyModel.getPlayerList();


//    String[][] data = this.playerlist.stream()
//        .map(e -> new String[] { e.getName(), e.getCurrentRoom().getName(),
//            Integer.toString(e.getItemCapacity()), e.isComputerPlayer() ? "Computer" : "Human" })
//        .toArray(String[][]::new);
    
    String col[] = { "Name", "Initial Space", "Item Capacity", "Human/Computer" };


//    this.playerTable = new JTable(data, col);
//    JTableHeader header = this.playerTable.getTableHeader();
    //header.setBackground(Color.YELLOW);
    
    JPanel tablePanel = new JPanel();
    tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
    this.model = new DefaultTableModel(col, 0);
    this.playerTable = new JTable(model);
    
    JScrollPane pane = new JScrollPane(this.playerTable);

    this.playerTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    this.playerTable.setRowHeight(this.playerTable.getRowHeight() + 30);
    this.playerTable.setBackground(new Color(137, 207, 240));

    this.playerTable.setBorder(new EmptyBorder(30, 100, 50, 70));
    
    tablePanel.add(pane);
    
    this.add(tablePanel, BorderLayout.CENTER);

  }

  @Override
  public void paintComponent(Graphics graphics) {
    if (graphics == null) {
      throw new IllegalArgumentException("Graphics cannot be null.\n");
    }
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

  @Override
  public void itemStateChanged(ItemEvent itemEvent) {

    if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
      this.space = (String) itemEvent.getItem();
    }
  }

  public String getPlayerName() {
    return this.nameText.getText();
  }

  public String getSpace() {
    return this.space;
  }

  public int itemCapacity() {
    return Integer.parseInt(itemLimitText.getText());
  }

  public boolean getPlayerType() {
    if ("Human".equals(this.groupType.getSelection().getActionCommand())) {
      return false;
    } else {
      return true;
    }
  }

  public void resetFields() {
    this.nextButton.setEnabled(true);
    this.nameText.setText("");
    this.spaceName.setSelectedIndex(-1);
    this.itemLimitText.setText("");
    this.groupType.clearSelection();
  }
  
  public void addDataToTable() {
    
    this.model.addRow(new Object[] {
        this.nameText.getText(),
        this.spaceName.getSelectedItem(),
        this.itemLimitText.getText(),
        this.groupType.getSelection().getActionCommand()
    });
  }

  public void addActionListener(ActionListener listener) {
    this.addButton.addActionListener(listener);
    this.nextButton.addActionListener(listener);
  }

}
