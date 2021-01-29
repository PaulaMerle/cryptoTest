# CryptoTest
is written using Vue 3 in client-side, Java in server-side and PostgreSQL as database. App is published in Heroku and deployed via GitHub repo.

App can be found from: https://crypto-shelf.herokuapp.com/

# Description of the project:

## Scenario: Add a new portfolio item

- The system shows a list of previously saved portfolio items and input form to enter a new portfolio item.
- Cryptocurrency type: select box to choose from of Bitcoin, Ethereum and Ripple.
- The system saves the protfolio item to the relational database and shows the following table on the bottom of the same page. The Current market value is calculated by the current market price. Current price is queried from bitfinex API and calculated by the backend.

## Scenario: Delete item from portfolio

- The system shows a list of previously saved portfolio items and input form to enter a new portfolio item.
- User clicks Delete on crypto line
- The system prompts to confirm the deletion of the item
- User confirms
- The system removes the item from the list
- The system shows the renewed table

## Non functional
- Frontend layer uses JavaScript framework (Vue) and communicates with backend by calling JSON rest services
- Backend system is written in Java using Spring Boot framework
- Database to store the portfolio data is PostgreSQL
- Backend system serves all the services via JSON microservice
- Source code is publicly available via github
- Working code is deployed to cloud (Heroku)
