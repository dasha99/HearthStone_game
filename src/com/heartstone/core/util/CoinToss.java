package com.heartstone.core.util;

import java.util.Random;

public class CoinToss {
	private enum Coin{Heads, Tails};

	Random randomNum = new Random();
	private int heads = 0;
	private int tails = 1;
	Coin coinFlip;
	
	public int toss(){
		return randomNum.nextInt(2);
	}
	
	public String tossResult(){
		
		int result = randomNum.nextInt(2);
	    if(result == 0){
	        return Coin.Heads.toString();
	    }else{
	        return Coin.Tails.toString();
	    }
	}

}
