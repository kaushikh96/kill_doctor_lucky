package controllertest;


import controller.Features;
import theworldview.BoardGameView;

/**
 * This class mocks the view to test for controller exceptions.
 */
public class MockViewException implements BoardGameView {
  private StringBuilder log;
  private final int uniquecode;

  /**
   * Constructor of MockViewException that initializes log
   * and the uniquecode.
   *
   * @param log        to log the output
   * @param uniquecode to show the unique code of the test
   */
  public MockViewException(StringBuilder log, int uniquecode) {
    this.log = log;
    this.uniquecode = uniquecode;
  }

  @Override
  public void refresh() {
    log.append(String.format("refresh method for exception called: %d", uniquecode));
  }

  @Override
  public void makeVisible() {
    log.append(String.format("makeVisible method for exception called: %d", uniquecode));
  }

  @Override
  public void displayAddPlayerScreen() {
    log.append(String.format("displayAddPlayerScreen method for exception called: %d", 
        uniquecode));
  }

  @Override
  public void setFeatures(Features f) {
    log.append(String.format("setFeatures method for exception called: %d", uniquecode));
  }

  @Override
  public void displayWorldSelectionScreen() {
    log.append(String.format("displayWorldSelectionScreen method for exception called: %d", 
        uniquecode));
  }

  @Override
  public void closeWindow() {
    log.append(String.format("closeWindow method for exception called: %d", uniquecode));
  }

  @Override
  public void displayGameScreen() {
    log.append(String.format("displayGameScreen method for exception called: %d", uniquecode));
  }

  @Override
  public String showPickDialog() {
    return String.format("showPickDialog merhod for exception called: %d", uniquecode);
  }

  @Override
  public String showAttackDialog() {
    return String.format("showAttackDialog method for exception called: %d", uniquecode);
  }

  @Override
  public String showFileUploadDialog() {
    return String.format("showFileUploadDialog method for exception called: %d", uniquecode);
  }

  @Override
  public void setIfTurnExecuted(boolean ifTurnExecuted) {
    log.append(String.format("setIfTurnExecuted method for exception called: %s %d", 
        ifTurnExecuted, uniquecode));
  }

  @Override
  public void setOutputMessage(String outputMessage) {
    log.append(String.format("setOutputMessage method for exception called: %s %d", 
        outputMessage, uniquecode));
  }

  @Override
  public String getCurrentPlayerName() {
    return String.format("getCurrentPlayerName method for exception called: %d", uniquecode);
  }

  @Override
  public void resetFocus() {
    log.append(String.format("resetFocus method for exception called: %s", uniquecode));
  }

  @Override
  public void setPlayerInfoDialog(String output) {
    log.append(String.format("setPlayerInfoDialog method for exception called: %s %d", 
        output, uniquecode));

  }

  @Override
  public void ifPlayerAdded() {
    log.append(String.format("ifPlayerAdded method for exception called: %s", uniquecode));  
  }

  @Override
  public void setTurnMessage(String turnMessage) {
    log.append(String.format("setTurnMessage method for exception called: %s %d", 
        turnMessage, uniquecode));  
  }

  @Override
  public void setComputerPlayerMessage(String computerPlayerTurnMessage) {
    log.append(String.format("setComputerPlayerMessage method for exception called: %s %d", 
        computerPlayerTurnMessage, uniquecode));
  }
}