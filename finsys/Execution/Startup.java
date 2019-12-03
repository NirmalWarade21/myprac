package ejagruti.finsys.Execution;

import ejagruti.finsys.TestCase.US_SBDC_1000_TC1;
import ejagruti.finsys.TestCase.US_SBDC_1001_TC2;
import ejagruti.finsys.TestCase.US_SBDC_1001_TC3;

public class Startup {

	public static void main(String[] args) throws Exception {
        US_SBDC_1000_TC1 test1obj=new US_SBDC_1000_TC1();
        test1obj.Executetestcase();
		US_SBDC_1001_TC2 test2obj=new US_SBDC_1001_TC2();
		test2obj.Executetestcase();
		US_SBDC_1001_TC3 test3obj=new US_SBDC_1001_TC3();
		test3obj.Executetestcase();
		
	}

}
