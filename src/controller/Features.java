package controller;

/**
 * This interface represents a set of features that the game offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller.
 *
 */
public interface Features {
  
  /**
   * This method creates the repective command design Add Player class object by
   * passing the player information retrieved from the view for the player to be
   * added.
   *
   * @param playerName the name of the player to be added.
   * @param spaceName the name of the space to be added.
   * @param itemCapacity the maximum number of items the player can carry.
   * @param isComputerPlayer the boolean valye true if the player is computer else if false 
   from human.
   */
  public void addPlayer(String playerName, String spaceName, int itemCapacity,
      boolean isComputerPlayer);
  
  /**
   * This method creates the respective command design GetPlayerTurn object to get the
   * turn information from the model.
   *
   * @param playerName the name of the player.
   * @return the next name of the player in the game.
   */

  public String getTurns(String playerName);

  /**
   * This method handles the keyPress events by creating objects for command design classes
   * that are responsible for picking an item, making an attempt on the target and look around which
   * brings the functionality from the model.
   *
   * @param action the name of the action to be performed.
   * @param playername the current name of the player in the game.
   * @param itemName the name of the item.
   */
  public void handleKeyPressEvent(String action, String playername, String itemName);

  /**
   * This method handles the mouse click event for the move player action and creates a respective
   * command design class object that calls the move player functionality in the model.
   *
   * @param x the x-coordinate of the space where the click is performed.
   * @param y the y-coordinate of the space where the click is performed.
   */
  public void handleMouseClickEvent(int x, int y);

  /**
   * This method creates the respective command design computer player turn class that 
   * calls the functionality of the computer player present in the model.
   *
   * @param playerName the name of the computer player.
   * @return the result of the action performed in the form of a string.
   */
  public String playComputerPlayer(String playerName);

  /**
   * This method handles the mouse click on the graphical representation of the player to give
   * player information by creating the respective player information command design class that
   * inturn calls the model functionality.
   *
   * @param playerName the name of the player on which the click is performed.
   */
  public void handlePlayerMouseClickEvent(String playerName);

  /**
   * This method calls the displayworldSelectionScreen in the view that removes the current panel
   * and adds the worldselection panel to the frame.
   */
  public void moveToWorldSelectionScreen();

  /**
   * This method calls the displayGameScreen in the view that removes the current add player panel
   * and adds the GamePanel to the frame.
   */
  public void moveToGameScreen();

  public void updateWorld(String inputFileData);
}
