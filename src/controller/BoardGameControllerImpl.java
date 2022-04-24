package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import theworld.BoardGameModel;
import theworld.PlayerImpl;
import theworldview.BoardGameView;

public class BoardGameControllerImpl implements BoardGameController, Features {
  private final BoardGameView view;
  private final BoardGameModel model;
  private Map<String, BiConsumer<String, String>> actionMap;

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
    this.actionMap = new HashMap<String, BiConsumer<String, String>>();
    this.actionMap.put(Action.ATTACK.toString(), (playerName, itemName) -> {
      GameController cmd = new AttackTarget(playerName, itemName);
      cmd.execute(model);
      this.view.setOutputMessage(cmd.getOutput());
    });
    this.actionMap.put(Action.LOOK_AORUND.toString(), (playerName, itemName) -> {
      GameController cmd = new LookAround(playerName);
      cmd.execute(model);
      this.view.setOutputMessage(cmd.getOutput());
    });
    this.actionMap.put(Action.PICK_ITEM.toString(), (playerName, itemName) -> {
      GameController cmd = new PickUpItem(playerName, itemName);
      cmd.execute(model);
      this.view.setOutputMessage(cmd.getOutput());
    });
    this.actionMap.put(Action.MOVE_PET.toString(), (playerName, itemName) -> {
      GameController cmd = new MovePet(playerName, itemName);
      cmd.execute(model);
      this.view.setOutputMessage(cmd.getOutput());
    });
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
    GameController cmd = new AddPlayer(playerName, spaceName, itemCapacity, isComputerPlayer);
    cmd.execute(model);
    this.view.ifPlayerAdded();
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
      this.view.setIfTurnExecuted(true);
      return cmd.getOutput();
    } catch (IllegalStateException ise) {
      this.view.setIfTurnExecuted(false);
      return String.format(ise.getMessage());
    }
  }

  @Override
  public void handleKeyPressEvent(String action, String playerName, String roomOrItemName) {
    try {
      this.actionMap.get(action).accept(playerName, roomOrItemName);
      this.view.setIfTurnExecuted(true);
    } catch (IllegalStateException ise) {
      this.view.setOutputMessage(ise.getMessage());
      this.view.setIfTurnExecuted(false);
    }
  }

  @Override
  public void handleMouseClickEvent(int x, int y) {
    try {
      GameController cmd = new MovePlayer(x, y);
      cmd.execute(model);
      view.setOutputMessage(cmd.getOutput());
      view.setIfTurnExecuted(true);
      view.displayGameScreen();
    } catch (IllegalStateException ise) {
      view.setOutputMessage(ise.getMessage());
      view.setIfTurnExecuted(false);
      view.displayGameScreen();
    }
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

  @Override
  public void updateWorld(String inputFileData) {
    GameController cmd = new UpdateWorld(inputFileData);
    cmd.execute(model);
    this.view.setPlayerInfoDialog(cmd.getOutput());
    this.view.displayAddPlayerScreen();
  }
}