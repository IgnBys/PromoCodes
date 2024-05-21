# **PromoCodes**

**PromoCodes** is an application to manage discount codes for sales or promotions 

# **How to use PromoCodes application?**

## Step 1.

Run file PromoCodesApplication.

## Step 2.

Use an HTTP client to test the API (for example Postman).

## Step 3.

The initial URL is http://localhost:8080. 

## Step 4.

For further HTTP requests, add the necessary commands to the initial URL address.

# **HTTP requests**

## Product

> ##### To work with products, you can use a data structure in JSON format:
> {
> "name": "Blueberries",
> "price": 50.99,
> "currency": "PLN",
> "description": "A kilo of blueberries"
> }

_(Method POST)_

To add a product enter **/api/products**.

To apply a promo code to a product, enter **/api/products/{id}/{promocode}** ({id} - product identifier, {promocode} - promo code name).

_(Method PUT)_

To change product parameters enter **/api/products/{id}**.

_(Method GET)_

To get all existing products enter **/api/products**.

To get a particular product enter **/api/products/{id}**.

## Promo code

> ##### To work with promo codes, you can use a data structure in JSON format:
> {
> "name": "SUMMER2024",
> "discountAmount": 25.55,
> "expirationDate": "2024-07-01T11:06:18",
> "currency": "PLN",
> "maxUsages": 3
> }

_(Method POST)_

To add a promo code enter **/api/promocodes**.

_(Method GET)_

To get all existing promo codes enter **/api/promocodes**.

To get a particular promo code enter **/api/promocodes/{promocode}** ({promocode} - promo code name).

## Purchase

_(Method POST)_

To simulate a purchase with using promo code enter **/api/purchase/{id}/{promocode}** ({id} - product identifier, {promocode} - promo code name).

To simulate a purchase without using promo code enter **/api/purchase/{id}**.


_(Method GET)_

To get all existing purchases enter **/api/purchase**.
