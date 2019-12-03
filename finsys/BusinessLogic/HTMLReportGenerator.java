package ejagruti.finsys.BusinessLogic;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;



public class HTMLReportGenerator {

	static ExtentReports report=null;
	static ExtentTest logger; 
	
	public static void TestSuiteStart(String ResultHTMLFilePath,String UserName) throws UnknownHostException
	{
		report=new ExtentReports(ResultHTMLFilePath,false,NetworkMode.OFFLINE);
		
		report
		
			.addSystemInfo("Host Name", InetAddress.getLocalHost().getHostName())
	        .addSystemInfo("Environment", "QA")
	        .addSystemInfo("User Name", UserName);
	}
	
	public static void TestSuiteEnd()
	{
		report.flush();report.close();
	}
	public static void TestCaseStart(String TestName,String Description)
	{
		logger=report.startTest(TestName, Description);
	}
	public static void TestCaseEnd()
	{
		report.endTest(logger);
		
	}
	public static void StepDetails(String Status,String StepName,String StepDetails,String objectImagePath)
	{
		String tbl=StepDetails+"<br>"+logger.addScreenCapture(objectImagePath);
		if(Status.equalsIgnoreCase("pass"))
		{		logger.log(LogStatus.PASS,StepName,tbl);}
		else if(Status.equalsIgnoreCase("fail"))
		{		logger.log(LogStatus.FAIL,StepName, StepDetails);}
		else if(Status.equalsIgnoreCase("error"))
		{		logger.log(LogStatus.ERROR,StepName, StepDetails);}
		else if(Status.equalsIgnoreCase("info"))
		{		logger.log(LogStatus.INFO,StepName, StepDetails);}
		else
		{logger.log(LogStatus.INFO,StepName, StepDetails);}
	
		
	}
	
	public static void main(String[] args) throws UnknownHostException {
		TestSuiteStart("C:\\LOGS\\manish.html", "nirmal");
		
		TestCaseStart("this is test name1", "this is description");
		StepDetails("INFO", "this is step1", "this is step details1","C://LOGS//T.PNG");
		StepDetails("ERROR", "this is step2", "this is step details2","C://LOGS//T.PNG");
		StepDetails("pass", "this is step1", "this is step details1","C://LOGS//T.PNG");
		StepDetails("fail", "this is step2", "this is step details2","C://LOGS//T.PNG");
		TestCaseEnd();
		
		
		TestSuiteEnd();
		System.out.println(" COMPLETED...!!!!");
		

	}

}
