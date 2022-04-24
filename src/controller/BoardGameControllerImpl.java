package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import theworld.BoardGameModel;
import theworld.PlayerImpl;
import theworldview.BoardGameView;

/**
 * This class is the implementation of controller interface that regulates the
 * communication with the model as well the view.
 *
 */
public class BoardGameControllerImpl implements BoardGameController, Features {
  private final BoardGameView view;
  private final BoardGameModel model;
  private Map<String, BiConsumer<String, String>> actionMap;

  /**
   * Constructor for the controller that initializes the BoardGameView object and
   * the BoardGameModel object.
   *
   * @param view  the BoardGameView type object that is responsible for the visual
   *              representation of the game and taking user inputs
   * @param model the BoardGameModel type object that is responsible for the
   *              actual functionality of the game.
   */
<<<<<<< HEAD
  public BoardGameControllerImpl(BoardGameModel model, BoardGameView view) {
    
    if (model == null || view == null) {
=======
  public BoardGameControllerImpl(BoardGameView view, BoardGameModel model) {

    if (view == null || model == null) {
>>>>>>> 7604aa943ba99da3ca0ae6c90f6e9d02c92120b5
      throw new IllegalArgumentException("BoardGame model or BoardGameView can't be null");
    }
    
    this.model = model;
<<<<<<< HEAD
    this.view = view;
    
=======
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
>>>>>>> 7604aa943ba99da3ca0ae6c90f6e9d02c92120b5
  }

  /**
   * Starts the game and sends and process requests for the actions the user
   * inputs.
   */
  public void start() throws IllegalStateException {
    view.setFeatures(this);
    view.makeVisible();
  }

  @Override
  public void addPlayer(String playerName, String spaceName, int itemCapacity,
      boolean isComputerPlayer) {

    if (playerName == null || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }

    if (spaceName == null || "".equals(spaceName.trim())) {
      throw new IllegalArgumentException("Invalid space name");
    }

    if (itemCapacity < 0) {
      throw new IllegalArgumentException("Item Capacity for a player cannot be negative \n");
    }

    GameController cmd = new AddPlayer(playerName, spaceName, itemCapacity, isComputerPlayer);
    cmd.execute(model);
    this.view.ifPlayerAdded();
  }

  @Override
  public String playComputerPlayer(String playerName) {

    if (playerName == null || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }

    GameController cmd = new ComputerPlayerTurn(playerName);
    cmd.execute(model);
    return cmd.getOutput();
  }

  @Override
  public String getTurns(String playerName) {

    if (playerName == null || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
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
  public void handleKeyPressEvent(String action, String playerName, String itemName) {

    if (action == null || "".equals(action)) {
      throw new IllegalArgumentException("Action cannot be null");
    }

    if (playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("playerName cannot be null");
    }
    try {
      this.actionMap.get(action).accept(playerName, itemName);
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

    if (playerName == null || "".equals(playerName)) {
      throw new IllegalArgumentException("playerName cannot be null");
    }

    GameController cmd = new DisplayPlayerInfo(playerName);
    cmd.execute(model);
    this.view.setPlayerInfoDialog(cmd.getOutput());
  }

  @Override
  public void moveToWorldSelectionScreen() {
    this.view.displayWorldSelectionScreen();
  }
  
  @Override
  public void moveToAddPlayerScreen() {
    this.view.displayAddPlayerScreen();
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