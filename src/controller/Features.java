package controller;

import java.util.List;

import theworld.PlayerImpl;

public interface Features {
  public void addPlayer(String playerName, String spaceName, int itemCapacity,
      boolean isComputerPlayer);

  public String getTurns(String playerName);

  public String keyPressEvent(String action, String playername, String roomOrItemName);
}
