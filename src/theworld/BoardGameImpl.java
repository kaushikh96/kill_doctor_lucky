package theworld;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;

import driver.RandomClass;

/**
 * This class represents a BoardGameImplementation.
 */
public class BoardGameImpl implements ReadOnlyBoardGameModel {

  private TargetCharacterImpl targetcharacter;
  private PetImpl targetpet;
  private final String name;
  private List<SpaceImpl> spacelist;
  private BufferedImage bufferedimage;
  private Graphics2D graphics2d;
  private final List<Integer> worldcoordinates;
  private final RandomClass randomref;
  private String neighboursstring;
  private String itemsstring;
  private String roomstring;
  private List<PlayerImpl> playerlist;
  private List<SpaceImpl> listvisitednodes;
  private Stack<SpaceImpl> roomstack;
  private String playerstring;
  private String playeritemstring;
  private String compdisplaymessage;
  private String neighbourinfo;
  private boolean isBackTrack;

  /**
   * Construct a BoardGameImpl object that has the provided targetcharacter, name,
   * spacelist, worldcoordinates, randomref.
   * 
   *
   * @param target           target character name
   * @param name             name of the world
   * @param spacelist        list of spaces
   * @param worldcoordinates world coordinates
   * @param targetpet        details of the target character pet
   * @param randomref        the variable of the type Random
   */
  public BoardGameImpl(TargetCharacterImpl target, String name, List<SpaceImpl> spacelist,
      List<Integer> worldcoordinates, PetImpl targetpet, RandomClass randomref) {
    if (target == null) {
      throw new IllegalArgumentException("Target character entity cannot be null");
    }
    if (name == null || name.trim().equals("")) {
      throw new IllegalArgumentException("Name cannot be empty or null");
    }
    if (spacelist == null) {
      throw new IllegalArgumentException("Space list cannot be null");
    }
    if (worldcoordinates == null) {
      throw new IllegalArgumentException("World coordinates cannot be null or empty");
    }
    if (targetpet == null) {
      throw new IllegalArgumentException("Target Pet entity cannot be null");
    }
    if (randomref == null) {
      throw new IllegalArgumentException("Random Variable cannot be null");
    }
    this.targetcharacter = target;
    this.targetpet = targetpet;
    this.name = name;
    this.spacelist = spacelist;
    this.worldcoordinates = worldcoordinates;
    this.playerlist = new ArrayList<>();
    this.randomref = randomref;
    this.listvisitednodes = new ArrayList<>();
    this.roomstack = new Stack<SpaceImpl>();
    int height = worldcoordinates.get(0);
    int width = worldcoordinates.get(1);
    this.isBackTrack = false;
    this.bufferedimage = new BufferedImage(width * 60, height * 30, BufferedImage.TYPE_INT_RGB);
    this.graphics2d = (Graphics2D) this.bufferedimage.getGraphics();
    this.graphics2d.setColor(Color.WHITE);
    this.graphics2d.fillRect(0, 0, width * 60, height * 30);
    this.neighboursstring = "";
    this.itemsstring = "";
    this.roomstring = "";
    this.playerstring = "";
    this.compdisplaymessage = "";
    this.neighbourinfo = "";
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public List<Integer> getWorldCoordinates() {
    List<Integer> copy = new ArrayList<>();
    copy.addAll(this.worldcoordinates);
    return copy;
  }

  @Override
  public TargetCharacterImpl getTargetCharacterImpl() {
    return this.targetcharacter;
  }

  @Override
  public PetImpl getTargetPetImpl() {
    return this.targetpet;
  }

  @Override
  public List<SpaceImpl> getAllVisibleSpaces(SpaceImpl space) {
    if (space == null) {
      throw new IllegalArgumentException("Space cannot be null");
    } else {
      SpaceImpl space1 = spacelist.stream().filter(s -> s.getRoomId() == space.getRoomId())
          .collect(Collectors.toList()).get(0);
      List<Integer> coordinates = space1.getRoomLocation();
      neighboursstring = "";
      int topleftrowcoordinate = coordinates.get(0);
      int topleftcolumncoordinate = coordinates.get(1);
      int bottomrightrowcoordinate = coordinates.get(2);
      int bottomrightcolumncoordinate = coordinates.get(3);
      List<SpaceImpl> tempspacelist1 = new ArrayList<>();
      List<SpaceImpl> tempspacelist2 = new ArrayList<>();

      List<SpaceImpl> neighbours = new ArrayList<>();
      tempspacelist1 = spacelist.stream()
          .filter(s -> s.getRoomLocation().get(2) == topleftrowcoordinate - 1)
          .collect(Collectors.toList());
      for (int j = 0; j < tempspacelist1.size(); j++) {
        if (((topleftcolumncoordinate <= tempspacelist1.get(j).getRoomLocation().get(1)
            && bottomrightcolumncoordinate >= tempspacelist1.get(j).getRoomLocation().get(1))
            || (bottomrightcolumncoordinate >= tempspacelist1.get(j).getRoomLocation().get(3)
                && topleftcolumncoordinate <= tempspacelist1.get(j).getRoomLocation().get(3)))
            && (!(topleftcolumncoordinate == tempspacelist1.get(j).getRoomLocation().get(3))
                || !(bottomrightcolumncoordinate == tempspacelist1.get(j).getRoomLocation()
                    .get(1)))) {
          neighbours.add(tempspacelist1.get(j));
        }
      }
      tempspacelist2 = spacelist.stream()
          .filter(t -> t.getRoomLocation().get(3) == topleftcolumncoordinate - 1)
          .collect(Collectors.toList());
      for (int i = 0; i < tempspacelist2.size(); i++) {
        if (((topleftrowcoordinate <= tempspacelist2.get(i).getRoomLocation().get(0)
            && bottomrightrowcoordinate >= tempspacelist2.get(i).getRoomLocation().get(0))
            || (topleftrowcoordinate <= tempspacelist2.get(i).getRoomLocation().get(2)
                && bottomrightrowcoordinate >= tempspacelist2.get(i).getRoomLocation().get(2)))
            && (!(topleftrowcoordinate == tempspacelist2.get(i).getRoomLocation().get(2))
                || !(bottomrightrowcoordinate == tempspacelist2.get(i).getRoomLocation().get(1)))) {
          neighbours.add(tempspacelist2.get(i));
        }
      }
      List<SpaceImpl> tempspacelist3 = new ArrayList<>();
      tempspacelist3 = spacelist.stream()
          .filter(t -> t.getRoomLocation().get(0) == bottomrightrowcoordinate + 1)
          .collect(Collectors.toList());
      for (int k = 0; k < tempspacelist3.size(); k++) {
        if (((topleftcolumncoordinate <= tempspacelist3.get(k).getRoomLocation().get(1)
            && bottomrightcolumncoordinate >= tempspacelist3.get(k).getRoomLocation().get(1))
            || (bottomrightcolumncoordinate >= tempspacelist3.get(k).getRoomLocation().get(3)
                && topleftcolumncoordinate <= tempspacelist3.get(k).getRoomLocation().get(3)))
            && (!(topleftcolumncoordinate == tempspacelist3.get(k).getRoomLocation().get(3))
                || !(bottomrightcolumncoordinate == tempspacelist3.get(k).getRoomLocation()
                    .get(1)))) {
          neighbours.add(tempspacelist3.get(k));
        }
      }
      List<SpaceImpl> tempspacelist4 = new ArrayList<>();
      tempspacelist4 = spacelist.stream()
          .filter(t -> t.getRoomLocation().get(1) == bottomrightcolumncoordinate + 1)
          .collect(Collectors.toList());
      for (int m = 0; m < tempspacelist4.size(); m++) {
        if (((topleftrowcoordinate <= tempspacelist4.get(m).getRoomLocation().get(0)
            && bottomrightrowcoordinate >= tempspacelist4.get(m).getRoomLocation().get(0))
            || (topleftrowcoordinate <= tempspacelist4.get(m).getRoomLocation().get(2)
                && bottomrightrowcoordinate >= tempspacelist4.get(m).getRoomLocation().get(2)))
            && (!(topleftrowcoordinate == tempspacelist4.get(m).getRoomLocation().get(2))
                || !(bottomrightrowcoordinate == tempspacelist4.get(m).getRoomLocation().get(0)))) {
          neighbours.add(tempspacelist4.get(m));
        }
      }
      neighbours.stream().forEach(t -> {
        neighboursstring = neighboursstring + ", " + t.getName();
      });
      neighboursstring = neighboursstring.substring(2);
      return neighbours;
    }
  }

  @Override
  public String getNextTargetCharacterRoom() throws IllegalStateException {
    return targetcharacter.getTargetCharacterNextRoom(this.getSpaceList());
  }

  @Override
  public String getRoomInfo(String spacename) {
    if (spacename == null || "".equals(spacename.trim())) {
      throw new IllegalArgumentException("Spacename cannot be null");
    } else {
      List<SpaceImpl> templist = getSpaceList();
      List<SpaceImpl> spaceobjectlist = templist.stream()
          .filter(s -> s.getName().trim().equalsIgnoreCase(spacename.trim()))
          .collect(Collectors.toList());
      if (!spaceobjectlist.isEmpty()) {
        itemsstring = "";
        roomstring = "";
        playerstring = "";
        SpaceImpl spaceobject = spaceobjectlist.get(0);
        Map<String, Object> spaceinfo = new HashMap<String, Object>();
        roomstring = spaceobject.getName();
        spaceinfo.put("Name", spaceobject.getName());
        spaceinfo.put("Items", spaceobject.getItems());
        spaceinfo.put("Players", this.getPlayerList());
        List<SpaceImpl> neighbours = getAllVisibleSpaces(spaceobject);
        spaceinfo.put("Neighbours", neighbours);
        spaceinfo.entrySet().forEach(entry -> {
          if (entry.getKey().equals("Items")) {
            List<ItemImpl> items = spaceobject.getItems();

            if (items != null && items.size() > 0) {
              items.forEach(s -> {
                StringBuilder sb = new StringBuilder();
                sb.append(itemsstring);
                sb.append(", ");
                sb.append(s.getName());
                itemsstring = sb.toString();
              });
              itemsstring = itemsstring.substring(2);
            } else {
              itemsstring = "No items available";
            }
          } else if (entry.getKey().equals("Players")) {
            List<PlayerImpl> players = this.getPlayerList().stream().filter(
                p -> p.getCurrentRoom().getName().trim().equalsIgnoreCase(spaceobject.getName()))
                .collect(Collectors.toList());

            if (players.size() > 0) {
              players.forEach(s -> {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(playerstring);
                sb1.append(", ");
                sb1.append(s.getName());
                playerstring = sb1.toString();
              });
              playerstring = playerstring.substring(2);
            } else {
              playerstring = "No players available";
            }
          }
        });
      } else {
        return String.format("Invalid space name\n");
      }
      if (spacename.trim().equalsIgnoreCase(this.targetpet.getCurrentRoom().getName().trim())) {
        return String.format("%s;\n Pet: %s)\n\n",
            this.toString().substring(0, this.toString().length() - 3), this.targetpet.getName());
      } else {
        return String.format("%s\n", this.toString());
      }
    }
  }

  @Override
  public String getPlayerInfo(String playername) throws IllegalStateException {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalArgumentException("Invalid player name");
    } else {
      List<PlayerImpl> playerlist = this.playerlist.stream()
          .filter(s -> s.getName().trim().equalsIgnoreCase(playername.trim()))
          .collect(Collectors.toList());
      PlayerImpl player;
      if (!playerlist.isEmpty()) {
        player = playerlist.get(0);
        List<ItemImpl> playeritems = player.getItems();
        playeritemstring = "";
        if (playeritems.size() > 0) {
          playeritems.stream().forEach(s -> {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(playeritemstring);
            sb3.append(", ");
            sb3.append(s.getName());
            playeritemstring = sb3.toString();
          });
          playeritemstring = playeritemstring.substring(2);
        } else {
          playeritemstring = "No items on the player";
        }
      } else {
        throw new IllegalStateException("Player doesn't exist");
      }
      return String.format("Player Info (Name: %s; Current Room: %s; Items: %s)\n",
          player.getName(), player.getCurrentRoom().getName(), playeritemstring);
    }
  }

