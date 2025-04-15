
DROP TABLE IF EXISTS supplier;

CREATE TABLE IF NOT EXISTS supplier(
    `mancc` VARCHAR(20) NOT NULL,
    `tencc` varchar(25) NOT NULL,
    `diachi` varchar(100) NOT NULL,
    `dienthoai` VARCHAR(11) NOT NULL,
     `sofax` VARCHAR(11) not null
);

DROP TABLE IF EXISTS entry_form;
CREATE Table IF NOT EXISTS entry_form(
    `maphieunhap` VARCHAR(20) NOT NULL,
    `ngaynhap` DATETIME(6),
    `mancc` VARCHAR(20) NOT NULL,
    `maaccount` VARCHAR(20) NOT NULL
);

DROP TABLE IF EXISTS staff;
CREATE TABLE IF NOT EXISTS staff(
    `mastaff` VARCHAR(20) NOT NULL,
    `address` varchar(100),
    `tennhanstaff` varchar(50) NOT NULL,
    `dienthoai` VARCHAR(11) NOT NULL
);INSERT INTO staff (mastaff, address, tennhanstaff, dienthoai)
VALUES 
('STF001', '123 Main St', 'Admin', '0123456789'),
('STF002', '456 Elm St', 'John Doe', '0987654321'),
('STF003', '789 Oak St', 'Jane Smith', '0112233445');
-- Insert dữ liệu cho bảng supplier
INSERT INTO supplier (mancc, tencc, diachi, dienthoai, sofax)
VALUES 
('SUP001', 'Nhà cung cấp A', '123 Đường A', '0123456789', '0123456789'),
('SUP002', 'Nhà cung cấp B', '456 Đường B', '0987654321', '0987654321'),
('SUP003', 'Nhà cung cấp C', '789 Đường C', '0112233445', '0112233445');

-- Insert dữ liệu cho bảng entry_form
INSERT INTO entry_form (maphieunhap, ngaynhap, mancc, maaccount)
VALUES 
('EF001', '2025-04-15 10:00:00', 'SUP001', 'ACC001'),
('EF002', '2025-04-15 11:00:00', 'SUP002', 'ACC002'),
('EF003', '2025-04-15 12:00:00', 'SUP003', 'ACC003');


-- Insert dữ liệu cho bảng product

-- Insert dữ liệu cho bảng producttype








-- Insert dữ liệu cho bảng bill_product


DROP TABLE IF EXISTS account;
CREATE Table if not exists account(
    `maaccount` VARCHAR(20) NOT NULL,
    `mastaff` VARCHAR(20) NOT NULL,
    `username` varchar(50) NOT NULL,
    `password` varchar(255) NOT NULL,
    `powergroupid` VARCHAR(20),
    `email` VARCHAR(255)
);INSERT INTO account (maaccount, mastaff, username, password, powergroupid, email)
VALUES 
('ACC001', 'STF001', 'admin', '123456', 'PG01', 'admin@example.com'),
('ACC002', 'STF002', 'john_doe', 'password123', 'PG02', 'john.doe@example.com'),
('ACC003', 'STF003', 'jane_smith', 'securepass', 'PG02', 'jane.smith@example.com');


DROP TABLE IF EXISTS powergroup;
CREATE Table if not exists powergroup(
    `powergroupid` VARCHAR(20) NOT NULL,
    `powergroupname` varchar(50) NOT NULL
);INSERT INTO powergroup (powergroupid, powergroupname)
VALUES 
('PG01', 'Admin'),
('PG02', 'User'),
('PG03', 'Guest');

DROP TABLE IF EXISTS powergroup_func;
CREATE Table if not exists powergroup_func(
    `powergroupid` VARCHAR(20) NOT NULL,
    `funcid` VARCHAR(20) NOT NULL
    );-- Insert dữ liệu cho bảng powergroup_func
INSERT INTO powergroup_func (powergroupid, funcid)
VALUES 
('PG01', 'FUNC001'),
('PG01', 'FUNC002'),
('PG02', 'FUNC003'),
('PG03', 'FUNC004');


DROP TABLE IF EXISTS func;
CREATE Table if not exists func(
    `funcid` VARCHAR(20) NOT NULL,
    `funcname` varchar(50) NOT NULL
);INSERT INTO func (funcid, funcname)
VALUES 
('FUNC001', 'Quản lý sản phẩm'),
('FUNC002', 'Quản lý nhân viên'),
('FUNC003', 'Quản lý khách hàng'),
('FUNC004', 'Xem báo cáo');


