-- =============================================
-- Complete Database Setup Script for De13_23110236
-- Sinh vien: Nguyen Phuoc Khang - MSSV: 23110236
-- Ma de: De13_23110236
-- Ho tro tieng Viet day du voi Vietnamese_CI_AS collation
-- =============================================

-- Xoa database cu neu ton tai
IF EXISTS (SELECT name FROM sys.databases WHERE name = 'De13_23110236')
BEGIN
    ALTER DATABASE De13_23110236 SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE De13_23110236;
END
GO

-- Tao database moi voi ho tro tieng Viet
CREATE DATABASE De13_23110236 COLLATE Vietnamese_CI_AS;
GO

-- Su dung database
USE De13_23110236;
GO

-- =============================================
-- Tao cac bang voi collation tieng Viet
-- =============================================

-- 1. Bang UserRoles
CREATE TABLE UserRoles (
    roleId INT IDENTITY(1,1) PRIMARY KEY,
    roleName NVARCHAR(50) COLLATE Vietnamese_CI_AS NOT NULL
);
GO

-- 2. Bang Seller
CREATE TABLE Seller (
    sellerId INT IDENTITY(1,1) PRIMARY KEY,
    sellerName NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL,
    images NVARCHAR(255),
    status INT DEFAULT 1
);
GO

-- 3. Bang Category
CREATE TABLE Category (
    categoryId INT IDENTITY(1,1) PRIMARY KEY,
    categoryName NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL,
    images NVARCHAR(255),
    status INT DEFAULT 1
);
GO

-- 4. Bang Users
CREATE TABLE Users (
    userId INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) COLLATE Vietnamese_CI_AS NOT NULL UNIQUE,
    email NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL UNIQUE,
    fullName NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL,
    password NVARCHAR(100) COLLATE Vietnamese_CI_AS NOT NULL,
    images NVARCHAR(255),
    phone NVARCHAR(20),
    status INT DEFAULT 1,
    code NVARCHAR(50),
    roleId INT NOT NULL,
    sellerId INT,
    FOREIGN KEY (roleId) REFERENCES UserRoles(roleId),
    FOREIGN KEY (sellerId) REFERENCES Seller(sellerId)
);
GO

-- 5. Bang Product
CREATE TABLE Product (
    productId INT IDENTITY(1,1) PRIMARY KEY,
    productName NVARCHAR(200) COLLATE Vietnamese_CI_AS NOT NULL,
    productCode BIGINT NOT NULL,
    categoryId INT NOT NULL,
    description NTEXT COLLATE Vietnamese_CI_AS,
    price FLOAT NOT NULL,
    amount INT NOT NULL DEFAULT 0,
    stock INT NOT NULL DEFAULT 0,
    images NVARCHAR(255),
    wishlist INT DEFAULT 0,
    status INT NOT NULL DEFAULT 1,
    createDate DATE DEFAULT GETDATE(),
    sellerId INT NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES Category(categoryId),
    FOREIGN KEY (sellerId) REFERENCES Seller(sellerId)
);
GO

-- 6. Bang Cart
CREATE TABLE Cart (
    cartId NVARCHAR(50) PRIMARY KEY,
    userId INT NOT NULL,
    buyDate DATETIME DEFAULT GETDATE(),
    status INT DEFAULT 1,
    FOREIGN KEY (userId) REFERENCES Users(userId)
);
GO

-- 7. Bang CartItem
CREATE TABLE CartItem (
    cartItemId NVARCHAR(50) PRIMARY KEY,
    quantity INT NOT NULL DEFAULT 1,
    unitPrice FLOAT NOT NULL,
    productId INT NOT NULL,
    cartId NVARCHAR(50) NOT NULL,
    FOREIGN KEY (productId) REFERENCES Product(productId),
    FOREIGN KEY (cartId) REFERENCES Cart(cartId)
);
GO

-- =============================================
-- Them du lieu mau voi tieng Viet dung
-- =============================================

-- Them UserRoles
INSERT INTO UserRoles (roleName) VALUES 
(N'ADMIN'),
(N'SELLER'),
(N'USER');
GO

-- Them Seller
INSERT INTO Seller (sellerName, images, status) VALUES 
(N'Cua hang thoi trang ABC', 'https://via.placeholder.com/300x200?text=Fashion+Store', 1),
(N'Sieu thi dien may XYZ', 'https://via.placeholder.com/300x200?text=Electronics+Store', 1),
(N'Cua hang do gia dung DEF', 'https://via.placeholder.com/300x200?text=Home+Store', 1);
GO

-- Them Category
INSERT INTO Category (categoryName, images, status) VALUES 
(N'Thoi trang', 'https://via.placeholder.com/300x200?text=Fashion', 1),
(N'Do gia dung', 'https://via.placeholder.com/300x200?text=Home+Appliances', 1),
(N'Dien tu', 'https://via.placeholder.com/300x200?text=Electronics', 1),
(N'The thao', 'https://via.placeholder.com/300x200?text=Sports', 1);
GO

