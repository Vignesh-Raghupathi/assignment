package testcases;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

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
		resSet =coinPageObjects.pageContent(driver);		
		System.out.println("\n----------");
		System.out.println(resSet);

	}

	@When("^click filter by alogrithm - \"([^\"]*)\"$")
	public  void click_filter_by_alogrithm(String arg1) throws Throwable {

		coinPageObjects.changeToPow(driver);


	}

	@When("^toggle \"([^\"]*)\"$")
	public  void toggle(String arg1) throws Throwable {

		coinPageObjects.toggleMinemable(driver).click();

	}

	@When("^click \"([^\"]*)\"$")
	public  void click(String arg1) throws Throwable {
		coinPageObjects.selectAllCrypto(driver).click();
	}

	@When("^select \"([^\"]*)\"$")
	public  void select(String arg1) throws Throwable {

		coinPageObjects.selectCoins(driver).click();

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

		ArrayList<String> resSet1 = new ArrayList<String>();
		resSet1 =coinPageObjects.tableFilterContent(driver);		
		System.out.println("\n-----After Filter List-----");
		System.out.println(resSet1);

	}

}