DROP TABLE IF EXISTS detail_entry_form;
CREATE Table if NOT exists detail_entry_form(
    `maphieunhap` VARCHAR(20) NOT NULL,
    `masp` VARCHAR(20) NOT NULL,
    `dongianhap` int(11) NOT NULL,
    `ngayhethan` DATETIME(6) not NULL,
    `soluongnhap` int(11) NOT NULL
);-- Insert dữ liệu cho bảng detail_entry_form
INSERT INTO detail_entry_form (maphieunhap, masp, dongianhap, ngayhethan, soluongnhap)
VALUES 
('EF001', 'PRO001', 10000, '2025-12-31 23:59:59', 50),
('EF002', 'PRO002', 20000, '2025-12-31 23:59:59', 30),
('EF003', 'PRO003', 15000, '2025-12-31 23:59:59', 20);

DROP TABLE IF EXISTS product;
CREATE Table IF NOT EXISTS product(
    `masp` VARCHAR(20) NOT NULL,
    `tensp` VARCHAR(50) NOT NULL,
    `soluong` INT(11) NOT NULL,
    `dongiasanpham` int(11) DEFAULT NULL,
    `maloaisp` VARCHAR(20) NOT NULL,
    `mancc` VARCHAR(20) NOT NULL,
    `img` VARCHAR(100) NOT NULL DEFAULT "img/Blank.png"
);INSERT INTO product (masp, tensp, soluong, dongiasanpham, maloaisp, mancc, img)
VALUES 
('PRO001', 'Sản phẩm A', 100, 15000, 'TYP001', 'SUP001', 'img/productA.png'),
('PRO002', 'Sản phẩm B', 200, 25000, 'TYP002', 'SUP002', 'img/productB.png'),
('PRO003', 'Sản phẩm C', 300, 35000, 'TYP003', 'SUP003', 'img/productC.png');

-- Weak Entity

DROP TABLE IF EXISTS `remove_product`;
CREATE TABLE IF NOT EXISTS `remove_product` (
    `masp` VARCHAR(20) NOT NULL,
    `soluong` int(11) NOT NULL,
    `ngayxoa` DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    `lido` VARCHAR(255) NOT NULL
);INSERT INTO remove_product (masp, soluong, ngayxoa, lido)
VALUES 
('PRO001', 10, '2025-04-15 17:00:00', 'Hết hạn sử dụng'),
('PRO002', 5, '2025-04-15 18:00:00', 'Hàng lỗi');

DROP TABLE IF EXISTS comment;
CREATE Table if not exists comment(
    `macomment` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `noidung` TEXT NOT NULL,
    `ngaydang` DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    `masp` VARCHAR(20) NOT NULL,
    `mauser` VARCHAR(20) NOT NULL
);INSERT INTO comment (noidung, ngaydang, masp, mauser)
VALUES 
('Sản phẩm rất tốt!', '2025-04-15 19:00:00', 'PRO001', 'CUS001'),
('Giá cả hợp lý.', '2025-04-15 20:00:00', 'PRO002', 'CUS002'),
('Chất lượng tuyệt vời.', '2025-04-15 21:00:00', 'PRO003', 'CUS003');


DROP TABLE IF EXISTS producttype;
CREATE TABLE if not exists producttype(
    `maloaisp` VARCHAR(20) NOT NULL,
    `tenloaisp` varchar(50) NOT NULL
);INSERT INTO producttype (maloaisp, tenloaisp)
VALUES 
('TYP001', 'Loại sản phẩm A'),
('TYP002', 'Loại sản phẩm B'),
('TYP003', 'Loại sản phẩm C');

DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT exists customer(
    `macustomer` VARCHAR(20) NOT NULL,
    `address` VARCHAR(100),
    `phone` VARCHAR(11),
    `name` VARCHAR(25)
);INSERT INTO customer (macustomer, address, phone, name)
VALUES 
('CUS001', '123 Đường A', '0123456789', 'Nguyễn Văn A'),
('CUS002', '456 Đường B', '0987654321', 'Trần Thị B'),
('CUS003', '789 Đường C', '0112233445', 'Lê Văn C');

