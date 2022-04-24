package controller;

public interface Features {
  public void addPlayer(String playerName, String spaceName, int itemCapacity,
      boolean isComputerPlayer);

  public String getTurns(String playerName);

  public String handleKeyPressEvent(String action, String playername, String roomOrItemName);

  public String handleMouseClickEvent(int x, int y);

  public String playComputerPlayer(String playerName);

  public void handlePlayerMouseClickEvent(String playerName);

  public void moveToAddPlayerScreen();

  public void moveToGameScreen();
}
