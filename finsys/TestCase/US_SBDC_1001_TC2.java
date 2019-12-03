package ejagruti.finsys.TestCase;
import ejagruti.finsys.config.Config;
import java.io.IOException;
import org.testng.annotations.Test;

//@Listeners(org.ejagruti.finsys.listenres.TestCaseListener.class)
public class US_SBDC_1001_TC2 {
	
	@Test
	public void Executetestcase() throws IOException, Exception {
		
        Config.obj.IsObjectExists(Config.or.GetParameterValue("_managecompany_linktext_xpath"));
		Config.obj.ClickLinkText(Config.or.GetParameterValue("_managecompany_linktext_xpath"));
		Config.obj.FrameSwitch(Config.or.GetParameterValue("_frameswitch_xpath"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_new_btn_xpath"));
		Config.obj.ButtonClick(Config.or.GetParameterValue("_new_btn_xpath"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_companyname_textbox_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_companyname_textbox_xpath"), Config.pfr.GetParameterValue("_companyname"));
		Config.obj.IsCheckBoxSelected(Config.or.GetParameterValue("_companytype_select_xpath"));
		Config.obj.DropDownSelectoptionByValue(Config.or.GetParameterValue("_companytype_select_xpath"),Config.pfr.GetParameterValue("_companytype"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_companysubtype_sel_xpath"));
		Config.obj.DropDownSelectValueByVisibleText(Config.or.GetParameterValue("_companysubtype_sel_xpath"), Config.pfr.GetParameterValue("_companysubtype"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_address_textarea_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_address_textarea_xpath"),Config.pfr.GetParameterValue("_CompanyAddress"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_phone_textbox_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_phone_textbox_xpath"), Config.pfr.GetParameterValue("_phone"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_Email_testbox_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_Email_testbox_xpath"),Config.pfr.GetParameterValue("_EmailID"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_pan_textbox_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_pan_textbox_xpath"), Config.pfr.GetParameterValue("_pan"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_tin_textbox_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_tin_textbox_xpath"),Config.pfr.GetParameterValue("_tin"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_mobile_textbox_xapth"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_mobile_textbox_xapth"),Config.pfr.GetParameterValue("_mobile"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_website_textbox_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_website_textbox_xpath"),Config.pfr.GetParameterValue("_website"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_country_select_xpath"));
		Config.obj.DropDownSelectValueByVisibleText(Config.or.GetParameterValue("_country_select_xpath"), Config.pfr.GetParameterValue("_county"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_state_select_xpath"));
		Config.obj.DropDownSelectValueByVisibleText(Config.or.GetParameterValue("_state_select_xpath"), Config.pfr.GetParameterValue("_state"));
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_city_select_xpath"));
		Config.obj.DropDownSelectValueByVisibleText(Config.or.GetParameterValue("_city_select_xpath"), Config.pfr.GetParameterValue("_cityname"));		
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_totalemp_textbox_xpath"));
		Config.obj.TextBoxSetValue(Config.or.GetParameterValue("_totalemp_textbox_xpath"), Config.pfr.GetParameterValue("_totalemployee"));
		
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_save_btn_xpath"));
		Config.obj.ButtonClick(Config.or.GetParameterValue("_save_btn_xpath"));
		Thread.sleep(6000);
		String actualresult="company name";
		boolean result=Config.obj.TableCellVlaueExist(actualresult);
		System.out.println(result);
		
		if(result==true){
			System.out.println("Test Case 2 is passed");
		}
		else {
			 System.out.println("Test Case 2 is failed");
		}
		
		 
	
	}

	
}
