
-- Users Table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    role VARCHAR(20) NOT NULL
);

--Memberships Table
CREATE TABLE memberships (
    id SERIAL PRIMARY KEY,
    membership_type VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    member_id INT REFERENCES users(id) ON DELETE CASCADE,
    purchase_date DATE DEFAULT CURRENT_DATE
);

--Workout Classes Table
CREATE TABLE workout_classes (
    id SERIAL PRIMARY KEY,
    class_name VARCHAR(100) NOT NULL,
    description TEXT,
    trainer_id INT REFERENCES users(id) ON DELETE SET NULL,
    schedule VARCHAR(100)
);

--Gym Merch Table
CREATE TABLE gym_merch (
    id SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    product_type VARCHAR(50),
    price DECIMAL(10,2) NOT NULL,
    stock_level INT DEFAULT 0
);