# gameoflife
Demo of a simple cellular automaton.

Conway's Game of Life is an example of a simple cellular automaton - a matrix of "cells" that change their state based on the state of neighboring cells. The cells have two possible states - "living" and "dead". The simulation starts with populating a portion of the matrix with "living" cells and then iterating through steps of state changes based on three rules:
- Any cell that is "living" and has exactly two "living" neighbours stays "alive".
- Any cell that has exactly three "living" neighbours will become "alive".
- Any other cell will "die" if it's not already "dead".
Edges of the matrix "wrap around", i.e. the topmost and bottommost row as well as the leftmost and rightmost column are considered to be adjacent.
The simulation, once started, will run until:
- all "live" cells have died out
- a looping state is detected, i.e. the matrix forms a pattern of "living" cells that is exactly the same as any previous state
- the user changes the status of any cell(s), in which case the simulation will pause and loop detection will be reset ("forgetting" all previous states of the board used for loop detection)
- the user stops or resets the simulation (Stopping the simulation will not reset loop detection.)

The purpose of this project is to create a demo implementation of Game of Life as a desktop Java application.
This demo shall be implemented in Java 8 using JavaFX UI.