  @Override
  public void createGraphicalRepresentation() {
    try {
      List<SpaceImpl> roomlist = getSpaceList();
      this.graphics2d.setColor(Color.BLACK);
      roomlist.stream().forEach(s -> {
        List<Integer> coord = s.getRoomLocation();
        this.graphics2d.drawRect(coord.get(1) * 60, coord.get(0) * 30,
            (coord.get(3) - coord.get(1)) * 60 + 59, (coord.get(2) - coord.get(0)) * 30 + 29);
        this.graphics2d.drawString(s.getName(), coord.get(1) * 60 + 19, coord.get(0) * 30 + 29);
      });
      File newfile = new File("rep.jpg");
      ImageIO.write(this.bufferedimage, "jpg", newfile);
    } catch (IOException io) {
      throw new IllegalArgumentException("Invalid buffer image");
    }
  }

  @Override
  public String movePlayer(String playername, String roomtobemovedto) throws IllegalStateException {
    if (playername == null || roomtobemovedto == null || "".equals(playername)
        || "".equals(roomtobemovedto)) {
      throw new IllegalStateException("Invalid player name or room name");
    } else {
      PlayerImpl player = this.playerlist.stream()
          .filter(s -> s.getName().trim().equalsIgnoreCase(playername.trim()))
          .collect(Collectors.toList()).get(0);
      List<SpaceImpl> neighbours = this.getAllVisibleSpaces(player.getCurrentRoom());
      if (neighbours.stream()
          .filter(s -> s.getName().trim().equalsIgnoreCase(roomtobemovedto.trim()))
          .collect(Collectors.toList()).isEmpty()) {
        throw new IllegalStateException(
            "Player can't be moved as the Given room as it is not a neighbour\n");
      }
      player.movePlayer(neighbours, roomtobemovedto);
      this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
      return this.getNextTargetCharacterRoom();
    }
  }

