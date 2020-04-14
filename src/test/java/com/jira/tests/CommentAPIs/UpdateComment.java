package com.jira.tests.CommentAPIs;
import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jira.base.BaseTest;
import com.jira.pojo.CommentAPIPojo.UpdateCommentPojo;
import com.jira.util.APIConstants;
import com.jira.util.CommonMethods;

import io.restassured.http.ContentType;


public class UpdateComment extends BaseTest
{
  String endpoint="/rest/api/2/issue/{issueKey}/comment/{CommentId}";
  String response;
  String issuekey="RES-11";
  String commentId="10101";
  String newComment="Updated comment using the Rest Assured-UpdateComment API  test script";
  
  @Test
  public void verifyUpdateCommentTest()
  {
	  //Initializing the logger reference
	  logger=LogManager.getLogger(UpdateComment.class);
	  
	  //Constructing the API request body
	  UpdateCommentPojo updateComment=new UpdateCommentPojo();
	  updateComment.setBody(newComment);
	  
	  //SEnding the API Request
	  logger.info("Executing the UpdateComment API Request");
	  response=   given()
			              .spec(requestSpec)
			              .pathParam("issueKey", issuekey)
			              .pathParam("CommentId",commentId)
			              .contentType(ContentType.JSON)
			              .body(updateComment)
			      .when()
			              .put(endpoint)
			      .then()
			               .spec(responseSpec)
			               .statusCode(APIConstants.SUCCESS_RESPONSE_CODE)
			               .statusLine(APIConstants.SUCCESS_STATUS_LINE)
			               .extract().asString();
	  
	  //Assertions
	  CommonMethods cm=new CommonMethods(response);
	  Assert.assertEquals(cm.getStringValue("body"),newComment);
	  Assert.assertEquals(cm.getStringValue("id"),commentId);
	  Assert.assertFalse(cm.checkNullAndEmpty("self"));
	  Assert.assertFalse(cm.checkNullAndEmpty("created"));
	  Assert.assertFalse(cm.checkNullAndEmpty("updated"));
		
	  logger.info("Successfully executed the UpdateComment API Request");
  }
  
}
