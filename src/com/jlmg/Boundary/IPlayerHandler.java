package com.jlmg.Boundary;

import com.jlmg.util.CellType;

/**
 * Interface for Player actions Handlers
 * @author Jose Luiz Gomes
 *
 */

public interface IPlayerHandler {
	
	// btn Roll Dice event handler
	public void btnDiceOnClick();
	
	// btn Done event handler
	public void btnDoneOnClick();
	
	// btn New Development card event handler
	public void btnNewDevOnClick();
	
	// btn Trade card event handler
	public void btnTradeOnClick(CellType card, int btnValue);
	
}