DROP TABLE IF EXISTS bill_product;
CREATE TABLE IF NOT EXISTS bill_product(
    `mabill` VARCHAR(20) NOT NULL,
    `masp` VARCHAR(20) NOT NULL,
    `soluong` int(11) NOT NULL
    );INSERT INTO bill_product (mabill, masp, soluong)
VALUES 
('BILL001', 'PRO001', 2),
('BILL002', 'PRO002', 3),
('BILL003', 'PRO003', 1);


DROP TABLE IF EXISTS bill;
create TABLE IF NOT EXISTS bill(
    `mabill` VARCHAR(20) NOT NULL,
    `macustomer` VARCHAR(20) NOT NULL,
    `mapayby` VARCHAR(20) NOT NULL,
    `ngaymua` DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    `tongtien` int(11) DEFAULT 0
);INSERT INTO bill (mabill, macustomer, mapayby, ngaymua, tongtien)
VALUES 
('BILL001', 'CUS001', 'PAY001', '2025-04-15 14:00:00', 50000),
('BILL002', 'CUS002', 'PAY002', '2025-04-15 15:00:00', 75000),
('BILL003', 'CUS003', 'PAY003', '2025-04-15 16:00:00', 100000);

DROP TABLE IF EXISTS payby;
CREATE Table IF NOT EXISTS payby(
    `mapayby` VARCHAR(20) NOT NULL,
    `paybyname` varchar(50) NOT NULL,
    `address` varchar(100) NOT NULL,
    `details` JSON NOT NULL
);INSERT INTO payby (mapayby, paybyname, address, details)
VALUES 
('PAY001', 'Thanh toán tiền mặt', '123 Đường A', '{"method": "cash"}'),
('PAY002', 'Thanh toán thẻ', '456 Đường B', '{"method": "card"}'),
('PAY003', 'Thanh toán chuyển khoản', '789 Đường C', '{"method": "bank_transfer"}');
-- Insert dữ liệu cho bảng supplier


-- 
-- Thêm khóa chính
ALTER TABLE supplier ADD PRIMARY KEY (`mancc`);
ALTER TABLE entry_form ADD PRIMARY KEY (`maphieunhap`);
ALTER TABLE account ADD PRIMARY KEY (`maaccount`);
ALTER TABLE powergroup ADD PRIMARY KEY (`powergroupid`);
ALTER TABLE powergroup_func ADD PRIMARY KEY (`powergroupid`, `funcid`);
ALTER TABLE func ADD PRIMARY KEY (`funcid`);
ALTER TABLE detail_entry_form ADD PRIMARY KEY (`maphieunhap`, `masp`);
ALTER TABLE product ADD PRIMARY KEY (`masp`);
ALTER TABLE producttype ADD PRIMARY KEY (`maloaisp`);
ALTER TABLE customer ADD PRIMARY KEY (`macustomer`);
ALTER TABLE bill ADD PRIMARY KEY (`mabill`);
ALTER TABLE payby ADD PRIMARY KEY (`mapayby`);
ALTER TABLE bill_product ADD PRIMARY KEY (`mabill`, `masp`);
ALTER TABLE staff ADD PRIMARY KEY (`mastaff`);

-- Thêm khóa ngoại
ALTER TABLE entry_form ADD CONSTRAINT fk_entry_form_mancc FOREIGN KEY (`mancc`) REFERENCES supplier(`mancc`) ON DELETE CASCADE;
ALTER TABLE entry_form ADD CONSTRAINT fk_entry_form_maaccount FOREIGN KEY (`maaccount`) REFERENCES account(`maaccount`) ON DELETE CASCADE;

ALTER TABLE account ADD CONSTRAINT fk_account_powergroup FOREIGN KEY (`powergroupid`) REFERENCES powergroup(`powergroupid`) ON DELETE CASCADE;

ALTER TABLE powergroup_func ADD CONSTRAINT fk_powergroup_func_powergroup FOREIGN KEY (`powergroupid`) REFERENCES powergroup(`powergroupid`) ON DELETE CASCADE;
ALTER TABLE powergroup_func ADD CONSTRAINT fk_powergroup_func_func FOREIGN KEY (`funcid`) REFERENCES func(`funcid`) ON DELETE CASCADE;

