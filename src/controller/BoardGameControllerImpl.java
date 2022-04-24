package controller;

import theworld.BoardGameModel;
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
   * @param boardgameimpl the boardGame facade interface type
   */
  public void start() throws IllegalStateException {
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

  @Override
  public String playComputerPlayer(String playerName) {
    GameController cmd = new ComputerPlayerTurn(playerName);
    cmd.execute(model);
    return cmd.getOutput();
  }

  @Override
  public String getTurns(String playerName) {
    try {
      GameController cmd = new GetPlayerTurn(playerName);
      cmd.execute(model);
      return cmd.getOutput();
    } catch (IllegalStateException ise) {
      return null;
    }
  }

  @Override
  public void handleKeyPressEvent(String action, String playerName, String roomOrItemName) {
    try {
      if ("Attack".equalsIgnoreCase(action)) {
        GameController cmd = new AttackTarget(playerName, roomOrItemName);
        cmd.execute(model);
        this.view.setOutputMessage(cmd.getOutput());
      } else if ("pickItem".equalsIgnoreCase(action)) {
        GameController cmd = new PickUpItem(playerName, roomOrItemName);
        cmd.execute(model);
        this.view.setOutputMessage(cmd.getOutput());
      } else if ("lookAround".equalsIgnoreCase(action)) {
        GameController cmd = new LookAround(playerName);
        cmd.execute(model);
        this.view.setOutputMessage(cmd.getOutput());
      } else {
        GameController cmd = new MovePet(playerName, roomOrItemName);
        cmd.execute(model);
        this.view.setOutputMessage(cmd.getOutput());
      }
      this.view.setIfTurnExecuted(true);
    } catch (IllegalStateException ise) {
      this.view.setOutputMessage(ise.getMessage());
      this.view.setIfTurnExecuted(false);
    }
  }

  @Override
  public String handleMouseClickEvent(int x, int y) throws IllegalStateException {
    GameController cmd = new MovePlayer(x, y);
    cmd.execute(model);
    return cmd.getOutput();
  }

  @Override
  public void handlePlayerMouseClickEvent(String playerName) {
    GameController cmd = new DisplayPlayerInfo(playerName);
    cmd.execute(model);
    this.view.setPlayerInfoDialog(cmd.getOutput());
  }

  @Override
  public void moveToAddPlayerScreen() {
    this.view.displayWorldSelectionScreen();
  }

  @Override
  public void moveToGameScreen() {
    this.view.displayGameScreen();
  }
}