  @Override
  public String pickItem(String playername, String itemname) throws IllegalStateException {
    if (playername == null || itemname == null || "".equals(playername) || "".equals(itemname)) {
      throw new IllegalStateException("Invalid player name or item name");
    } else {
      PlayerImpl player = this.playerlist.stream()
          .filter(s -> s.getName().trim().equals(playername.trim())).collect(Collectors.toList())
          .get(0);
      player.pickItem(itemname);
      this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
      return this.getNextTargetCharacterRoom();
    }
  }

  @Override
  public String lookAround(String playername) {
    if (playername == null || "".equals(playername)) {
      throw new IllegalArgumentException("Invalid player name");
    } else {
      List<PlayerImpl> playerlist = this.playerlist.stream()
          .filter(s -> s.getName().trim().equals(playername.trim())).collect(Collectors.toList());
      if (!playerlist.isEmpty()) {
        PlayerImpl player = playerlist.get(0);
        List<SpaceImpl> neighbourlist = this.getAllVisibleSpaces(player.getCurrentRoom());
        this.neighbourinfo = "";
        for (SpaceImpl ne : neighbourlist) {
          this.playerstring = "";
          this.itemsstring = "";
          if (!this.targetpet.getCurrentRoom().equals(ne)) {
            this.getPlayerList().stream().forEach(s -> {
              if (ne.getName().trim().equalsIgnoreCase(s.getCurrentRoom().getName().trim())) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(this.playerstring);
                sb4.append(", ");
                sb4.append(s.getName());
                this.playerstring = sb4.toString();
              }
            });
            if (ne.getItems() != null) {

              ne.getItems().stream().forEach(t -> {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(this.itemsstring);
                sb5.append(", ");
                sb5.append(t.getName());
                this.itemsstring = sb5.toString();
              });
            }
            if ("".equals(this.playerstring)) {
              this.playerstring = "No players available";
            } else {
              this.playerstring = this.playerstring.substring(2);
            }
            if ("".equals(this.itemsstring)) {
              this.itemsstring = "No items available";
            } else {
              this.itemsstring = this.itemsstring.substring(2);
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append(this.neighbourinfo);
            sb6.append("\n");
            sb6.append(String.format("(Name: %s; Items: %s; Players: %s)", ne.getName(),
                this.itemsstring, this.playerstring));
            this.neighbourinfo = sb6.toString();
          }
        }
        this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
        return String.format("%s\nTarget Character Current Room: %s\n",
            player.lookAround(
                this.getRoomInfo(player.getCurrentRoom().getName())
                    .replace("RoomInfo", "LookAround").replace("Pet: %s", this.targetpet.getName()),
                this.neighbourinfo.replace("RoomInfo", "Details")),
            this.getNextTargetCharacterRoom());
      } else {
        return String.format("No such player exists");
      }
    }
  }

