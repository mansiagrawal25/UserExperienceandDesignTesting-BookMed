
public class BuyMedicine {
	import java.io.File;
	import java.io.IOException;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

	import org.apache.commons.io.FileUtils;
	import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
	import org.apache.poi.ss.usermodel.DataFormatter;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.Select;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.Assert;
	import org.testng.ITestResult;
	import org.testng.annotations.AfterMethod;
	import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

	public class PatientSignIn {
		
		
		ExtentReports report;
	    ExtentHtmlReporter htmlReporter;
	    ExtentTest logger;

	    private static String email;
	    private static String password;
	    private static String name;
	    private static String dob;
	    private static String sex;

	    
	    //We are loading data from the excel sheet.
	    public static void reader() throws IOException, InvalidFormatException {
	        XSSFWorkbook workbook = null;
			try {
				
				workbook = new XSSFWorkbook(System.getProperty("user.dir") + "/testSheet.xlsx");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
	        DataFormatter dataFormatter = new DataFormatter();

	        Sheet sheet = workbook.getSheetAt(0);
			Row row1 = (Row) sheet.getRow(0);
	        email = dataFormatter.formatCellValue(((org.apache.poi.ss.usermodel.Row) row1).getCell(0));
	        password = dataFormatter.formatCellValue(((org.apache.poi.ss.usermodel.Row) row1).getCell(1));
	        name = dataFormatter.formatCellValue(((org.apache.poi.ss.usermodel.Row) row1).getCell(2));
	        dateOfbirth = dataFormatter.formatCellValue(((org.apache.poi.ss.usermodel.Row) row1).getCell(3));
	        sex = dataFormatter.formatCellValue(((org.apache.poi.ss.usermodel.Row) row1).getCell(4));
	    }

	   

	     public static void messagePrinter(String actual, String expected, ExtentTest logger) {
	        try {
	            Assert.assertEquals(actual, expected);
	            logger.pass("Actual value is:  " + actual);
	            logger.pass("Expected value is:" + expected);
	            logger.pass("------------------------------------");
	        } catch (Error e) {
	            logger.fail("Actual value is:  " + actual);
	            logger.fail("Expected value is:" + expected);
	            logger.fail(e.toString());
	            logger.fail("------------------------------------");
	        }
	    }

	    
	    @BeforeTest
	    public void startTest() throws IOException, InvalidFormatException {
	        reader();
	        report = new ExtentReports();
	        System.out.println("The path is : "+System.getProperty("user.dir"));
	         htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "http://127.0.0.1:32767/start.html#id=g7rkxo&p=homepage_shweta&g=1");
	         report.attachReporter(htmlReporter);
	         report.setSystemInfo("Host Name", "Risabh Rai");
	         report.setSystemInfo("Environment", "Macbook");
	    }

	   
	    
	    
	    @Test(priority = 1)
	    public void secondCase() throws InterruptedException, IOException {
	    	
	        logger = report.createTest("Registration Test(Positive)");
	        
	        System.setProperty("webdriver.chrome.driver", "/Users/rishabh/Downloads/chromedriver");
	        WebDriver driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("http://127.0.0.1:32767/start.html#id=cnx131&p=patient_signup_login"");
	    	
	        File srcFile1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(srcFile1, new File("./Test2Screenshots/2-1.jpg"));
	        
	        
	        driver.findElement(By.id("u6")).click();
			driver.findElement(By.id("u33_input")).sendKeys("rishabh@gmail.com");
			driver.findElement(By.id("u35_input")).sendKeys("abasdasdasdc");
			driver.findElement(By.id("u37_input")).sendKeys("Rishabh");
			driver.findElement(By.id("u39_input")).sendKeys("Shrivastava");
			driver.findElement(By.id("u41_input")).sendKeys("25");
			driver.findElement(By.id("u42_input")).sendKeys("august");
			driver.findElement(By.id("u43_input")).sendKeys("1991");
			
			
			
			
			driver.findElement(By.id("u49_image")).click();
			driver.findElement(By.id("u51_text")).click();
			
//			LOGIN
			
			driver.findElement(By.id("u83_input")).sendKeys("rishabh@gmail.com");
			driver.findElement(By.id("u35_input")).sendKeys("abasdasdasdc");
			
			
			
//			BUY Medicine
			
			driver.findElement(By.id("u195_test")).click();
			
//			into BUy medicine page
			
			driver.findElement(By.id("u40-1_text")).click();
			
			driver.findElement(By.id("u46_text")).click();
			
			
		
	        
	        Thread.sleep(2000);
	        driver.quit();
	        messagePrinter(success, "Registered", logger);
	        logger.pass("Test Scenario 1: Positive case successfull");
	    }



	   


	   
	


}
