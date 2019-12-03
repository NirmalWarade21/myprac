package ejagruti.finsys.TestCase;
import ejagruti.finsys.config.Config;
import org.testng.annotations.Test;

//@Listeners(org.ejagruti.finsys.listenres.TestCaseListener.class)

public class US_SBDC_1000_TC1 { 
	
	@Test
	public void Executetestcase() throws Exception
	{	 
		Config.obj.LaunchApplication(Config.pfr.GetParameterValue("_browsername"), Config.pfr.GetParameterValue("_URL"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_username_textbox_xpath")); 
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_username_textbox_xpath"),Config.pfr.GetParameterValue("_username"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_password_textbox_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_password_textbox_xpath"),Config.pfr.GetParameterValue("_password"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_login_btn_xpath"));
		Config.obj.ButtonClick(Config.or.GetParameterValue("_login_btn_xpath"));
		
		
		String val=Config.obj.ObjectGetAttributeValue(Config.or.GetParameterValue("_logout_attrib_xpath"), "innerHTML",30);/// if error comes chenage html to text 
		System.out.println(val);
	 	if(val.equalsIgnoreCase("LOGOUT"))
	 	{
	 		
	 		 System.out.println("Test Case 1 is passed");
	 	}
	 	else
	 	{
	 		System.out.println("Test Case 2 is Failed");
	 	}
	 	
	}

	

}
