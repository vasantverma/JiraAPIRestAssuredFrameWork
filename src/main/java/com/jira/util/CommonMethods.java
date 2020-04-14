package com.jira.util;

import io.restassured.path.json.JsonPath;

public class CommonMethods 
{
	 String response;
	 public  JsonPath js;
	 
	//Constructor
	public CommonMethods(String response)
	{
		this.response=response;
	}
	
	/**
	 * Generic method for parsing the API response.
	 * @return JsonPath reference
	 */
     public  JsonPath jsonParser()
     {
    	 js=new JsonPath(response);
    	 return js;
     }
     public  String getStringValue(String key)
     {
    	 return jsonParser().getString(key);
    	 
     }
     
     /**
      * Generic method for checking if the value of a key is not null and  empty
      * @param property
      * @return boolean 
      */
     public  boolean checkNullAndEmpty(String property)
     {
    	 String value=getStringValue(property);
    	 if(value.isEmpty() || value==null)
    		 return true;
    	 else
    		 return false;
    	 
     }
}
