package com.heartstone.core.model;

import java.util.LinkedList;
import java.util.List;

public class HSPlayer {
	private List<HearthStoneCard> hsPlayerCards;
	private int hitLife;
	private int currManaCrystals;
	private List<HearthStoneCard> hsDeckCards;
	private String playerName;
	
	public List<HearthStoneCard> getHsPlayerCards() {
		return hsPlayerCards;
	}
	public void setHsPlayerCards(List<HearthStoneCard> hsPlayerCards) {
		this.hsPlayerCards = hsPlayerCards;
	}
	public int getHitLife() {
		return hitLife;
	}
	public void setHitLife(int hitLife) {
		this.hitLife = hitLife;
	}
	public int getCurrManaCrystals() {
		return currManaCrystals;
	}
	public void setCurrManaCrystals(int currManaCrystals) {
		this.currManaCrystals = currManaCrystals;
	}
	public List<HearthStoneCard> getHsDeckCards() {
		return hsDeckCards;
	}
	public void setHsDeckCards(List<HearthStoneCard> hsDeckCards) {
		this.hsDeckCards = hsDeckCards;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

		
	
}
