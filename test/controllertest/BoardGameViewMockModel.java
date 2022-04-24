package controllertest;

import java.util.List;
import controller.Features;
import driver.RandomClass;
import theworld.BoardGameFacade;
import theworld.ItemImpl;
import theworldview.BoardGameView;

/**
 * This is GameControllerMockMode class which acts as a mock model for
 * controller testing.
 */
public class BoardGameViewMockModel implements BoardGameView {
  private StringBuilder log;
  private final int uniquecode;

  /**
   * Constructor of GameControllerMockModel.
   * 
   * @param log        to log the output
   * @param uniquecode to show the unique code of the test
   */
  public BoardGameViewMockModel(StringBuilder log, int uniquecode) {
    this.log = log;
    this.uniquecode = uniquecode;
  }

  @Override
  public void refresh() {
    log.append(String.format(String.format("%s", uniquecode)));
  }

  @Override
  public void makeVisible() {
    log.append(String.format(String.format("%s", uniquecode)));
  }

  @Override
  public void displayAddPlayerScreen() {
    log.append(String.format(String.format("%s", uniquecode)));
  }

  @Override
  public void setFeatures(Features f) {
    log.append(String.format(String.format("%s", uniquecode)));
  }

  @Override
  public void displayWorldSelectionScreen() {
    log.append(String.format("%s", uniquecode));
  }

  @Override
  public String getTurnsofPlayers(String playerName) {
    log.append(String.format(String.format("%s %s", playerName, uniquecode)));
    return String.format("%s", uniquecode);
  }

  @Override
  public void closeWindow() {
    log.append(String.format("%s", uniquecode));
  }

  @Override
  public void displayGameScreen() {
    log.append(String.format("%s", uniquecode));
  }

  @Override
  public String showPickDialog() {
    return String.format("%s", uniquecode);
  }

  @Override
  public String showAttackDialog() {
    return String.format("%s", uniquecode);
  }

  @Override
  public String showFileUploadDialog() {
    return String.format("%s", uniquecode);
  }

  @Override
  public void setIfTurnExecuted(boolean ifTurnExecuted) {
    log.append(String.format("%s %s", ifTurnExecuted, uniquecode));
  }

  @Override
  public void setOutputMessage(String outputMessage) {
    log.append(String.format("%s %s", outputMessage, uniquecode));
  }

  @Override
  public String getCurrentPlayerName() {
    return String.format("%d", uniquecode);
  }

  @Override
  public void resetFocus() {
    log.append(String.format("%s", uniquecode));
  }

  @Override
  public void setPlayerInfoDialog(String output) {
    log.append(String.format("%s %s", output, uniquecode));
  }

  @Override
  public void ifPlayerAdded() {
    log.append(String.format("%s", uniquecode));

  }
}
