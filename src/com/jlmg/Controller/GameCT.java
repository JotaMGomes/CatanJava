package com.jlmg.Controller;

import java.util.concurrent.TimeUnit;

import com.jlmg.Boundary.Board;
import com.jlmg.Entity.Cell;
import com.jlmg.Entity.DevCard;
import com.jlmg.Entity.Dice;
import com.jlmg.Entity.Edge;
import com.jlmg.Entity.Player;
import com.jlmg.Entity.Vertex;
import com.jlmg.util.CellType;
import com.jlmg.util.DevCardType;
import com.jlmg.util.GameState;

import javafx.scene.paint.Color;

public class GameCT {

	private static Board vBoard;
	public static Integer currPlayer = 0;
	public static GameState gameStep = GameState.START0;
	
	//define arrays
	public static Dice[] diceCT = new Dice[2];
	public static Player[] arPlayer = new Player[3];
	public static DevCard[] deckDev = new DevCard[25];
	public static Cell[] arCell = new Cell[19];
	public static Vertex[] arVertex = new Vertex[54];
	public static Edge[] arEdge = new Edge[72];
	
	/**
	 * Initialize game objects
	 */
	public static void initGame() {
		initArrays();
	}
	
	/**
	 * assign the board for the game
	 * @param newBoard
	 */
	public static void setBoard(Board newBoard) {
		vBoard = newBoard;
	}
	
	/**
	 * Initialize arrays of objects
	 */
	public static void initArrays() {
		//create array of cells
		createArrayCells();
		
		//create array of vertices
		createArrayVertices();
		
		//create array of Edges
		createArrayEdges();
		
		//link edges and vertices
		linkEdgesAndVertices();
	}
	
	
	public static void playGame() {
		startGame();
	}
	
	private static void startGame() {
		startBoard();
		initDecks();
		initPlayers();
		initDices();
		currPlayer = 0;
		rollDices();
		playFase1();
	}
	
	private static void startBoard() {
		
		//Hide all vertices spots
		for (Vertex vVtc : arVertex) {
			vVtc.vCircle.setVisible(false);
		}
		
		//Hide all edges spots
		for (Edge vEdg : arEdge) {
			vEdg.vCircle.setVisible(false);
		}
		
		//Hide all thief spots
		for (Cell vCell : arCell) {
			vCell.vCircle.setVisible(vCell.getCellType() == CellType.DESERT);
		}
	}
	
	private static void initDecks() {
		
		//Create 25 dev cards
		for (int i = 0; i < 21; i++) {
			
			if (i < 15) 
				//14 knigts
				deckDev[i] = new DevCard(DevCardType.KNIGHT);
			else if (i < 19)
				//6 victory
				deckDev[i] = new DevCard(DevCardType.VICTORY);
			else {
				//2 road build
				deckDev[19] = new DevCard(DevCardType.ROAD_BUILD);
				deckDev[20] = new DevCard(DevCardType.ROAD_BUILD);
				//2 monopoly
				deckDev[21] = new DevCard(DevCardType.MONOPOLY);
				deckDev[22] = new DevCard(DevCardType.MONOPOLY);
				//2 years of plenty
				deckDev[23] = new DevCard(DevCardType.YEARS_PLENTY);
				deckDev[24] = new DevCard(DevCardType.YEARS_PLENTY);
			}
		}
	}
	
	private static void initPlayers() {
		arPlayer[0] = new Player(Color.BROWN, 0);
		arPlayer[0].playerStage.setX(1000);
		arPlayer[0].playerStage.setY(150);
		
		arPlayer[1] = new Player(Color.DARKMAGENTA, 1);
		arPlayer[1].playerStage.setX(1000);
		arPlayer[1].playerStage.setY(350);
		
		arPlayer[2] = new Player(Color.TOMATO, 2);
		arPlayer[2].playerStage.setX(1000);
		arPlayer[2].playerStage.setY(550);
	}
	
	private static void playFase1() {
		
		vBoard.updateCurrPlayer();
		vBoard.showAllFreeVertices(true);
		
	}
	
	public static void nextStep() {
		
		if (gameStep == GameState.START1) {
			
			int totalVillage = 0;
			for (Vertex vAux : arVertex) {
				if (vAux.getPlayerNum() > -1) {
					totalVillage++;
				}
			}
			
			int totalRoads = 0;
			for (Edge eAux : arEdge) {
				if (eAux.getPlayerNum() > -1) {
					totalRoads++;
				}
			}
			
			if (totalVillage == 6 && totalRoads == 6) {
				gameStep = GameState.PLAY_DICE;
			} 
		}
	}
	
