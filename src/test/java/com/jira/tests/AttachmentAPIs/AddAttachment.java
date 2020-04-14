package com.jira.tests.AttachmentAPIs;

import com.jira.base.BaseTest;
import com.jira.util.APIConstants;
import com.jira.util.CommonMethods;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;


public class AddAttachment extends BaseTest
{
	   String endpoint="/rest/api/2/issue/{issueKey}/attachments";
	   String issuekey="RES-18";
	   File attachment=new File(APIConstants.ATTACHMENT_FILEPATH);
	   String response;
	   
	 @Test
	 public void verifyAddAttachmentTest()
	 {
		 //Initializing the logger reference
		 logger=LogManager.getLogger(AddAttachment.class);
		 
		 //Sending the API request
		 logger.info("Executing the AddAttachment API Request");
		 
		 response=  given()
				           .spec(requestSpec)
				           .pathParam("issueKey", issuekey)
				           .header("X-Atlassian-Token", "no-check")
				           .multiPart(attachment)
				    .when()   
				           .post(endpoint)
				    .then()
				           .spec(responseSpec)
				           .statusCode(APIConstants.SUCCESS_RESPONSE_CODE)
				           .statusLine(APIConstants.SUCCESS_STATUS_LINE)
				           .extract().asString();
		 
		 //Assertions
		 CommonMethods cm=new CommonMethods(response);
		 Assert.assertTrue(cm.getStringValue("filename").contains(APIConstants.Filename));
		 Assert.assertFalse(cm.checkNullAndEmpty("self"));
		 Assert.assertFalse(cm.checkNullAndEmpty("id"));
		 Assert.assertFalse(cm.checkNullAndEmpty("created"));
		 Assert.assertFalse(cm.checkNullAndEmpty("size"));
		 Assert.assertFalse(cm.checkNullAndEmpty("mimeType"));
		 Assert.assertFalse(cm.checkNullAndEmpty("content"));
		 
		 logger.info("Successfully executed AddAttachment API Request");
		 
	 }
}
