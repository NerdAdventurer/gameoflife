/**
 * 
 */
package com.github.nerdadventurer.gameoflife.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Represents the logical model of a single cell in the grid.
 * @author Aki Laukkanen
 */
public class CellModel implements Serializable {
	private static final int NUM_NEIGHBORS = 8;
	private static final int NEIGHBORS_TO_SURVIVE=2;
	private static final int NEIGHBORS_TO_BREED=3;
	private int x;
	private int y;
	private CellModel[] neighbors = new CellModel[NUM_NEIGHBORS];
	private boolean alive;
	private boolean aliveNextRound;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	public CellModel(int x, int y) {
		this.x=x;
		this.y=y;
		alive=false;
		aliveNextRound=false;
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the neighbors
	 */
	public CellModel[] getNeighbours() {
		return neighbors;
	}

	/**
	 * @param neighbors the neighbors to set
	 */
	public void setNeighbours(CellModel[] neighbours) {
		this.neighbors = neighbours;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the aliveNextRound
	 */
	public boolean isAliveNextRound() {
		return aliveNextRound;
	}

	/**
	 * @param aliveNextRound the aliveNextRound to set
	 */
	public void setAliveNextRound(boolean aliveNextRound) {
		this.aliveNextRound = aliveNextRound;
	}

	/**
	 * Fill in the information of this cell's neighboring cells.
	 * Cells that are located one step in any direction are considered neighboring cells. 
	 * If this cell is on any edge of the board, then cells on the opposing edge are considered neighboring cells.
	 * @param board The BoardModel that this cell belongs to
	 * @throws InvalidCoordinateException if coordinates are outside board's grid
	 */
	public void setNeighbours(BoardModel board) throws InvalidCoordinateException{
		int prevX=this.x-1;
		// If we're in the leftmost column, our neighbor is on the rightmost one.
		if(prevX<0){
			prevX=board.getGridSize()-1;
		}
		int nextX=this.x+1;
		// If we're on rightmost column, our neighbor is on the leftmost one.
		if(nextX>=board.getGridSize()){
			nextX=0;
		}
		int prevY=this.y-1;
		// If we're in the top row, our neighbor is on the bottom one.
		if(prevY<0){
			prevY=board.getGridSize()-1;
		}
		int nextY = this.y+1;
		// If we're in the bottom row, our neighbor is on the top one.
		if(nextY>=board.getGridSize()){
			nextY=0;
		}
		this.neighbors[0]=board.getCellAt(prevX, prevY);
		this.neighbors[1]=board.getCellAt(prevX, y);
		this.neighbors[2]=board.getCellAt(prevX, nextY);
		this.neighbors[3]=board.getCellAt(x, prevY);
		this.neighbors[4]=board.getCellAt(x, nextY);		
		this.neighbors[5]=board.getCellAt(nextX, prevY);
		this.neighbors[6]=board.getCellAt(nextX, y);
		this.neighbors[7]=board.getCellAt(nextX, nextY);		
	}
	
	/**
	 * Sets this cell's status for next round to current status.
	 * Cell status effectively changes on next {@link #update()} call.
	 */
	public void remain(){
		this.aliveNextRound=this.alive;
	}
	
	/**
	 * Sets this cell's status for next round to "alive".
	 * Cell status effectively changes on next {@link #update()} call.
	 */
	public void live(){
		this.aliveNextRound=true;
	}
	
	/**
	 * Sets this cell's status for next round to "dead".
	 * Cell status effectively changes on next {@link #update()} call.
	 */
	public void die(){
		this.aliveNextRound=false;
	}
	
	/**
	 * Immediately switches this cell's state between "dead" and "alive".
	 */
	public void flipState(){
		this.alive=!this.alive;
	}
	
	/**
	 * Updates this cell's status at beginning of new round.
	 */
	public void update(){
		this.alive=this.aliveNextRound;
	}
	
	/**
	 * Calculates this cell's next state based on number of living neighbors.
	 * If number of living neighbors is {@link #NEIGHBORS_TO_SURVIVE}, this cell will remain in its current state.
	 * If number of living neighbors is {@link #NEIGHBORS_TO_BREED}, this cell will become "alive".
	 * Otherwise, this cell will become "dead".
	 * Cell status effectively changes on next {@link #update()} call.
	 */
	public void setNextState(){
		Stream<CellModel> neighborStream = Arrays.asList(this.neighbors).stream();
		Predicate<CellModel> itIsAlive = p-> p.isAlive();
		int liveNeighbors=(int) neighborStream.filter(itIsAlive).count();
		switch(liveNeighbors){
			case NEIGHBORS_TO_SURVIVE:
				this.remain();
				break;
			case NEIGHBORS_TO_BREED:
				this.live();
				break;
			default:
				this.die();
				break;
		}
	}
	
	/**
	 * @param other CellModel to compare to
	 * @return <b>true</b> if and only if the x and y coordinates and live state of this and other are the same, otherwise <b>false</b>
	 */
	public boolean equals(CellModel other){
		if(this.x==other.getX()&& this.y==other.getY()&&this.alive==other.isAlive()){
			return true;
		}
		else{
			return false;
		}
	}
}
