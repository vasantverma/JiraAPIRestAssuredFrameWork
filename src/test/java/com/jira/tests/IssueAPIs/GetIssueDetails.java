package com.jira.tests.IssueAPIs;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jira.base.BaseTest;
import com.jira.util.APIConstants;
import com.jira.util.CommonMethods;

import static io.restassured.RestAssured.*;

public class GetIssueDetails extends BaseTest
{
  //Variable declaration
  String endpoint="/rest/api/2/issue/{IssueKey}";
  String issuekey="RES-23";
  String issueId="10404";
  String issueLink="http://localhost:8086/rest/api/2/issue/10403";
  String issueSummary="Rest Assured Issue 23";
  String issueDescription="This is a bug and issue number is 23";
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
	
	
    //Assertion for response body
	CommonMethods cm=new CommonMethods(response);
    Assert.assertEquals(cm.getStringValue("id"),issueId);
    Assert.assertEquals(cm.getStringValue("key"),issuekey);
    Assert.assertEquals(cm.getStringValue("self"),issueLink);
    Assert.assertEquals(cm.getStringValue("fields.summary"),issueSummary);
    Assert.assertEquals(cm.getStringValue("fields.description"),issueDescription);
    
   
    
    //Logging  the Issue Details in console and log file.
    logger.info("Issue Details fetched Successfully");
  
  
	
}
}
