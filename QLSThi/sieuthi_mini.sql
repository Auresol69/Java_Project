
DROP TABLE IF EXISTS supplier;

CREATE TABLE IF NOT EXISTS supplier(
    `mancc` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `tencc` varchar(25) NOT NULL,
    `diachi` varchar(100) NOT NULL,
    `dienthoai` VARCHAR(11) NOT NULL,
     `sofax` VARCHAR(11) not null
);

DROP TABLE IF EXISTS entry_form;
CREATE Table IF NOT EXISTS entry_form(
    `maphieunhap` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `ngaynhap` DATETIME(6),
    `mancc` INT(20) NOT NULL,
    `maaccount` INT(20) NOT NULL
);

DROP TABLE IF EXISTS staff;
CREATE TABLE IF NOT EXISTS staff(
    `mastaff` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `address` varchar(100),
    `tennhanstaff` varchar(50) NOT NULL,
    `dienthoai` VARCHAR(11) NOT NULL
);

DROP TABLE IF EXISTS account;
CREATE Table if not exists account(
    `maaccount` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `mastaff` INT(20) NOT NULL,
    `username` varchar(50) NOT NULL,
    `password` varchar(255) NOT NULL,
    `powergroupid` INT(20),
    `email` VARCHAR(255)
);

DROP TABLE IF EXISTS powergroup;
CREATE Table if not exists powergroup(
    `powergroupid` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `powergroupname` varchar(50) NOT NULL,
    `statsus` TINYINT(1) DEFAULT 1,
    `created_time` timestamp NOT NULL DEFAULT current_timestamp(),
    `last_updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
);  


DROP TABLE IF EXISTS powergroup_func;
CREATE Table if not exists powergroup_func(
    `powergroupid` INT(20) NOT NULL,
    `funcid` INT(20) NOT NULL
    );

DROP TABLE IF EXISTS func;
CREATE Table if not exists func(
    `funcid` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `funcname` varchar(50) NOT NULL
);

DROP TABLE IF EXISTS detail_entry_form;
CREATE Table if NOT exists detail_entry_form(
    `maphieunhap` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `masp` INT(20) NOT NULL,
    `dongianhap` int(11) NOT NULL,
    `ngayhethan` DATETIME(6) not NULL,
    `soluongnhap` int(11) NOT NULL
);

DROP TABLE IF EXISTS product;
CREATE Table IF NOT EXISTS product(
    `masp` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `tensp` VARCHAR(50) NOT NULL,
    `soluong` INT(11) NOT NULL,
    `dongiasanpham` int(11) DEFAULT NULL,
    `maloaisp` INT(20) NOT NULL,
    `mancc` INT(20) NOT NULL,
    `img` VARCHAR(100) NOT NULL DEFAULT "img/Blank.png"
);

-- Weak Entity

DROP TABLE IF EXISTS `remove_product`;
CREATE TABLE IF NOT EXISTS `remove_product` (
    `masp` INT(20) NOT NULL,
    `soluong` int(11) NOT NULL,
    `ngayxoa` DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    `lido` VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS comment;
CREATE Table if not exists comment(
    `macomment` INT(11) AUTO_INCREMENT PRIMARY KEY,
    `noidung` TEXT NOT NULL,
    `ngaydang` DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    `masp` INT(20) NOT NULL,
    `mauser` INT(20) NOT NULL
);


DROP TABLE IF EXISTS producttype;
CREATE TABLE if not exists producttype(
    `maloaisp` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `tenloaisp` varchar(50) NOT NULL
);

DROP TABLE IF EXISTS customer;
CREATE TABLE IF NOT exists customer(
    `macustomer` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `address` VARCHAR(100),
    `phone` VARCHAR(11),
    `name` VARCHAR(25)
);

DROP TABLE IF EXISTS bill_product;
CREATE TABLE IF NOT EXISTS bill_product(
    `mabill` INT(20) NOT NULL,
    `masp` INT(20) NOT NULL,
    `soluong` int(11) NOT NULL
    );

DROP TABLE IF EXISTS bill;
create TABLE IF NOT EXISTS bill(
    `mabill` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `macustomer` INT(20) NOT NULL,
    `mapayby` INT(20) NOT NULL,
    `ngaymua` DATETIME(6) DEFAULT CURRENT_TIMESTAMP,
    `tongtien` int(11) DEFAULT 0
);

DROP TABLE IF EXISTS payby;
CREATE Table IF NOT EXISTS payby(
    `mapayby` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `paybyname` varchar(50) NOT NULL,
    `address` varchar(100) NOT NULL,
    `details` JSON NOT NULL
);

-- Thêm khóa chính
-- ALTER TABLE supplier ADD PRIMARY KEY (`mancc`);
-- ALTER TABLE entry_form ADD PRIMARY KEY (`maphieunhap`);
-- ALTER TABLE account ADD PRIMARY KEY (`maaccount`);
-- ALTER TABLE powergroup ADD PRIMARY KEY (`powergroupid`);
-- ALTER TABLE powergroup_func ADD PRIMARY KEY (`powergroupid`, `funcid`);
-- ALTER TABLE func ADD PRIMARY KEY (`funcid`);
-- ALTER TABLE detail_entry_form ADD PRIMARY KEY (`maphieunhap`, `masp`);
-- ALTER TABLE product ADD PRIMARY KEY (`masp`);
-- ALTER TABLE producttype ADD PRIMARY KEY (`maloaisp`);
-- ALTER TABLE customer ADD PRIMARY KEY (`macustomer`);
-- ALTER TABLE bill ADD PRIMARY KEY (`mabill`);
-- ALTER TABLE payby ADD PRIMARY KEY (`mapayby`);
-- ALTER TABLE bill_product ADD PRIMARY KEY (`mabill`, `masp`);
-- ALTER TABLE staff ADD PRIMARY KEY (`mastaff`);

-- Thêm khóa ngoại
ALTER TABLE entry_form ADD CONSTRAINT fk_entry_form_mancc FOREIGN KEY (`mancc`) REFERENCES supplier(`mancc`) ON DELETE CASCADE;
ALTER TABLE entry_form ADD CONSTRAINT fk_entry_form_maaccount FOREIGN KEY (`maaccount`) REFERENCES account(`maaccount`) ON DELETE CASCADE;

ALTER TABLE account ADD CONSTRAINT fk_account_powergroup FOREIGN KEY (`powergroupid`) REFERENCES powergroup(`powergroupid`) ON DELETE SET NULL;

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

-- CREATE TRIGGER after_insert_detail_entry_form
-- AFTER INSERT ON detail_entry_form
-- FOR EACH ROW
-- BEGIN
--     UPDATE product SET soluong = soluong + NEW.soluongnhap, dongiasanpham = NEW.dongianhap where masp = NEW.masp;
-- END$$

-- CREATE TRIGGER after_insert_bill_product
-- AFTER INSERT ON bill_product
-- FOR EACH ROW
-- BEGIN
--     UPDATE product SET soluong = soluong - NEW.soluong WHERE masp = NEW.masp;
-- END$$

-- DELIMITER ;
