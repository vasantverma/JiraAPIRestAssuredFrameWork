package com.jira.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import com.jira.util.APIConstants;

import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest 
{
	//Variable declaration
	public RequestSpecification requestSpec;
	public ResponseSpecification responseSpec;
    public Properties prop;
    public Logger logger;
    
    /**
     * This method is used to initialize the config.properties file
     * @return Properties reference
     */
     public Properties init_prop()
     {
    	 try {
			FileInputStream file=new FileInputStream(APIConstants.CONFIG_FILE_PATH);
			prop=new Properties();
			prop.load(file);
		} 
    	 catch (FileNotFoundException e)
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
    	 return prop;
     }
     
     
     /**
      * This method is used for declare RequestSpecBuilder and ResponseSpecBuilder 
      */
     @BeforeMethod
     public void setUp()
     {
    	 
    	 //Calling the init_prop method for getting config values
    	 prop=init_prop();
    	 
    	//Get the Authentication mode from config.properties file
    	 String authMode=prop.getProperty("auth_mode");  
    	 
    	 //Create RequestSpecBuilder according to the auth mode
    	 if(authMode.contains("cookie based"))
    	 {
    		 //Initializing the RequestSpecBuilder with common values for all api requests.
    		 requestSpec=new RequestSpecBuilder()
     		.setBaseUri(prop.getProperty("baseURI"))
     		.log(LogDetail.ALL)
     		.build();	 
    	 }
    	 else if(authMode.contains("basic"))
    	 {
    		 //Initializing BasicAuthScheme object with username and password
    		 PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
    		 authScheme.setUserName(prop.getProperty("username"));
    		 authScheme.setPassword(prop.getProperty("password"));
    		 
    		 //Initializing the RequestSpecBuilder with common values for all api requests.
    		 requestSpec=new RequestSpecBuilder()
    		.setBaseUri(prop.getProperty("baseURI"))
    		.setAuth(authScheme)
    		.log(LogDetail.ALL)
    		.build();
    		
    	 }
    	 else if(authMode.contains("oauth "))
    	 {
    		 
    	 }
    	 else
    	 {
    		 try
    		 {  //Custom Error message 
				throw new Exception("Please provide API auth mode in config.properties file");
			  } 
    		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 }
    	 
    	    
    	 //Adding response headers using Map interface
    	 Map<String,Object> expectedHeaders=new HashMap<String,Object>();
    	 expectedHeaders.put("Cache-Control", "no-cache, no-store, no-transform");
    	 expectedHeaders.put("Content-Type", "application/json;charset=UTF-8");
    	 
    	//Initializing the ResponseSpecBuilder with common values for all api responses.
    	 responseSpec=new ResponseSpecBuilder()
    	.expectHeaders(expectedHeaders)
    	.log(LogDetail.ALL)
    	.build();
    	 
    	
     }
    		 
}
