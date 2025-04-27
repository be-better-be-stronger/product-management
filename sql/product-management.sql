-- Tạo database
CREATE DATABASE IF NOT EXISTS product_management;

-- Sử dụng database
USE product_management;

-- Tạo bảng products
CREATE TABLE IF NOT EXISTS products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    quantity INT NOT NULL
);


INSERT INTO products (name, price, quantity) VALUES 
('Gạo thơm Jasmine', 18.5, 200),
('Gạo ST25', 22.0, 150),
('Đường cát trắng', 12.0, 300),
('Muối hột tự nhiên', 5.0, 500),
('Nước mắm Phú Quốc', 45.0, 100),
('Nước tương đậu nành', 20.0, 180),
('Dầu ăn hướng dương', 35.5, 250),
('Dầu oliu Extra Virgin', 120.0, 80),
('Trứng gà ta', 30.0, 400),
('Thịt heo ba rọi', 95.0, 120),
('Thịt gà ta nguyên con', 110.0, 90),
('Cá basa phi lê', 70.0, 150),
('Cá hồi tươi Nauy', 280.0, 50),
('Tôm sú tươi', 220.0, 70),
('Mực lá tươi', 180.0, 60),
('Bột mì đa dụng', 22.0, 200),
('Bột năng', 15.0, 180),
('Mì gói hảo hạng', 8.0, 600),
('Sữa tươi tiệt trùng', 25.0, 250),
('Sữa đặc có đường', 30.0, 300),
('Rau cải xanh', 12.0, 150),
('Rau xà lách', 15.0, 130),
('Cà chua đỏ', 18.0, 140),
('Dưa leo sạch', 14.0, 200),
('Ớt hiểm đỏ', 28.0, 80),
('Hành tím Lý Sơn', 38.0, 120),
('Tỏi cô đơn', 90.0, 70),
('Khoai lang mật', 22.0, 180),
('Chuối sứ chín', 20.0, 160),
('Xoài cát Hòa Lộc', 45.0, 90);

select* from products;

-- thêm sản phẩm
DELIMITER //

CREATE PROCEDURE proc_add_product(
    IN p_name VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT
)
BEGIN
    INSERT INTO products (name, price, quantity)
    VALUES (p_name, p_price, p_quantity);
END //

DELIMITER ;

-- lấy tất cả sản phẩm
DELIMITER //

CREATE PROCEDURE proc_get_all_products()
BEGIN
    SELECT * FROM products;
END //

DELIMITER ;



-- tìm sản phẩm bằng id
DELIMITER //

CREATE PROCEDURE proc_find_product_by_id(
    IN p_id INT
)
BEGIN
    SELECT * FROM products WHERE id = p_id;
END //

DELIMITER ;



-- tìm sản phẩm theo tên
DELIMITER //

CREATE PROCEDURE proc_find_products_by_name(
    IN p_keyword VARCHAR(255)
)
BEGIN
    SELECT * FROM products
    WHERE name LIKE CONCAT('%', p_keyword, '%');
END //

DELIMITER ;

-- cập nhật sản phẩm 
DELIMITER //

CREATE PROCEDURE proc_update_product(
    IN p_id INT,
    IN p_name VARCHAR(255),
    IN p_price DOUBLE,
    IN p_quantity INT
)
BEGIN
    UPDATE products
    SET name = p_name, price = p_price, quantity = p_quantity
    WHERE id = p_id;
END //

DELIMITER ;

-- xóa sản phẩm
DELIMITER //

CREATE PROCEDURE proc_delete_product(
    IN p_id INT
)
BEGIN
    DELETE FROM products WHERE id = p_id;
END //

DELIMITER ;

-- lấy sản phẩm phân trang
DELIMITER //

CREATE PROCEDURE proc_get_products_by_page(
    IN p_limit INT,
    IN p_offset INT
)
BEGIN
    SELECT * FROM products
    LIMIT p_limit OFFSET p_offset;
END //

DELIMITER ;

call proc_get_all_products;
call proc_find_product_by_id(5);
call proc_get_products_by_page(5, 0);






