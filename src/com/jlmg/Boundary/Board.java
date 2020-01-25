package com.jlmg.Boundary;

import com.jlmg.Controller.GameCT;
import com.jlmg.Entity.Edge;
import com.jlmg.Entity.Vertex;
import com.jlmg.util.CellType;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Defines the board object
 * @author Jose Luiz Gomes
 *
 */
public class Board extends Application {

	// set edge size of the cell
	private final double hexSize = 50.0d;
	
	// set radios of the circles
	private final double vtcRadios = 8.0d;
	
	// set arrays
	public Text[] txtDice = new Text[2];
	
	// text for current player
	private Text txtCurrPlayer = new Text();
	
	// text for messages
	private Text txtMsg = new Text();
	
	/**
	 * draw objects on stage
	 */
	@Override
	public void start(Stage primaryStage) {
		
		// define basic position for board
		double posX = 150;
		double posY = 40;
		
		try {
			
			// objects for board
			Group grp01 = drawBoard(posX, posY);
			
			// objects for info pannel
			Group grp02 = drawInfoPannel(posX, posY);
			
			// pane to add all objects
			BorderPane root = new BorderPane();
			root.getChildren().addAll(grp01, grp02);
			
			// define scene
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("CATAN Project");
			primaryStage.show();
			
			// exit app when first stage is closed
			primaryStage.setOnCloseRequest(e -> Platform.exit());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create main GUI objects for the board
	 * @param x: reference for x pos
	 * @param y: reference for y pos
	 * @return:  group of GUI objects
	 */
	private Group drawBoard(double x, double y) {
		
		// factor of proportion
		final double xFactor = Math.sqrt(3.0d)/2.0d;
		
		// {cells per line, x cells related to first cell}
		final int[][] arBoard = {{3,0},{4,-1},{5,-2},{4,-1},{3,0}};
		
		// array of text to print numbers
		Text[] arTextNum = new Text[19];
		
		// array of hexagons
		Polygon[] arPlHex = new Polygon[19];
		
		// aux to cells
		int k = 0;
		
		// aux vars for position
		double px, py;
		
		// groups of objects
		Group vGrpH = new Group();
		Group vGrpV = new Group();
		Group vGrpE = new Group();
		Group vGrpT = new Group();

		
		// Draw hexagons
		for (int i=0; i < arBoard.length; i++) {
			
			// calculate new start position
			py = y + i * 1.5d * hexSize;
			px = x + arBoard[i][1] * xFactor * hexSize;
			
			// iteract to each line of cells
			for (int j=0; j < arBoard[i][0]; j++) {
				
				// define poligon points
				arPlHex[k] = new Polygon();
				arPlHex[k].getPoints().addAll(new Double[] {
		        		px, py,
		        		px + xFactor * hexSize, py + 0.5d * hexSize,
		        		px + xFactor * hexSize, py + 1.5d * hexSize,
		        		px, py + 2.0d * hexSize,
		        		px - xFactor * hexSize, py + 1.5d * hexSize,
		        		px - xFactor * hexSize, py + 0.5d * hexSize
		        });
				
				// define color
				arPlHex[k].setFill(GameCT.arCell[k].getCellType().getColor());
				
				// add hexagon to group
				vGrpH.getChildren().addAll(arPlHex[k]);
				
				// draw hexagon numbers
				if (GameCT.arCell[k].getIndex() > 0) {
					arTextNum[k] = new Text(GameCT.arCell[k].getStrNumber());
					arTextNum[k].setFont(Font.font("Verdana",20));
					arTextNum[k].setFill(Color.BLACK);
					arTextNum[k].setX(px + ((GameCT.arCell[k].getIndex() < 10) ? -6.0d : -12.0d));
					arTextNum[k].setY(py + hexSize + 6.0d);
					
					vGrpH.getChildren().addAll(arTextNum[k]);
				}
				
				// draw thief circles
				GameCT.arCell[k].vCircle.setCenterX(px - 0.4d * hexSize);
				GameCT.arCell[k].vCircle.setCenterY(py + hexSize);
				GameCT.arCell[k].vCircle.setRadius(vtcRadios);
				GameCT.arCell[k].vCircle.setFill(Color.BLACK);
				
				vGrpT.getChildren().addAll(GameCT.arCell[k].vCircle);
				
				// draw vertex circles
				//i f cell is not the desert 
				if (GameCT.arCell[k].getCellType() != CellType.DESERT) {
					
					// reset aux counter
					int m = 0;
					
					// reset aux variables for positions
					double vx = 0.0d, vy = 0.0d;
					
					// iterate through all vertices of the cell
					for (int l: GameCT.arCell[k].getArVertex()) {
						
						// if spot is not yet defined
						if (GameCT.arVertex[l].vCircle.getCenterX() == 0.0d) {
							
							// switch vertex number
							switch (m) {
								case 0:
									vx = px - xFactor * hexSize;
									vy = py + 0.5d * hexSize;
									break;
								case 1:
									vx = px;
									vy = py;
									break;
								case 2:
									vx = px + xFactor * hexSize;
									vy = py + 0.5d * hexSize;
									break;
								case 3:
									vx = px - xFactor * hexSize;
									vy = py + 1.5d * hexSize;;
									break;
								case 4:
									vx = px;
									vy = py + 2.0d * hexSize;
									break;
								case 5:
									vx = px + xFactor * hexSize;
									vy = py + 1.5d * hexSize;
									break;
							}
							
							// define spot
							GameCT.arVertex[l].vCircle.setCenterX(vx);
							GameCT.arVertex[l].vCircle.setCenterY(vy);
							GameCT.arVertex[l].vCircle.setRadius(vtcRadios);
							GameCT.arVertex[l].vCircle.setFill(Color.SLATEBLUE);
							vGrpV.getChildren().addAll(GameCT.arVertex[l].vCircle);
						}
						
						// update aux variable
						m++;
					}

					// reset aux counter
					m = 0;
					
					// reset aux variables for positions
					vx = 0.0d;
					vy = 0.0d;
					
					// iterate through all edges of the cell
					for (int l: GameCT.arCell[k].getArEdge()) {
						
						// if spot is not yet defined
						if (GameCT.arEdge[l].vCircle.getCenterX() == 0.0d) {
							
							// switch edge number
							switch (m) {
								case 0:
									vx = px - 0.5d * xFactor * hexSize;
									vy = py + 0.25d * hexSize;
									break;
								case 1:
									vx = px + 0.5d * xFactor * hexSize;;
									vy = py + 0.25d * hexSize;
									break;
								case 2:
									vx = px - xFactor * hexSize;
									vy = py + hexSize;
									break;
								case 3:
									vx = px + xFactor * hexSize;
									vy = py + hexSize;;
									break;
								case 4:
									vx = px - 0.5d * xFactor * hexSize;;
									vy = py + 1.75d * hexSize;
									break;
								case 5:
									vx = px + 0.5d * xFactor * hexSize;;
									vy = py + 1.75d * hexSize;
									break;
							}
							
							// define spot
							GameCT.arEdge[l].vCircle.setCenterX(vx);
							GameCT.arEdge[l].vCircle.setCenterY(vy);
							GameCT.arEdge[l].vCircle.setRadius(vtcRadios);
							GameCT.arEdge[l].vCircle.setFill(Color.AQUAMARINE);
							vGrpE.getChildren().addAll(GameCT.arEdge[l].vCircle);
						}
						
						// update aux variable
						m++;
					}
				}
				
				
				// update cell counter
				k++;
				
				// update x position
				px = px + 2.0 * xFactor * hexSize;

			}
		}
		
		vGrpH.getChildren().addAll(vGrpV);
		vGrpH.getChildren().addAll(vGrpE);
		vGrpH.getChildren().addAll(vGrpT);
		
		return vGrpH;
	}
		
	
    /**
     * Create GUI objects for info pannel
     * @param posX: reference for x pos
     * @param posY: reference for y pos
     * @return:     group of GUI objects
     */
	private Group drawInfoPannel(double posX, double posY) {
		
		Group vGrpI = new Group();
		
		// current player
		updateCurrPlayer();
		txtCurrPlayer.setFont(Font.font("Verdana",20));
		txtCurrPlayer.setFill(Color.BLACK);
		txtCurrPlayer.setX(posX + 8 * hexSize);
		txtCurrPlayer.setY(posY + 0.5d * hexSize);
		vGrpI.getChildren().add(txtCurrPlayer);
		
		// info msg
		updateMsg("Put a village");
		txtMsg.setFont(Font.font("Verdana",20));
		txtMsg.setFill(Color.BLACK);
		txtMsg.setX(posX + 0.5d * hexSize);
		txtMsg.setY(posY + 10 * hexSize);
		vGrpI.getChildren().add(txtMsg);
		
		// dices
		for (Integer i=0; i<2;i++) {
			txtDice[i] = new Text();
			txtDice[i].setFont(Font.font("Verdana",20));
			txtDice[i].setFill(Color.BLACK);
			txtDice[i].setX(posX + 8 * hexSize);
			txtDice[i].setY(posY + hexSize + i * 20.0d);
			txtDice[i].setText("Dice " + i + ": ");
			vGrpI.getChildren().add(txtDice[i]);	
		}
		
		return vGrpI;
	}
	
	/**
	 * Update current player
	 */
	public void updateCurrPlayer() {
		txtCurrPlayer.setText("Player " + GameCT.currPlayer.toString());
	}
	
	/**
	 * Update info message
	 * @param strMsg: message
	 */
	public void updateMsg(String strMsg) {
		txtMsg.setText(strMsg);
	}
	
	/**
	 * Display/hide all free vertices
	 * @param showVertex: boolean to display/hide free vertices
	 */
	public void showAllFreeVertices(boolean showVertex) {
		
		// iterate thought all vertices
		for(Vertex v1: GameCT.arVertex) {
				
			// choose only free vertices (planyerNum = -1)
			if (v1.getPlayerNum() == -1) {
				
				// set isFree true
				boolean isFree = true;
				
				// iterate thought all edges linked to the vertex
				for (Integer e1: v1.getEdgeLst()) {
					
					// verify if the vertices linked to the edge are free
					isFree = isFree && (GameCT.arVertex[GameCT.arEdge[e1].getVertex(0)].getPlayerNum() == -1);
					isFree = isFree && (GameCT.arVertex[GameCT.arEdge[e1].getVertex(1)].getPlayerNum() == -1);
				}
				
				// show/hide vertex
				v1.vCircle.setVisible(isFree && showVertex);
			}
		}
	}
	
	/**
	 * Display/hide edges linked to a vertex
	 * @param numVertex: vertex index
	 * @param showEdge:  display/hide edge
	 */
	public void showEdgesFromVertex(int numVertex, boolean showEdge) {
		
		// iterate thought all edges linked to the vertex
		for (Integer e1: GameCT.arVertex[numVertex].getEdgeLst()) {
			// show/hide edge
			if (showEdge)
				GameCT.arEdge[e1].vCircle.setVisible(GameCT.arEdge[e1].getPlayerNum() == -1);
			else
				GameCT.arEdge[e1].vCircle.setVisible(!(GameCT.arEdge[e1].getPlayerNum() == -1));
		}
	}
	
	/**
	 * Hide all free vertices from the board
	 */
	public void hideAllFreeEdges() {
		
		// iterate thought all edges
		for(Edge e1: GameCT.arEdge) {
			
			// if it is a free object, then hide it
			if (e1.getPlayerNum() == -1 && e1.vCircle.isVisible()) {
				e1.vCircle.setVisible(false);
			}
		}
	}
	
}
