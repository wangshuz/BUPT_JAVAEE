-- 1. 用户表 (User)
CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile_picture VARCHAR(255)
);

-- 2. 地址表 (Address)
CREATE TABLE Address (
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    recipient_name VARCHAR(255) NOT NULL,
    recipient_phone VARCHAR(20) NOT NULL,
    address_line VARCHAR(255) NOT NULL,
    is_default BOOLEAN DEFAULT FALSE,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES User(user_id)
);

-- 3. 商家表 (Merchant)
CREATE TABLE Merchant (
    merchant_id INT AUTO_INCREMENT PRIMARY KEY,
    merchant_username VARCHAR(255) NOT NULL UNIQUE,
    merchant_password VARCHAR(255) NOT NULL,
    merchant_name VARCHAR(255) NOT NULL,
    merchant_address VARCHAR(255),
    phone_number VARCHAR(20),
    opening_hours VARCHAR(255),
    business_type VARCHAR(255),
    merchant_description TEXT,
    is_open BOOLEAN DEFAULT TRUE,
    delivery_fee DECIMAL(10, 2) NOT NULL,
    minimum_order_amount DECIMAL(10, 2) NOT NULL,
    packaging_fee_per_item DECIMAL(10, 2) NOT NULL
);

-- 4. 商品表 (Product)
CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    merchant_id INT,
    product_name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    available BOOLEAN DEFAULT TRUE,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (merchant_id) REFERENCES Merchant(merchant_id)
);

-- 5. 订单表 (Order)
CREATE TABLE `Order` (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    merchant_id INT,
    user_id INT,
    address_id INT,
    order_date DATETIME NOT NULL,
    estimated_delivery_time DATETIME,
    actual_delivery_time DATETIME,
    payment_method VARCHAR(50) NOT NULL,
    packaging_fee DECIMAL(10, 2) NOT NULL,
    delivery_fee DECIMAL(10, 2) NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    notes TEXT,
    is_deleted_by_user BOOLEAN DEFAULT FALSE,
    is_deleted_by_merchant BOOLEAN DEFAULT FALSE,
    total_amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (merchant_id) REFERENCES Merchant(merchant_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (address_id) REFERENCES Address(address_id)
);

-- 6. 订单明细表 (Order_Item)
CREATE TABLE Order_Item (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES `Order`(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);