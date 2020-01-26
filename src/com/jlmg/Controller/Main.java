package com.jlmg.Controller;

import com.jlmg.Boundary.Board;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//initialize game objects
			GameCT.initGame();
			
			//create board
			Board vBoard = new Board();
			vBoard.start(primaryStage);
			
			//set board to game rules
			GameCT.setBoard(vBoard);
			
			//play game
			GameCT.startGame();		
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
