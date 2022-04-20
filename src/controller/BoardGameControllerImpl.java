package controller;

import java.util.List;
import theworld.BoardGameModel;
import theworld.PlayerImpl;
import theworldview.BoardGameView;

public class BoardGameControllerImpl implements BoardGameController, Features {
  private final BoardGameView view;
  private final BoardGameModel model;

  /**
   * Constructor for the controller.
   * 
   * @param view  the BoardGameView interface
   * @param model the BoardGameModel interface
   */
  public BoardGameControllerImpl(BoardGameView view, BoardGameModel model) {
    if (view == null || model == null) {
      throw new IllegalArgumentException("BoardGame model or BoardGameView can't be null");
    }
    this.view = view;
    this.model = model;
  }

  /**
   * Starts the game and sends and process requests for the actions the user
   * inputs.
   * 
   * @param boardgameimpl the boardgame facade interface type
   */
  public void start() throws IllegalStateException {
    view.addClickListener(this);
    view.setFeatures(this);
    view.makeVisible();

  }

  @Override
  public void addPlayer(String playerName, String spaceName, int itemCapacity,
      boolean isComputerPlayer) {
    try {
      GameController cmd = new AddPlayer(playerName, spaceName, itemCapacity, isComputerPlayer);
      cmd.execute(model);
    } catch (IllegalStateException ise) {
    }

  }
}
