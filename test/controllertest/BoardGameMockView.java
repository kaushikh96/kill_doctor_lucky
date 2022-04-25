package controllertest;


import controller.Features;
import theworldview.BoardGameView;

/**
 * This class mocks the view useful for
 * controller testing which checks if input is correct or not.
 */
public class BoardGameMockView implements BoardGameView {
  private StringBuilder log;
  private final int uniquecode;

  /**
   * Constructor of BoardGameMockVoew that initializes log
   * and the uniquecode.
   *
   * @param log        to log the output
   * @param uniquecode to show the unique code of the test
   */
  public BoardGameMockView(StringBuilder log, int uniquecode) {
    this.log = log;
    this.uniquecode = uniquecode;
  }

  @Override
  public void refresh() {
    log.append(String.format("refresh method called: %d", uniquecode));
  }

  @Override
  public void makeVisible() {
    log.append(String.format("makeVisible method called: %d", uniquecode));
  }

  @Override
  public void displayAddPlayerScreen() {
    log.append(String.format("displayAddPlayerScreen method called: %d", 
        uniquecode));
  }

  @Override
  public void setFeatures(Features f) {
    log.append(String.format("setFeatures method called: %d", uniquecode));
  }

  @Override
  public void displayWorldSelectionScreen() {
    log.append(String.format("displayWorldSelectionScreen method called: %d", uniquecode));
  }

  @Override
  public void closeWindow() {
    log.append(String.format("closeWindow method called: %d", uniquecode));
  }

  @Override
  public void displayGameScreen() {
    log.append(String.format("displayGameScreen method called: %d", uniquecode));
  }

  @Override
  public String showPickDialog() {
    return String.format("showPickDialog merhod called: %d", uniquecode);
  }

  @Override
  public String showAttackDialog() {
    return String.format("showAttackDialog method called: %d", uniquecode);
  }

  @Override
  public String showFileUploadDialog() {
    return String.format("showFileUploadDialog method called: %d", uniquecode);
  }

  @Override
  public void setIfTurnExecuted(boolean ifTurnExecuted) {
    log.append(String.format("setIfTurnExecuted method called: %s %d", ifTurnExecuted, uniquecode));
  }

  @Override
  public void setOutputMessage(String outputMessage) {
    log.append(String.format("setOutputMessage method called: %s %d", outputMessage, uniquecode));
  }

  @Override
  public String getCurrentPlayerName() {
    return String.format("getCurrentPlayerName method called: %d", uniquecode);
  }

  @Override
  public void resetFocus() {
    log.append(String.format("resetFocus method called: %s", uniquecode));
  }

  @Override
  public void setPlayerInfoDialog(String output) {
    log.append(String.format("setPlayerInfoDialog method called: %s %d", output, uniquecode));

  }

  @Override
  public void ifPlayerAdded() {
    log.append(String.format("ifPlayerAdded method called: %d", uniquecode));  
  }

  @Override
  public void setTurnMessage(String turnMessage) {
    log.append(String.format("setTurnMessage method called: %s %d", turnMessage, uniquecode)); 
  }

  @Override
  public void setComputerPlayerMessage(String computerPlayerTurnMessage) {
    log.append(String.format("computerPlayerTurnMessage method called: %s %d", 
        computerPlayerTurnMessage, uniquecode)); 
  }
}