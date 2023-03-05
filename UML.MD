+------------------------------------------------+
|                    KenoGame                    |
+------------------------------------------------+
| - numSpots: int                                 |
| - numDrawings: int                              |
| - betCard: BetCard                              |
| - drawnNumbers: List<Integer>                   |
| - winnings: double                              |
| - gameResults: List<GameResult>                 |
+------------------------------------------------+
| + startGame(): void                             |
| + selectNumSpots(numSpots: int): void           |
| + selectNumDrawings(numDrawings: int): void     |
| + selectBetCardNumber(number: int): void        |
| + clearBetCard(): void                          |
| + generateRandomNumbers(): void                 |
| + calculateWinnings(): void                      |
| + updateGameResults(): void                      |
| + getGameResults(): List<GameResult>             |
+-------------------------------------------------+

+------------------------------------------------+
|                    BetCard                     |
+------------------------------------------------+
| - spots: int[]                                  |
| - selectedSpots: Set<Integer>                   |
| - selectedAuto: boolean                         |
+------------------------------------------------+
| + selectSpot(spot: int): boolean                |
| + deselectSpot(spot: int): boolean              |
| + selectAuto(): void                            |
| + deselectAuto(): void                          |
| + clearSelectedSpots(): void                    |
| + getSpots(): int[]                             |
| + getSelectedSpots(): Set<Integer>              |
| + isSelectedAuto(): boolean                     |
+-------------------------------------------------+

+------------------------------------------------+
|                   GameResult                   |
+------------------------------------------------+
| - drawNums: List<Integer>                       |
| - matchNums: Set<Integer>                       |
| - winAmount: double                             |
+------------------------------------------------+
| + getDrawnNumbers(): List<Integer>              |
| + getMatchedNumbers(): Set<Integer>             |
| + getWinAmount(): double                        |
+-------------------------------------------------+

# UML Diagram Explanation

The UML diagram consists of three classes: `KenoGame`, `BetCard`, and `GameResult`. Each of these classes has its own set of attributes and methods that are used to implement the game.

Starting with the `KenoGame` class, this class represents the overall game and contains the following attributes:

- `numSpots`: an integer representing the number of spots the player has chosen to play.
- `numDrawings`: an integer representing the number of drawings the player has chosen to play.
- `betCard`: an instance of the `BetCard` class representing the player's bet card.
- `drawnNumbers`: a list of integers representing the numbers that have been randomly drawn in each drawing.
- `winnings`: a double representing the total amount that the player has won since starting the game.
- `gameResults`: a list of instances of the `GameResult` class representing the results of each drawing.

The `KenoGame` class also has several methods that are used to play the game, including:

- `startGame()`: a method that starts the game and initializes the necessary variables.
- `selectNumSpots(numSpots: int)`: a method that allows the player to select the number of spots they want to play.
- `selectNumDrawings(numDrawings: int)`: a method that allows the player to select the number of drawings they want to play.
- `selectBetCardNumber(number: int)`: a method that allows the player to select a number on their bet card.
- `clearBetCard()`: a method that clears the player's bet card.
- `generateRandomNumbers()`: a method that generates the random numbers for each drawing.
- `calculateWinnings()`: a method that calculates the player's winnings for each drawing.
- `updateGameResults()`: a method that updates the list of game results.
- `getGameResults()`: a method that returns the list of game results.

Moving on to the `BetCard` class, this class represents the player's bet card and contains the following attributes:

- `spots`: an array of integers representing the spots on the bet card.
- `selectedSpots`: a set of integers representing the spots that the player has selected.
- `selectedAuto`: a boolean representing whether the player has chosen to have their numbers selected automatically.

The `BetCard` class also has several methods that are used to interact with the bet card, including:

- `selectSpot(spot: int)`: a method that allows the player to select a spot on the bet card.
- `deselectSpot(spot: int)`: a method that allows the player to deselect a spot on the bet card.
- `selectAuto()`: a method that allows the player to select automatic number selection.
- `deselectAuto()`: a method that allows the player to deselect automatic number selection.
- `clearSelectedSpots()`: a method that clears the player's selected spots.
- `getSpots()`: a method that returns the array of spots on the bet card.
- `getSelectedSpots()`: a method that returns the set of selected spots on the bet card.
- `isSelectedAuto()`: a method that returns a boolean indicating whether the player has selected automatic number selection.

The `GameResult` class represents the result of a single drawing and contains the following attributes:

- `drawNums`: a list of integers representing the numbers that were drawn.
- `matchNums`: a set of integers representing the numbers that matched the player's selected spots.
- `winAmount`: a double representing the amount that the player won in the drawing.
