package theworldview;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import theworld.PlayerImpl;
import theworld.ReadOnlyBoardGameModel;
import theworld.SpaceImpl;

/**
 * This panel adds the components that takes in the player information for
 * adding the player and displays the added player information in a JTable which
 * is displayed in the panel.
 *
 */
public class AddPlayerPanel extends JPanel implements ItemListener {

  private final ReadOnlyBoardGameModel readOnlyModel;
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
  private GridBagConstraints add;
  private JPanel name;
  private GridBagConstraints cname;
  private JButton addButton;
  private JButton nextButton;
  private JRadioButton humanType;
  private JRadioButton computerType;
  private ButtonGroup groupType;
  private JTable playerTable;
  private String[] spaceNames;
  private String space;
  private List<PlayerImpl> playerlist;
  private DefaultTableModel model;
  private Features features;
  private String labelTextAdd;
  private String labelPlayerDetails;
  private String labelTextName;
  private String labelTextSpaceName;
  private String labelTextItemLimit;
  private String labelTextPlayerType;
  private String[] col;
  private JPanel tablePanel;
  private JScrollPane pane;

  /**
   * Constructor that initializes the read only model.
   *
   * @param readOnlyModel the readOnlyModel 
   */
  public AddPlayerPanel(ReadOnlyBoardGameModel readOnlyModel) {

    if (readOnlyModel == null) {
      throw new IllegalArgumentException("Read Only Model cannot be null.\n");
    }

    this.readOnlyModel = readOnlyModel;

    this.setLayout(new BorderLayout());

    this.addP = new JPanel(new GridBagLayout());
    this.add = new GridBagConstraints();
    this.addP.setBackground(new Color(137, 207, 240));

    this.labelTextAdd = "<html><font color=#000000 size=20>ADD PLAYER</font><br></html>";
    this.coloredLabelAdd = new JLabel(labelTextAdd, SwingConstants.LEFT);

    this.add.gridx = 0;
    this.add.gridy = 0;
    this.add.anchor = GridBagConstraints.NORTHWEST;
    this.add.weightx = 0.1;
    this.add.weighty = 0.1;
    this.add.insets = new Insets(35, 160, 15, 15);

    this.addP.add(coloredLabelAdd, add);

    this.add(addP, BorderLayout.NORTH);

    this.labelPlayerDetails = "<html><font color=#000000 size=20>Player Details</font><br></html>";
    this.coloredLabelPlayerDetails = new JLabel(labelPlayerDetails, SwingConstants.LEFT);

    this.add.gridx = 1;
    this.add.gridy = 0;
    this.add.anchor = GridBagConstraints.NORTHWEST;
    this.add.weightx = 0.1;
    this.add.weighty = 0.1;
    this.add.insets = new Insets(35, 160, 35, 15);

    this.addP.add(coloredLabelPlayerDetails, add);

    this.add(addP, BorderLayout.NORTH);

    this.name = new JPanel(new GridBagLayout());
    this.cname = new GridBagConstraints();
    this.name.setBackground(new Color(137, 207, 240));

    this.labelTextName = "<html><font color=#000000 size=7>Name</font><br></html>";
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

    this.labelTextSpaceName = "<html><font color=#000000 size=7>Space Name</font><br></html>";
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

    this.labelTextItemLimit = "<html><font color=#000000 size=7>Item Limit</font><br></html>";
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

    this.labelTextPlayerType = "<html><font color=#000000 size=7>Type</font><br></html>";
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

    this.col = new String[] { "Name", "Initial Space", "Item Capacity", "Human/Computer" };

    this.tablePanel = new JPanel();
    this.tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));

    this.model = new DefaultTableModel(col, 0);
    this.playerTable = new JTable(model);

    this.pane = new JScrollPane(this.playerTable);

    this.playerTable.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    this.playerTable.setRowHeight(this.playerTable.getRowHeight() + 30);
    this.playerTable.setBackground(new Color(137, 207, 240));

    this.playerTable.setBorder(new EmptyBorder(30, 100, 50, 70));

    tablePanel.add(pane);

    this.add(tablePanel, BorderLayout.CENTER);

  }

  /**
   * This method gives the space selected by the user from the JComboBox.
   */

  @Override
  public void itemStateChanged(ItemEvent itemEvent) {

    if (itemEvent == null) {
      throw new IllegalArgumentException("ItemEvent cannot be null");
    }

    if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
      this.space = (String) itemEvent.getItem();
    }
  }

  /**
   * This method resets the fields that has player information entered.
   */

  public void resetFields() {
    this.nextButton.setEnabled(true);
    this.nameText.setText("");
    this.spaceName.setSelectedIndex(-1);
    this.itemLimitText.setText("");
    this.groupType.clearSelection();
  }

  /**
   * This method adds data to the Table row-wise on adding each player after the
   * player details have been provided in the screen.
   */
  public void addDataToTable() {

    this.model.addRow(new Object[] { this.nameText.getText(), this.spaceName.getSelectedItem(),
        this.itemLimitText.getText(), this.groupType.getSelection().getActionCommand() });
  }

  /**
   * This method implements the functionalities for different events so that the
   * listener responds accordingly.
   *
   * @param features the callbacks sent as a feature object.
   */

  public void setFeatures(Features features) {

    if (features == null) {
      throw new IllegalArgumentException("Features object cannot be null\n");
    }

    this.features = features;

    this.addButton.addActionListener(l -> {
      try {
        if (!readOnlyModel.getPlayerList().stream()
            .filter(s -> s.getName().equals(nameText.getText())).collect(Collectors.toList())
            .isEmpty()) {
          this.showErrorPopUp("Player already exists in the space");
          resetFields();
        } else {
          features.addPlayer(nameText.getText(), spaceName.getSelectedItem().toString(),
              Integer.parseInt(itemLimitText.getText()),
              "Human".equalsIgnoreCase(groupType.getSelection().getActionCommand()) ? false : true);
        }
      } catch (NumberFormatException nfe) {
        this.showErrorPopUp("Invalid Item Capacity");
        resetFields();
      }
    });
    this.nextButton.addActionListener(l -> features.moveToGameScreen());
  }

  /**
   * This method shows the error in the form of a message dialog.
   *
   * @param message the error message to be displayed in the dialog box
   */
  private void showErrorPopUp(String message) {
    JOptionPane.showMessageDialog(null, message);
  }

}
