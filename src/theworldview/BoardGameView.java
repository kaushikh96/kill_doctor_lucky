package theworldview;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

import controller.BoardGameController;
import controller.Features;
import theworld.PlayerImpl;

public interface BoardGameView {

  /**
   * Set up the controller to handle click events in this view.
   * 
   * @param listener the controller
   */
  // void addClickListener(Features listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  public void refresh();

  /**
   * Make the view visible to start the game session.
   */
  public void makeVisible();

  public void displayAddPlayerScreen();

  public void setFeatures(Features f);

  public void displayWorldSelectionScreen();

  public String getTurnsofPlayers(String playerName);

  public void closeWindow();

  public void addActionListener(ActionListener actionListener);

  public void addPlayers();
  
  public void displayGameScreen();

  public String showPickDialog();
  
  /**
   * This is to force the view to have a method to set up the keyboard. The name
   * has been chosen deliberately. This is the same method signature to add a key
   * listener in Java Swing.
   *
   * <p>Thus our Swing-based implementation of this interface will already have such
   * a method.
   * 
   * @param listener the listener to add
   */
  void addKeyListener(KeyListener listener);

  public void setOutputMessage(String outputMessage);
  
  public String getCurrentPlayerName();

  void resetFocus();
}
