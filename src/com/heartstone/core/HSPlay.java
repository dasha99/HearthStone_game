package com.heartstone.core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import com.heartstone.core.model.HSPlayer;
import com.heartstone.core.model.HearthStoneCard;
import com.heartstone.core.util.HSPropFile;

public class HSPlay {

private static HSGame hsGame;
Random randomNum = new Random();

	public void HSPlay(){
		
		hsGame = new HSGame();
		//read the property file
		hsGame.setInitProp(HSPropFile.propertyFileData());

		
	}

	public void addPlayers(String playerName){
		hsGame.setInitProp(HSPropFile.propertyFileData());
		hsGame.gamePlayers(playerName);
		//Add the deck 
	}
	
	public HearthStoneCard draw(String playerName){
		List<HearthStoneCard> lstCards = new ArrayList<HearthStoneCard>();
		HearthStoneCard cardDrawn;
		if(hsGame.getPlayers().containsKey(playerName)){
			HSPlayer player = (HSPlayer)hsGame.getPlayers().get(playerName);
			lstCards = player.getHsDeckCards();
			if(lstCards.size() > 0){
				
				//If there is card at hand just get a random card
				return getCardFromDeck(lstCards,player);
			}else{
				//If the Deck is empty then reset the Deck
				hsGame.updateDeckCards(playerName);
				//With Resetting the Deck decrease the the hit counter by one
				hsGame.getPlayerManager().decreamentPlayAttr(player, "HITLIST");
				return getCardFromDeck(lstCards,player);
			}
		}
		return null;
	}
	
	//Adding a Mana Crystal to the player after each turn
	public void addManaCrystal(int manaCrystals,String playerName){
		HSPlayer player = (HSPlayer)hsGame.getPlayers().get(playerName);
		hsGame.getPlayerManager().addManaCrystal(manaCrystals,player);
	}
	
	//Adding drawn card to the Players Deck
	public void addCardToPlayerDeck(HearthStoneCard card,String playerName,boolean addMana){
		HSPlayer player = (HSPlayer)hsGame.getPlayers().get(playerName);
		hsGame.getPlayerManager().addPlayerCards(card,player,addMana);
	}
	
	//Check playing options
	public List<HearthStoneCard> getPlayingOptions(String playerName){
		return hsGame.getPlayableCard(playerName);
	}
	//Damage the opponent
	public int slayOpponentLife(HearthStoneCard hsCard, String oppPlayerName, String playerName){
		
		return hsGame.slayOpponentLife(hsCard,oppPlayerName,playerName);
		//Call the Slay to damage the opposition
	}
	//Add life to your self
	public int addHitLife(HearthStoneCard hsCard, String playerName){
		
		return hsGame.addHitLife(hsCard,playerName);
		//Call the Slay to damage the opposition
	}
	//Method to randomize and draw a card
	public HearthStoneCard getCardFromDeck(List<HearthStoneCard> lstCards, 
						HSPlayer player){
		int index = randomNum.nextInt(lstCards.size());
		HearthStoneCard cardDrawn = lstCards.get(index);
		//Discard the card from the Deck
		hsGame.getPlayerManager().removeDeck(player, index);
		
		return cardDrawn;
	}

	public static HSGame getHsGame() {
		return hsGame;
	}

	public static void setHsGame(HSGame hsGame) {
		HSPlay.hsGame = hsGame;
	}
	
	
}
