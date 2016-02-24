package com.heartstone.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.heartstone.core.model.HSPlayer;
import com.heartstone.core.model.HearthStoneCard;
import com.heartstone.core.util.MiscUtil;

public class HSGame {
	
	private static Map<String, HSPlayer> players = new HashMap<String,HSPlayer>();
	String playerTurn;
	private static Properties prop;
	private HSPlayerManager playerManager;
	
	public void gamePlayers(String playerName){
		playerManager = new HSPlayerManager();
		HSPlayer hsPlayer = playerManager.addPlayer(playerName);
		hsPlayer.setCurrManaCrystals(new Integer(prop.getProperty("manaperround")));
		hsPlayer.setHitLife(new Integer(prop.getProperty("starthitcount")));
		hsPlayer.setHsDeckCards(drawingDeck());
		players.put(playerName, hsPlayer);
	}
	
	public void updateDeckCards(String playerName){
		players.get(playerName).setHsDeckCards(drawingDeck());
		
	}
	//Calll the player Manager to get the List of Playable cards
	public List<HearthStoneCard> getPlayableCard(String playerName){
		return playerManager.getPlayableCard(players.get(playerName));
	}
	
	public int slayOpponentLife(HearthStoneCard hsCard, String oppPlayerName, String player){
		return playerManager.slayOpponentLife(hsCard, players.get(oppPlayerName),players.get(player));
	}
	public int addHitLife(HearthStoneCard hsCard, String playerName){
		return playerManager.addHitLife(hsCard, players.get(playerName));
	}
	
	//Add the configured cards to the deck
	public List<HearthStoneCard> drawingDeck(){
		
		//Get the number of cards and add it to the deck
		List<HearthStoneCard> deckCards = new ArrayList<HearthStoneCard>();
		for(String keys: prop.stringPropertyNames()){
			if(keys.substring(0, 3).equalsIgnoreCase("dec")){
					for(int count = 0; count <= new Integer(prop.getProperty(keys)); 
							count++) {
						List<Integer> lstStr = MiscUtil.lstFromString(prop.getProperty(keys.substring(3)));
						if(keys.substring(3, 6).equalsIgnoreCase("leg")){
							deckCards.add(new HearthStoneCard(lstStr.get(0), lstStr.get(1), lstStr.get(2), lstStr.get(3),true));
						}else{
							deckCards.add(new HearthStoneCard(lstStr.get(0), lstStr.get(1), lstStr.get(2), lstStr.get(3),false));
						}
				}
			}
		}
		return deckCards;
	}
	
	

	public HSPlayerManager getPlayerManager() {
		return playerManager;
	}

	public void setPlayerManager(HSPlayerManager playerManager) {
		this.playerManager = playerManager;
	}

	public void setInitProp(Properties passProp){
		prop = passProp;
	}

	public Map<String, HSPlayer> getPlayers() {
		return players;
	}

	public void setPlayers(Map<String, HSPlayer> players) {
		this.players = players;
	}

	public String getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(String playerTurn) {
		this.playerTurn = playerTurn;
	}

	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		HSGame.prop = prop;
	}
	
	
}