  @Override
  public void addPlayer(String name, int health, String currentRoom, int itemcapacity,
      List<ItemImpl> playeritems, boolean isComputerPlayer)
      throws IllegalStateException, IllegalArgumentException {
    if (name == null || health < 0 || currentRoom == null || itemcapacity < 0
        || playeritems == null) {
      throw new IllegalArgumentException("Invalid paramaters");
    } else {
      if (this.getPlayerList().stream().filter(c -> c.getName().trim().equalsIgnoreCase(name))
          .collect(Collectors.toList()).isEmpty()) {
        List<SpaceImpl> spacelist = this.getSpaceList().stream()
            .filter(c -> c.getName().trim().equalsIgnoreCase(currentRoom))
            .collect(Collectors.toList());
        if (!spacelist.isEmpty()) {
          SpaceImpl space = spacelist.get(0);
          PlayerImpl player = new PlayerImpl(name, space, itemcapacity, playeritems,
              isComputerPlayer);
          playerlist.add(player);
        } else {
          throw new IllegalStateException("Space doesn't exist in the world\n");
        }
      } else {
        throw new IllegalStateException("Player already exists\n");
      }
    }
  }

  @Override
  public String playTurnComputerPlayer(String playername) throws IllegalStateException {
    if (playername == null || "".equals(playername.trim())) {
      throw new IllegalStateException("Not a valid player name or random variable");
    } else {
      List<PlayerImpl> playerlist = this.getPlayerList().stream()
          .filter(t -> t.getName().trim().equalsIgnoreCase(playername.trim()))
          .collect(Collectors.toList());

      if (!playerlist.isEmpty()) {
        PlayerImpl player = playerlist.get(0);
        String itemname = "poke";
        this.compdisplaymessage = "";
        if (player.getItems().size() > 0) {
          List<ItemImpl> itemlist = player.getItems().stream()
              .sorted(Comparator.comparingInt(ItemImpl::getItemdamage).reversed())
              .collect(Collectors.toList());
          if (!itemlist.isEmpty()) {
            itemname = itemlist.get(0).getName();
          }
        }
        if ("poke".equalsIgnoreCase(itemname)) {
          this.compdisplaymessage = this.playComputerPlayerAttackTarget(playername, itemname);
        } else {
          int chosenitem = randomref.next(player.getItems().size());
          String selecteditem = player.getItems().get(chosenitem).getName();
          this.compdisplaymessage = this.playComputerPlayerAttackTarget(player.getName(),
              selecteditem);
        }
        if ("False".equalsIgnoreCase(this.compdisplaymessage)) {
          int r;
          List<ItemImpl> itemlist = player.getCurrentRoom().getItems();
          if (itemlist != null && itemlist.size() > 0
              && player.getItemCapacity() == player.getItems().size()) {
            r = randomref.next(4);
          } else {
            r = randomref.next(3);
          }
          List<SpaceImpl> compplayerneighbours = this.getAllVisibleSpaces(player.getCurrentRoom());
          compplayerneighbours.stream().filter(d -> d.equals(this.targetpet.getCurrentRoom()))
              .collect(Collectors.toList());
          if (r == 0) {
            this.compdisplaymessage = this.lookAround(playername);
          } else if (r == 1) {
            int chooseroom = randomref.next(compplayerneighbours.size());
            String selectedroom = compplayerneighbours.get(chooseroom).getName();
            player.movePlayer(compplayerneighbours, selectedroom);
            this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
            this.compdisplaymessage = String.format(
                "Executed Move: %s has moved to %s\nTarget Character Current Room: %s\n",
                playername, selectedroom, this.getNextTargetCharacterRoom());
          } else if (r == 2) {
            List<SpaceImpl> copyspacelist = this.getSpaceList();
            copyspacelist.remove(this.targetpet.getCurrentRoom());
            int chooseroom = randomref.next(copyspacelist.size());
            this.targetpet.movepet(this.getSpaceList().get(chooseroom));
            this.compdisplaymessage = String.format(
                "Executed Move Pet: Pet has been moved to %s\nTarget Character Current Room: %s\n",
                this.getSpaceList().get(chooseroom).getName(), this.getNextTargetCharacterRoom());
          } else {
            int chosenitem = randomref.next(itemlist.size());
            String selecteditem = itemlist.get(chosenitem).getName();
            player.pickItem(selecteditem);
            this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
            this.compdisplaymessage = String.format(
                "Executed Pick Item: %s has picked up %s\nTarget Character Current Room: %s\n",
                playername, selecteditem, this.getNextTargetCharacterRoom());
          }
        }
        return this.compdisplaymessage;
      } else {
        return String.format("Player not exists");
      }
    }
  }

