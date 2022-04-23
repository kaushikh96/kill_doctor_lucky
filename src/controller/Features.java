package controller;

import java.util.List;

import theworld.PlayerImpl;

public interface Features {
  public void addPlayer(String playerName, String spaceName, int itemCapacity,
      boolean isComputerPlayer);

  public String getTurns(String playerName);

  public String handleKeyPressEvent(String action, String playername, String roomOrItemName);

  public String handleMouseClickEvent(int x, int y);

  public String playComputerPlayer(String playerName);
}
