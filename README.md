# JiraAPIRestAssuredFrameWork
Jira is an Issue and Project Management Software owned by Atlassian.

The Jira REST API enables you to interact with Jira programmatically. 
Using the Jira API one can build apps, script interactions with Jira, or develop any other type of integration.

Jira API dosumentation is available on https://developer.atlassian.com/cloud/jira/platform/rest/v2/#version.

Using Rest Assured java library I have automated some of the API endpoints.

#### To test these API,you need to install Jira Software Server on your machine.
Download link-https://www.atlassian.com/software/jira/free

## Features

* Implemented Basic Auth and Cookie based authentication for authentication of the API Requests.

* Implemented RequestSpecBuilder and ResponseSpecBuilder for passing/asserting common values across all API Requests.

* Implemented Serialization for constructing API Request Payload using POJO classes.


## Getting Started

Step 1 : Install Jira Software Server on your local machine.Its available for 30 days free trial.

Step 2 : Start Jira Server.

Step 3 : Create a Project.Note down the Project Key(This will be used in API Requests).

Step 4 : Clone or download this repository.

Step 5 : Import it as a Maven Project in Eclipse IDE.

Step 6 : Open pom.xml file. If required edit it according to your requirements.

Step 7 : Click on Project>Maven>Update Project.

Step 8 : Open config.properties file present under src/main/java>com.jira.config package.

Step 9 : Provide your API base URI, Jira Username and Password and other details.Save the changes.

Step 10 : You can execute the testcases using the testng.xml file present under src/test/resources>TestRunner or maven commands.


### Prerequisites
1.Jira Software 

2.Java 8 or higher

3.Eclipse IDE

4.TestNG 

5.Maven 

## Built With

* TestNG - Unit Testing Framework
* Page Object Model - Design Pattern
* Maven - Build Automation and Dependency Management
* Rest Assured - Java library for RESTful APIs automation
* Extent Reports v4.1.3, Allure Report and Tesults Report - Reporting libraries
* Apache Log4J v2 - Java-based logging utility


## Author

* **Vasant Verma** -(https://github.com/vasantverma)