  @Override
  public String playComputerPlayerAttackTarget(String playername, String itemname) {
    if (playername == null || itemname == null || "".equals(itemname) || "".equals(playername)) {
      throw new IllegalArgumentException("Invalid player name or item name");
    } else {
      try {
        return this.attackTarget(playername, itemname);
      } catch (IllegalStateException ise) {
        return "False";
      }
    }
  }

  @Override
  public String movePet(String spacename) throws IllegalArgumentException, IllegalStateException {
    if (spacename == null || "".equals(spacename)) {
      throw new IllegalArgumentException("Not a valid space name\n");
    } else {
      List<SpaceImpl> petspace = this.getSpaceList().stream()
          .filter(a -> a.getName().equalsIgnoreCase(spacename)).collect(Collectors.toList());
      if (!petspace.isEmpty()) {
        if (this.targetpet.getCurrentRoom().getName().trim().equalsIgnoreCase(spacename.trim())) {
          throw new IllegalStateException("Space name is same as current pet space.\n");
        } else {
          this.listvisitednodes = new ArrayList<>();
          this.roomstack = new Stack<SpaceImpl>();
          return String.format("%s\nTarget Character Current Room: %s\n",
              this.targetpet.movepet(petspace.get(0)), this.getNextTargetCharacterRoom());
        }
      } else {
        throw new IllegalStateException("Space doesn't exist\n");
      }
    }
  }