ALTER TABLE detail_entry_form ADD CONSTRAINT fk_detail_entry_form_maphieunhap FOREIGN KEY (`maphieunhap`) REFERENCES entry_form(`maphieunhap`) ON DELETE CASCADE;
ALTER TABLE detail_entry_form ADD CONSTRAINT fk_detail_entry_form_masp FOREIGN KEY (`masp`) REFERENCES product(`masp`) ON DELETE CASCADE;

ALTER TABLE product ADD CONSTRAINT fk_product_maloaisp FOREIGN KEY (`maloaisp`) REFERENCES producttype(`maloaisp`) ON DELETE CASCADE;
ALTER TABLE product ADD CONSTRAINT fk_product_mancc FOREIGN KEY (`mancc`) REFERENCES supplier(`mancc`) ON DELETE CASCADE;

ALTER TABLE comment ADD CONSTRAINT fk_comment_masp FOREIGN KEY (`masp`) REFERENCES product(`masp`) ON DELETE CASCADE;
ALTER TABLE comment ADD CONSTRAINT fk_comment_mauser FOREIGN KEY (`mauser`) REFERENCES customer(`macustomer`) ON DELETE CASCADE;

ALTER TABLE bill ADD CONSTRAINT fk_bill_macustomer FOREIGN KEY (`macustomer`) REFERENCES customer(`macustomer`) ON DELETE CASCADE;
ALTER TABLE bill ADD CONSTRAINT fk_bill_mapayby FOREIGN KEY (`mapayby`) REFERENCES payby(`mapayby`) ON DELETE CASCADE;

ALTER TABLE bill_product ADD CONSTRAINT fk_bill_product_mabill FOREIGN KEY (`mabill`) REFERENCES bill(`mabill`) ON DELETE CASCADE;
ALTER TABLE bill_product ADD CONSTRAINT fk_bill_product_masp FOREIGN KEY (`masp`) REFERENCES product(`masp`) ON DELETE CASCADE;

ALTER TABLE `remove_product` ADD CONSTRAINT fk_remove_product_masp FOREIGN KEY (`masp`) REFERENCES product(`masp`) ON DELETE CASCADE;

ALTER TABLE account ADD CONSTRAINT fk_account_mastaff FOREIGN KEY (`mastaff`) REFERENCES staff(`mastaff`) ON DELETE CASCADE;
-- Trigger

-- DELIMITER $$

-- CREATE TRIGGER before_insert_customer
-- BEFORE INSERT ON customer
-- FOR EACH ROW
-- BEGIN
--     DECLARE new_id INT;
--     SELECT COALESCE(MAX(CAST(SUBSTRING(macustomer,4) AS UNSIGNED)),0) + 1 INTO new_id FROM customer;
--     SET NEW.macustomer = CONCAT('CUS', LPAD(CAST(new_id AS CHAR),3,'0')); 
-- END$$

-- CREATE TRIGGER before_insert_product
-- BEFORE INSERT ON product
-- FOR EACH ROW
-- BEGIN
--     DECLARE new_id INT;
--     SELECT COALESCE(MAX(CAST(SUBSTRING(masp,4) AS UNSIGNED)),0) + 1 INTO new_id FROM product;
--     SET NEW.masp = CONCAT('PRO', LPAD(CAST(new_id AS CHAR),3,'0')); 
-- END$$

-- CREATE TRIGGER before_insert_producttype
-- BEFORE INSERT ON producttype
-- FOR EACH ROW
-- BEGIN
--     DECLARE new_id INT;
--     SELECT COALESCE(MAX(CAST(SUBSTRING(maloaisp,4) AS UNSIGNED)),0) + 1 INTO new_id FROM producttype;
--     SET NEW.maloaisp = CONCAT('TYP', LPAD(CAST(new_id AS CHAR),3,'0')); 
-- END$$

-- CREATE TRIGGER before_insert_supplier
-- BEFORE INSERT ON supplier
-- FOR EACH ROW
-- BEGIN
--     DECLARE new_id INT;
--     SELECT COALESCE(MAX(CAST(SUBSTRING(mancc,4) AS UNSIGNED)),0) + 1 INTO new_id FROM supplier;
--     SET NEW.mancc = CONCAT('SUP', LPAD(CAST(new_id AS CHAR),3,'0')); 
-- END$$

-- DELIMITER ;
