package controller;

import theworld.BoardGameFacade;

/**
 * This class is a part of command design pattern to generate graphical
 * representation.
 */
public class GraphicalRepresentation implements GameController {

  @Override
  public void execute(BoardGameFacade b) throws IllegalArgumentException {
    if (b == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    b.createGraphicalRepresentation();
  }

  @Override
  public String getOutput() {
    return String.format("Graphical Representation of World created successfully");
  }
}