  @Override
  public String attackTarget(String playername, String itemname)
      throws IllegalArgumentException, IllegalStateException {
    if (itemname == null || playername == null || "".equals(playername) || "".equals(itemname)) {
      throw new IllegalArgumentException("Not a valid player name or item name\n");
    } else {
      PlayerImpl playercurrent = this.playerlist.stream()
          .filter(p -> p.getName().trim().equalsIgnoreCase(playername.trim()))
          .collect(Collectors.toList()).get(0);
      String targetcurrentspace = this.targetcharacter.getCurrentRoom().getName();
      if (!playercurrent.getCurrentRoom().getName().equalsIgnoreCase(targetcurrentspace)) {
        throw new IllegalStateException(
            "Cannot attack the target as it is not in the current room\n");
      } else {
        if (playercurrent.getCurrentRoom().getName().trim()
            .equalsIgnoreCase(this.targetpet.getCurrentRoom().getName().trim())) {
          if (this.playerlist.stream()
              .filter(d -> d.getCurrentRoom().equals(playercurrent.getCurrentRoom()))
              .collect(Collectors.toList()).size() == 1) {
            String hitmessage = this.decreaseTargetHealth(playercurrent, itemname);
            if (hitmessage.contains("Wins")) {
              return hitmessage;
            } else {
              this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
              return String.format("%s\nTarget Character Current Room: %s\n", hitmessage,
                  this.getNextTargetCharacterRoom());
            }
          } else {
            this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
            String stopmessage = "Target Character attack stopped as the attack is being seen";
            return String.format("%s by other players.\nTarget Character Current Room: %s\n",
                stopmessage, this.getNextTargetCharacterRoom());
          }
        } else {
          List<SpaceImpl> neighbourlist = this.getAllVisibleSpaces(playercurrent.getCurrentRoom());
          List<PlayerImpl> players = new ArrayList<>();
          players.addAll(playerlist.stream()
              .filter(r -> r.getCurrentRoom().getName()
                  .equalsIgnoreCase(playercurrent.getCurrentRoom().getName()))
              .collect(Collectors.toList()));

          neighbourlist.forEach(t -> {
            players.addAll(playerlist.stream()
                .filter(s -> (s.getCurrentRoom().getName().equalsIgnoreCase(t.getName())))
                .collect(Collectors.toList()));
          });
          if (players.size() > 1) {
            this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
            String stopmessage = "Target Character attack stopped as the attack is being seen";
            return String.format("%s by other players.\nTarget Character Current Room: %s\n",
                stopmessage, this.getNextTargetCharacterRoom());
          } else {
            String hitmessage = this.decreaseTargetHealth(playercurrent, itemname);
            if (hitmessage.contains("Wins")) {
              return hitmessage;
            } else {
              this.petMovementDfs(this.targetpet.getCurrentRoom().getName());
              return String.format("%s\nTarget Character Current Room: %s\n", hitmessage,
                  this.getNextTargetCharacterRoom());
            }
          }
        }
      }
    }
  }

