package ejagruti.finsys.TestCase;
import ejagruti.finsys.config.Config;
import org.testng.annotations.Test;

//@Listeners(org.ejagruti.finsys.listenres.TestCaseListener.class)
public class US_SBDC_1001_TC3 {
	
@Test
	public void Executetestcase() throws Exception{
		
		
	
		Config.obj.IsObjectExists(Config.or.GetParameterValue("_new_btn_xpath"));
		Config.obj.ButtonClick(Config.or.GetParameterValue("_new_btn_xpath"));
		Config.obj.IsCheckBoxSelected(Config.or.GetParameterValue("_companytype_select_xpath"));
		String dropdownvalues=Config.obj.DropDownAllValues(Config.or.GetParameterValue("_companytype_select_xpath"));
        System.out.println(dropdownvalues);
        String DDval="|Manufacturing|IT|Consultancy Services|Marketing|FMCG";
        if(dropdownvalues.equals(DDval))
        {
			System.out.println("Test Case 3 is passed");	
        }
        else 
        {
			System.out.println("Test Case 3 is failed");

		}
        
	}
	
}
