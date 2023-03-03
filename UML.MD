# UML 

     +-------------------+         +---------------------------------+
     |       Player      |         |            KenoGame             |
     +-------------------+         +---------------------------------+
     | -name: String     |         | -betCard: BetCard               |
     | -balance: double  |         | -drawings: Drawing[]            |
     |                   |         |                                 |
     | +getPlayerName(): |         | +getBetCard(): BetCard          |
     | +getBalance():    |         | +setBetCard(card: BetCard): void|
     | +addBalance():    |         | +play(drawingCount: int): void  |
     |                   |         |                                 |
     |                   |         |                                 |
     +--------+----------+         +---------+-----------------------+
              |                              |
              | uses                         | creates
              |                              |
     +--------v----------+         +---------v----------------------+
     |      BetCard      |         |       Drawing                  |
     +-------------------+         +--------------------------------+
     | -spots: int[]     |         | -numbers: int[]                |
     |                   |         |                                |
     | +getSpots(): int[]|         | +getNumbers(): int[]           |
     | +setSpot(i: int,  |         | +setNumbers(nums: int[]): void |
     |  num: int): void  |         |                                |
     |                   |         |                                |
     +-------------------+         +--------------------------------+

The UML diagram includes four main classes: Player, KenoGame, BetCard, and Drawing. Here is a brief description of each class:

Player: represents the player of the game. It has attributes such as the player's name and balance. It also has methods to get and add the player's balance.

KenoGame: represents the main game engine. It has a BetCard attribute and an array of Drawing objects that will be used to play the game. It also has methods to get and set the bet card, and to play the game with a given number of drawings.

BetCard: represents the player's bet card. It has an array of int that stores the player's chosen spots. It has methods to get and set the chosen spots.

Drawing: represents a single drawing in the game. It has an array of int that stores the 20 randomly selected numbers. It has methods to get and set the drawn numbers.

The UML diagram also includes some relationships between the classes, such as the Player using the KenoGame, the KenoGame creating BetCard and Drawing objects, and the BetCard setting and getting the chosen spots.

Note that this is just one possible UML diagram for the Keno game, and there may be other ways to represent the game's classes and their relationships.
