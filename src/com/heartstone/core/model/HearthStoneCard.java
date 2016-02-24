package com.heartstone.core.model;

import java.util.Comparator;

public class HearthStoneCard implements Comparable<HearthStoneCard> {
	
	private Integer damageCost;
	private Integer hitCost;
	private Integer manaCrystals;
	private Integer draws;
	private String messages;
	
	public HearthStoneCard(){
		
	}
	public HearthStoneCard(Integer pdamgCost,Integer pmanaCrystal,  
			Integer phitCost,Integer draws, boolean isLegend){
		damageCost = pdamgCost;
		hitCost = phitCost;
		manaCrystals = pmanaCrystal;
		if(isLegend){
			messages = "You will never Defeat me!";
		}
		
	}
	
	 @Override
	    public int compareTo(HearthStoneCard hsCard) {
	        return Comparators.MANA.compare(this, hsCard);
	    }
	
	public Integer getDamageCost() {
		return damageCost;
	}
	public void setDamageCost(Integer damageCost) {
		this.damageCost = damageCost;
	}
	public Integer getHitCost() {
		return hitCost;
	}
	public void setHitCost(Integer hitCost) {
		this.hitCost = hitCost;
	}
	public Integer getManaCrystals() {
		return manaCrystals;
	}
	public void setManaCrystals(Integer manaCrystals) {
		manaCrystals = manaCrystals;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		messages = messages;
	}

	 public Integer getDraws() {
		return draws;
	}
	public void setDraws(Integer draws) {
		this.draws = draws;
	}

	public static class Comparators {

	        public static Comparator<HearthStoneCard> MANA = new Comparator<HearthStoneCard>() {
	            @Override
	            public int compare(HearthStoneCard obj1, HearthStoneCard obj2) {
	                return obj1.manaCrystals.compareTo(obj2.manaCrystals);
	            }
	        };
	        
	    }
	
	
}
