# The-Goose-Game-Kata
 implementation of the Goose Game kata.

The project concerns a basic implementation of the goose game.There are also some unit tests

If you have suggestions on how to improve or possibly rethink some parts of the code,
I would be happy to receive them.

# Build and run
The project is based on Java and Maven Archetype as a dependency manager.

To use the code on this repo from command line:

command to type to get the local source code
```cucumber
git clone https://github.com/ant-giannone/tlabs-goose-game.git
```
move to the project folder

mvn clean install to perform dependency download, project build and installation of the jar produced on your local repo

mvn clean install
If you just want to produce the jar but don't install it on your local repo
mvn clean package
If you prefer not to perform the tests
 mvn clean package -Dmaven.test.skip=true
to execute from the command line the jar produced, move to the target folder and type:
java -jar tlabs-goose-game-1.0-SNAPSHOT.jar
Java properties that you could modify
if you prefer, you can change the values of the following properties to change some game behaviors:

the cell where to place the bridge and the respective jump cell
the cells on which the players can find the goose
the victory cell
application.board.cell.victory=63
application.board.element.bridge.cell=6
application.board.element.bridge.jump_to_cell=12
application.board.element.goose.cell=5,9,14,18,23,27
List of commands recognized by the system
to add a player, you can type
add player Bob
to move a player, with a manual rolling dice, you can type
move alice 4,6
to move a player, with automatic system rolling dice, you can type
move alice
I hope you have fun
