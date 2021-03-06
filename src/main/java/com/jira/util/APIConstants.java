package com.jira.util;

public class APIConstants 
{
	//File Path constants
    public static String CONFIG_FILE_PATH=System.getProperty("user.dir")+"\\src\\main\\java\\com\\jira\\config\\config.properties";
    
    //API Response constants
    public static int CREATED_RESPONSE_CODE=201;
    public static String CREATED_STATUS_LINE="HTTP/1.1 201 ";
    public static int UPDATE_RESPONSE_CODE=204;
    public static String UPDATED_STATUS_LINE="HTTP/1.1 204 ";
    public static int DELETE_RESPONSE_CODE=204;
    public static String DELETE_STATUS_LINE="HTTP/1.1 204 ";
    public static int SUCCESS_RESPONSE_CODE=200;
    public static String SUCCESS_STATUS_LINE="HTTP/1.1 200 ";
    
    //Attachement Details
    public static String  Filename="IssueAttachment.txt";
    public static String ATTACHMENT_FILEPATH=System.getProperty("user.dir")+"\\src\\main\\java\\com\\jira\\testdata\\"+Filename;
    
    
    
}
