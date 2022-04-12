package controller;

import java.io.IOException;
import java.util.Scanner;
import theworld.BoardGameFacade;
import theworld.RandomClass;

/**
 * This is a GameConsoleController class used to handle the controller related
 * operations.
 */
public class GameConsoleController {
  private final Appendable out;
  private final Scanner scan;
  private int turns;
  private String playername;
  private String playernamebackup;
  private String playertype;
  private String itemsonplayer;
  private String displaymessage;
  private String outputmessage;
  private boolean disablePlayerAdding;
  private boolean isGameEnd;
  private boolean ifErrorValues;
  private boolean ifPlayerAdded;
  private RandomClass randomref;
  private GameController command;

  /**
   * Constructor for the controller.
   * 
   * @param in        the source to read from
   * @param out       the target to print to
   * @param turns     maximum number of turns
   * @param randomref random class reference
   */
  public GameConsoleController(Readable in, Appendable out, int turns, RandomClass randomref) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable can't be null");
    }
    if (turns == 0) {
      throw new IllegalArgumentException("Turns at the beginnig cannot be zero");
    }
    if (randomref == null) {
      throw new IllegalArgumentException("Rnadom Variable cannot be null");
    }
    this.out = out;
    scan = new Scanner(in);
    this.turns = turns;
    this.disablePlayerAdding = false;
    this.isGameEnd = false;
    this.ifErrorValues = true;
    this.randomref = randomref;
  }

  /**
   * Starts the game and sends and process requests for the actions the user
   * inputs.
   * 
   * @param boardgameimpl the boardgame facade interface type
   */
  public void start(BoardGameFacade boardgameimpl) throws IllegalStateException {
    if (boardgameimpl == null) {
      throw new IllegalArgumentException("BoardGameFacade reference cannot be null");
    } else {
      try {
        out.append("Welcome to my World !\n");
        out.append("Add a player\n");
        out.append(this.switchCase(boardgameimpl, "9"));
        out.append("Choose from the below options !\n");
        out.append("1 -- > Create a graphical representation\n");
        out.append("2 -- > Turn: Look Around\n");
        out.append("3 -- > Turn: Move to Space\n");
        out.append("4 -- > Turn: Pick up an item\n");
        out.append("5 -- > Turn: Move Pet\n");
        out.append("6 -- > Turn: Attack Target\n");
        out.append("7 -- > Display Room Info\n");
        out.append("8 -- > Display Player Info\n");
        if (this.disablePlayerAdding == false) {
          out.append("9 -- > Add a player\n");
        }

        while (scan.hasNext()) {
          String in = scan.nextLine();
          if ("2".equalsIgnoreCase(in) || "3".equalsIgnoreCase(in) || "4".equalsIgnoreCase(in)
              || "5".equalsIgnoreCase(in) || "6".equalsIgnoreCase(in)) {
            if (this.ifPlayerAdded) {
              disablePlayerAdding = true;
            }
          }
          if (disablePlayerAdding == true) {
            while (this.turns != 0 && playername != null) {
              if ("2".equalsIgnoreCase(in) || "3".equalsIgnoreCase(in) || "4".equalsIgnoreCase(in)
                  || "5".equalsIgnoreCase(in) || "6".equalsIgnoreCase(in)
                  || "1".equalsIgnoreCase(in) || "7".equalsIgnoreCase(in)
                  || "8".equalsIgnoreCase(in)) {

                if (ifErrorValues) {
                  if (!this.ifPlayerAdded) {
                    GameController cmd = new GetPlayerTurn(playername);
                    cmd.execute(boardgameimpl);
                    outputmessage = cmd.getOutput();
                  }
                  this.ifPlayerAdded = false;
                  playername = outputmessage.split("Turn of ")[1].split(";")[0];
                  displaymessage = outputmessage.split("; Items:")[0];
                  playernamebackup = playername;
                  itemsonplayer = outputmessage.split("Items:")[1].split(", PlayerType")[0];
                  itemsonplayer = itemsonplayer.substring(1, itemsonplayer.length() - 2);
                  if (outputmessage.contains("true")) {
                    playertype = "True";
                  } else {
                    playertype = "False";
                  }
                } else {
                  playername = playernamebackup;
                }
                out.append(String.format("%s\n", displaymessage.replace(";", ",")));
                if ("True".equalsIgnoreCase(playertype)) {
                  try {
                    command = new ComputerPlayerTurn(playername, randomref);
                    command.execute(boardgameimpl);
                    out.append(command.getOutput());
                    if (command.getOutput().contains("Wins")) {
                      this.isGameEnd = true;
                      break;
                    }
                    this.turns--;
                    this.out.append(String.format("Turn Executed! Turns remaining: %s\n\n",
                        Integer.toString(this.turns)));
                    if (this.turns == 0) {
                      this.isGameEnd = true;
                      out.append("Target Character Escapes\n");
                      break;
                    }
                  } catch (IllegalStateException ise) {
                    out.append(ise.getMessage());
                  }
                  continue;
                }
                out.append("Select an action to be performed\n");
                String action = scan.nextLine();

                if ("7".equalsIgnoreCase(action) || "8".equalsIgnoreCase(action)) {
                  this.ifErrorValues = false;
                }
                if ("Q".equalsIgnoreCase(action)) {
                  out.append("Game Quit!\n");
                  this.isGameEnd = true;
                  break;
                }
                try {
                  String outputmessage = this.switchCase(boardgameimpl, action);
                  if (outputmessage.contains("Wins")) {
                    out.append(outputmessage);
                    this.isGameEnd = true;
                    break;
                  }
                  out.append(outputmessage);
                  this.ifErrorValues = false;
                  if ("2".equalsIgnoreCase(action) || "3".equalsIgnoreCase(action)
                      || "4".equalsIgnoreCase(action) || "5".equalsIgnoreCase(action)
                      || "6".equalsIgnoreCase(action)) {
                    this.ifErrorValues = true;
                  }
                } catch (IllegalStateException iae) {
                  this.out.append(iae.getMessage());
                  this.ifErrorValues = false;
                  continue;
                }

                if ("2".equalsIgnoreCase(action) || "3".equalsIgnoreCase(action)
                    || "4".equalsIgnoreCase(action) || "5".equalsIgnoreCase(action)
                    || "6".equalsIgnoreCase(action)) {
                  this.turns--;
                  this.out.append(String.format("Turn Executed! Turns remaining : %s\n\n",
                      Integer.toString(this.turns)));
                  if (this.turns == 0) {
                    this.isGameEnd = true;
                    out.append("Target Character Escapes\n");
                    break;
                  }
                }
              }
              out.append("Choose from the below options !\n");

              out.append("1 -- > Create a graphical representation\n");
              out.append("2 -- > Turn: Look Around\n");
              out.append("3 -- > Turn: Move to Space\n");
              out.append("4 -- > Turn: Pick up an item\n");
              out.append("5 -- > Turn: Move Pet\n");
              out.append("6 -- > Turn: Attack Target\n");
              out.append("7 -- > Display Room Info\n");
              out.append("8 -- > Display Player Info\n");
            }
          }
          if (disablePlayerAdding == false) {
            try {
              out.append(this.switchCase(boardgameimpl, in));
            } catch (IllegalStateException iae) {
              out.append(iae.getMessage());
            }
          }
          if (this.isGameEnd) {
            out.append("Game Ended");
            break;
          } else {
            out.append("Choose from the below options !\n");

            out.append("1 -- > Create a graphical representation\n");
            out.append("2 -- > Turn: Look Around\n");
            out.append("3 -- > Turn: Move to Space\n");
            out.append("4 -- > Turn: Pick up an item\n");
            out.append("5 -- > Turn: Move Pet\n");
            out.append("6 -- > Turn: Attack Target\n");
            out.append("7 -- > Display Room Info\n");
            out.append("8 -- > Display Player Info\n");
            if (this.disablePlayerAdding == false) {
              out.append("9 -- > Add a player\n");
            }
          }
        }
        scan.close();
      } catch (IOException ioe) {
        throw new IllegalStateException();
      }
    }
  }

  private String switchCase(BoardGameFacade boardgameimpl, String in) throws IllegalStateException {
    boolean ifComputerPlayer = false;
    String itemname;
    String roomname;
    try {
      switch (in) {
        case "9":
          if (disablePlayerAdding == false) {
            out.append("Is it a human or a computer player ? Select C or H\n");
            String c = scan.nextLine();
            if ("H".equalsIgnoreCase(c)) {
              ifComputerPlayer = false;
            } else if ("C".equalsIgnoreCase(c)) {
              ifComputerPlayer = true;
            } else {
              return String.format("Invalid value entered\n");
            }
            out.append("Enter a player name\n");
            String name = scan.nextLine();
            out.append("Enter a space to enter\n");
            String space = scan.nextLine();
            out.append("Enter item capacity (should be a number)\n");
            String cap = scan.nextLine();
            try {
              int capint = Integer.parseInt(cap);

              command = new AddPlayer(name, space, capint, ifComputerPlayer);
              if (!this.ifPlayerAdded) {
                outputmessage = String.format(
                    "Turn of %s; Current Room: %s; Items: No items on player, PlayerType: %s)",
                    name, space, Boolean.toString(ifComputerPlayer));
              }
              this.ifPlayerAdded = true;
              command.execute(boardgameimpl);
              playername = name;
            } catch (IllegalStateException ise) {
              this.out.append(ise.getMessage());
            } catch (NumberFormatException nfe) {
              return String.format("Invalid number %s\n", cap);
            } catch (IllegalArgumentException iae) {
              this.out.append(iae.getMessage());
            }
          } else {
            out.append("Cannot add player after the game starts\n\n");
          }
          break;
        case "1":
          new GraphicalRepresentation().execute(boardgameimpl);
          out.append("Graphical representation created\n");
          break;
        case "2":
          command = new LookAround(playername);
          command.execute(boardgameimpl);
          break;
        case "3":
          out.append("Enter the room name the player has to move to\n");
          roomname = scan.nextLine();
          command = new MoveSpace(playername, roomname);
          command.execute(boardgameimpl);
          break;
        case "4":
          out.append("Enter the item name to be picked up\n");
          itemname = scan.nextLine();
          command = new PickUpItem(playername, itemname);
          command.execute(boardgameimpl);
          break;
        case "5":
          out.append("Enter the space name the pet has to be moved to\n");
          roomname = scan.nextLine();
          command = new MovePet(playername, roomname);
          command.execute(boardgameimpl);
          break;
        case "6":
          out.append(String.format("Items on player: %s\n", itemsonplayer));
          StringBuilder sb = new StringBuilder();
          sb.append("Enter the item name used to attack target\n");
          sb.append("(You can also enter 'poke' to poke in the eye)\n");
          out.append(sb);
          itemname = scan.nextLine();
          command = new AttackTarget(playername, itemname);
          command.execute(boardgameimpl);
          break;
        case "7":
          out.append("Enter the Room name for information has to be displayed\n");
          roomname = scan.nextLine();
          command = new DisplayRoomInfo(roomname);
          command.execute(boardgameimpl);
          break;
        case "8":
          out.append("Enter the Player name for information to be displayed\n");
          playername = scan.nextLine();
          command = new DisplayPlayerInfo(playername);
          command.execute(boardgameimpl);
          break;
        default:
          out.append(String.format("Invalid command: %s\n", in));
          break;
      }
    } catch (IOException e) {
      throw new IllegalStateException("Error occured");
    }
    if (command.getOutput() == null) {
      return "";
    } else {
      return command.getOutput();
    }
  }
}
