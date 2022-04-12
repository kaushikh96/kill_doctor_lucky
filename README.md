# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Spring 2022 Edition!

**Name:** Kaushik Komanduri
          Sanjana Kandunoori

**Email:** komanduri.k@northeastern.edu 
           kandunoori.s@northeastern.edu

**Preferred Name:** Kaushik
                    Sanjana



### About/Overview

The project is a board game in which we have to kill a target character. The player who kills the character is the winner. The project consists of a world which is made up of some spaces. The target character moves around the spaces and the players can use the items inside the spaces to kill the character. Each player has a turn in which he can executed 5 different actions.
1. Look Around
2. Move to Neighbouring Space
3. Pick an Item from Space
4. Move the Pet
5. Attack Target

The player who kills the Target Character would be declared the winner.

### List of Features

The project consists of the following features:
1. We can determine the neighboring spaces of a specified space in the world.
2. We can display information of the specified space. The information consists of the name of the space, the neighboring spaces and the items present in that space.
3. The target character can move around the world. It moves in the order of the spaces that is specified in the world specifications text file.
4. The project will create a graphical image of the world.
5. We can add players into the world and into a particular space of our choice.
6. Each player can take turns in 5 different ways. First, would be to look around - Means displaying current space and its neighbouring spaces.
7. Second, would be to make a move to any of the neighbouring space.
8. Third, would be to pick an item, if exists from a particular space.
9. This program also has the features to display player information which includes the current player room is in and the item it currently holds.
10. Modified the information displayed for the room to include the players present in that room.
11. The program also features a flexibility to add a computer player which executes its turns on its own. 
12. Once the program finishes the number of turns specified, the game ends and the target character escapes.
13. The Target character moves during every turn of the player.
14. Reads the world from the a file and creates the world. 
15. The Target Pet has been added to the game and it enteres the game from the same place as that of the target character.
16. The Target pet can be moved from any space to any other space in the world. This represents another turn.
17. The Target pet would also be able to act as a Wandering pet and can traverse in the world using Depth First Traversal algorithm.
18. If the Target pet is in a particular room it is invisible to the other players in the game.
19. The player would be able to attack the target character, if he is in the same space as the player.
20. The attack is successful if the player not being watched by other players i.e; there is a target pet in the room which makes the room invisible for others.
21. The attack is a failure if the player is being watch by others from the neighbouring space or from the current space.
22. Look Around not only displays the names of the neighbouring spaces but also what items and players are there in that space.
23. Game ends if the Target Character is successfully killed by one of the players.
24. Computer player would make an attempt to attack target character always if there is a possibility, otherwise it executes other turn.
25. If no items exists on a player he could as well poke the target character in the eye which does a health damage of 1.
26. Player can use the items on him to attack the target character. The item is then removed from the game.
27. Computer Player would automatically choose the item on him with the highest damage.


### How to Run

We can use the executable jar file to give a text file as an input and run the program. The command is `java -jar theworld.jar world.txt 200`. The command goes like

java -jar theworld.jar {myworld text file} {maximum number of turns}

We are creating a Runnable jar file which we would execute in the command prompt to run the program. Using this command we run the program and start giving inputs for  
execution.


### How to Use the Program

The program demonstrates all the combined features. We have to just make sure that the input text file is present. We run the generated Runnable jar file which in this case is theworld.jar with 2 command line arguments 

The program uses a dedicated command design pattern to execute all of the features of the current project. Commands are given through the command line and the corresponding actions are performed.


### Example Runs

There are 9 example run files submitted: 

1. examplerun_computerplayerattempt.txt - It demonstrates the computer player's attempt to attack the target character
2. examplerun_computerplayerwinning.txt - It demonstrates computer player's Win
3. examplerun_computerplayerwinning2.txt - It is another run of Computer player's Win
4. examplerun_humanplayerattempt.txt - It demonstrates the human player's attempt to attack the target character
5. examplerun_humanplayerwins.txt - It demonstrates human player winning
6. examplerun_playermovingpet.txt - It demonstrates how player moves a pet
7. examplerun_targetcharacterescapes.txt - It demonstrates that target character escapes after maximum turns are done
8. examplerun_targetpetexistencevisibility.txt - It demonstrates how spaces are not visible by the presence of target pet in them
9. examplerun_wanderingpetmovement.txt - It demonstrates the functioning of wandering pet movement using DFS alogrithm.

### Design/Model Changes

1. Implemented all the features of the model requirements in the Board Game Interface class itself.
2. Clubbed the AddHumanPlayer and AddComputerPlayer method into one method for Adding a player as a whole.
3. Also created a new command design class to execute when it is ComputerPlayer's Turn.
4. Created a new Facade Interface to be used for mocking during the testing of the controller.
5. Player Information has been moved to model.
6. Added an additional method for Depth first traversal and computer player execution.
7. Pet Entity has been added to store pet information.

### Assumptions
1. Player should be knowing the space names.
2. User should perform operation as directed.
3. After reaching the last space, target character would move back to the first space.
4. As the target character movement is being tracked in each turn, it is not included in the DisplayRoomInfo.
5. During DFS after all the rooms are traversed it automatically starts mvoing from the first place it has started.
6. Once a player executes the move pet turn, it automatically starts DFS from the current space for the subsequent turns.


### Limitations
1. We would only know the location of a pet when Display Room Info command is executed.
2. Players cannot be added to the game after the turn of a player has started.


### Citations

Took references from stackoverflow for design related implementations. Also, GeeksforGeeks was a great source of information.

