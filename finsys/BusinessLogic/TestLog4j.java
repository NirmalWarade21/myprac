package generatelog;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestLog4j 
{
private static final Logger logger = Logger.getLogger(TestLog4j.class.getName());
	

@BeforeTest
public void loadlog4J(){
	/**
	 * Add the path for the properties file used for log 4j
	 */
	String log4jConfPath = System.getProperty("user.dir")+"\\src\\test\\java\\generatelog\\log4J.properties";
	PropertyConfigurator.configure(log4jConfPath);
}
	@Test
	public void testLogin(){
		
		
		logger.info("staring login test");
		logger.info("clicked on sign in button");
		logger.info("enter user name");
		logger.info("enter password");
		logger.info("click on submit button");
	}

}
