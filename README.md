# Stockman API

## Description

The Stocks API is an API for managing stocks and positions in a portfolio. It provides endpoints to add new stocks, add new positions, and retrieve position performance data for specific stock symbols.

## Endpoints

### Add New Stock

- **Summary:** Adds a new stock
- **HTTP Method:** POST
- **Path:** `/stocks`
- **Operation ID:** addStock
- **Request Body:**
    - Content Type: application/json
    - Schema: [Stock](#stock)
- **Responses:**
    - `201`: Created new stock
        - Content Type: application/json
        - Schema: [Stock](#stock)

### Add New Position

- **Summary:** Adds a new position
- **HTTP Method:** POST
- **Path:** `/positions`
- **Operation ID:** addPosition
- **Request Body:**
    - Content Type: application/json
    - Schema: [Position](#position)
- **Responses:**
    - `201`: Created new position
        - Content Type: application/json
        - Schema: [Position](#position)

### Get Position Performance Data

- **Summary:** Get position performance data
- **HTTP Method:** GET
- **Path:** `/positionPerformance`
- **Parameters:**
    - `symbol`: Stock symbol (e.g., AAPL for Apple Inc.)
        - In: query
        - Type: string
        - Required: true
- **Responses:**
    - `200`: Successful response
        - Content Type: application/json
        - Schema: [PositionPerformance](#positionperformance)
    - `400`: Bad request
    - `500`: Internal server error

## Schemas

### Stock

- **Type:** object
- **Properties:**
    - `symbol`: The stock symbol
        - Type: string
    - `name`: The name of the stock
        - Type: string

### Position

- **Type:** object
- **Properties:**
    - `quantity`: Number of shares
        - Type: integer
    - `price`: Purchase price of position
        - Type: number
        - Format: double
    - `symbol`: Stock Symbol
        - Type: string
    - `portfolioId`: The ID for the portfolio to add the position to
        - Type: integer

### PositionPerformance

- **Type:** object
- **Properties:**
    - `performance`: Performance in percent
        - Type: double
