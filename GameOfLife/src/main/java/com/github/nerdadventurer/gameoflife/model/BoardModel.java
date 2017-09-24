/**
 * 
 */
package com.github.nerdadventurer.gameoflife.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a logical model of a grid of cells.
 * @author Aki Laukkanen
 *
 */
public class BoardModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<CellModel> cells;
	private HashMap<Integer, HashMap<Integer, CellModel>> xyMap;
	private int gridSize;
	private HashMap <Long, ArrayList<BoardState>> loopDetectionCache;
	private long turnNumber;
	/**
	 * 
	 */
	public BoardModel(int size) {
		cells=new ArrayList<CellModel>();
		xyMap= new HashMap<Integer, HashMap<Integer, CellModel>>();
		this.gridSize=size;
		loopDetectionCache=new HashMap<Long, ArrayList<BoardState>>();
		turnNumber=0;
	}
	
	/**
	 * Create new cells for this Board
	 */
	public void populate(){
		for(int x = 0; x < gridSize; x++){
			HashMap<Integer, CellModel> column = new HashMap<Integer, CellModel>();
			for(int y = 0; y < gridSize; y++){
				CellModel cell = new CellModel(x,y);
				cells.add(cell);
				column.put(y, cell);
			}
			xyMap.put(x, column);
		}
	}

	/**
	 * @return the cells
	 */
	public ArrayList<CellModel> getCells() {
		return cells;
	}

	/**
	 * @param cells the cells to set
	 */
	public void setCells(ArrayList<CellModel> cells) {
		this.cells = cells;
	}

	/**
	 * @return the xyMap
	 */
	public HashMap<Integer, HashMap<Integer, CellModel>> getXyMap() {
		return xyMap;
	}

	/**
	 * @param xyMap the xyMap to set
	 */
	public void setXyMap(HashMap<Integer, HashMap<Integer, CellModel>> xyMap) {
		this.xyMap = xyMap;
	}

	/**
	 * @return the gridSize
	 */
	public int getGridSize() {
		return gridSize;
	}

	/**
	 * @param gridSize the gridSize to set
	 */
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @param x coordinate on x axis
	 * @param y coordinate on y axis
	 * @return CellModel at given XY coordinates on grid
	 * @throws InvalidCoordinateException if coordinates are outside current grid size
	 */
	public CellModel getCellAt(int x, int y) throws InvalidCoordinateException {
		if(x < 0 || x >= gridSize || y < 0 || y >= gridSize){
			throw new InvalidCoordinateException("Trying to access cell outside of grid.");
		}
		else{
			return xyMap.get(x).get(y);
		}		
	}
	
	/**
	 * Get representation of current board state as a collection of live cells, and a turn number.
	 * @return current board state
	 */
	private BoardState getCurrentState(){
		Stream<CellModel> neighborStream = cells.stream();
		Predicate<CellModel> itIsAlive = p-> p.isAlive();
		List<CellModel> liveCellsNow = neighborStream.filter(itIsAlive).collect(Collectors.toList());
		BoardState state = new BoardState(liveCellsNow,this.turnNumber);
		return state;
	}
	
	/**
	 * Check if board has reached a looping state, and if not, store current state to cache.
	 * Must be called <b> exactly once</b> per cycle. 
	 */
	public void updateLoopCache(){
		BoardState current = getCurrentState();
		long liveNow = current.getLiveCount();	
		if(loopDetectionCache.containsKey(liveNow)){
			//There are previous states with same live cell count. Get list of those from cache.
			ArrayList<BoardState> sameCount = loopDetectionCache.get(liveNow);
			Stream<BoardState> sameCountStream = sameCount.stream();
			Predicate<BoardState> identicalExists = p-> p.isIdentical(current);
			if(sameCountStream.filter(identicalExists).count()>0){
				// Identical board state was found; stop simulation on loop state.
				stopOnLoop();
			}
			else{
				// None of the states were identical, add this state to cache and carry on.
				sameCount.add(current);
				nextStep();
			}
		}
		else{
			// There are no previous states with same live cell count; start a new list of states with this live cell count, add current state, and continue.
			ArrayList<BoardState> newCount = new ArrayList<BoardState>();
			newCount.add(current);
			loopDetectionCache.put(liveNow, newCount);
			nextStep();
		}
	}

	private void stopOnLoop() {
		// TODO STOP! HAMMERTIME!
	}
	
	private void nextStep(){
		// TODO Keep calm and carry on...
	}
}