  @Override
  public String petMovementDfs(String petcurrentroom) {
    if (petcurrentroom == null || "".equals(petcurrentroom.trim())) {
      throw new IllegalArgumentException("Invalid pet room");
    } else {
      List<SpaceImpl> allspaces = this.getSpaceList();
      List<SpaceImpl> currpetspace = allspaces.stream()
          .filter(b -> b.getName().trim().equalsIgnoreCase(petcurrentroom))
          .collect(Collectors.toList());
      if (this.listvisitednodes.size() == allspaces.size()) {
        this.listvisitednodes = new ArrayList<>();
        this.roomstack = new Stack<SpaceImpl>();
      }
      if (listvisitednodes.size() == 0) {
        this.listvisitednodes.add(currpetspace.get(0));
        this.roomstack.push(currpetspace.get(0));
      }
      if (!currpetspace.isEmpty()) {
        List<SpaceImpl> spaceneighbourlist = this.getAllVisibleSpaces(currpetspace.get(0));
        for (SpaceImpl s : allspaces) {
          if (!this.listvisitednodes.stream().filter(t -> t.getName().equals(s.getName()))
              .collect(Collectors.toList()).isEmpty()) {
            spaceneighbourlist.remove(s);
          }
          if (spaceneighbourlist.size() == 0) {
            if (isBackTrack == false) {
              roomstack.pop();
              isBackTrack = true;
              roomstring = roomstack.pop().getName();
            } else {
              roomstring = roomstack.pop().getName();
            }
            break;
          } else {
            if (!spaceneighbourlist.stream().filter(t -> t.getName().equals(s.getName()))
                .collect(Collectors.toList()).isEmpty()) {
              roomstring = s.getName();
              isBackTrack = false;
              if (listvisitednodes.stream().filter(p -> p.getName().equalsIgnoreCase(s.getName()))
                  .collect(Collectors.toList()).isEmpty()) {
                listvisitednodes.add(s);
              }
              roomstack.push(s);
              break;
            }
          }
        }
        this.targetpet
            .movepet(allspaces.stream().filter(b -> b.getName().trim().equalsIgnoreCase(roomstring))
                .collect(Collectors.toList()).get(0));
        return roomstring;
      } else {
        return String.format("Invalid Space name");
      }
    }
  }

  @Override
  public boolean ifPlayerSeen(String playerA, String playerB) {
    if (playerA == null || playerB == null) {
      throw new IllegalArgumentException("Invalid player name");
    } else {
      PlayerImpl playerAdata = this.playerlist.stream()
          .filter(m -> m.getName().trim().equalsIgnoreCase(playerA.trim()))
          .collect(Collectors.toList()).get(0);
      PlayerImpl playerBdata = this.playerlist.stream()
          .filter(m -> m.getName().trim().equalsIgnoreCase(playerB.trim()))
          .collect(Collectors.toList()).get(0);
      return playerAdata.ifPlayerSeen(playerBdata,
          this.getAllVisibleSpaces(playerAdata.getCurrentRoom()), this.targetpet.getCurrentRoom());
    }
  }

