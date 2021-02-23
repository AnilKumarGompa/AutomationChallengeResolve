
								###	Selenium-JAVA-TestNG-Maven-Framework ###
---------------
 Introduction:     
---------------
	This Test Automation Framework is created using Java + Selenium Web Driver + TestNG with Page Object Model 
	which can be used across different web based applications desktop browsers. 
	With this framework in place, whenever we need to automate a web based application, we would not need to start from scratch.
	Libraries and Frameworks 
 
 Version for some of these can be found in the POM file:
---------------
	•	Selenium - Web automation
	•	Appium - Mobile automation
	•	Maven - Build and package management
	•	TestNG - Test execution and Reporting

 Prerequisites: 
---------------
	•	Java jdk-1.8 or higher
	•	Apache Maven 3 or higher (Optional but helpful)
	•	Please refer the below links for any help in Maven 
	•	http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
	•	http://www.tutorialspoint.com/maven/maven_environment_setup.htm
 
 How to use: 
---------------
	•	Clone and import the project as existing Maven project in any Java IDE.
		You can run the program using any IDE like Eclipse or IntelliJ
	•	Add tests under src/test/java/testsuite directory.
	•	Add the test names in TestSuits/ResolveAutomationChallengeSuite.xml file .
  
 Execution:  
---------------
	Ways to execute:
	
	•	1) Import the project in Eclipse or IntelliJ and run with maven command "mvn clean and verify" or right click and run xml file (TestSuits\ResolveAutomationChallengeSuite.xml)
	•	2) Double click on RunTests.bat to run the tests.

 What happens when a build is triggered..  
---------------
	Steps:
	•	Batch file will add the maven to the environment variables.
	•	Build is triggered through Maven by using the command- clean install
	•	It triggers the ResolveAutomationChallengeSuite.xml to execute tests.
	•	If any tests failed , Failure screenshots will be captured with respective test names + timestamp and stored under Automation Reports\LatestResults\Screenshots
	•	Once the test execution is completed , ReportNG reports will be generated under target\surefire-reports\html folder.
	•	Generated Log reports at Logs.log.

  
 Features: 
---------------
 1.	Batch File Execution:

	•	Execute the tests by double click on a batch file RunTest.bat
	
 2.	Logging:

	•	Log4j is configured to capture the test execution logs.
	•	Configuration file is located at ConfigFiles/log4j.xml.
	•	Execution log is captured in the Logs.log
 3.	Reporting:

	•	The framework produce index.html report. It resides in the same 'target\surefire-reports' folder.
		This reports gives the link to all the different component of the TestNG reports like Groups & Reporter Output.
		On clicking these will display detailed descriptions of execution.
	•	You can find emailable-report.html from target\surefire-reports to email the test reports. 
		As this is a html report you can open it with browser.
 4.	Object Repository

	•	Maintained with the help of Page object model.
	•	Java Interfaces are used to store the Locators/Objects
	
 5. Utilities

	•	Different Utilities exist to read Properties files.	
	
 6. Listeners

	•	Web Listener- To observe and log the browser actions.
	•	Test Listener- To do some actions like taking failure screenshot, 
		updating run time report based on test results (On Failure, On Success, On Start…)

 
 What is this repository for? 
---------------
	* For resolve automation challenge
