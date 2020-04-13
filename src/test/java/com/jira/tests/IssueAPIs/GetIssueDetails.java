package com.jira.tests.IssueAPIs;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jira.base.BaseTest;
import com.jira.util.APIConstants;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class GetIssueDetails extends BaseTest
{
  //Variable declaration
  String endpoint="/rest/api/2/issue/{IssueKey}";
  String response;
  

  
  @Test
  public void verifyGetIssue()
  {
	  //Initializing the logger reference variable
	  logger=LogManager.getLogger(GetIssueDetails.class);
	  
	  logger.info("Executing Get Issue Details API Request");
	  
		 //Sending the api request  
	response=   given()
			           .spec(requestSpec)
			           .pathParam("IssueKey",CreateIssue.issueKey)
			           .queryParam("fields", "summary,description,comment")
			    .when()
			           .get(endpoint)
			    .then()
			           .spec(responseSpec)
			           .statusCode(APIConstants.SUCCESS_RESPONSE_CODE)
			           .statusLine(APIConstants.SUCCESS_STATUS_LINE)
			           .extract().asString();
	
	  //Converting the response object using JsonPath
    JsonPath js=new JsonPath(response);
    
    //Assertion for response body
    Assert.assertEquals(js.getString("id"),CreateIssue.issueId);
    Assert.assertEquals(js.getString("key"),CreateIssue.issueKey);
    Assert.assertEquals(js.getString("self"),CreateIssue.issueLink);
    Assert.assertEquals(js.getString("fields.summary"),CreateIssue.summary);
    Assert.assertEquals(js.getString("fields.description"),CreateIssue.description);
    
   
    
    //Logging  the Issue Details in console and log file.
    logger.info("Issue Details fetched Successfully");
  
  
	
}
}
