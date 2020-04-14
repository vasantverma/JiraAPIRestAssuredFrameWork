package com.jira.tests.AttachmentAPIs;

import com.jira.base.BaseTest;
import com.jira.util.APIConstants;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.Test;

public class DeleteAttachment extends BaseTest
{
	   String endpoint="/rest/api/2/attachment/{attachmentid}";
	   String attcahmentID="10004";
	   
	   @Test
	   public void verifyDeleteAttachmentTest()
	   {
		 //Initializing the logger reference
			 logger=LogManager.getLogger(DeleteAttachment.class);
			 
			 //Sending the API request
			 logger.info("Executing the DeleteAttachment API Request");
			 
			    given()
			           .spec(requestSpec)
			           .pathParam("attachmentid", attcahmentID)
			    .when()   
			           .delete(endpoint)
			    .then()
			           .spec(responseSpec)
			           .statusCode(APIConstants.DELETE_RESPONSE_CODE)
			           .statusLine(APIConstants.DELETE_STATUS_LINE);
	 
			    //No response body is returned
			    logger.info("Successfully executed the DeleteAttachment API Request");
	   }
}
