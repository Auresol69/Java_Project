
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
    `status` TINYINT(1) DEFAULT 1
);

DROP TABLE IF EXISTS powergroup;
CREATE Table if not exists powergroup(
    `powergroupid` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `powergroupname` varchar(50) NOT NULL,
    `status` TINYINT(1) DEFAULT 1,
    `created_time` timestamp NOT NULL DEFAULT current_timestamp(),
    `last_updated` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
);  

DROP TABLE IF EXISTS permission;
CREATE Table if not exists permission(
    `permissionid` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `permissionname` varchar(50) NOT NULL
);  

DROP TABLE IF EXISTS func;
CREATE Table if not exists func(
    `funcid` INT(20) AUTO_INCREMENT PRIMARY KEY,
    `funcname` varchar(50) NOT NULL
);

DROP TABLE IF EXISTS `powergroup_func_permission`;
CREATE Table if not exists `powergroup_func_permission`(
    `powergroupid` INT(20) NOT NULL,
    `funcid` INT(20) NOT NULL,
    `permissionid` INT(20) NOT NULL
    );

DROP TABLE IF EXISTS detail_entry_form;
CREATE Table if NOT exists detail_entry_form(
    `maphieunhap` INT(20),
    `masp` INT(20) NOT NULL,
    `dongianhap` int(11) NOT NULL,
    `giaban` INT(11) NOT NULL,
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

ALTER TABLE account ADD CONSTRAINT fk_account_powergroup FOREIGN KEY (`powergroupid`) REFERENCES powergroup(`powergroupid`) ON DELETE CASCADE;

ALTER TABLE powergroup_func_permission ADD CONSTRAINT fk_powergroup_func_permission_powergroup FOREIGN KEY (`powergroupid`) REFERENCES powergroup(`powergroupid`) ON DELETE CASCADE;
ALTER TABLE powergroup_func_permission ADD CONSTRAINT fk_powergroup_func_permission_func FOREIGN KEY (`funcid`) REFERENCES func(`funcid`) ON DELETE CASCADE;
ALTER TABLE powergroup_func_permission ADD CONSTRAINT fk_powergroup_func_permission_permission FOREIGN KEY (`permissionid`) REFERENCES permission(`permissionid`) ON DELETE CASCADE;

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

DELIMITER $$
CREATE TRIGGER `after_insert_detail_entry_form` AFTER INSERT ON `detail_entry_form` FOR EACH ROW BEGIN
    UPDATE product SET soluong = soluong + NEW.soluongnhap, dongiasanpham = NEW.giaban where masp = NEW.masp;
END
$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER `after_insert_bill_product` AFTER INSERT ON `bill_product` FOR EACH ROW BEGIN
    UPDATE product SET soluong = soluong - NEW.soluong WHERE masp = NEW.masp;
END
$$
DELIMITER ;

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
-- Bảng Supplier
INSERT INTO `supplier` (`tencc`, `diachi`, `dienthoai`, `sofax`) VALUES
('Công ty ABC', 'Hà Nội', '0123456789', '0241234567'),
('Snack XYZ', 'TP. Hồ Chí Minh', '0987654321', '0282345678'),
('Vinamilk', 'TP. Hồ Chí Minh', '0909090909', '0289090909'),
('Kinh Đô', 'Bình Dương', '0911223344', '0274223344'),
('Tân Hiệp Phát', 'Bình Dương', '0933112233', '0274112233');

-- Bảng Staff
INSERT INTO `staff` (`address`, `tennhanstaff`, `dienthoai`) VALUES
('hau giang', 'Roy', '1234567891');

-- Bảng Powergroup
INSERT INTO `powergroup` (`powergroupname`, `status`, `created_time`, `last_updated`) VALUES
('Admin', 1, '2025-04-15 06:35:21', '2025-04-15 06:35:21');

-- Bảng Account
-- Lưu ý: Bạn cần truy xuất đúng `mastaff` và `powergroupid` đang được tạo tự động ở trên
-- nên cần JOIN lại hoặc thêm bằng mã nếu không dùng AUTO_INCREMENT cho staff và powergroup
INSERT INTO `account` (`mastaff`, `username`, `password`, `powergroupid`, `email`, `status`) VALUES
(1, 'roy', '123', 1, 'A@gm.com', 1);  -- Giả sử STF001 và PGR001 là id tự tăng đầu tiên

-- Bảng ProductType
INSERT INTO `producttype` (`tenloaisp`) VALUES
('Nước giải khát'),
('Snack'),
('Mì ăn liền'),
('Sữa'),
('Bánh kẹo');

-- Bảng Product
-- Lưu ý: bạn phải xác định đúng `maloaisp` và `mancc` dựa vào ID đã sinh ra từ các bảng trên
INSERT INTO `product` (`tensp`, `soluong`, `dongiasanpham`, `maloaisp`, `mancc`, `img`) VALUES
('Nước ngọt Coca-Cola', 100, 8000, 1, 1, 'img/coca.png'),
('Snack khoai tây Lay'"'"'s', 150, 12000, 2, 2, 'img/snack.png'),
('Mì Hảo Hảo tôm chua cay', 200, 3500, 3, 1, 'img/haohao.png'),
('Sữa tươi Vinamilk 1L', 80, 28000, 4, 3, 'img/vinamilk.png'),
('Bánh Oreo socola', 90, 15000, 5, 4, 'img/oreo.png');
 --------------------------------------------------------------------
 INSERT INTO `supplier` (`tencc`, `diachi`, `dienthoai`, `sofax`) VALUES
('Suntory PepsiCo', 'Hà Nội', '0912345678', '0245678901'),
('Nestlé Việt Nam', 'TP. Hồ Chí Minh', '0988776655', '0281234567'),
('Unilever', 'Đà Nẵng', '0933334455', '0236456789'),
('Thái Bình Dương', 'Hải Phòng', '0988554433', '0225533366');
INSERT INTO `staff` (`address`, `tennhanstaff`, `dienthoai`) VALUES
('Hà Nội', 'John Doe', '0988888888'),
('TP. Hồ Chí Minh', 'Jane Smith', '0911222333'),
('Đà Nẵng', 'Nguyễn Văn A', '0944777888'),
('Bình Dương', 'Lê Thị B', '0933444555');
INSERT INTO `account` (`mastaff`, `username`, `password`, `powergroupid`, `status`) VALUES
(1, 'johndoe', 'password123', 1, 1),
(2, 'janesmith', 'abc123', 1, 1),
(3, 'nguyenva', 'mypassword', 1, 1),
(4, 'lethib', 'securepass', 1, 1);
INSERT INTO `producttype` (`tenloaisp`) VALUES
('Thực phẩm'),
('Đồ uống'),
('Sản phẩm gia dụng'),
('Mỹ phẩm'),
('Điện tử');
INSERT INTO `product` (`tensp`, `soluong`, `dongiasanpham`, `maloaisp`, `mancc`, `img`) VALUES
('Coca-Cola 500ml', 150, 10000, 2, 1, 'img/coca.png'),
('Pepsi 500ml', 200, 9500, 2, 2, 'img/pepsi.png'),
('Nestlé Milo', 120, 35000, 1, 3, 'img/milo.png'),
('Sữa bột Vinamilk', 80, 200000, 1, 3, 'img/vinamilk_sua.png'),
('Bình nước nóng', 50, 550000, 3, 4, 'img/binhnong.png');
INSERT INTO `comment` (`noidung`, `ngaydang`, `masp`, `mauser`) VALUES
('Sản phẩm rất tuyệt vời, nước uống thơm ngon!', '2025-04-15 08:00:00', 1, 1),
('Mì rất ngon, gia vị đậm đà!', '2025-04-15 09:00:00', 3, 2),
('Bánh kẹo này rất ngon và giòn!', '2025-04-15 10:00:00', 5, 3),
('Sữa Vinamilk tươi ngon, dễ uống!', '2025-04-15 11:00:00', 4, 4);
INSERT INTO `bill` (`macustomer`, `mapayby`, `ngaymua`, `tongtien`) VALUES
(1, 1, '2025-04-15 12:00:00', 45000),
(2, 2, '2025-04-15 12:30:00', 36000),
(3, 1, '2025-04-15 13:00:00', 280000),
(4, 3, '2025-04-15 13:30:00', 550000);
INSERT INTO `bill_product` (`mabill`, `masp`, `soluong`) VALUES
(1, 1, 3),
(2, 3, 2),
(3, 4, 5),
(4, 5, 2);
INSERT INTO `payby` (`paybyname`, `address`, `details`) VALUES
('Tiền mặt', 'Hà Nội', '{"chuyenkhoan": false, "tienmat": true}'),
('Chuyển khoản', 'TP. Hồ Chí Minh', '{"chuyenkhoan": true, "tienmat": false}'),
('Thẻ tín dụng', 'Đà Nẵng', '{"chuyenkhoan": true, "tienmat": false}');