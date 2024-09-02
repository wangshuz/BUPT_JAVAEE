-- 1. 用户表 (User)
CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    profile_picture VARCHAR(255)
);
-- 用户ID (user_id): 主键，唯一标识
-- 用户头像 (profile_picture): 用户的头像URL或路径
-- 用户名 (username): 候选关键字，标识用户的唯一名称
-- 密码 (password): 用户的密码，建议加密存储


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
-- 地址ID (address_id): 主键，唯一标识
-- 用户ID (user_id): 外键，关联用户表，表示该地址属于哪个用户
-- 收件人姓名 (recipient_name): 收货人姓名
-- 收件人电话 (recipient_phone): 收货人电话
-- 地址 (address_line): 地址信息
-- 是否默认地址 (is_default): 标记此地址是否为用户的默认地址
-- 是否删除标记 (is_deleted): 用于软删除标记，布尔值（是/否）


-- 3. 商家表 (Merchant)
CREATE TABLE Merchant (
    merchant_id INT AUTO_INCREMENT PRIMARY KEY,
    merchant_username VARCHAR(255) NOT NULL UNIQUE,
    merchant_password VARCHAR(255) NOT NULL,
    merchant_name VARCHAR(255) NOT NULL,
    merchant_avatar VARCHAR(255),
    merchant_address VARCHAR(255),
    phone_number VARCHAR(20),
    opening_hours VARCHAR(255),
    type_id INT,
    merchant_description TEXT,
    is_open BOOLEAN DEFAULT TRUE,
    delivery_fee DECIMAL(10, 2) NOT NULL,
    minimum_order_amount DECIMAL(10, 2) NOT NULL,
    packaging_fee_per_item DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (type_id) REFERENCES Merchant_Type(type_id)
);
-- 商家ID (merchant_id): 主键，唯一标识
-- 商家用户名 (merchant_username): 候选关键字，标识商家的唯一登录名
-- 商家密码 (merchant_password): 商家登录的密码，建议加密存储
-- 商家名称 (merchant_name): 商家的显示名称
-- 商家头像 (merchant_avatar): 存储商家头像的URL或路径
-- 商家地址 (merchant_address): 商家的物理地址
-- 电话号码 (phone_number): 商家的联系电话
-- 营业时间 (opening_hours): 商家的营业时间
-- 商家类型 (type_id): 外键，关联商家类别表，表示商家所属的类别
-- 商家简介 (merchant_description): 商家的简介或描述
-- 是否营业 (is_open): 标记商家是否在营业，布尔值（是/否）
-- 配送费 (delivery_fee): 商家的配送费用
-- 起送费（minimum_order_amount）：商家的最低起送费
-- 打包单价 (packaging_fee_per_item): 每件商品的打包费用


-- 4. 商家类别表 (Merchant_Type)
CREATE TABLE Merchant_Type (
    type_id INT AUTO_INCREMENT PRIMARY KEY,
    type_name VARCHAR(255) NOT NULL UNIQUE
);
-- 类型ID(type_id): 主键，唯一标识每种商家类型
-- 类型名称(type_name): 商家类型名称（例如餐饮、零售等），并且是唯一的。


-- 5. 商品表 (Product)
CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    merchant_id INT,
    category_id INT,
    product_name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description TEXT,
    available BOOLEAN DEFAULT TRUE,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (merchant_id) REFERENCES Merchant(merchant_id),
    FOREIGN KEY (category_id) REFERENCES Product_Category(category_id)
);
-- 商品ID (product_id): 主键，唯一标识
-- 商家ID (merchant_id): 外键，关联商家表
-- 类别ID(category_id): 外键，关联商品类别表，表示商品属于哪个类别
-- 商品名称 (product_name): 商品的名称
-- 价格 (price): 商品的价格
-- 描述 (description): 商品的描述
-- 是否可用 (available): 用于标记商品是否在售，布尔值（是/否）
-- 是否删除标记 (is_deleted): 用于软删除标记，布尔值（是/否）


-- 6. 商品类别表 (Product_Category)
CREATE TABLE Product_Category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    merchant_id INT,
    category_name VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (merchant_id) REFERENCES Merchant(merchant_id)
); 
-- 类别ID(category_id): 主键，唯一标识每个商品类别
-- 商家ID (merchant_id): 外键，关联商家表
-- 类别名称 (category_name): 商品类别的名称（例如：饮料、小吃、主食等）
-- 是否删除标记 (is_deleted): 用于软删除标记，布尔值（是/否）


-- 7. 订单表 (Orders)
CREATE TABLE Orders (
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
-- 订单ID (order_id): 主键，唯一标识
-- 商家ID (merchant_id): 外键，关联商家表
-- 用户ID (user_id): 外键，关联用户表
-- 地址ID (address_id): 外键，关联地址表，用于记录订单的配送地址
-- 下单日期 (order_date): 记录订单的创建时间
-- 预计送达时间 (estimated_delivery_time): 订单预计的送达时间
-- 实际送达时间 (actual_delivery_time): 订单实际的送达时间
-- 支付方式 (payment_method): 支付的方式，例如信用卡、微信支付等
-- 打包费 (packaging_fee): 订单中的打包费用总计
-- 配送费 (delivery_fee): 订单的配送费用
-- 订单状态 (order_status): 记录订单的当前状态，比如“已下单”、“正在配送”、“已完成”
-- 备注 (notes): 用户在下单时附加的备注信息
-- 用户是否删除 (is_deleted_by_user): 用户是否标记删除，布尔值（是/否）
-- 商家是否删除 (is_deleted_by_merchant): 商家是否标记删除，布尔值（是/否）
-- 总金额 (total_amount): 订单的总金额，包含所有商品的总价和其他费用（如配送费）


-- 8. 订单明细表 (Order_Item)
CREATE TABLE Order_Item (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_id INT,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);
-- 明细ID (order_item_id): 主键，唯一标识
-- 订单ID (order_id): 外键，关联订单表
-- 商品ID (product_id): 外键，关联商品表
-- 数量 (quantity): 该商品在订单中的数量