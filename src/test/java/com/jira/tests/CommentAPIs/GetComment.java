package com.jira.tests.CommentAPIs;

import com.jira.base.BaseTest;
import com.jira.util.APIConstants;
import com.jira.util.CommonMethods;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetComment extends BaseTest
{
  String endpoint="/rest/api/2/issue/{IssueKey}/comment/{CommentId}";
  String issuekey="RES-11";
  String commentId="10101";
  String response;
  
  @Test
  public void verifyGetCommentTest()
  {
	  //Initializing the logger reference variable
	  logger=LogManager.getLogger(GetComment.class);
	  
	  //Sending the API Request
	  logger.info("Executing the GetComment API Request");
	  
	  response=  given()
			            .spec(requestSpec)
			            .pathParam("IssueKey",issuekey)
			            .pathParam("CommentId",commentId)
			     .when()
			             .get(endpoint)
			     .then()
			             .spec(responseSpec)
			             .statusCode(APIConstants.SUCCESS_RESPONSE_CODE)
			             .statusLine(APIConstants.SUCCESS_STATUS_LINE)
			             .extract().asString();
	  
	//Asesrtions
	   CommonMethods cm=new CommonMethods(response);
	     Assert.assertEquals(cm.getStringValue("id"), commentId);
	     Assert.assertFalse(cm.checkNullAndEmpty("body"));
	     Assert.assertFalse(cm.checkNullAndEmpty("self"));
	     Assert.assertFalse(cm.checkNullAndEmpty("created"));
	     Assert.assertFalse(cm.checkNullAndEmpty("updated"));
	 
	 logger.info("Sucessfully Executed the GetComment API Request");
	 
	  
  }
}
