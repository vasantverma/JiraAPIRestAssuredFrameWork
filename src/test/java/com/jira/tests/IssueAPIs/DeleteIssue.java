package com.jira.tests.IssueAPIs;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;

import com.jira.base.BaseTest;
import com.jira.util.APIConstants;

import static io.restassured.RestAssured.*;

public class DeleteIssue extends BaseTest
{
    String endpoint="/rest/api/2/issue/{issueKey}";
    String issuekey="RES-12";
    
    @Test
    public void verifyDeleteIssueTest()
    {
    	//Initializing the logger reference variavble
    	logger=LogManager.getLogger(DeleteIssue.class);
    	
    	//Sending the API Request
    	logger.info("Executing the DeleteIssue API Request");
    	
    	                given()
    	                        .spec(requestSpec)
    	                        .pathParam("issueKey", issuekey)
    	                .when()
    	                        .delete(endpoint)
    	                .then()
    	                        .spec(responseSpec)
    	                        .statusCode(APIConstants.UPDATE_RESPONSE_CODE)
    	                        .statusLine(APIConstants.UPDATED_STATUS_LINE);
    	                
       logger.info("Successfully executed  DeleteIssue API Request");               
    	                        
    	                
    	
    }
}
