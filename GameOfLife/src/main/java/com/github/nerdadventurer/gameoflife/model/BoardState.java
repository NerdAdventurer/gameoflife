/**
 * 
 */
package com.github.nerdadventurer.gameoflife.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Representation of a BoardModel state on a given turn, expressed as a list of live cells.
 * @author Aki Laukkanen
 *
 */
public class BoardState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long turnNumber;
	private List<CellModel> liveCells;
	/**
	 * 
	 */
	public BoardState() {
		liveCells=new ArrayList<CellModel>();
	}
	
	/**
	 * @param liveCellsNow ArrayList of live cells on board this game turn
	 * @param turn Turn number
	 */
	public BoardState(List<CellModel> liveCellsNow, long turn){
		this.liveCells=liveCellsNow;
		this.turnNumber=turn;
	}

	/**
	 * @return the turnNumber
	 */
	public long getTurnNumber() {
		return turnNumber;
	}

	/**
	 * @param turnNumber the turnNumber to set
	 */
	public void setTurnNumber(long turnNumber) {
		this.turnNumber = turnNumber;
	}

	/**
	 * @return the liveCells
	 */
	public List<CellModel> getLiveCells() {
		return liveCells;
	}

	/**
	 * @param liveCells the liveCells to set
	 */
	public void setLiveCells(List<CellModel> liveCells) {
		this.liveCells = liveCells;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * Determine whether this board state contains the same living cells as another state.
	 * @param other BoardState to compare against
	 * @return <b>true</b> if <b>all</b> living cells in this BoardState are the same as the other BoardState, otherwise <b>false</b>
	 */
	boolean isIdentical(BoardState other){
		if(this.getLiveCount()!=other.getLiveCount()){
			return false;
		}
		else{
			Stream<CellModel> thisLivingAsStream=this.liveCells.stream();
			List<CellModel> otherLiving=other.getLiveCells();
			Predicate<CellModel> notInOther = p -> !otherLiving.contains(p);
			thisLivingAsStream.filter(notInOther); // Because all elements in both lists are unique, any match must be 1:1.
			return thisLivingAsStream.count()==0;
		}
	}

	long getLiveCount() {
		return this.liveCells.size();
	}

}
