package com.heartstone.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class MiscUtil {

	
	public static List<Integer> lstFromString(String str){
		
		if(str != null){
		
			StringTokenizer strTok = new StringTokenizer(str, ",");
			List<Integer> lstStr = new ArrayList<Integer>();
			while(strTok.hasMoreTokens()){
				lstStr.add(new Integer(strTok.nextToken()));
			}
			return lstStr;
		}
		return null;
		
		
	}

}
