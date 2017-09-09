/**
 * 
 */
package com.github.nerdadventurer.gameoflife.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
}
