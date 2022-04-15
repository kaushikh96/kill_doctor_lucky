package driver;

import controller.BoardGameController;
import controller.GameConsoleController;
import theworld.BoardGameImpl;
import theworld.BoardGameModel;
import theworldview.BoardGameView;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    try {
      FileReader fr;
      StringBuilder inputdata = new StringBuilder();
      String filepath = "C:\\Users\\Kaushik\\Documents\\Project Files\\myworld.txt";
      int turns = 10;// Integer.parseInt(args[1]);
      Readable input = new InputStreamReader(System.in);
      Appendable output = System.out;
      fr = new FileReader(filepath);
      int data;
      while ((data = fr.read()) != -1) {
        inputdata.append((char) data);
      }
      BoardGameImpl world = Builder.readfile(inputdata.toString());
      BoardGameView boardgameview = new BoardGameView();

      new BoardGameController(boardgameview, world).start();
    } catch (IOException e) {
      System.err.println("File not found");
    } catch (IllegalArgumentException iae) {
      System.err.println(iae.getMessage());
    }
  }
}
