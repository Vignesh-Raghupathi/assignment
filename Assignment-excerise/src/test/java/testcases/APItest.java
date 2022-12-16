package testcases;

	import io.cucumber.java.en.*;
	import org.junit.Assert;
	import io.restassured.RestAssured;
	import static io.restassured.RestAssured.given;
	import io.restassured.response.Response;
	import io.restassured.path.json.JsonPath;
 
	public class APItest {

		int amount;
	    String fromCurrency;
	    String toCurrency;
	    Response response;

	    @When("/^user converts (\\d+) Guatemalan Quetzal to British pounds$/")
	    public void input_parametes() {
	    	RestAssured.baseURI="https://pro-api.coinmarketcap.com";
	    	amount=10000000;
	        fromCurrency = "GTQ";
	        toCurrency = "GBP";
	        response = given()
	                .header("X-CMC_PRO_API_KEY","3e1b29f6-54c5-435b-82a4-2d6fde43e231")
	                .param("amount", amount)
	                .param("symbol", fromCurrency)
	                .param("convert", toCurrency)
	                .when()
	                .get("/v2/tools/price-conversion")
	                .then()
	                .extract().response();

	        Assert.assertEquals(200, response.statusCode());  
	    }

	    @Then("/^user should be able to save the British pounds Price$/")
	    public void first_conversion(){

	        
	    	JsonPath jsonResp = new JsonPath(response.asString());
	    	Double price = jsonResp.getDouble("data.quote.GBP.price");
	    	System.out.println("Price in GBP is: " + price);
	    	amount = (int)Math.round(price);
	    	System.out.println("Amount is: " + amount);
	    }

	 

	    @Then("/^user should be able to convert British pounds Price to Doge Coin$/")
	    public void user_should_be_able_to_convert_British_pounds_Price_to_Doge_Coin(){
	        fromCurrency = "GBP";
	        toCurrency = "DOGE";
	        response = given()
	                .header("X-CMC_PRO_API_KEY", "3e1b29f6-54c5-435b-82a4-2d6fde43e231")
	                .param("amount", amount)
	                .param("symbol", fromCurrency)
	                .param("convert", toCurrency)
	                .when()
	                .get("/v2/tools/price-conversion")
	                .then()
	                .extract().response();
	        Assert.assertEquals(200, response.statusCode());
	        JsonPath jsonResp = new JsonPath(response.asString());

	        double price = jsonResp.getDouble("data.quote.DOGE.price");
	        System.out.println("Price in Doge Coin is: " + price);

	    }
}
