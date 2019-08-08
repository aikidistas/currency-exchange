# Currency Exchange console application

## Build information

Project build/run dependencies:``maven 3`` and ``java 8``

Build project with :
```
mvn package site
```

After successful build run Exchange console application:
```
java -jar target/currencyexchange-1.0.jar
```

After successful build open unit test + code coverage reports in your browser:
```
target/site/index.html
```

To check if project has at least 70% code coverage run:
```
mvn verify
``` 

## Requirements/Task below

Use: OOP, S.O.L.I.D., Clean Code(Uncle Bob), TDD

### **Domain**

The problem domain is a `FX Exchange`, where an `amount` in one `currency` is `exchanged` to another `amount` in another `currency`. 
This is commonly done using an `ISO currency pair`, e.g. `EUR/DKK`, where `EUR` is the `main currency` and `DKK` is `money currency`. 
Each `currency pair` is associated with an `exchange rate`, where 1 of the `main currency` can be exchanged to given `amount` in the `money currency`. 
For instance: `EUR/DKK 7,4394`, would denote that 1 EUR can be exchange with `7,4394 DKK`.


### **Task**

Using the exchange rates below, denoting the amount Danish kroner (DKK) required to purchase 100 in the mentioned currency,  
make a command line tool that is able to take a ISO currency pair and an  amount, and write the exchanged amount to the console

| Currency        | ISO   | Amount |
| ---             | ---   | ---: |
| Euro            | EUR	| 743,94 |
| American dollar | USD	| 663,11 |
| British pund    | GBP	| 852,85 |
| Swedish krona   | SEK	| 76,10 |
| Norwegian krone | NOK	| 78,40 |
| Swiss franc     | CHF	| 683,58 |
| Japanese yen    | JPY	| 5,9740 |


It is expected that a currency pair can contain any combination of the mentioned currencies (including DKK), e.g. EUR/USD. 
If a currency pair has the same ISO currency as both main and money, then the passed amount should be returned. 
If a currency pair contains an unknown currency, then an appropriate error message should be written to console.
It is expected that the code includes tests for the solution elements.
Please note, that the firewall will restrict executables to be sent via email. As such you should only send the source-code. 
We will build the application during the evaluation of your solution.


### **Requirement**

Please don’t integrate with any kind of databases. Use of filesystem is ok, but you are welcome to hardcode the exchange rates supplied.

### Unspecified Requirements interpretation

Solution is built with idea, that all rates and amounts will be rounded to four decimal fractional parts 
without considering possible different rules for specific currency/currency pair
