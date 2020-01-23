package com.jlmg.Boundary;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class SpotGUI implements IMouseHandler{
	
	//circle to show on the board
	public Circle vCircle;
	
	public SpotGUI(){
		vCircle = new Circle(0,0,1);
	}
	
    //add mouse event handler 
    public void addMouseClickEvent() {
   	 vCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, doOnClick);
    }
	
	//remove mouse event handler from spot
    public void removeMouseClickEvent() {
   	 vCircle.removeEventHandler(MouseEvent.MOUSE_CLICKED, doOnClick);
    }
    
	//event handles
	//mouse event
	private EventHandler<MouseEvent> doOnClick = new EventHandler<MouseEvent>() { 
        @Override 
        public void handle(MouseEvent e) { 
        	handleSpotOnClick(); 
        } 
     };

	@Override
	public void handleSpotOnClick() {
		System.out.println("Hello World");
	} 
     
}
