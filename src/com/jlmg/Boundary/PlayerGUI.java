package com.jlmg.Boundary;

import java.util.HashMap;

import com.jlmg.util.CellType;
import com.jlmg.util.DevCardType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
public class PlayerGUI implements IPlayerHandler {
	
	// public GUI objects
	public Stage playerStage = new Stage(); // stage object
	
	// private GUI objects
	private Button btnRollDices = new Button();
	private Button btnDevCard[] = new Button[5];
	private Button btnTrade = new Button();
	private Button btnDone = new Button();
	private Button btnNewDevCard = new Button();
	private Button btnNewVillage = new Button();
	private Button btnNewCity = new Button();
	private Button btnNewRoad = new Button();
	
	
	
	private Text txtResources;

	/**
	 * Initializes a new player GUI
	 * @param index;  the player index
	 * @param vColor: the player color
	 */
	public PlayerGUI(Integer index, Color vColor) {
		
		double BoxWidth = 400;
		
		// creates the interface
		GridPane root=new GridPane(); 
		
		// basic window
		Rectangle rect01 = new Rectangle(BoxWidth,15.0d);
		rect01.setFill(vColor);
		root.add(rect01,0,0);
		
		// resources info
		txtResources = new Text();
		txtResources.setFont(Font.font("Verdana",12));
		txtResources.setFill(Color.BLACK);
		txtResources.setText("BRICK:0 | WOOD:0 | WHEAT:0 | SHEEP:0 | ORE:0");
		txtResources.setY(25.0d);
		root.add(txtResources, 0, 1);
		
		btnDevCard[0] = new Button();
		btnDevCard[0].setText("KNIGHT 0");

		btnDevCard[1] = new Button();
		btnDevCard[1].setText("VICTORY 0");

		btnDevCard[2] = new Button();
		btnDevCard[2].setText("ROAD_BUILD 0");
		
		btnDevCard[3] = new Button();
		btnDevCard[3].setText("MONOPOLY 0");
		
		btnDevCard[4] = new Button();
		btnDevCard[4].setText("YEARS PLENTY 0");
		
		// new row
		HBox hb1 = new HBox();
		for (int i=0;i<5;i++) {
			btnDevCard[i].setFont(Font.font("Verdana",10));
			btnDevCard[i].setDisable(true);	
			hb1.getChildren().add(btnDevCard[i]);
		}		
		root.add(hb1, 0, 2);
		
		// new row
		HBox hb2 = new HBox();
		
		// new village
		btnNewVillage.setText("New Village");
		btnNewVillage.setFont(Font.font("Verdana",10));
		btnNewVillage.setDisable(true);
		// btnNewVillage.setOnAction(doRollDice);
		hb2.getChildren().add(btnNewVillage);
		
		// new city
		btnNewCity.setText("New City");
		btnNewCity.setFont(Font.font("Verdana",10));
		btnNewCity.setDisable(true);
		// btnNewCity.setOnAction(doRollDice);
		hb2.getChildren().add(btnNewCity);
		
		// new road
		btnNewRoad.setText("New Road");
		btnNewRoad.setFont(Font.font("Verdana",10));
		btnNewRoad.setDisable(true);
		// btnNewRoad.setOnAction(doRollDice);
		hb2.getChildren().add(btnNewRoad);
		
		// new Dev Card
		btnNewDevCard.setText("New Dev Card");
		btnNewDevCard.setFont(Font.font("Verdana",10));
		btnNewDevCard.setDisable(true);
		btnNewDevCard.setOnAction(doDevCard);
		hb2.getChildren().add(btnNewDevCard);
		
		root.add(hb2, 0, 3);
		
		// new row
		HBox hb3 = new HBox();
		
		// dice info
		btnRollDices.setText("Roll Dices");
		btnRollDices.setFont(Font.font("Verdana",10));
		btnRollDices.setDisable(true);
		btnRollDices.setOnAction(doRollDice);
		hb3.getChildren().add(btnRollDices);
		
		// trade
		btnTrade.setText("Trade");
		btnTrade.setFont(Font.font("Verdana",10));
		btnTrade.setDisable(true);
		hb3.getChildren().add(btnTrade);
		
		// finish turn
		btnDone.setText("Done");
		btnDone.setFont(Font.font("Verdana",10));
		btnDone.setDisable(true);
		btnDone.setOnAction(doDone);
		hb3.getChildren().add(btnDone);
		
		root.add(hb3, 0, 4);
		
		Scene secondScene = new Scene(root, BoxWidth, 100);
		
		playerStage = new Stage();
		playerStage.setScene(secondScene);
		playerStage.setTitle("Player " + index.toString());
		playerStage.show();
		
	}
	
