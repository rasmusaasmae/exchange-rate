# Currency Exchance

This project implements a frontend and a backend for an exchange rate API.

## Technologies

Frontend - NextJS, React, TypeScript

Backend - Spring Boot, Java

## Features

- [x] Exchange rates from the [European Central Bank](https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html)

- [x] Currency conversion API. Inputs: amount, starting currency, target currency. Output: amount in target currency.

- [x] API to get TOP 5 currencies with the highest growth in the past 10 days.

- [ ] API to get TOP 5 currencies with the highest growth and decline over a 10-day period in the last 90 days.

- [ ] Exchange rates automatically obtained every day using quartz.

- [x] Using H2 database for data storage.

- [x] Initially, if no rates yet loaded - populate rates for last 90 days.

## Testing

Due to time constrainst no tests were implemented.

## Running the project

There are run scripts for both frontend and backend in the `scripts` folder.

Frontend requires the api url to be provided via an environment variable. There is an example environment file given in `frontend/.env.example`. You can make a copy of this to `frontend/.env`.
