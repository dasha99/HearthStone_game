package com.heartstone.core;

import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import com.heartstone.core.model.HearthStoneCard;
import com.heartstone.core.util.CoinToss;
import com.heartstone.core.util.HSPropFile;

public class HSCardGameTest {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HSPropFile hsFile = new HSPropFile();
		int choice = 0;
		int playCount = 0;
		boolean tossWon = false;
		boolean playerOneOwn = false;
		String names[] = new String[2];
		HSPlay play = new HSPlay();
		HSGame game = new HSGame();
		play.setHsGame(game);
		System.out.println("==============================================");
		System.out.println("**** WelCome to HEARTHSTONE Game *****\n");
		System.out.println("==============================================");
		System.out.print("Currently two players can play the game \n ");
		System.out.print("Players name can only be alphnumeric and should be between 2 to 12 characters \n");
		
			do{
				for (int i = 0; i <= 1;i++){
					System.out.print("Enter Player "+ i + " name or -1 to Quit: ");
				    Scanner input = new Scanner(System.in);
	//	            choice = input.nextInt();
				    String data = input.nextLine();
				    if(data.length() < 2 || data.length() > 12){
	//			    	if(data.length() == 1 && new Integer(data)
				    	System.out.print("Invalid Entry, has to be between 2 - 12 letters");
				    }else{
			            names[i] = data;
			            play.addPlayers(names[i]);
			            ++playCount;
				    }
				}
			}while (playCount < 2);
		
//		Properties prop = hsFile.propertyFileData();
//		System.out.println("Starting Deck is:" + prop.getProperty("startdeck"));

		System.out.println("Welcome to the coin toss! \n");
		System.out.println("Player <<" + names[0] + ">> please enter your Coin toss Choice \n");

        do{
            System.out.print("Enter 0 for 'HEAD' 1 for 'TAIL' or 2 to Quit:: ");
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            CoinToss toss = new CoinToss();
            if(choice == 0 || choice == 1){
            	if(choice == toss.toss()){
            		tossWon = true;
            		System.out.print("Player <<" + names[0] + ">> You Won the Toss And will draw the first card \n");
            	}else{
            		tossWon = false;
            		System.out.print("Player <<" + names[1] + ">> You Won the Toss \n");
            	}
            	break;
            }else if(choice == 2){
            	System.out.println("Quitting the GAME... ");
            	System.exit(0);
            }
            else  {
	            System.out.println("Invalid entry - please enter 1 or 0: \n\n ");
	            choice = input.nextInt();
            }
        }while (choice != 2);
        String player;
        String oppPlayer;
        if(tossWon){
        	//Set the player 1 played flag to true
        	playerOneOwn = true;
        	player = names[0];
        	oppPlayer = names[1];
        }else{
        	player = names[1];
        	oppPlayer = names[0];
        }
        
      //draw and add 4 cards for player 2, draw and add 3 cards for player 1
    	for (int i = 0; i < 4 ; ++i){
//    		play.draw(names[0]);
    		if(playerOneOwn){
    			play.addCardToPlayerDeck(play.draw(names[1]), names[1], false);
    			if(i < 3){
    				play.addCardToPlayerDeck(play.draw(names[0]), names[0],false);
    			}
    		}else{
    			play.addCardToPlayerDeck(play.draw(names[0]), names[0], false);
    			if(i < 3){
    				play.addCardToPlayerDeck(play.draw(names[1]), names[1],false);
    			}
    		}
    	}
        //Play the game 
        do {
        	//Ask player one or two to draw a new card, this time u r adding the Mana value of the
        	boolean prevHandPlayed = false;
        	
        	System.out.println("Player <<" + player + ">>'s Turn to draw a card ");
        	System.out.println("Opposition Player is <<" + oppPlayer + ">>");
        	//Draw to the players Mana Crystal strength
      		play.addCardToPlayerDeck(play.draw(player), player, true);
      		System.out.println("Player " + player + " You have drawn a card, Your Options are...\n");
//      		play.addManaCrystal(1, player);
       		//Check the Players Next option
      		List<HearthStoneCard> playableCards = play.getPlayingOptions(player);
        	//Check player 1's options after draw (If he can play a card against the opponent)
      		int optionCount = 0;
      		if(playableCards.isEmpty()){
      			System.out.println(" You don't have enough Mana Crystals to Play the opponent \n");
      			System.out.println(" The Card is added to your deck, Your Turn is Complete \n");
      			prevHandPlayed = true;
      		}else{
      			//First Get all the Relevant Card
	      			for(HearthStoneCard card: playableCards){
	      				if(card.getDamageCost() == 0){
	      					System.out.println("OPTION <<"+ optionCount + ">> This will give you [" 
	      								+ card.getHitCost() + "] Hit Life/s \n");
	      					++optionCount;
	      				}else{
		      				System.out.println("OPTION <"+ optionCount + "> This card will cause [" 
		      								+ card.getDamageCost() + "] damage to the <<"+ oppPlayer +">> \n");
		      				++optionCount;
	      				}
	      			}
      			System.out.println("Enter your option << -1 to Exit the Game, -2 to Pass your turn >>:: ");
      			do{
    	      		Scanner input = new Scanner(System.in);
    	      		int res = input.nextInt();
    	      		int hitCountLeft = 0;
    	      		int urLifeCount = 0;
    	      		if(res >= 0 && res < optionCount ){
    	      			HearthStoneCard card = playableCards.get(res);
    	      			//Call the slay method of the opponent
    	      				if(card.getDamageCost() > 0){
    	      					hitCountLeft = play.slayOpponentLife(playableCards.get(res), oppPlayer,player);
    	      					if(hitCountLeft == 0){
        	      					System.out.println("PLAYER <<"+ player + ">> WON THIS GAME... EXISTING");
        	    	      			System.out.println("*******Existing the Game********");
        	      					System.exit(0);
        	      				}
    	      				}else{
    	      					urLifeCount = play.addHitLife(playableCards.get(res), player);
    	      					System.out.println("Congratulations Your Life <<"+ player + ">> is:"+ urLifeCount);
    	      				}

    	      			prevHandPlayed = true;
    	      		}else if(res == -2){
    	      			break;
      				}else if(res == -1){
      					System.out.println("==============================================");
    	      			System.out.println("*******Existing the Game********");
    	      			System.out.println("==============================================");
    	      			System.exit(0);
    	      		}
    	      		else{
    	      			System.out.println("Invalid Entry please enter your choice again..");
    	      		}
          		}while(prevHandPlayed == false);
      		}
      		play.addManaCrystal(1, player);
      		//Do the final bit before next player starts playing
      		prevHandPlayed = true;
  			String temp = player;
  			player = oppPlayer;
  			oppPlayer = temp;
        	
        }while (choice != -1);
        
	}

	


}
