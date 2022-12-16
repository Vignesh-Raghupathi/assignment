package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class coinPageObjects {

	public static void jse( WebDriver driver, WebElement element)
	{
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",element);
		jse.executeScript("arguments[0].click();",element);	
	}

	public static void changeRowValue(WebDriver driver)
	{
		WebElement rowValue =driver.findElement(By.xpath("(//div[@class=\"sc-aef7b723-0 sc-dae82938-0 coScOT\"])[1]"));
		jse(driver,rowValue);
		JavascriptExecutor jsE=(JavascriptExecutor) driver;
		List<WebElement> rowValues=driver.findElements(By.xpath("//button[@class='sc-a4a6801b-0 jgNqHP']"));
		for(WebElement webElement : rowValues) 
		{
			String val = webElement.getText();
			if(val.contains("20")) 
			{
				jsE.executeScript("arguments[0].click();",webElement);
				break;
			}
		} 
	}

	public static ArrayList<String> pageContent(WebDriver driver)
	{
		List<String> rank = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> price = new ArrayList<String>();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[2]"));
		int rowCount=rows.size();
		System.out.println(" Initial Row count: "+rowCount);
		String first  = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[2]/p")).getText();
		rank.add(first);
		String first1 = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[3]/div/a/div/div/p")).getText(); 
		name.add(first1);
		String first2 = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[4]//span")).getText();
		price.add(first2);

		for (int i=1;i<11;i++)
		{

			String test  = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr[" + i +"]/td[2]/p")).getText();
			rank.add(test);
			String test1 = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr[" + i +"]/td[3]/div/a/div/div/p")).getText(); 
			name.add(test1);
			String test2 = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr[" + i +"]/td[4]//span")).getText();
			price.add(test2);
		}
		ArrayList<String> resultSet = new ArrayList<String>();
		System.out.println(" rank | name | price");
		for( int i=1;i<11;i++)
		{
			System.out.println(rank.get(i) + "|" +name.get(i)+" |"+price.get(i)); 
			resultSet.add(rank.get(i) + "|" +name.get(i)+" |"+price.get(i));
		}

		return resultSet;
	}

	public static void changeToPow(WebDriver driver)
	{
		WebElement filterButton= driver.findElement(By.xpath("(//button[@class=\"sc-a4a6801b-0 gNHIvn sc-c8c9e58f-0 eTWSGQ table-control-filter\"])[2]"));
		jse(driver,filterButton);
		JavascriptExecutor jsE=(JavascriptExecutor) driver;
		WebElement alGorithm = driver.findElement(By.xpath("(//button[@class=\"sc-a4a6801b-0 gNHIvn sc-cc37dd9f-0 sc-7b31fd38-0 cmPAGl\"])[2]"));
		Actions act = new Actions(driver);
		act.moveToElement(alGorithm).click().build().perform();
		List <WebElement> algOptions=driver.findElements(By.xpath("//div[@class=\"sc-45fbdd6f-0 dURHKG\"]/div/ul/li"));
		for(WebElement element : algOptions) 
		{
			String val = element.getText();
			if(val.equals("PoW")) 
			{
				jsE.executeScript("arguments[0].click();",element);
				break;
			}
		} 
	}

	public static WebElement toggleMinemable(WebDriver driver)
	{														  
		WebElement addFilter= driver.findElement(By.xpath("(//button[@class=\"sc-a4a6801b-0 gNHIvn sc-cc37dd9f-0 sc-7b31fd38-0 cmPAGl\"])[4]"));
		jse(driver,addFilter);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"buttons\"]/button[2]")));
		driver.findElement(By.xpath("//div[@class=\"buttons\"]/button[2]")).click();
		return driver.findElement(By.xpath("//input[@id=\"mineable\"]/parent::div/label/span"));
	}

	public static WebElement selectAllCrypto(WebDriver driver)
	{

		return driver.findElement(By.xpath("(//div[@class=\"sc-3f0e0564-0 cUVoXU\"])/div[2]/div/div[1]/button"));
	}

	public static WebElement selectCoins(WebDriver driver)
	{
		return driver.findElement(By.xpath("(//div[@class=\"sc-3f0e0564-0 cUVoXU\"])/div[2]/div/div[2]/div[2]/button"));
	}
	public static WebElement selectPrice(WebDriver driver)
	{
		return driver.findElement(By.xpath("(//div[@class=\"sc-3f0e0564-0 cUVoXU\"])/div[2]/div/div[3]/button"));
	}

	public static WebElement lowerPriceValue(WebDriver driver)
	{
		return driver.findElement(By.xpath("(//div[@class=\"sc-aef7b723-0 iqqamJ\"])/div/input"));
	}
	public static WebElement upperPriceValue(WebDriver driver)
	{
		return driver.findElement(By.xpath("(//div[@class=\"sc-aef7b723-0 iqqamJ\"])/div/input[2]"));
	}	
	public static WebElement applyFilter(WebDriver driver)
	{
		return driver.findElement(By.xpath("//div[@class=\"sc-67bdc129-2 euMFmi cmc-input-row\"]/button"));
	}
	public static WebElement showResults(WebDriver driver)
	{
		return driver.findElement(By.xpath("//div[@class=\"sc-aef7b723-0 sc-f6433b52-0 hfka-Dm\"]/button"));
	}

	public static ArrayList<String> tableFilterContent(WebDriver driver)
	{
		List<String> rank = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> price = new ArrayList<String>();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		List<WebElement> rows = driver.findElements(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[2]"));
		int rowCount=rows.size();	
		System.out.println(" After filter Row count "+rowCount);

		String first  = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[2]/p")).getText();
		rank.add(first);
		String first1 = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[3]/div/a/div/div/p")).getText(); 
		name.add(first1);
		String first2 = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[4]//span")).getText();
		price.add(first2);
		for (int i=1;i<=rowCount;i++)
		{

			String test  = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr[" + i +"]/td[2]/p")).getText();
			rank.add(test);
			String test1 = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr[" + i +"]/td[3]/div/a/div/div/p")).getText(); 
			name.add(test1);
			String test2 = driver.findElement(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr[" + i +"]/td[4]//span")).getText();
			price.add(test2);
		}

		ArrayList<String> resultSet = new ArrayList<String>();
		System.out.println(" After filter , table looks ");
		System.out.println(" rank | name | price");
		for( int i=0;i<rowCount;i++)
		{
			System.out.println(rank.get(i) + "|" +name.get(i)+" |"+price.get(i)); 
			resultSet.add(rank.get(i) + "|" +name.get(i)+" |"+price.get(i));
		}

		return resultSet;
	}

}
