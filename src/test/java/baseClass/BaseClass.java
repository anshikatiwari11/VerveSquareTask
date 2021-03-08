package baseClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import testScripts.HomePage;

public class BaseClass extends Configuration{
	
	protected static WebDriver dr = null;
	static HomePage homePage;
	
	@BeforeTest
	@Parameters("Browser")
	public void setUp(String Browser)
	{
		if (Browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", chromePath);
			dr = new ChromeDriver();
		}
		
		else if (Browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", iePath);
			dr = new InternetExplorerDriver();
		}
		else if (Browser.equalsIgnoreCase("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", geckoPath);
			dr = new FirefoxDriver();
		}
		
		dr.manage().window().maximize();
		dr.manage().timeouts().implicitlyWait(Configuration.implicitWait, TimeUnit.SECONDS);
		dr.get(Configuration.url);
	}
	
	@AfterTest
	public void close_Browser()
	{
		dr.quit();
	}
}
