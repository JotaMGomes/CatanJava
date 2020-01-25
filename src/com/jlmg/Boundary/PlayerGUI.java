package com.jlmg.Boundary;

import java.util.HashMap;

import com.jlmg.util.CellType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Defines the player GUI
 * @author Jose Luiz Gomes
 *
 */
public class PlayerGUI implements IMouseHandler {
	
	// public GUI objects
	public Stage playerStage = new Stage(); // stage object
	
	// private GUI objects
	private Button btnRollDices = new Button();
	private Text txtResources;

	/**
	 * Initializes a new player GUI
	 * @param index;  the player index
	 * @param vColor: the player color
	 */
	public PlayerGUI(Integer index, Color vColor) {
		
		// creates the interface
		BorderPane root = new BorderPane();
		
		// basic window
		Rectangle rect01 = new Rectangle(300.0d,15.0d);
		rect01.setFill(vColor);
		root.getChildren().add(rect01);
		
		// resources info
		txtResources = new Text();
		txtResources.setFont(Font.font("Verdana",12));
		txtResources.setFill(Color.BLACK);
		txtResources.setText("BRICK:0 | WOOD:0 | WHEAT:0 | SHEEP:0 | ORE:0");
		txtResources.setY(25.0d);
		root.getChildren().add(txtResources);
		
		// dice info
		btnRollDices.setText("Roll Dices");
		btnRollDices.setFont(Font.font("Verdana",10));
		btnRollDices.setDisable(true);
		btnRollDices.setOnAction(doRollDice);
		root.setBottom(btnRollDices);
		
		Scene secondScene = new Scene(root, 300, 100);
		
		playerStage = new Stage();
		playerStage.setScene(secondScene);
		playerStage.setTitle("Player " + index.toString());
		playerStage.show();
		
	}
	
	/**
	 * Enable/disable roll dice button
	 * @param isDisabled: boolean to enable/disable button
	 */
	public void DisableBtnDice(boolean isDisabled) {
		btnRollDices.setDisable(isDisabled);
	}
	
	/**
	 * Button event handler: Roll dice
	 */
	private EventHandler<ActionEvent> doRollDice = new EventHandler<ActionEvent>() {
		@Override
        public void handle(ActionEvent event) {
			// disable button
			btnRollDices.setDisable(true);
			
			// call event handler
			handleSpotOnClick();
		}
	};

	/**
	 * Defines event handler do be overridden
	 */
	@Override
	public void handleSpotOnClick() {
		System.out.println("Roll Dice"); 
	}
	
	/**
	 * update player resources
	 */
	public void updateResources(HashMap<CellType, Integer> hmResCard) {
		
		String txtRsc = "";
		txtRsc += "BRICK:"+hmResCard.get(CellType.BRICK);
		txtRsc += " | WOOD:"+hmResCard.get(CellType.WOOD);
		txtRsc += " | WHEAT:"+hmResCard.get(CellType.WHEAT);
		txtRsc += " | SHEEP:"+hmResCard.get(CellType.SHEEP);
		txtRsc += " | ORE:"+hmResCard.get(CellType.ORE);
		txtResources.setText(txtRsc);
		
	}
}