	public static void showEdgesFromVertex(int numVertex, boolean showEdge) {
		vBoard.showAllFreeVertices(false);
		vBoard.showEdgesFromVertex(numVertex, showEdge);
		vBoard.updateMsg("Put a road");
	}
	
	public static void hideAllFreeEdges() {
		vBoard.hideAllFreeEdges();
		nextPlayer();
	}
	
	private static void nextPlayer() {
		
		
		if (gameStep == GameState.START0) {
			//first round of start stage
			
			//set next player
			currPlayer++;
			if (currPlayer > 2) {
				
				//first round has ended, start second round
				//with the same player
				currPlayer = 2;
				gameStep = GameState.START1;
			}
			
			//show all free vertices
			vBoard.showAllFreeVertices(true);
			
			//update message
			vBoard.updateMsg("Put a village");
			
		} else if (gameStep == GameState.START1) {
			//second round of start stage
			
			//set next player
			currPlayer--;
			if (currPlayer < 0) {
				
				//second round has finished, enable play dice
				//with the same player
				currPlayer = 0;
				
				//hide all free vertices
				vBoard.showAllFreeVertices(false);
				
				//update game state to PLAY_DICE
				gameStep = GameState.PLAY_DICE;
				
				//update message
				vBoard.updateMsg("Roll the dices");
				
				//enable roll dice button
				arPlayer[currPlayer].DisableBtnDice(false);
			} else {
				vBoard.showAllFreeVertices(true);
				vBoard.updateMsg("Put a village");
			}
		} else {
			//set next player
			currPlayer++;
			if (currPlayer > 2) 
				currPlayer = 0;
			
			//enable roll dice button
			arPlayer[currPlayer].DisableBtnDice(false);
		}
		
		//update current player message
		vBoard.updateCurrPlayer();

	}
	
	/**
	 * init dices
	 */
	private static void initDices() {
		diceCT[0] = new Dice();
		diceCT[1] = new Dice();
	}
	
	/**
	 * star new turn
	 */
	public static void startTurn() {
		rollDices();
		dealCards();
		nextPlayer();
	}
	
