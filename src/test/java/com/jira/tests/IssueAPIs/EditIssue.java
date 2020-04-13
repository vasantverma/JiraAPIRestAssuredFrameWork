package com.jira.tests.IssueAPIs;

import com.jira.base.BaseTest;
import com.jira.pojo.EditIssuePojo.Description;
import com.jira.pojo.EditIssuePojo.EditIssuePojo;
import com.jira.pojo.EditIssuePojo.Labels;
import com.jira.pojo.EditIssuePojo.Summary;
import com.jira.pojo.EditIssuePojo.Update;
import com.jira.util.APIConstants;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;

public class EditIssue extends BaseTest
{
   String endpoint="/rest/api/2/issue/{IssueKey}";
   String newSummary="Changed the issue summary through Rest Assured";
   String newDescription="Changed the description summary through Rest Assured";
   String label1="blocker";
   String label2="burning";
   String Issuekey="RES-10";
 	
	@Test
	public void verifyEditIssueTest()
	{
		//Initializing the logger reference variable
		logger=LogManager.getLogger(EditIssue.class);
		
		//Creating the request payload using POJO
		EditIssuePojo ei=new EditIssuePojo();
		Update up=new Update();
		Summary su=new Summary();
		Description des=new Description();
		Labels lb=new Labels();
		Labels lb1=new Labels();		
		//For labels object
		lb.setAdd("blocker");
		lb1.setAdd("burning");
		List<Labels> labelsList=new ArrayList<Labels>();
		labelsList.add(lb);
		labelsList.add(lb1);
		up.setLabels(labelsList);
		
		//For summary
		su.setSet(newSummary);
		List<Summary> summary=new ArrayList<Summary>();
		summary.add(su);
		up.setSummary(summary);
		
		//for description
		des.setSet(newDescription);
		List<Description> desc=new ArrayList<Description>();
		desc.add(des);
		up.setDescription(desc);
		
		//Combining all the values
		ei.setUpdate(up);
		
		//Sending the API request 
		logger.info("Executing the Edit Issue API Request");
		
		           given()
				          .spec(requestSpec)
				          .pathParam("IssueKey",Issuekey)
				          .contentType(ContentType.JSON)
				          .body(ei)
				   .when()
				          .put(endpoint)
				   .then()
				          .spec(responseSpec)
				          .statusCode(APIConstants.UPDATE_RESPONSE_CODE)
				          .statusLine(APIConstants.UPDATED_STATUS_LINE); 
				          
		logger.info("Successfully executed EditIssue API Request");
			
	}
}