-- Them Users
INSERT INTO Users (username, email, fullName, password, images, phone, status, roleId, sellerId) VALUES 
('admin1', 'admin@example.com', 'Nguyen Van Admin', '123456', 'https://via.placeholder.com/100x100?text=Admin', '0123456789', 1, 1, NULL),
('seller1', 'seller1@example.com', 'Tran Thi Seller', '123456', 'https://via.placeholder.com/100x100?text=Seller', '0123456788', 1, 2, 1),
('seller2', 'seller2@example.com', 'Le Van Seller', '123456', 'https://via.placeholder.com/100x100?text=Seller2', '0123456787', 1, 2, 2),
('user1', 'user1@example.com', 'Pham Thi User', '123456', 'https://via.placeholder.com/100x100?text=User', '0123456786', 1, 3, NULL),
('user2', 'user2@example.com', 'Hoang Van User', '123456', 'https://via.placeholder.com/100x100?text=User2', '0123456785', 1, 3, NULL);
GO

-- Them Product
INSERT INTO Product (productName, productCode, categoryId, description, price, amount, stock, images, wishlist, status, sellerId) VALUES 
(N'Quan jean nu cao cap', 123456791, 1, N'Quan jean nu chat luong cao, thiet ke hien dai, phu hop voi moi phong cach', 299000, 50, 25, 'https://via.placeholder.com/300x300?text=Jeans+Women', 12, 1, 1),
(N'May loc nuoc RO thong minh', 123456792, 2, N'May loc nuoc RO cong nghe tien tien, tiet kiem dien, an toan cho suc khoe', 2500000, 30, 15, 'https://via.placeholder.com/300x300?text=RO+Water+Purifier', 8, 1, 2),
(N'Bo noi inox cao cap 5 mon', 123456793, 2, N'Bo noi inox khong gi, ben dep va an toan, phu hop cho gia dinh', 1200000, 20, 10, 'https://via.placeholder.com/300x300?text=Stainless+Steel+Pots', 15, 1, 2),
(N'Ao thun nam cotton', 123456794, 1, N'Ao thun nam chat lieu cotton 100%, thoang mat, de giat', 150000, 100, 50, 'https://via.placeholder.com/300x300?text=Cotton+T-Shirt', 25, 1, 1),
(N'Dien thoai smartphone', 123456795, 3, N'Dien thoai thong minh voi camera chuyen nghiep, pin trau', 8000000, 15, 8, 'https://via.placeholder.com/300x300?text=Smartphone', 30, 1, 2),
(N'Giay the thao nam', 123456796, 4, N'Giay the thao nam em chan, chong truot, phu hop tap luyen', 800000, 40, 20, 'https://via.placeholder.com/300x300?text=Sports+Shoes', 18, 1, 1),
(N'Vay dam nu cong so', 123456797, 1, N'Vay dam nu cong so thanh lich, chat lieu cao cap', 450000, 25, 12, 'https://via.placeholder.com/300x300?text=Office+Dress', 22, 1, 1),
(N'May giat long ngang', 123456798, 2, N'May giat long ngang tiet kiem dien, nhieu che do giat', 12000000, 10, 5, 'https://via.placeholder.com/300x300?text=Washing+Machine', 35, 1, 2),
(N'Tai nghe khong day', 123456799, 3, N'Tai nghe khong day chat luong cao, am thanh song dong', 1200000, 60, 30, 'https://via.placeholder.com/300x300?text=Wireless+Headphones', 28, 1, 2),
(N'Balo du lich', 123456800, 4, N'Balo du lich chong nuoc, nhieu ngan, tien loi', 350000, 35, 18, 'https://via.placeholder.com/300x300?text=Travel+Backpack', 20, 1, 1);
GO

-- Them Cart mau
INSERT INTO Cart (cartId, userId, buyDate, status) VALUES 
(N'CART001', 4, GETDATE(), 1),
(N'CART002', 5, GETDATE(), 1);
GO

-- Them CartItem mau
INSERT INTO CartItem (cartItemId, quantity, unitPrice, productId, cartId) VALUES 
(N'ITEM001', 2, 299000, 1, N'CART001'),
(N'ITEM002', 1, 150000, 4, N'CART001'),
(N'ITEM003', 1, 800000, 6, N'CART002');
GO

-- =============================================
-- Tao Index de toi uu hieu suat
-- =============================================

CREATE INDEX IX_Users_Username ON Users(username);
CREATE INDEX IX_Users_Email ON Users(email);
CREATE INDEX IX_Product_SellerId ON Product(sellerId);
CREATE INDEX IX_Product_CategoryId ON Product(categoryId);
CREATE INDEX IX_CartItem_CartId ON CartItem(cartId);
CREATE INDEX IX_CartItem_ProductId ON CartItem(productId);
GO


