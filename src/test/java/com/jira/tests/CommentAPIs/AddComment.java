package com.jira.tests.CommentAPIs;

import com.jira.base.BaseTest;
import com.jira.pojo.CommentAPIPojo.AddCommentPojo;
import com.jira.util.APIConstants;
import com.jira.util.CommonMethods;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddComment extends BaseTest
{
   String endpoint="/rest/api/2/issue/{IssueKey}/comment";
   String issuekey="RES-11";
   String comment="This is another comment.It has been added using Rest Assured test script";
   String response;
   
   @Test
   public void verifyAddCommentTest()
   {
	   //Initializing the logger reference variable
	   logger=LogManager.getLogger(AddComment.class);
	   
	   //Creating the request payload
	   AddCommentPojo addComment=new AddCommentPojo();
	   addComment.setBody(comment);
	   
	   //Sending the API Request
	   logger.info("Executing the AddComment API Request");
	  response=       given()
	                          .spec(requestSpec)
	                          .pathParam("IssueKey",issuekey)
	                          .contentType(ContentType.JSON)
	                          .body(addComment)
	                  .when()
	                          .post(endpoint)
	                  .then()
	                          .spec(responseSpec)
	                          .statusCode(APIConstants.CREATED_RESPONSE_CODE)
	                          .statusLine(APIConstants.CREATED_STATUS_LINE)
	                          .extract().asString();
	  
	  
	  //Assertions 
	  CommonMethods cm=new CommonMethods(response);
	  Assert.assertEquals(cm.getStringValue("body"), comment);
	  Assert.assertFalse(cm.checkNullAndEmpty("self"));
	  Assert.assertFalse(cm.checkNullAndEmpty("id"));
	  Assert.assertFalse(cm.checkNullAndEmpty("created"));
	  Assert.assertFalse(cm.checkNullAndEmpty("updated"));
	  
	  logger.info("Successfully executed the AddComment API Request");
	                        
   }

}
