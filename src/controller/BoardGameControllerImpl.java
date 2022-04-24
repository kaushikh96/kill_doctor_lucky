package controller;

import theworld.BoardGameModel;
import theworldview.BoardGameView;

/**
 * This class is the implementation of controller interface that
 * regulates the communication with the model as well the view.
 *
 */
public class BoardGameControllerImpl implements BoardGameController, Features {
  private final BoardGameView view;
  private final BoardGameModel model;

  /**
   * Constructor for the controller that initializes the BoardGameView object and the 
   * BoardGameModel object.
   *
   * @param view  the BoardGameView type object that is responsible for the visual representation
   of the game and taking user inputs
   * @param model the BoardGameModel type object that is responsible for the actual
   functionality of the game.
   */
  public BoardGameControllerImpl(BoardGameModel model, BoardGameView view) {
    
    if (model == null || view == null) {
      throw new IllegalArgumentException("BoardGame model or BoardGameView can't be null");
    }
    
    this.model = model;
    this.view = view;
    
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
    
    try {
      GameController cmd = new AddPlayer(playerName, spaceName, itemCapacity, isComputerPlayer);
      cmd.execute(model);
    } catch (IllegalStateException ise) {
      throw new IllegalArgumentException("Invalid state while adding a player.\n");
    }
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
      return cmd.getOutput();
    } catch (IllegalStateException ise) {
      throw new IllegalArgumentException("Invalid State\n");
    }
  }

  @Override
  public void handleKeyPressEvent(String action, String playerName, String itemName) {
    
    if (action == null 
        || "".equals(action)) {
      throw new IllegalArgumentException("Action cannot be null");
    }
    
    if (playerName == null
        || "".equals(playerName)) {
      throw new IllegalArgumentException("playerName cannot be null");
    }
    try {
      if ("Attack".equalsIgnoreCase(action)) {
        GameController cmd = new AttackTarget(playerName, itemName);
        cmd.execute(model);
        this.view.setOutputMessage(cmd.getOutput());
      } else if ("pickItem".equalsIgnoreCase(action)) {
        GameController cmd = new PickUpItem(playerName, itemName);
        cmd.execute(model);
        this.view.setOutputMessage(cmd.getOutput());
      } else if ("lookAround".equalsIgnoreCase(action)) {
        GameController cmd = new LookAround(playerName);
        cmd.execute(model);
        this.view.setOutputMessage(cmd.getOutput());
      } else {
        GameController cmd = new MovePet(playerName, itemName);
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
    
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("Coordinated must be of positive range.");
    }
    GameController cmd = new MovePlayer(x, y);
    cmd.execute(model);
    return cmd.getOutput();
  }

  @Override
  public void handlePlayerMouseClickEvent(String playerName) {
    
    if (playerName == null
        || "".equals(playerName)) {
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
}