	/**
	 * Enable/disable roll dice button
	 * @param isDisabled: boolean to enable/disable button
	 */
	public void disableBtnDice(boolean isDisabled) {
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
			btnDiceOnClick();
		}
	};
	
	/**
	 * Button event handler: Done
	 */
	private EventHandler<ActionEvent> doDone = new EventHandler<ActionEvent>() {
		@Override
        public void handle(ActionEvent event) {

			// call event handler
			btnDoneOnClick();
		}
	};
	
	/**
	 * Button event handler: New Card
	 */
	private EventHandler<ActionEvent> doDevCard = new EventHandler<ActionEvent>() {
		@Override
        public void handle(ActionEvent event) {

			// call event handler
			btnNewDevOnClick();
		}
	};

	/**
	 * Defines event handlers do be overridden
	 */
	@Override
	public void btnDiceOnClick() {
		System.out.println("Roll Dice"); 
	}
	
	@Override
	public void btnDoneOnClick() {
		System.out.println("Done"); 
	}
	
	@Override
	public void btnNewDevOnClick() {
		System.out.println("Dev Card"); 
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
	
	/**
	 * disable all buttons
	 */
	public void disableAllBtns() {
		btnRollDices.setDisable(true);;
		btnNewDevCard.setDisable(true);
		btnTrade.setDisable(true);
		btnDone.setDisable(true);
		btnNewCity.setDisable(true);
		btnNewVillage.setDisable(true);
		btnNewRoad.setDisable(true);
		
		disableDevBtns();
	}
	
	/**
	 * disable all development card buttons
	 */
	public void disableDevBtns() {
		for (int i=0;i<5;i++) {
			btnDevCard[i].setDisable(true);	
		}
	}
	
	/** 
	 * enable/disable trade Button
	 */
	public void disableBtnTrade(boolean isDisabled) {
		btnTrade.setDisable(isDisabled);
	}
	
	/** 
	 * enable/disable done Button
	 */
	public void disableBtnDone(boolean isDisabled) {
		btnDone.setDisable(isDisabled);
	}
	
	/** 
	 * enable/disable road Button
	 */
	public void disableBtnRoad(Boolean isDisabled) {
		btnNewRoad.setDisable(isDisabled);
	}
	
	/** 
	 * enable/disable village Button
	 */
	public void disableBtnVillage(Boolean isDisabled) {
		btnNewVillage.setDisable(isDisabled);
	}
	
	/** 
	 * enable/disable city Button
	 */
	public void disableBtnCity(Boolean isDisabled) {
		btnNewCity.setDisable(isDisabled);
	}
	
	/** 
	 * enable/disable development card Button
	 */
	public void disableBtnNewDevCard(Boolean isDisabled) {
		btnNewDevCard.setDisable(isDisabled);
	}
	
	/**
	 * update development card button
	 * @param dct: type of the card
	 * @param value: quantity of cards
	 */
	public void updateBtnDevCard(DevCardType dct, Integer value) {
		
		// update development card button text and enable it
		switch (dct) {
		case KNIGHT:
			btnDevCard[0].setText("KNIGHT " + value.toString());
			btnDevCard[0].setDisable(false);
			break;
		case VICTORY:
			btnDevCard[1].setText("VICTORY " + value.toString());
			btnDevCard[1].setDisable(false);
			break;
		case ROAD_BUILD:
			btnDevCard[2].setText("ROAD_BUILD " + value.toString());
			btnDevCard[2].setDisable(false);
			break;
		case MONOPOLY:
			btnDevCard[3].setText("MONOPOLY " + value.toString());
			btnDevCard[3].setDisable(false);
			break;
		case YEARS_PLENTY:
			btnDevCard[4].setText("YEARS PLENTY " + value.toString());
			btnDevCard[4].setDisable(false);
			break;
		}
	}
}
