Feature: CoinMarketCap UI 

Scenario: Filtering UI and extracting data for comparison

 Given user is on coinmarket page
 When user changes the display row to twenty
 And user gets the first 20 records
 And click filter by alogrithm - "PoW"
 And toggle "mineable"
 And click "All Cryptocurrencies"
 And select "coins"
 And select price 
 And set minimum value to 100 and maximum value to 10000
 Then compare the results with first 20 records