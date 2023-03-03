# JavaFX_Keno
CS 342 Project 2

# Keno Game

This is a simple command-line version of the classic casino game, Keno. The game allows players to pick up to 10 numbers from a pool of 80, and then randomly draws 20 numbers. The more numbers that match between the player's picks and the drawn numbers, the higher the payout.

## Getting Started

1. Clone the repository: `git clone https://github.com/your-username/keno-game.git`
2. Navigate to the project directory: `cd keno-game`
3. Install the required dependencies: `npm install`
4. Start the game: `npm start`

## How to Play

1. When prompted, enter your selected numbers (up to 10) separated by commas.
2. The game will then randomly draw 20 numbers.
3. The game will display the numbers you picked and the numbers drawn, as well as the number of matches.
4. If you have any matching numbers, the game will display the payout based on the number of matches and your initial bet.
5. You can then choose to play again or quit the game.

## Game Rules

- Players can pick between 1 and 10 numbers.
- The pool of numbers to pick from is 1 to 80.
- The game will randomly draw 20 numbers from the pool of 80.
- Payouts are based on the number of matches between the player's picks and the drawn numbers, as well as the initial bet.

## Technologies Used

- Node.js
- Inquirer.js

## Contributing

Contributions are welcome! If you would like to contribute to the project, please create a pull request.

## License

This project is licensed under the MIT License.

# UML 

     +-------------------+         +-------------------+
     |       Player      |         |       KenoGame    |
     +-------------------+         +-------------------+
     | -name: String     |         | -betCard: BetCard |
     | -balance: double  |         | -drawings: Drawing[] |
     |                   |         |                   |
     | +getPlayerName(): |         | +getBetCard(): BetCard |
     | +getBalance():    |         | +setBetCard(card: BetCard): void |
     | +addBalance():    |         | +play(drawingCount: int): void |
     |                   |         |                   |
     |                   |         |                   |
     +--------+----------+         +---------+---------+
              |                              |
              | uses                         | creates
              |                              |
     +--------v----------+         +---------v---------+
     |      BetCard      |         |       Drawing      |
     +-------------------+         +-------------------+
     | -spots: int[]     |         | -numbers: int[]   |
     |                   |         |                   |
     | +getSpots(): int[]|         | +getNumbers(): int[] |
     | +setSpot(i: int,  |         | +setNumbers(nums: int[]): void |
     |  num: int): void  |         |                   |
     |                   |         |                   |
     +-------------------+         +-------------------+

The UML diagram includes four main classes: Player, KenoGame, BetCard, and Drawing. Here is a brief description of each class:

Player: represents the player of the game. It has attributes such as the player's name and balance. It also has methods to get and add the player's balance.

KenoGame: represents the main game engine. It has a BetCard attribute and an array of Drawing objects that will be used to play the game. It also has methods to get and set the bet card, and to play the game with a given number of drawings.

BetCard: represents the player's bet card. It has an array of int that stores the player's chosen spots. It has methods to get and set the chosen spots.

Drawing: represents a single drawing in the game. It has an array of int that stores the 20 randomly selected numbers. It has methods to get and set the drawn numbers.

The UML diagram also includes some relationships between the classes, such as the Player using the KenoGame, the KenoGame creating BetCard and Drawing objects, and the BetCard setting and getting the chosen spots.

Note that this is just one possible UML diagram for the Keno game, and there may be other ways to represent the game's classes and their relationships.
