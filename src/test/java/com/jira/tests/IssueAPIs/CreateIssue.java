package com.jira.tests.IssueAPIs;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jira.base.BaseTest;
import com.jira.pojo.CreateIssuePojo.CreateIssuePojo;
import com.jira.pojo.CreateIssuePojo.Fields;
import com.jira.pojo.CreateIssuePojo.IssueType;
import com.jira.pojo.CreateIssuePojo.Project;
import com.jira.util.APIConstants;

import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class CreateIssue extends BaseTest
{
	String endpoint="/rest/api/2/issue";
	Fields fs=new Fields();
	String response;
	
	//Issue constants.This will be used in further api request
	static String issueId;
	static String issueKey;
	static String summary="Rest Assured Issue 20";
	static String description="This is a bug and issue number is 20";
    static String issueLink;
  @Test
  public void verifyCreateIssueTest()
  {
	//Initializing the logger 
	logger=LogManager.getLogger(CreateIssue.class);
	  
   //Constructing the request payload/body using POJO classes.
	  //Creating the json object of fields
	  CreateIssuePojo cr=new CreateIssuePojo();
	  cr.setFields(fs);
	  
	  //Setting id inside the project json 
	  Project proj=new Project();
	  proj.setId("10100");
	  
	  //Setting the id value inside the issuetype json
	  IssueType it=new IssueType();
	  it.setId("10000");
	  
	 fs.setProject(proj);
	 fs.setSummary(summary);
	 fs.setDescription(description);
	 fs.setIssuetype(it);
	 
	 logger.info("Executing Create Issue API Request");
	 //Sending the api request
response=
         given()
				.spec(requestSpec)
				.header("Content-Type","application/json")
				.body(cr)
		.when()
	       		.post(endpoint)
	    .then()
	    		.spec(responseSpec)
	    		.statusCode(APIConstants.CREATED_RESPONSE_CODE)
	    		.statusLine(APIConstants.CREATED_STATUS_LINE)
	    		.extract().asString();
    
     //Converting the response object using JsonPath
     JsonPath js=new JsonPath(response);
     
     //Updating the values of static variable for other API testcases
     issueId =js.getString("id");
     issueKey =js.getString("key");
     issueLink=js.getString("self");
     
     //Assertion for response body
     Assert.assertTrue( !issueId.isEmpty() && issueId!=null );
     Assert.assertTrue(issueKey.contains(prop.getProperty("projectKey")+"-"));
     Assert.assertEquals(issueLink,prop.getProperty("baseURI")+endpoint+"/"+issueId);
     
     //Logging  the Issue Details in console and log file.
     logger.info("Issue Create Successfully");
     logger.info("Issue Id :"+issueId);
     logger.info("Issue Id :"+issueKey);
     logger.info("Issue Id :"+issueLink);
     
     
  
  }
}
