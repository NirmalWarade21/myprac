package ejagruti.finsys.BusinessLogic;

import org.ejagruti.generic.GFL;
import org.ejagruti.generic.TextOperations;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Opreations {

    public int counter = 1;
    public boolean isLogEnabled = false;
    public String LogFilePath;
    public String resultfilepath;
    public boolean isResultEnable ;
    public String GetImagepath;
    public String SendImagepath=null;
    public static WebDriver driver;
    public static WebDriverWait wait;


    public void TestCaseStart(String tcid, String title) {
        HTMLReportGenerator.TestCaseStart(tcid, title);

    }

    public void TestCaseEnd() {
    	counter=1;
        HTMLReportGenerator.TestCaseEnd();

    }

    public void TestSuiteStart(String suitename) throws UnknownHostException {
        HTMLReportGenerator.TestSuiteStart(resultfilepath, suitename);
    }

    public void TestSuiteEnd() {
        HTMLReportGenerator.TestSuiteEnd();
    }
    
    /// ?????????????????? Defaut constructor ??????????????????????
     public Opreations(){
 
     }
 
    /// ?????????????????? log enable ??????????????????????

    public Opreations(boolean LogEnabled, String LogFilePath) {
        if (LogEnabled) {
            this.LogFilePath = LogFilePath;
            isLogEnabled = true;
            if (!TextOperations.FileExists(LogFilePath)) {
                TextOperations.CreateTextFile(LogFilePath);
                System.out.println("File created");
            } else {
                System.out.println("File already exist");
            }
        }
    }

    //????????????????? result enable ????????????????????
    public Opreations(String FolderPath, boolean ResultEnabled) {
        if (ResultEnabled) {

            String uniqueFileName = GFL.GenerateUniqueString();
            resultfilepath = FolderPath + "\\" + uniqueFileName + ".html";
            TextOperations.CreateTextFile(resultfilepath);
            System.out.println("File created");

            if (FolderPath != null) {
                isResultEnable = ResultEnabled;
           	     GetImagepath=FolderPath;	

            }
        }
    }

    //?????? result and log enable ?????????????????
    public Opreations(String imagepath,String ResultPath, String LogPath, boolean Enablelog, boolean EnableResult) {

        if (EnableResult && Enablelog) {
            this.LogFilePath = LogPath;
            isLogEnabled = true;

            String uniqueFileName = GFL.GenerateUniqueString();
            resultfilepath = ResultPath + "\\" + uniqueFileName + ".html";
            TextOperations.CreateTextFile(ResultPath);
            System.out.println("result File created");
            
           

            if (!TextOperations.FileExists(LogPath) ) {
                TextOperations.CreateTextFile(LogPath);
                System.out.println(" log File created");
                }
                if  (ResultPath != null){
                	 isResultEnable = EnableResult;
                	 GetImagepath=imagepath;	
                }
             else {
                System.out.println("File already exist");
            }
        }
    }

    //##################### screenshot capture #########################################

    public void captureScreenShot(String imagepath) {
        // Take screenshot and store as a file format             
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String imagepath1 = imagepath +"image"+System.currentTimeMillis() + ".png";
        SendImagepath = imagepath1;

        try {
            // now copy the  screenshot to desired location using copyFile method

            FileUtils.copyFile(src, new File(imagepath1));
            System.out.println("screenshot taken");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //##################### launch application #########################################

    public void LaunchApplication(String BrowserName, String URL) {
        try {

            if (BrowserName.contains("ff")) {
                driver = new FirefoxDriver();
                driver.get(URL);
            } else if (BrowserName.contains("c")) {

                WebDriver driver = new ChromeDriver();
                driver.get(URL);
            } else if (BrowserName.contains("ie")) {

            }

            driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
            driver.manage().window().maximize();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number: " + (counter++) +" Able to launch the application";
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "LaunchApplication", message,SendImagepath );
               System.out.println("result genrated...!!");
               
           }
            
        } catch (Exception ex) {
        	
        	//??????  log file
            String message = TextOperations.getDateTime() + "---Error---Step Number: "+ (counter++)+ " fail to launch the application" + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            
            }
            //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "LaunchApplication", message,SendImagepath );
        }
    }

    //##################### click link text #########################################
    public void ClickLinkText(String xpath) {
        try {
            WebElement obj = IsObjectExists(xpath);
            obj.click();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to click link text using" + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "ClickLinkText", message,SendImagepath );
               System.out.println("result genrated...!!");
           }
            
        } catch (Exception ex) {
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " fail to click link text " + "\n Exception:" + ex.getMessage();
          //??????  log file  
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "ClickLinkText", message,SendImagepath );
        }  
    }  

    //##################### button action #########################################

    public void ButtonClick(String xPath) {
        try {
            WebElement obj = IsObjectExists(xPath);
            obj.click();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to click on button using xPath " + xPath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //result & screenshot
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "ButtonClick", message,SendImagepath );
               System.out.println("result genrated...!!");  
           }

        } catch (Exception e) {
        	 //??????  log file 
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " fail to click onbutton using xPath" + xPath + "\n Exception:" + e.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //result & screenshot
            HTMLReportGenerator.StepDetails("fail", "ButtonClick", message,SendImagepath );
        }
    }

    public void ButtonDoubleClick(String xpath) {
        try {
            Actions action = new Actions(driver);
            WebElement obj = IsObjectExists(xpath);
            action.doubleClick(obj).perform();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to on double click button using xPath " + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //result & screenshot
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "ButtonDoubleClick", message,SendImagepath );
               System.out.println("result genrated...!!");
               
           }
        } catch (Exception e) {
            //??????  log file 
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " fail to double click on button using xPath" + xpath + "\n Exception:" + e.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //result & screenshot
            HTMLReportGenerator.StepDetails("fail", "ButtonDoubleClick", message,SendImagepath );
        }     
    }

    public void RightClick(String xpath) {
        try {
            Actions action = new Actions(driver);
            WebElement obj = IsObjectExists(xpath);
            action.contextClick(obj).perform();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to right click on the Button using the xPath " + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "RightClick", message,SendImagepath );
               System.out.println("result genrated...!!");   
           }
        } catch (Exception e) {
            //??????  log file  
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " fail to right click on button using xPath" + xpath + "\n Exception:" + e.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "RightClick", message,SendImagepath );
        }
    }

    public void MouseMove(String xpath) {

        try {
            Actions action = new Actions(driver);
            WebElement obj = IsObjectExists(xpath);
            action.moveToElement(obj).perform();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to perform mouseover by using xapth= " + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "MouseMove", message,SendImagepath );
               System.out.println("result genrated...!!");               
           }

        } catch (Exception ex) {
        	//??????  log file  
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to perform mouseover by using xPath=" + xpath + "\n Exception:" + ex.getMessage();

            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "MouseMove", message,SendImagepath );
        }
    }

    public void AlertBoxAccept() {
        try {
            driver.switchTo().alert().accept();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " AlertBox Accepted ";
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "AlertBoxAccept", message,SendImagepath );
               System.out.println("result genrated...!!");
               
           }
            
        } catch (Exception ex) {
        	//??????  log file  
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to Accept AlertBox" + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "AlertBoxAccept", message,SendImagepath );
        }
    }

    public void AlertBoxDiscard() {
        try {
            driver.switchTo().alert().dismiss();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " AlertBox Discard ";
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "AlertBoxDiscard", message,SendImagepath );
               System.out.println("result genrated...!!");        
           }

        } catch (Exception ex) {
            //??????  log file  
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to Discard AlertBox" + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "AlertBoxDiscard", message,SendImagepath );
        }
    }
    
    //##################### text box #########################################
    public void TextBoxSetValue(String xpath, String keys) {

        try {
            WebElement obj = IsObjectExists(xpath);
            obj.clear();
            obj.sendKeys(keys);

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to set textbox value using the xPath " + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "TextBoxSetValue", message,SendImagepath );
               System.out.println("result genrated...!!");      
           }
        } catch (Exception ex) {
            //??????  log file  
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " fail to set text box value using xPath" + xpath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "TextBoxSetValue", message,SendImagepath );
        }
    }

    public void TextBoxAppendValue(String xPath, String Value) {
        try {
            WebElement obj = IsObjectExists(xPath);
            obj.sendKeys(Value);

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + "Able to Perform Append Value Action in Text Box using xPath=" + xPath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "TextBoxAppendValue", message,SendImagepath );
               System.out.println("result genrated...!!");    
           }
        } catch (Exception ex) {
        	//??????  log file 
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to Perform Append Value Action in Text Box using xPath=" + xPath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "TextBoxAppendValue", message,SendImagepath );
        }
    }
    public void ClearTextBox(String Xpath) {
        try {
            WebElement clear = driver.findElement(By.xpath(Xpath));
            clear.sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END));

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to clear textbox using  Xpath=" + Xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result

            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "ClearTextBox", message,SendImagepath );
               System.out.println("result genrated...!!");        
           }
        } catch (Exception ex) {
        	 //??????  log file
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to clear textbox using  xPath=" + Xpath + "\n Exception:" + ex.getMessage();
            System.out.println(message);
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "ClearTextBox", message,SendImagepath );
        }
    }

    //##################### table operations #########################################
    public int TableGetRowCount(String xPath) {
        int count = 0;
        try {
            WebElement obj = IsObjectExists(xPath);
            count = obj.findElements(By.tagName("tr")).size();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to get the table row count by using xapth= " + xPath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "TableGetRowCount", message,SendImagepath );
               System.out.println("result genrated...!!");       
           }
        } catch (Exception ex) {
        	//??????  log file
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to get the table row count by using xPath=" + xPath + "\n Exception:" + ex.getMessage();

            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "TableGetRowCount", message,SendImagepath );
        }
        return count;
    }

    public int TableGetColumnCount(String xPath, int RowNumber) {
        int count = 0;
        try {
            WebElement obj = IsObjectExists(xPath);
            count = obj.findElements(By.tagName("tr")).get(RowNumber).findElements(By.tagName("td")).size();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to get the table coloum count by using xapth= " + xPath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "TableGetColumnCount", message,SendImagepath );
               System.out.println("result genrated...!!");   
           }
        } catch (Exception ex) {
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to get the table coloum count by using xPath=" + xPath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "TableGetColumnCount", message,SendImagepath );
        }
        return count;
    }

    public String TableGetCellValue(String xPath, int RowNumber, int ColumnNumber) {
        String
        var = null;
        try {
            WebElement obj = IsObjectExists(xPath);

            var = obj.findElements(By.tagName("tr")).get(RowNumber).findElements(By.tagName("td")).get(ColumnNumber).getText();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to get the table cell value by using xapth= " + xPath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "TableGetCellValue", message,SendImagepath );
               System.out.println("result genrated...!!");   
           }
        } catch (Exception ex) {
        	 //??????  log file
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to get the table cell value by using xPath=" + xPath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "TableGetCellValue", message,SendImagepath );
        }     
        return var;
    }

    public boolean TableCellVlaueExist(String Ctext){
    	boolean TextFound = false;
    List<WebElement> Tr_collection= driver.findElements(By.tagName("tr"));
    for (WebElement trElement:Tr_collection)
    {
    	List<WebElement> Td_collection=trElement.findElements(By.xpath("td"));
    	for (WebElement tdElement:Td_collection){
    		String tbData = tdElement.getText();
    		if(tbData.contains(Ctext))
    		{
    			TextFound=true;
    			return TextFound;
    		}
    	}
    	}
	return TextFound;
    }    
    
    //##################### dropdown action ##t#######################################

    public void DropDownSelectValueByVisibleText(String xpath, String str) {
        try {
            WebElement obj = IsObjectExists(xpath);
            Select germany = new Select(obj);
            germany.selectByVisibleText(str);

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to select text from drpodown by using xapth= " + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "DropDownSelectValueByVisibleText", message,SendImagepath );
               System.out.println("result genrated...!!");              
           }
        } catch (Exception ex) {
            //??????  log file  

            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to select text from drpodown by using xPath=" + xpath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "DropDownSelectValueByVisibleText", message,SendImagepath );
        }
    }

    public void DropDownSelectValueByIndex(String xpath, int index) {

        try {
            WebElement obj = IsObjectExists(xpath);
            Select sel = new Select(obj);
            sel.selectByIndex(index);

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to select dropdown by index using xapth= " + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "DropDownSelectValueByIndex", message,SendImagepath );
               System.out.println("result genrated...!!");             
           }
        } catch (Exception ex) {
        	//??????  log file  
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to select dropdown by index using xPath=" + xpath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "DropDownSelectValueByIndex", message,SendImagepath );
        }
    }

    public void DropDownSelectoptionByValue(String xpath, String str) {
        try {
            WebElement obj = IsObjectExists(xpath);
            Select sel = new Select(obj);
            sel.selectByValue(str);

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to select dropdown value by value using xapth= " + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "DropDownSelectoptionByValue", message,SendImagepath );
               System.out.println("result genrated...!!");
              }

        } catch (Exception ex) {
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to select dropdown value by value using xPath=" + xpath + "\n Exception:" + ex.getMessage();
          //??????  log file
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "DropDownSelectoptionByValue", message,SendImagepath );
        }
    }

    public String DropDownGetSelectedValue(String xPath) {
        String
        var = null;
        try {
            WebElement obj = IsObjectExists(xPath);
            Select sel = new Select(obj);
            var = sel.getFirstSelectedOption().getText();

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to get selected value form DropDown using xapth= " + xPath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "DropDownGetSelectedValue", message,SendImagepath );
               System.out.println("result genrated...!!");       
           }
        } catch (Exception ex) {
        	//??????  log file
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to get selected valuefrom dropdown using xPath=" + xPath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
          //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "DropDownGetSelectedValue", message,SendImagepath );
        }
        return var;
    }


    public ArrayList < String > DropDownGetAllSelectedValue(String xPath) {
        ArrayList < String > allSelectedValue = null;
        try {
            WebElement obj = IsObjectExists(xPath);
            Select sel = new Select(obj);
            allSelectedValue = new ArrayList < String > ();
            for (WebElement ele: sel.getAllSelectedOptions()) {
                allSelectedValue.add(ele.getText());
            }

            //??????  log file  
            String message = TextOperations.getDateTime() + "---Info---Step Number:" + (counter++) + " Able to get all selected value form DropDown using xapth= " + xPath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "DropDownGetAllSelectedValue", message,SendImagepath );
               System.out.println("result genrated...!!");
               
           }

        } catch (Exception ex) {
        	//??????  log file
            String message = TextOperations.getDateTime() + "---Error---Step Number:" + (counter++) + " Failed to get all selected value from dropdown using xPath= " + xPath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
                System.out.println("text appended");
            }
            //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "DropDownGetAllSelectedValue", message,SendImagepath );
        }           
        return allSelectedValue;
    }
    
    public String DropDownAllValues( String xpath) {
    	String Dropval="";
    	 WebElement obj = IsObjectExists(xpath);
    	 obj.click();
    	 Select sel=new Select(obj);
    	 List<WebElement>alloption=sel.getOptions();
    	 for(int i=1; i<alloption.size();i++)
    	 {
    		 Dropval=Dropval+"|"+alloption.get(i).getText();
    	 }	
		return Dropval;
    	
	}

    //##################### frame action #########################################
    public WebDriver FrameSwitch(String xPath) {
        WebElement obj = IsObjectExists(xPath);
        return driver.switchTo().frame(obj);
    }

    public WebDriver FrameSwitchByIndex(int Index) {
        return driver.switchTo().frame(Index);
    }

    //##################### check box and radio button #########################################
    public boolean IsCheckBoxSelected(String xpath) {
        boolean checkstatus = false;
        try {
            WebElement obj = IsObjectExists(xpath);

            checkstatus = obj.isSelected();
            if (checkstatus == true) {
                System.out.println("Checkbox is already checked");
            }

            //??????  log file  
            String message = "---Info---Step Number:" + (counter++) + " Able to checked checkbox using  xPath=" + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
            }
            //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "IsCheckBoxSelected", message,SendImagepath );
               System.out.println("result genrated...!!");              
           }
        } catch (Exception ex) {
        	//??????  log file
            String message = "---Error---Step Number:" + (counter++) + " Failed to checked checkbox using xPath=" + xpath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
            }
            //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "IsCheckBoxSelected", message,SendImagepath );
        }     
        return checkstatus;
    }

    public void CheckBoxDeselect(String xpath) {

        try {
            
            WebElement obj = IsObjectExists(xpath);
           
           //boolean chkbox = driver.findElement(By.xpath(xpath)).isSelected();
            boolean chkbox = obj.isSelected();
            if (chkbox = false) {
                CheckBoxChecked(xpath);
            }

            //??????  log file  
            String message = "---Info---Step Number:" + (counter++) + " Able to Unchecked checkbox using  xPath=" + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
            }
            //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "CheckBoxDeselect", message,SendImagepath );
               System.out.println("result genrated...!!");
               
           }

        } catch (Exception ex) {
        	//????? log file
            String message = "---Error---Step Number:" + (counter++) + " Failed to Unchecked checkbox using  xPath=" + xpath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
            }
            //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "CheckBoxDeselect", message,SendImagepath );
        }      
    }

    public void CheckBoxChecked(String xpath) {
        try {
            WebElement obj = IsObjectExists(xpath);
            if (!obj.isSelected()) {
                obj.click();
            }

            //??????  log file  
            String message = "---Info--- Step Number: " + (counter++) + " Able to Click on checkbox using  xPath= " + xpath;
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
            }
            //?????? screen shot  & result
            if (isResultEnable) {
                captureScreenShot(GetImagepath);
               HTMLReportGenerator.StepDetails("pass", "CheckBoxChecked", message,SendImagepath );
               System.out.println("result genrated...!!");              
           }

        } catch (Exception ex) {
        	//????? log file
            String message = "---Error---Step Number:" + (counter++) + " Failed to Click on checkbox using  xPath=" + xpath + "\n Exception:" + ex.getMessage();
            if (isLogEnabled) {
                TextOperations.AppendTextFile(LogFilePath, message);
            }
            //?????? screen shot  & result
            HTMLReportGenerator.StepDetails("fail", "CheckBoxChecked", message,SendImagepath );
        }      
    }
    //##################### IsObjectExists application #########################################

    public WebElement IsObjectExists(String xPath) {
        wait = new WebDriverWait(driver, 30);
        WebElement obj = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
        return obj;
    }

    
  //##################### ObjectGetAttributeValue #########################################
    
    public String ObjectGetAttributeValue(String xPath,String AttributeName,int waittime)
	{
    	wait = new WebDriverWait(driver, waittime);
		WebElement obj=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
		return obj.getAttribute(AttributeName);
	}

   


    

}