	/**
	 * roll dices
	 */
	private static void rollDices() {
		for (Integer i=0; i<2;i++) {
			try {
		        if (i == 1)
		        	TimeUnit.MILLISECONDS.sleep(100);
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
			diceCT[i].rollDice();
			vBoard.txtDice[i].setText("Dice " + i + ": " + diceCT[i].getValue());
		}
		
	}
	
	/**
	 * deal cards
	 */
	private static void dealCards() {
		
		//get sum of dices
		final Integer totalDice = diceCT[0].getValue() + diceCT[1].getValue();
		
		//iterate through each cell 
		for (Cell vCell : arCell) {
			
			//if cell number = totalDice and thief is not there
			if (vCell.getNumber() == totalDice && !vCell.vCircle.isVisible()) {
				
				//verify if vertices has a village or city
				for (int index : vCell.getArVertex()) {
					if (arVertex[index].getPlayerNum() > -1) {
						//ad a card for a village
						arPlayer[arVertex[index].getPlayerNum()].addResCard(vCell.getCellType());
						
						//if it is a city, add one more card
						if(arVertex[index].getLevel() > 1) {
							arPlayer[arVertex[index].getPlayerNum()].addResCard(vCell.getCellType());
						}
					}
				}
				
			}
		}
		
		//update player resources
		for (Player vP : arPlayer) {
			vP.updatePlayerResources();
		}
		
	}
	
	/**
	 * Create array of cells
	 */
	private static void createArrayCells() {
		
		//array with dice number of cells
		final int[] arDiceNumber = {6,3,8,2,4,5,10,5,9,0,5,9,10,11,3,12,8,4,11};
		
		//array with cell types
		CellType[] arCT = {CellType.WOOD, CellType.SHEEP, CellType.SHEEP,
				CellType.WHEAT, CellType.ORE, CellType.WHEAT, CellType.WOOD,
				CellType.WOOD, CellType.BRICK, CellType.DESERT, CellType.ORE, CellType.WHEAT,
				CellType.WHEAT, CellType.ORE, CellType.WOOD ,CellType.SHEEP,
				CellType.BRICK, CellType.SHEEP, CellType.BRICK
		};
		
		//create objects
		for (int k = 0; k < 19; k++) {
			arCell[k] = new Cell(arDiceNumber[k],arCT[k]);
		}
	}
	
	/**
	 * Create array of vertices
	 */
	private static void createArrayVertices() {
		
		//vertices for each cell
		final int[][] arVertexCell = {
				{0,1,2,8,9,10}, {2,3,4,10,11,12}, {4,5,6,12,13,14},
				{7,8,9,17,18,19}, {9,10,11,19,20,21}, {11,12,13,21,22,23}, {13,14,15,23,24,25},
				{16,17,18,27,28,29}, {18,19,20,29,30,31}, {20,21,22,31,32,33}, {22,23,24,33,34,35}, {24,25,26,35,36,37},
				{28,29,30,38,39,40}, {30,31,32,40,41,42}, {32,33,34,42,43,44}, {34,35,36,44,45,46},
				{39,40,41,47,48,49}, {41,42,43,49,50,51}, {43,44,45,51,52,53}
		};
		
		//iterate through cells
		for (int k = 0; k < 19; k++) {
			
			//link vertex to cell
			arCell[k].setArVertex(arVertexCell[k]);
			
			//iterate through all vertices of this cell
			for (int l = 0; l < arVertexCell[k].length; l++) {
				
				//if vertex is not created yet, create it
				if (arVertex[arVertexCell[k][l]] == null) {
					arVertex[arVertexCell[k][l]] = new Vertex(arVertexCell[k][l]);
				}
			}
		}
	}
	
	
	/**
	 * create array of edges
	 */
	private static void createArrayEdges() {
		
		//edges for each cell
		final int[][] arEdgeCell = {
				{0,1,6,7,11,12}, {2,3,7,8,13,14}, {4,5,8,9,15,16},
				{10,11,18,19,24,25}, {12,13,19,20,26,27}, {14,15,20,21,28,29}, {16,17,21,22,30,31},
				{23,24,33,34,39,40}, {25,26,34,35,41,42}, {27,28,35,36,43,44}, {29,30,36,37,45,46},{31,32,37,38,47,48},
				{40,41,49,50,54,55}, {42,43,50,51,56,57}, {44,45,51,52,58,59}, {46,47,52,53,60,61},
				{55,56,62,63,66,67}, {57,58,63,64,68,69}, {59,60,64,65,70,71}
		};
		
		//iterate through cells
		for (int k = 0; k < 19; k++) {
			
			//link edge to cell
			arCell[k].setArEdge(arEdgeCell[k]);
			
			//iterate through all edges of this cell
			for (int l = 0; l < arEdgeCell[k].length; l++) {
				
				//if edge is not created yet, create it
				if (arEdge[arEdgeCell[k][l]] == null) {
					arEdge[arEdgeCell[k][l]] = new Edge(arEdgeCell[k][l]);
				}
			}
		}
	}
	
	/**
	 * Link edges and vertices
	 */
	private static void linkEdgesAndVertices() {
		
		//vertices for each edge
		int[][] arEdgVtc = {
				{0,1},{1,2},{2,3},{3,4},{4,5},{5,6},
				{0,8},{2,10},{4,12},{6,14},
				{7,8},{8,9},{9,10},{10,11},{11,12},{12,13},{13,14},{14,15},
				{7,17},{9,19},{11,21},{13,23},{15,25},
				{16,17},{17,18},{18,19},{19,20},{20,21},{21,22},{22,23},{23,24},{24,25},{25,26},
				{16,27},{18,29},{20,31},{22,33},{24,35},{26,37},
				{27,28},{28,29},{29,30},{30,31},{31,32},{32,33},{33,34},{34,35},{35,36},{36,37},
				{28,38},{30,40},{32,42},{34,44},{36,46},
				{38,39},{39,40},{40,41},{41,42},{42,43},{43,44},{44,45},{45,46},
				{39,47},{41,49},{43,51},{45,53},
				{47,48},{48,49},{49,50},{50,51},{51,52},{52,53}
		};
		
		//iterate through all edges
		for (int k = 0; k < 72; k++) {
			arEdge[k].addVertex(arEdgVtc[k][0]);
			arEdge[k].addVertex(arEdgVtc[k][1]);
		}
		
		//iterate through all edges
		for (int k = 0; k < 72; k++)
			
			//iterate through vertices linked to the edge
			for (int l = 0; l < 2; l++)
				if (!arVertex[arEdge[k].getVertex(l)].hasEdge(k)) 
					arVertex[arEdge[k].getVertex(l)].addEdge(k);
		
		
		//iterate through all cells
		for (int k = 0; k < 19; k++) {
			
			for (int l: arCell[k].getArVertex())
				// link cell to vertex
				arVertex[l].addCell(k);
				
			for (int l: arCell[k].getArEdge())
				// link cell to edges
				arEdge[l].addCell(k);
		}
	}

}
