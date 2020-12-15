--CREATE LOGIN Restaurant_User WITH PASSWORD='qwerty'
-- USE RestaurantApp
-- CREATE USER Restaurant_User

CREATE TABLE users
(
    id INT PRIMARY KEY,
    username VARCHAR(20),
    password VARCHAR(20),
    email VARCHAR(30),
    phone VARCHAR(20)
)

CREATE TABLE order_table
(
    id INT PRIMARY KEY,
    [order] VARCHAR(100),
    [user_id] INT,
)

CREATE TABLE orders_foods
(
    id INT PRIMARY KEY,
    [order_id] VARCHAR(100),
    [food_id] INT,
)

