package controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import theworldview.ButtonListener;
import theworldview.KeyboardListener;
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
   * @param boardgameimpl the boardgame facade interface type
   */
  public void start() throws IllegalStateException {
    // view.addClickListener(this);
    view.setFeatures(this);
    view.makeVisible();
    configureButtonListener();
    configureKeyBoardListener();

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
  public String handleKeyPressEvent(String action, String playerName, String roomOrItemName) {
    if ("Attack".equalsIgnoreCase(action)) {
      GameController cmd = new AttackTarget(playerName, roomOrItemName);
      cmd.execute(model);
      return cmd.getOutput();
    } else if ("pickItem".equalsIgnoreCase(action)) {
      GameController cmd = new PickUpItem(playerName, roomOrItemName);
      cmd.execute(model);
      return cmd.getOutput();
    } else {
      GameController cmd = new LookAround(playerName);
      cmd.execute(model);
      return cmd.getOutput();
    }
  }

  @Override
  public String handleMouseClickEvent(int x, int y) throws IllegalStateException {
    GameController cmd = new MovePlayer(x, y);
    cmd.execute(model);
    return cmd.getOutput();
  }

  private void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<>();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("START", () -> {
      this.view.displayWorldSelectionScreen();
    });

    buttonClickedMap.put("Current World", () -> {
      this.view.displayAddPlayerScreen();
    });

    buttonClickedMap.put("New World", () -> {
      this.view.showFileUploadDialog();
    });

    buttonClickedMap.put("Quit", () -> {
      this.view.closeWindow();
    });

    buttonClickedMap.put("ADD", () -> {
      this.view.addPlayers();
    });

    buttonClickedMap.put("CONTINUE", () -> {
      this.view.setOutputMessage(String.format("Game Starts !! Execute the first turn !"));
      this.view.displayGameScreen();
    });
    buttonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(buttonListener);

  }

  private void configureKeyBoardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    keyPresses.put(KeyEvent.VK_P, () -> {
      try {
        String playerName = this.view.getCurrentPlayerName();
        String itemName = view.showPickDialog();
        GameController cmd = new PickUpItem(playerName, itemName);
        cmd.execute(model);
        view.setOutputMessage(cmd.getOutput());
        view.displayGameScreen();
      } catch (IllegalStateException ise) {
        view.setIfTurnExecuted(false);
        view.setOutputMessage(ise.getMessage());
        view.displayGameScreen();
      }
    });

    keyPresses.put(KeyEvent.VK_L, () -> {
      try {
        String playerName = view.getCurrentPlayerName();
        GameController cmd = new LookAround(playerName);
        cmd.execute(model);
        view.setOutputMessage(cmd.getOutput());
        view.setIfTurnExecuted(true);
        view.displayGameScreen();
      } catch (IllegalStateException ise) {
        view.setIfTurnExecuted(false);
        view.setOutputMessage(ise.getMessage());
        view.displayGameScreen();
      }
    });

    keyPresses.put(KeyEvent.VK_A, () -> {
      try {
        String playerName = view.getCurrentPlayerName();
        String itemName = view.showAttackDialog();
        GameController cmd = new AttackTarget(playerName, itemName);
        cmd.execute(model);
        view.setOutputMessage(cmd.getOutput());
        view.setIfTurnExecuted(true);
        view.displayGameScreen();
      } catch (IllegalStateException ise) {
        view.setIfTurnExecuted(false);
        view.setOutputMessage(ise.getMessage());
        view.displayGameScreen();
      }
    });

    KeyboardListener kbd = new KeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);
  }
}