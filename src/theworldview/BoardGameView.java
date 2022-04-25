package theworldview;

import controller.Features;

/**
 * This is the view interface which displays the game and provides a visual
 * representation of the game to the user.
 */

public interface BoardGameView {

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  public void refresh();

  /**
   * Make the view visible to start the game session.
   */
  public void makeVisible();

  /**
   * Method displays the screen responsible for taking the player input from the
   * user.
   */
  public void displayAddPlayerScreen();

  /**
   * Get the set of feature callbacks that the view can use.
   *
   * @param f the set of feature callbacks as a Features object
   */
  public void setFeatures(Features f);

  /**
   * Method displays the screen which allows a user to select a world in which a
   * player can play.
   */
  public void displayWorldSelectionScreen();

  /**
   * This method is used for quitting the game by closing the current window.
   */
  public void closeWindow();

  /**
   * This method displays the screen which contains the game board and the
   * information of the game such as the game status, the player indexing and the
   * result of the action.
   */
  public void displayGameScreen();

  /**
   * This method displays a dialog with a combo box that allows user to pick an
   * item.
   *
   * @return the itemName selected by the user.
   */
  public String showPickDialog();

  /**
   * This method displays a dialog to choose an item from the item list the player
   * has to make an attempt to kill the target.
   *
   * @return the name of the item chosen to make a kill attempt on the target.
   */
  public String showAttackDialog();

  /**
   * This method allows a used to choose a text file containing world information
   * through a pop-up dialog box and this is triggered on choosing New World Menu
   * Item.
   *
   * @return the result of the file upload success or failure message.
   */
  public String showFileUploadDialog();

  /**
   * This method checks if a turn is executed or not in the game.
   *
   * @param ifTurnExecuted the boolean value that is true if a turn is performed
   *                       else false.
   */
  public void setIfTurnExecuted(boolean ifTurnExecuted);

  /**
   * This method sets the result of each action in the form of a string.
   *
   * @param outputMessage the message which represents the result of the action
   *                      performed.
   */
  public void setOutputMessage(String outputMessage);

  /**
   * This method gets the current player name who is playing the game.
   *
   * @return the name of the current player.
   */
  public String getCurrentPlayerName();

  /**
   * This method resets the focus of the current frame.
   */
  public void resetFocus();

  /**
   * This method displays a dialog bog showing the information of the player when
   * clicked on the player graphical representation.
   *
   * @param output the player information to be shown in the Dialog box.
   */
  public void setPlayerInfoDialog(String output);

  /**
   * This method adds the player data to the table on the panel and 
   * then resets the fields again on the panel for other data to be entered.
   */
  public void ifPlayerAdded();
  
  /**
   * This method sets the current player turn message.
   *
   * @param turnMessage the turn message to be set.
   */

  public void setTurnMessage(String turnMessage);

  /**
   * This method sets the computer Player action message.
   *
   * @param computerPlayerTurnMessage the message set for the computer player.
   */
  public void setComputerPlayerMessage(String computerPlayerTurnMessage);
}
