package com.jira.tests.AttachmentAPIs;

import com.jira.base.BaseTest;
import com.jira.util.APIConstants;
import com.jira.util.CommonMethods;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;


public class GetAttachmentDetails extends BaseTest
{
	   String endpoint="/rest/api/2/attachment/{attachmentid}";
	   String attcahmentID="10003";
	   String response;
	   
	 @Test
	 public void verifyGetAttachmentDetailsTest()
	 {
		 //Initializing the logger reference
		 logger=LogManager.getLogger(GetAttachmentDetails.class);
		 
		 //Sending the API request
		 logger.info("Executing the GetAttachmentDetails API Request");
		 
		 response=  given()
				           .spec(requestSpec)
				           .pathParam("attachmentid", attcahmentID)
				    .when()   
				           .get(endpoint)
				    .then()
				           .spec(responseSpec)
				           .statusCode(APIConstants.SUCCESS_RESPONSE_CODE)
				           .statusLine(APIConstants.SUCCESS_STATUS_LINE)
				           .extract().asString();
		 
		 //Assertions
		 CommonMethods cm=new CommonMethods(response);
		 Assert.assertTrue(cm.getStringValue("filename").contains(APIConstants.Filename));
		 Assert.assertFalse(cm.checkNullAndEmpty("self"));
		 Assert.assertFalse(cm.checkNullAndEmpty("created"));
		 Assert.assertFalse(cm.checkNullAndEmpty("size"));
		 Assert.assertFalse(cm.checkNullAndEmpty("mimeType"));
		 Assert.assertTrue(cm.getStringValue("content").contains(APIConstants.Filename));
		 
		 logger.info("Successfully executed GetAttachmentDetails API Request");
		 
	 }
}
