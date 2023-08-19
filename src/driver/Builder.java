package driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import theworld.BoardGameImpl;
import theworld.ItemImpl;
import theworld.PetImpl;
import theworld.SpaceImpl;
import theworld.TargetCharacterImpl;

/**
 * This class is used to read and parse the world text file.
 *
 */
public class Builder {

  /**
   * The readfile method is used to read the file data and parse the data it in a
   * world object.
   *
   * @param inputdata data read from the text file for the world.
   * @param turns     the maximum number of turns of the game play.
   * @return the BoardgameImpl entity
   */
  public static BoardGameImpl readfile(String inputdata, int turns) {
    try {
      if (inputdata == null || "".equals(inputdata.trim())) {
        throw new IllegalArgumentException("Not a valid parameter");
      } else {
        String[] worldattributes = inputdata.split("\r\n");

        String[] worldattributes1 = worldattributes[0].trim().split("\\s+");
        if (worldattributes1.length > 3) {
          for (int j = 3; j < worldattributes1.length; j++) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(worldattributes1[2]);
            sb3.append(" ");
            sb3.append(worldattributes1[j]);
            worldattributes1[2] = sb3.toString();
          }
        }
        String[] worldattributes2 = worldattributes[1].trim().split("\\s+");
        if (worldattributes2.length > 2) {
          for (int j = 2; j < worldattributes2.length; j++) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append(worldattributes2[1]);
            sb4.append(" ");
            sb4.append(worldattributes2[j]);
            worldattributes2[1] = sb4.toString();
          }
        }

        int height = Integer.parseInt(worldattributes1[0].trim());
        int width = Integer.parseInt(worldattributes1[1].trim());
        List<Integer> worldcoordinates = new ArrayList<>();
        worldcoordinates.add(height);
        worldcoordinates.add(width);

        List<SpaceImpl> roomlist = new ArrayList<>();
        int noofrooms = Integer.parseInt(worldattributes[3].trim());
        int noofweapons = Integer.parseInt(worldattributes[noofrooms + 4].trim());
        for (int i = 4; i <= noofrooms + 3; i++) {
          String[] spaceattr = worldattributes[i].trim().split("\\s+");
          if (spaceattr.length > 5) {
            for (int j = 5; j < spaceattr.length; j++) {
              StringBuilder sb2 = new StringBuilder();
              sb2.append(spaceattr[4]);
              sb2.append(" ");
              sb2.append(spaceattr[j]);
              spaceattr[4] = sb2.toString();
            }
          }
          List<Integer> roomcoordinates = new ArrayList<>();
          roomcoordinates.add(Integer.parseInt(spaceattr[0]));
          roomcoordinates.add(Integer.parseInt(spaceattr[1]));
          roomcoordinates.add(Integer.parseInt(spaceattr[2]));
          roomcoordinates.add(Integer.parseInt(spaceattr[3]));
          SpaceImpl demospace = new SpaceImpl(i - 4, roomcoordinates, spaceattr[4], null);
          roomlist.add(demospace);
        }

        for (int j = noofrooms + 5; j <= noofrooms + 4 + noofweapons; j++) {
          String[] itemattr = worldattributes[j].trim().split("\\s+");

          if (itemattr.length > 3) {
            for (int k = 3; k < itemattr.length; k++) {
              StringBuilder sb = new StringBuilder();
              sb.append(itemattr[2]);
              sb.append(" ");
              sb.append(itemattr[k]);
              itemattr[2] = sb.toString();
            }
          }
          ItemImpl demoitem = new ItemImpl(Integer.parseInt(itemattr[1]), itemattr[2]);
          SpaceImpl space = roomlist.get(Integer.parseInt(itemattr[0]));
          if (space.getItems() == null) {
            space.setItems(new ArrayList<>());
            roomlist.get(Integer.parseInt(itemattr[0])).getItems().add(demoitem);
          } else {
            roomlist.get(Integer.parseInt(itemattr[0])).getItems().add(demoitem);
          }
        }
        TargetCharacterImpl target = new TargetCharacterImpl(worldattributes2[1],
            Integer.parseInt(worldattributes2[0]), roomlist.get(0));
        PetImpl targetpet = new PetImpl(worldattributes[2], roomlist.get(0));
        RandomClass randomref = new RandomClass();
        BoardGameImpl world = new BoardGameImpl(target, worldattributes1[2], roomlist,
            worldcoordinates, targetpet, randomref, turns);
        return world;
      }

    } catch (IllegalArgumentException iae) {
      throw new IllegalArgumentException(iae.getMessage());
    }
  }
}