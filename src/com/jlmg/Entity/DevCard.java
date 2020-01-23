package com.jlmg.Entity;

import com.jlmg.util.DevCardType;

public class DevCard {

	private DevCardType type;
	private boolean isUsed;
	
	public DevCard (DevCardType type) {
		this.type = type;
		this.isUsed = false;
	}
	
	public DevCardType getType() {
		return type;
	}
	
	public boolean isUsed() {
		return this.isUsed;
	}
	
	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
}
