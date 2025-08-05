package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.slf4j.Logger;

public class BaseClass1 {
	
	static WebDriver driver;
	static Properties p;
	static Logger logger;
	
	public static WebDriver browserinitialize() throws IOException
	{
		p=getProperties();
		String executionEnv=p.getProperty("execution_env");
		String os=p.getProperty("os");
		String browser=p.getProperty("browser");
		
		if(executionEnv.equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			
			switch(os)
			{
			case "windows": capabilities.setPlatform(Platform.WINDOWS);
			break;
			case "mac":capabilities.setPlatform(Platform.MAC);
			break;
			case "linux":capabilities.setPlatform(Platform.LINUX);
			break;
			default:System.out.println("Platform not found");
			return null;
			}
			
			switch(browser)
			{
			case "chrome": capabilities.setBrowserName("chrome");
			break;
			case "edge": capabilities.setBrowserName("edge");
			break;
			case "firefox": capabilities.setBrowserName("firefox");
			default: System.out.println("browser not found");
			return null;
			
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
		}
		
		else if(executionEnv.equalsIgnoreCase("local"))
		{
			switch(browser.toLowerCase())
			{
			case "chrome":driver=new ChromeDriver();
			break;
			case "edge": driver=new EdgeDriver();
			break;
			case "firefox": driver=new FirefoxDriver();
			break;
			default:System.out.println("browser not found");
			driver=null;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		return driver;
	}
	
	public static Properties getProperties() throws IOException
	{
		FileReader file=new FileReader(System.getProperty("user.dir"+"\\src\\test\\resources\\config.properties"));
		p=new Properties();
		p.load(file);
		return p;
	}
	
	public static WebDriver getdriver()
	{
		return driver;
	}
	
	public static Logger getLogger()
	{
		logger=LogManager.getLogger();
		return logger;
	}
	
	public static String getRandomString()
	{
		String randString=RandomStringUtils.randomAlphabetic(5);
		return randString;
	}
	
	public static String getRandomNumbers()
	{
		String randnum=RandomStringUtils.randomNumeric(5);
		return randnum;
	}
	
	public static String getrandpass()
	{
		String alpha=RandomStringUtils.randomAlphabetic(5);
		String num=RandomStringUtils.randomNumeric(5);
		return alpha+num;
	}
	
}
