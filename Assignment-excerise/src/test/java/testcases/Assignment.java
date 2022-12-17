package testcases;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.coinPageObjects;

public class Assignment {
	WebDriver driver;

	@Given("^user is on coinmarket page$")
	public  void user_is_on_coinmarket_page() throws Throwable {

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

	}

	@When("^user changes the display row to twenty$")
	public  void user_changes_the_display_row_from_to_twenty() throws Throwable {

		coinPageObjects.changeRowValue(driver);

	}

	@When("^user gets the first (\\d+) records$")
	public  void user_gets_the_first_records(int arg1) throws Throwable {

		ArrayList<String> resSet = new ArrayList<String>();
		System.out.println("\n-----First 20 entry in the table-----");
		resSet =coinPageObjects.pageContent(driver);		
		System.out.println("ResSet :"+resSet);
	}

	@When("^click filter by alogrithm - \"([^\"]*)\"$")
	public  void click_filter_by_alogrithm(String arg1) throws Throwable {

		coinPageObjects.changeToPow(driver);
	}

	@When("^toggle \"([^\"]*)\"$")
	public  void toggle(String arg1) throws Throwable {
		try{
			coinPageObjects.toggleMinemable(driver).click();
		}
		catch(Exception e)
		{
			coinPageObjects.checkPop(driver);
			coinPageObjects.toggleMinemable(driver);
		}
	}

	@When("^click \"([^\"]*)\"$")
	public  void click_AllCrypto(String arg1) throws Throwable {
		try{
			coinPageObjects.selectAllCrypto(driver).click();
		}
		catch(Exception e)
		{
			coinPageObjects.checkPop(driver);
			coinPageObjects.selectAllCrypto(driver);
		}
	}

	@When("^select \"([^\"]*)\"$")
	public  void select_coins(String arg1) throws Throwable {
		try{
			coinPageObjects.selectCoins(driver);
		}catch(Exception e)
		{
			coinPageObjects.selectAllCrypto(driver);
		}
	}

	@When("^select price$")
	public  void select_price() throws Throwable {

		coinPageObjects.selectPrice(driver).click();
	}

	@When("^set minimum value to (\\d+) and maximum value to (\\d+)$")
	public  void set_minimum_value_to_and_maximum_value_to(int arg1, int arg2) throws Throwable {

		coinPageObjects.lowerPriceValue(driver).sendKeys(String.valueOf(arg1));
		coinPageObjects.upperPriceValue(driver).sendKeys(String.valueOf(arg2));
		coinPageObjects.applyFilter(driver).click();
		coinPageObjects.showResults(driver).click();

	}

	@Then("^compare the results with first (\\d+) records$")
	public  void compare_the_results_with_first_records(int arg1) throws Throwable {
		try {
			ArrayList<String> resSet1 = new ArrayList<String>();
			System.out.println("\n-----After applying Filter to the Table-----");
			resSet1 =coinPageObjects.pageContent(driver);
			System.out.println("resSet1:"+resSet1);
			coinPageObjects.tearOut(driver);
		} catch ( Exception e)
		{
			coinPageObjects.pageContent(driver);
		}
	}

	
}