  @Override
  public String decreaseTargetHealth(PlayerImpl playercurrent, String itemname)
      throws IllegalStateException {
    if (playercurrent == null || itemname == null || "".equals(itemname.trim())) {
      throw new IllegalArgumentException("Not a valid player entity or item name");
    } else {
      if (itemname.trim().equalsIgnoreCase("poke")) {
        this.targetcharacter.decreaseHealth(1);
        if (this.targetcharacter.getHealth() <= 0) {
          return String.format("Target Character killed Successfully.\nPlayer %s Wins\n",
              playercurrent.getName());
        } else {
          String successmsg = "Target Character hit successfully.\nHealth got decreased by 1.\n";
          return String.format("%sCurrent Health: %d\n", successmsg,
              this.targetcharacter.getHealth());
        }
      } else {
        List<ItemImpl> itemdatalist = playercurrent.getItems().stream()
            .filter(s -> s.getName().trim().equalsIgnoreCase(itemname.trim()))
            .collect(Collectors.toList());
        if (itemdatalist.isEmpty()) {
          throw new IllegalStateException("Item does not exist on the player.\n");
        } else {
          ItemImpl itemdata = itemdatalist.get(0);
          this.targetcharacter.decreaseHealth(itemdata.getItemdamage());
          int currplyindex = this.playerlist.indexOf(playercurrent);
          this.playerlist.get(currplyindex).getItems().remove(itemdata);
          if (this.targetcharacter.getHealth() <= 0) {
            return String.format("Target Character killed Successfully.\nPlayer %s Wins\n",
                playercurrent.getName());
          }
          String successmsg = String.format(
              "Target Character hit successfully.\nHealth got decreased by %d.\n",
              itemdata.getItemdamage());
          return String.format("%sCurrent Health: %d\n", successmsg,
              this.targetcharacter.getHealth());
        }
      }
    }
  }

  @Override
  public String getPlayerNextTurn(String currentplayername) {
    if (currentplayername == null || "".equals(currentplayername.trim())) {
      throw new IllegalArgumentException("Invalid player name");
    } else {
      List<PlayerImpl> currentplayerinfo = this.getPlayerList().stream()
          .filter(f -> f.getName().trim().equalsIgnoreCase(currentplayername.trim()))
          .collect(Collectors.toList());
      if (!currentplayerinfo.isEmpty()) {
        int currplayerindex = this.getPlayerList().indexOf(currentplayerinfo.get(0));
        if (currplayerindex == (this.getPlayerList().size() - 1)) {
          return String.format(
              "%s, PlayerType: %s", this.getPlayerInfo(this.getPlayerList().get(0).getName())
                  .replace("Player Info (Name:", "Turn of"),
              this.getPlayerList().get(0).isComputerPlayer());
        } else {
          return String.format("%s, PlayerType: %s",
              this.getPlayerInfo(this.getPlayerList().get(currplayerindex + 1).getName())
                  .replace("Player Info (Name:", "Turn of"),
              this.getPlayerList().get(currplayerindex + 1).isComputerPlayer());
        }
      } else {
        throw new IllegalArgumentException("Player not exists");
      }
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BoardGameImpl)) {
      return false;
    }
    BoardGameImpl that = (BoardGameImpl) o;
    return this.name.equals(that.name) && this.targetcharacter.equals(that.targetcharacter)
        && this.spacelist.equals(that.spacelist)
        && this.worldcoordinates.equals(that.worldcoordinates);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getTargetCharacterImpl(), this.worldcoordinates);
  }

  @Override
  public String toString() {
    return String.format("RoomInfo (Name: %s;\n Items: %s;\n Players: %s;\n Neighbours: %s)\n\n",
        this.roomstring, this.itemsstring, this.playerstring, this.neighboursstring);
  }

  @Override
  public List<SpaceImpl> getSpaceList() {
    List<SpaceImpl> copy = new ArrayList<>(this.spacelist);
    return copy;
  }

  @Override
  public List<PlayerImpl> getPlayerList() {
    List<PlayerImpl> copy = new ArrayList<>(this.playerlist);
    return copy;
  }
}
