# Personal Finance Micro-Tracker: Backend

- **Student:** TETA Allen
- **Student ID:** 27409
- **Project:** Personal Finance Micro-Tracker
- **Course:** INSY 8322 Web Technology and Internet, AUCA, Mid-Term Project 2026-2027 (1)
- **Lecturer:** Patrick DUSHIMIMANA

REST API (Spring Boot, Spring Data JPA, PostgreSQL) that stores transactions from raw bank or mobile-money lines, links merchants to spending categories and merchant codes, and records receipt lines for reviewed transactions.

## Stack

Java 17, Spring Boot, Spring Web, Spring Data JPA, PostgreSQL 17, Maven wrapper.

## Run the API

1. Create the database: `psql -U postgres -c "CREATE DATABASE finance_tracker;"`
2. Set the connection variables (PowerShell, use your own password): `$env:DB_URL = "jdbc:postgresql://localhost:5432/finance_tracker"`, `$env:DB_USER = "postgres"`, `$env:DB_PASSWORD = "your password"`
3. Start the API with `.\mvnw spring-boot:run` (on Linux or macOS: `./mvnw spring-boot:run` with `export` for the variables). Tables are created on first start (`ddl-auto=update`).
4. Optional: to load the seed data into a fresh database, start the API once so the tables exist, stop it, then run `psql -U postgres -d finance_tracker -f seed-data.sql`.

The API listens on http://localhost:8080.

## Postman

Import `personal-finance-micro-tracker-midterm.postman_collection.json`. Fill the collection variables with ids taken from the `GET /all` endpoints. There is one request per requirement, named by its ID, plus error cases such as `D2 error 400`.

## Requirements and endpoints

| ID | Endpoint |
|----|----------|
| A2 | `POST /api/{path}/save`, `GET /api/{path}/all`, `GET /api/{path}/{id}`, `PUT /api/{path}/update/{id}`, `DELETE /api/{path}/delete/{id}` for spending-categories, merchant-codes, merchants, account-holders, transactions |
| B1 | `GET /api/account-holders/by-last-name?lastName=` |
| B2 | `GET /api/transactions/by-status?status=` |
| B3 | `GET /api/transactions/between?start=&end=` |
| C1 | `GET /api/merchants/by-spending-category?name=` |
| C2 | `GET /api/merchant-codes/free` |
| C3 | `GET /api/spending-categories/unused` |
| C4 | `GET /api/account-holders/of-merchant/{merchantId}` |
| D1 | `POST /api/transactions/save` (past date 400, same merchant same day 409, always IMPORTED) |
| D2 | `PATCH /api/transactions/{id}/status?status=` |
| D3 | `PUT /api/merchants/{merchantId}/spending-categories/{spendingCategoryId}` |
| D4 | `PATCH /api/merchants/{merchantId}/merchant-code?codeNumber=` |
| E1 | `POST /api/receipt-lines/save?transactionId=` |
| E2 | `GET /api/receipt-lines/account-holder/{accountNumber}` |
| E3 | `DELETE /api/receipt-lines/delete/{id}` and `DELETE /api/transactions/delete/{id}` (409 when receipt lines exist) |
| F1 | `GET /api/transactions/stats/by-status` |
| F2 | `PATCH /api/transactions/cancel-day?merchantId=&date=` |
| Bonus | `GET /api/dashboard/summary` and `GET /api/transactions/page?page=&size=&sort=` |

## Seed data note

The seed data was entered through the save endpoints before rule D1 (no past dates) was added, because the seed transactions are dated October and November 2026. The file `seed-data.sql` restores the same data.
