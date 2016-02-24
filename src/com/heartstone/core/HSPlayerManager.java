package com.heartstone.core;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.heartstone.core.model.HSPlayer;
import com.heartstone.core.model.HearthStoneCard;

public class HSPlayerManager {
	
	int initManaCrystal;
	int initHitCount;

	
	public HSPlayer addPlayer(String playerName){
		if(playerName != null){
			return createPlayer(playerName);
		}else{
			return null;
		}
	}

	private HSPlayer createPlayer(String sPlayerName){
		//Read the 
		HSPlayer player = new HSPlayer();
		player.setCurrManaCrystals(initManaCrystal);
		player.setHitLife(initHitCount);
		return player;
				
	}
	
	
	public void addManaCrystal(int manaCrystals,HSPlayer player){
		//If the number of Crystals are less than 10 add 
		if(player.getCurrManaCrystals() < 10){
			player.setCurrManaCrystals(player.getCurrManaCrystals() + manaCrystals);
			System.out.println("Due to the turn a Mana crystal was added to the Current player, taking the "
					+ "count to:"+ player.getCurrManaCrystals());
		}
	}
	
	public void addPlayerCards(HearthStoneCard hsCard, HSPlayer player,boolean addMana){
		
		if(player.getHsPlayerCards() == null){
			player.setHsPlayerCards(new LinkedList<HearthStoneCard>());
			
		}
		player.getHsPlayerCards().add(hsCard);
		//Sort the Deck so that later we can help the player decide which cards can be played
		Collections.sort(player.getHsPlayerCards(), HearthStoneCard.Comparators.MANA);
		
	}
	
	public void decreamentPlayAttr(HSPlayer player, String attrib){
		if(attrib.equalsIgnoreCase("MANACRYSTAL")){
			int manaCount = player.getCurrManaCrystals();
			player.setCurrManaCrystals(manaCount - 1);
		}else{
			int hitCount = player.getHitLife();
			player.setHitLife(hitCount - 1);
		}
		
	}
	
	//Loop thru the sorted cards list and get all the cards that can be played
	public List<HearthStoneCard> getPlayableCard(HSPlayer player){
		
		List<HearthStoneCard> playableCards = new LinkedList<HearthStoneCard>();
		int playerManaStr = 0;
		for(int i = 0; i < player.getHsPlayerCards().size() ; ++ i){
			//Since the Cards are sorted Ascending Mana Crystal strength it will add from low to high
			playerManaStr += player.getHsPlayerCards().get(i).getManaCrystals();
			//Compare this with the player's Mana strength
			if( playerManaStr <= player.getCurrManaCrystals()){
				playableCards.add(player.getHsPlayerCards().get(i));
			}
		}
		
		return playableCards;
	}
	
	public void removeDeck(HSPlayer player, int index){
		if(player != null){
			player.getHsDeckCards().remove(index);
		}
	}
	//This involves decreasing oppositions life and paying the Mana Crystal price too
	public int slayOpponentLife(HearthStoneCard hsCard, HSPlayer oppPlayer, HSPlayer player){
		//Damage the opposition
		oppPlayer.setHitLife(oppPlayer.getHitLife() - hsCard.getDamageCost());
		System.out.println("Damaged oppositions Life Count now is (0 For Winning the Game): " + oppPlayer.getHitLife());
		//Decreament your Mana Crystals
		player.setCurrManaCrystals(player.getCurrManaCrystals() - hsCard.getManaCrystals());
		System.out.println("Damaging the opposition has reduced your Mana Crystal number to: " + player.getCurrManaCrystals());
		//Discard the card that was played from the player's deck
		player.getHsPlayerCards().remove(hsCard);
		return oppPlayer.getCurrManaCrystals();
	}
	
	public int addHitLife(HearthStoneCard hsCard,  HSPlayer player){
		player.setHitLife(player.getHitLife() + hsCard.getHitCost());
		player.setCurrManaCrystals(player.getCurrManaCrystals() - hsCard.getManaCrystals());
//		System.out.println("Your Mana Crystal numbers are: " + player.getCurrManaCrystals());
		player.getHsPlayerCards().remove(hsCard);
		return player.getHitLife();
	}
	public void increamentPlayAttr(HSPlayer player, String attrib){
		if(attrib.equalsIgnoreCase("MANACRYSTAL")){
			int manaCount = player.getCurrManaCrystals();
			player.setCurrManaCrystals(manaCount + 1);
		}else{
			int hitCount = player.getHitLife();
			player.setHitLife(hitCount + 1);
		}
		
	}

//	public void add
	
	public int getInitManaCrystal() {
		return initManaCrystal;
	}

	public void setInitManaCrystal(int initManaCrystal) {
		this.initManaCrystal = initManaCrystal;
	}

	public int getInitHitCount() {
		return initHitCount;
	}

	public void setInitHitCount(int initHitCount) {
		this.initHitCount = initHitCount;
	}

	
}
