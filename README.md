# CryptoTest 
is written using Vue 3 in client-side, Java in server-side and PostgreSQL as database. App is published in Heroku and deployed via GitHub repo. 

App can be found from: https://mindly-crypto-test.herokuapp.com/

# Description of the project:

## Functional requirements
### Scenario: Add a new portfolio item
I made a purchase of 10 bitcoins on 20.06.2018

- As a user i open portfolio management webpage
- The system shows me a list of previously saved portfolio items and input form to enter a new portfolio item. Initially the list of portfolio items is empty with newly installed system and database
- I must be able to enter the following data: - Cryptocurrency type. Select box to choose from of Bitcoin, Ethereum and Ripple. I choose Bitcoin - Amount of coins the user holds. I enter 10 - Wallet location. I write text: My personal hardware wallet - Date of purchase. Default date is today. I write 20.06.2018
- I click button Save
- The system saves the protfolio item to the relational database and shows the following table on the bottom of the same page. The Current market value is calculated by the current market price. Current price is queried from bitfinex API and calculated by the backend.

### Scenario: Delete item from portfolio
I sold my cryptocurrency and no longer need to see it on my list

- As a user i open portfolio management webpage
- The system shows me a list of previously saved portfolio items and input form to enter a new portfolio item. My portfolio has the following items saved:

| Attempt | Cryptocurrency |	Amount |	Date of purchase |	Wallet location |	Current market value (EUR) |	Option |
|---------|:---------------:|:-------:|:-----------------:|:---------------:|:--------------------------:|:-------:|
| Seconds | Bitcoin |	10 |	20.06.2018 | My personal hardware wallet |	62 314 |	Delete |
| Seconds | Ethereum |	5 |	21.06.2018 |	My personal walle |	2300 |	Delete |
- As a user i click Delete on Ethereum line
- The system prompts me to confirm that i am sure to delete the new item
- As a user i confirm
- The system removes the item from the list
- The system shows me the following table

| Attempt | Cryptocurrency |	Amount |	Date of purchase |	Wallet location |	Current market value (EUR) |	Option |
|---------|:---------------:|:-------:|:-----------------:|:---------------:|:--------------------------:|:-------:|
| Seconds | Bitcoin |	10 |	20.06.2018 | My personal hardware wallet |	62 314 |	Delete |
## Non functional requirements
- Frontend layer uses JavaScript framework (Vue, AngularJS, React, etc...) and communicates with backend by calling JSON rest services
- Backend system is written in Java using Spring Boot framework or Ruby with Ruby on Rails
- Database to store the portfolio data is PostgreSQL
- Backend system serves all the services via JSON microservice
- Source code is publicly available via bitbucket or github
- Working code is deployed to cloud (Google Cloud, AWS, Heroku etc...)
