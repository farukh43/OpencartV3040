package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j


public class BaseClass {
	
	protected static WebDriver driver;
	public Logger logger; //log 4j
	public Properties p;

	@Parameters({"os","browser"})
	@BeforeClass(groups = {"Sanity","Regression","Master","Datadriven"})
	public void setUp(String os, String br) throws IOException {
		
		//loading cconfig.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		//loading the log4j
		logger=LogManager.getLogger(this.getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			//OS
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if (os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("No matching os");
			}
			//Browser
			switch (br.toLowerCase()) {
			case "chrome" : capabilities.setBrowserName("chrome");break;
			case "edge" : capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox" : capabilities.setBrowserName("firefox");break;
			default  : System.out.println("No Matching browser"); return;
			}
			driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), capabilities);
			// Set timeouts 
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		}
				
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch (br.toLowerCase()) {
			case "chrome": driver = new ChromeDriver();break;
			case "edge": driver = new EdgeDriver();break;
			case "firefox": driver = new FirefoxDriver();break;
			default: System.out.println("Invalid browser name...");
			return;
			}
			
		}
				
		
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); //reading url from properties file.
	}
	
	@AfterClass(groups = {"Sanity","Regression","Master","Datadriven"})
	public void tearDown() {
		driver.quit();
	}
	
	/*
	 * randomAlphabetic is depricated
	public String randomString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(6);		
		return generatedString;
	}

*/
	public static String randomString() {
        // Using the secure instance of RandomStringUtils to generate a 6-character alphabetic string
		String generatedString=RandomStringUtils.secure().nextAlphabetic(6);
		return generatedString;
    }
	
	public static String randomNumber() {
      	String generatedNumber=RandomStringUtils.secure().nextNumeric(10);
		return generatedNumber;
    }
	
	public static String randomAlphaNumeric() {
		String generatedString=RandomStringUtils.secure().nextAlphabetic(6);
		String generatedNumber=RandomStringUtils.secure().nextNumeric(3);
		return generatedString+"@"+generatedNumber;
    }

	public String captureScreen(String tname) throws IOException {

	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

	    String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    sourceFile.renameTo(targetFile);

	    return targetFilePath;
	}
	
	
	


}
