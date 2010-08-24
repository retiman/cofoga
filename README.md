DESCRIPTION
===========
Cofoga is a Scalable Connect 4 Game.  You may expand the board to any number
of rows and columns; you may even choose to play Connect 5, 6, or whatever
number you wish.  The players are named White and Black, and traditionally, White
moves first.  You may set the parameters of the game, and the AI will play a match
against you.

INSTALLATION
============
Cofoga is built using SBT.  Download it from here:

    http://code.google.com/p/simple-build-tool

Run the build script and type `package` to build.  You can run Cofoga with this
command:

    java -jar cofoga-cli/target/cofoga-*-with-dependencies.jar

SEARCH
======
Cofoga uses min-max algorithm with alpha beta pruning for searching.

DEFINITIONS
===========
Assuming a 6x7 and 4 pieces that must be connected for a victory, here are some
definitions, and then an explanation of the evaluation algorithm.

- Group
This is a set of 4 consecutive horizontal, vertical, diagonally up, or diagonally
down squares on the board.  Any player who places 4 pieces in a group wins the
game.

- Unsolved
A group that is either completely empty, or has 1, 2, or 3 pieces of the same color
in it.  This is a group that can be used to win the game, and a group with more
pieces of the same color is worth more points.

- Solved
A group that has a mix of pieces in it.  That is, a group with at least one White
piece, and one Black piece.  Such a group cannot be used to win the game, and is
worth zero points in the evaluation.

- Threat
A group containing 1, 2, or 3 pieces of the same color.

Groups are calculated when the game is initalized, and threats are calculated
on each move and undo.

UTILITY
=======
White's 1, 2, and 3 threats are worth 3, 9, and 27 points respectively.  Black's
1, 2, and 3 threats are worth -3, -9, -27 points respectively.  The overall utility
is the value of White's threats minus the value of Black's threats.  My opinion
is that this utility function could stand to be tweaked.
