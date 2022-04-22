package theworldview;

import java.awt.event.ActionListener;
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
  void refresh();

  /**
   * Make the view visible to start the game session.
   */
  void makeVisible();

  public void displayAddPlayerScreen();

  public void setFeatures(Features f);

  public void displayWorldSelectionScreen();

  public String getTurnsofPlayers(String playerName);

  public void closeWindow();

  void addActionListener(ActionListener actionListener);

  void addPlayers();

  public void displayGameScreen();

  public void setOutputMessage(String outputMessage);
}
