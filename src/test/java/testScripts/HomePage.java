package testScripts;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;

public class HomePage extends BaseClass {
	
	By MenSection = By.xpath("(//a[text()='Men'])[1]");
	By PhoneCases = By.xpath("//*[text()='Phone Cases']");
	By ResultDisplay = By.xpath("//ul[@class='results-base']/li[11]");
	By PriceInfo = By.xpath("//span[@class='PriceInfo-price']");
	By AddToBag = By.xpath("//*[text()='ADD TO BAG']");
	
	@Test
	public void TC00()
	{
		Actions act= new Actions(dr);
		act.moveToElement(dr.findElement(MenSection)).build().perform();
				
		dr.findElement(PhoneCases).click();
		
		dr.findElement(ResultDisplay).click();
		
		String parent = dr.getWindowHandle();
		Set <String> wins = dr.getWindowHandles();
		for (String w: wins)
		{
			if (!w.equals(parent))
			{
				dr.switchTo().window(w);
			}
		}
		
		if (dr.findElement(PriceInfo).isDisplayed())
		{
			String priceofcover = dr.findElement(PriceInfo).getText();
			String [] price = priceofcover.split(" ");
			
			int price1 = Integer.parseInt(price[1]);
			if(price1>10)
			{
				System.out.println("My Given scenaio is passed");
			}
		}
		
		Assert.assertEquals(dr.findElement(AddToBag).isDisplayed(), true,"Add to Bag is displaying");
		dr.switchTo().window(parent);
	}
	
	

}
