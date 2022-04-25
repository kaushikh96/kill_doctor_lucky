package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import theworld.BoardGameModel;
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
   * @param model the BoardGameModel type object that is responsible for the
   *              actual functionality of the game.
   * 
   * @param view  the BoardGameView type object that is responsible for the visual
   *              representation of the game and taking user inputs
   */
  public BoardGameControllerImpl(BoardGameModel model, BoardGameView view) {

    if (model == null || view == null) {
      throw new IllegalArgumentException("BoardGame model or BoardGameView can't be null");
    }

    this.model = model;
    this.view = view;
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
   */
  public void start() throws IllegalStateException {
    view.setFeatures(this);
    view.makeVisible();
    GameController cmd = new GraphicalRepresentation();
    cmd.execute(model);
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
  public void playComputerPlayer(String playerName) {

    if (playerName == null || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    GameController cmd = new ComputerPlayerTurn(playerName);
    cmd.execute(model);
    this.view.setComputerPlayerMessage(cmd.getOutput());
  }

  @Override
  public void getTurns(String playerName) {

    if (playerName == null || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid Player name");
    }
    try {
      GameController cmd = new GetPlayerTurn(playerName);
      cmd.execute(model);
      this.view.setIfTurnExecuted(true);
      this.view.setTurnMessage(cmd.getOutput());
    } catch (IllegalStateException ise) {
      this.view.setIfTurnExecuted(false);
      this.view.setTurnMessage(ise.getMessage());
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
      this.view.displayGameScreen();
    } catch (IllegalStateException ise) {
      this.view.setOutputMessage(ise.getMessage());
      this.view.setIfTurnExecuted(false);
      this.view.displayGameScreen();
    }
  }

  @Override
  public void handleMouseClickEvent(int x, int y) {

    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Co-ordinates can't be negative");
    }
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
  public void handleGetPlayerInfo(String playerName) {

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

    if (inputFileData == null || "".equals(inputFileData)) {
      throw new IllegalArgumentException("Input File has no data in it");
    }
    GameController cmd = new UpdateWorld(inputFileData);
    cmd.execute(model);
    this.view.setPlayerInfoDialog(cmd.getOutput());
    this.view.displayAddPlayerScreen();
  }
}