package com.jlmg.Entity;

public class Dice {
	
	private Integer value;
	private final int maxValue = 6;
	
	public Dice() {
		this.value = 1;
	}

	public void rollDice() {
		this.value = (int)(Math.random() * maxValue + 1);
	}

	public Integer getValue() {
		return this.value;
	}
	
}