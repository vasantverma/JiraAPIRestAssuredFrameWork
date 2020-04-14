package com.jira.test.AuthenticationAPIs;
import static io.restassured.RestAssured.*;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jira.base.BaseTest;
import com.jira.pojo.CookieAPIPojo.CookieAPIPojo;
import com.jira.util.APIConstants;
import com.jira.util.CommonMethods;

import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;

public class CookieBasedAuth 
{
    String endpoint="/rest/auth/1/session";
    String response;
    Properties prop;
    BaseTest baseTest=new BaseTest();
    Logger logger;
   public  SessionFilter sessionFilter;
    		
    
    @Test
    public void verifyCookieAuthAPI()
    {
    	//Calling the init_prop method to initialize config.properties file
    	prop=baseTest.init_prop();
    	
    	//Initializing the SessionFilter reference
    	sessionFilter=new SessionFilter();
    	
    	//Initializing the logger reference
    	logger=LogManager.getLogger(CookieBasedAuth.class);
    	
    	//Creating the request payload
    	CookieAPIPojo cookiePojo=new CookieAPIPojo();
    	cookiePojo.setUsername(prop.getProperty("username"));
    	cookiePojo.setPassword(prop.getProperty("password"));
    	
    	//Sending the API Request
    	logger.info("Executing the Cookie Authentication API");
    	 response=  given()
    			           .baseUri(prop.getProperty("baseURI"))
    			           .contentType(ContentType.JSON)
    			           .body(cookiePojo)
    			           .log().all()
    			           .filter(sessionFilter)
    			    .when()
    			           .post(endpoint)
    			    .then()
    			           .statusCode(APIConstants.SUCCESS_RESPONSE_CODE)
    			           .statusLine(APIConstants.SUCCESS_STATUS_LINE)
    			           .header("Content-Type","application/json;charset=UTF-8")
    			           .log().all()
    			           .extract().asString();	
    	
    	 //Assertions
    	 CommonMethods cm=new CommonMethods(response);
    	 Assert.assertFalse(cm.checkNullAndEmpty("session.name"));
    	 Assert.assertFalse(cm.checkNullAndEmpty("session.value"));
    	 
    	 logger.info("Successfully Executed the Cookie Authentication API");            
    }
    
    /**
     * This SessionFilter object will be used in RequestSpecBuilder for Cookie Based Authentication
     * @return SessionFilter object 
     */
    public SessionFilter getSessionDetails()
    {
    	return sessionFilter;
    }
}
