package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class coinPageObjects {
	
	public static WebDriver driverInitiation( WebDriver driver) {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Vignesh Raghupathi\\eclipse-workspace\\chromedriver\\chromedriver.exe");
		ChromeOptions co =new ChromeOptions();
		co.setAcceptInsecureCerts(true);
		driver =new ChromeDriver(co);
		driver.get("https://coinmarketcap.com/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		WebElement element= driver.findElement(By.xpath("//*[local-name()='svg' and @class='sc-aef7b723-0 jhvPQd']/*[local-name()='path']"));
		element.click();
		WebElement element1= driver.findElement(By.xpath("//*[local-name()='svg' and @class='sc-aef7b723-0 fKbUaI close-button']/*[local-name()='path']"));
		element1.click();
		return driver;
	}

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
		jsE.executeScript("history.go(0)");
	}

	public static ArrayList<String> pageContent(WebDriver driver)
	{
		ArrayList<String> rank = new ArrayList<String>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<String> price = new ArrayList<String>();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		WebElement ele =driver.findElement(By.xpath("//div[@class=\"sc-aef7b723-0 sc-18df06a5-0 hBoqvQ\"]"));
		jse(driver,ele);
		List<WebElement> first  = driver.findElements(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[2]/p"));
		List<WebElement> first1 = driver.findElements(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[3]/div/a/div/div/p")); 
		List<WebElement> first2 = driver.findElements(By.xpath("//div[@class=\"sc-f7a61dda-2 efhsPu\"]/table/tbody/tr/td[4]//span"));

		for (WebElement fir : first)
		{ String test  = fir.getText();	rank.add(test); }
		for (WebElement fir : first1)
		{ String test  = fir.getText();	name.add(test);	}
		for (WebElement fir : first2)
		{ String test  = fir.getText();	price.add(test);}

		ArrayList<String> resultSet = new ArrayList<String>();
		System.out.println(" rank | name | price");
		for (int i=0;i<rank.size();i++)
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
		WebElement addFilter= driver.findElement(By.xpath("(//button[@class=\"sc-a4a6801b-0 gNHIvn sc-cc37dd9f-0 sc-7b31fd38-0 cmPAGl\"])[4]"));
		jse(driver,addFilter);
	}

	public static void checkPop(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("//div[@class=\"buttons\"]/button[2]")).click();
		selectAllCrypto(driver);
	}
	public static WebElement toggleMinemable(WebDriver driver)
	{														  
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"mineable\"]/parent::div/label/span")));
		return driver.findElement(By.xpath("//input[@id=\"mineable\"]/parent::div/label/span"));
	}

	public static WebElement selectAllCrypto(WebDriver driver)
	{
		WebElement crypto= driver.findElement(By.xpath("//button[@class=\"sc-a4a6801b-0 cGFzGl cmc-filter-button\"]"));
		jse(driver,crypto);
		return crypto;
	}										

	public static void selectCoins(WebDriver driver)
	{
		WebElement coins= driver.findElement(By.xpath("//button[@class=\"sc-a4a6801b-0 bcMxzk cmc-option-button\"]"));
		jse(driver,coins);
	}
	public static WebElement selectPrice(WebDriver driver)
	{
		return driver.findElement(By.xpath("(//button[@class=\"sc-a4a6801b-0 cGFzGl cmc-filter-button\"])[3]"));
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

	public static void tearOut(WebDriver driver)
	{
		driver.quit();
		System.out.println("Driver Closed");
	}
	
	
}
