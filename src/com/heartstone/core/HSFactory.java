package com.heartstone.core;

public class HSFactory {
	
	public HSFactory(){
		
	}
	
	public static Class getObject(String clazz){
		try{
			return ClassLoader.getSystemClassLoader().loadClass(clazz);
		}catch(ClassNotFoundException cle){
			cle.printStackTrace();
			return null;
		}
	}

}
