package driver;

import controller.BoardGameControllerImpl;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import theworld.BoardGameImpl;
import theworldview.BoardGameView;
import theworldview.BoardGameViewImpl;

/**
 * A Driver class used to demonstrate the features of the classes.
 *
 */
public class Driver {
  /**
   * A main method reads the text file for the world that was created.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {

    if (args.length == 0) {
      throw new IllegalArgumentException("Pass the file to be parsed");
    }

    if (args.length == 1) {
      throw new IllegalArgumentException("Maximum Number Of Game Turns must be specified");
    }
    try {
      FileReader fr;
      StringBuilder inputdata = new StringBuilder();
      String filepath = args[0];
      int turns = Integer.parseInt(args[1]);
      fr = new FileReader(filepath);
      int data;
      while ((data = fr.read()) != -1) {
        inputdata.append((char) data);
      }
      BoardGameImpl world = Builder.readfile(inputdata.toString(), turns);
      BoardGameView boardGameView = new BoardGameViewImpl("Board Game View", world);
      new BoardGameControllerImpl(world, boardGameView).start();
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found");
    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException(iae.getMessage());
    }
  }
}
