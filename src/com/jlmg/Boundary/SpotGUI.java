package com.jlmg.Boundary;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

/**
 * Defines a Spot GUI 
 * @author Jose Luiz Gomes
 *
 */
public class SpotGUI implements IMouseHandler{
	
	public Circle vCircle; //circle to show on the board
	
	/**
	 * Initializes a new object
	 */
	public SpotGUI(){
		vCircle = new Circle(0,0,1);
	}
	
    // add mouse event handler 
    public void addMouseClickEvent() {
   	 vCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, doOnClick);
    }
	
	// remove mouse event handler from spot
    public void removeMouseClickEvent() {
   	 vCircle.removeEventHandler(MouseEvent.MOUSE_CLICKED, doOnClick);
    }
    
	// event handles
	// mouse event
	private EventHandler<MouseEvent> doOnClick = new EventHandler<MouseEvent>() { 
        @Override 
        public void handle(MouseEvent e) { 
        	handleSpotOnClick(); 
        } 
     };

    // defines a method to be overridden 
	@Override
	public void handleSpotOnClick() {
		System.out.println("Hello World");
	} 
     
}
