package controller;

import theworld.BoardGameModel;

public class UpdateWorld implements GameController {
  private final String inputFileData;
  private String outputMessage;

  /**
   * Constructor of a UpdateWorld command class.
   * 
   * @param inputFileData data from the file
   */
  public UpdateWorld(String inputFileData) {
    if (inputFileData == null || "".equals(inputFileData.trim())) {
      throw new IllegalArgumentException("Invalid Input data");
    }
    this.inputFileData = inputFileData;
    this.outputMessage = "";
  }

  @Override
  public void execute(BoardGameModel b) throws IllegalStateException {
    if (b == null) {
      throw new IllegalStateException("model cannot be null");
    }
    this.outputMessage = b.updateWorld(inputFileData);
  }

  @Override
  public String getOutput() {
    return this.outputMessage;
  }
}
