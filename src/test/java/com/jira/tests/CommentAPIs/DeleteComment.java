package com.jira.tests.CommentAPIs;

import com.jira.base.BaseTest;
import com.jira.util.APIConstants;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;

public class DeleteComment extends BaseTest
{
	  String endpoint="/rest/api/2/issue/{issueKey}/comment/{CommentId}";
	  String issuekey="RES-10";
	  String commentId="10103";
	  
	  @Test
	  public void verifyDeleteCommentTest()
	  {
		  logger=LogManager.getLogger(DeleteComment.class);
		  //Sending the API request 
		  logger.info("Executing Delete Comment API Request");
		  
		              given()
				             .spec(requestSpec)
				             .pathParam("issueKey",issuekey)
				             .pathParam("CommentId", commentId)
				      .when()
				             .delete(endpoint)
				      .then()
				             .spec(responseSpec)
				             .statusCode(APIConstants.DELETE_RESPONSE_CODE)
				             .statusLine(APIConstants.DELETE_STATUS_LINE)
				             .extract().asString();
		  
		//No response body is returned 
		logger.info("Successfully executed the DeleteComment API Request");  
		  
				             
	  }
}
