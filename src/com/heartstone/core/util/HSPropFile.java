package com.heartstone.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HSPropFile {
	
	public static Properties propertyFileData(){
		
//		Map<String,String> propFileData = new HashMap<String, String>();
		Properties prop = new Properties();
		InputStream input = null;
		try{
			//Read the property file and update the 
			input = new FileInputStream("src/HSProperty.txt");
			// load a properties file
			prop.load(input);
		}catch(IOException ie){
				ie.printStackTrace();
			}
			
		return prop;
		
		
	}
 
}
