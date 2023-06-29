package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

//import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {

	public static WebDriver driver;
	
	public Logger log;
	
	public ResourceBundle rb;

	@BeforeClass(groups= {"Regression","Sanity","Master"})
	@Parameters("browser")
	public void setup(String br)
	{		
//		ChromeOptions options=new ChromeOptions();
//		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});	// To remove the message "browser is control by test automation"
		
		rb=ResourceBundle.getBundle("Config");
		
		log=LogManager.getLogger(this.getClass());
		
		String log4jPath=System.getProperty("user.dir") + "\\src\\test\\resources\\" + "log4j.properties";
		PropertyConfigurator.configure(log4jPath);
				
//		WebDriverManager.chromedriver().setup();		// this statement is optional from selenium version 4.6.0
		if(br.equals("chrome")) {
			driver=new ChromeDriver();
			
//			ChromeDriverService service=new ChromeDriverService.Builder().withLogOutput(System.out).build();		//this is used for console logs (but not complete code)
		}
		else if(br.equals("edge")) {
			driver=new EdgeDriver();
		}
		else {
			driver=new FirefoxDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		driver.get(rb.getString("OpenUrl"));						//this command get the url from config.properties file
//		driver.get("https://magento.softwaretestingboard.com/");
		
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Regression","Sanity","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return (generatedString);
	}

	public String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}
	
	public String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(8);
		String num = RandomStringUtils.randomNumeric(4);
		
		return (st+"@"+num);
	}

	public String captureScreen(String name) throws IOException {
		
		String timestamp=new SimpleDateFormat("yyyy-MM-dd.hh-mm-ss").format(new Date());
		
		TakesScreenshot takesSshot = (TakesScreenshot) driver;
		File source = takesSshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + name + "_" + timestamp + ".png";
		
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch(Exception e) {
			e.getMessage();
		}
		return destination;
	}
	
	
}
